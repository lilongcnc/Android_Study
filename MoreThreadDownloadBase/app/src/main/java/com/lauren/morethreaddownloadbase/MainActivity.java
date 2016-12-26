package com.lauren.morethreaddownloadbase;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_threadCount;
    private Context mContext;
    private LinearLayout ll_progress_layout;

    private int threadCount = 0;//开启3个线程
    private int blockSize = 0;//每个线程下载的大小
    private int runningTrheadCount = 0;//当前运行的线程数
//    private String path = "http://www.lilongcnc.cc/lauren_picture/download.ipa";
private String path = "http://downloads-2.binaryage.com/TotalFinder-1.8.1.dmg";


    private Map<Integer, ProgressBar> map = new HashMap<Integer, ProgressBar>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        findViewById(R.id.btn_dowland).setOnClickListener(this);
        ll_progress_layout = (LinearLayout) findViewById(R.id.ll_progresslayout);

        et_threadCount = (EditText) findViewById(R.id.et_threadNumber);
    }

    @Override
    public void onClick(View v) {
        Log.e("按钮被点击","按钮被点击");

        //获取用户输入的线程数
        String threadCount_str = et_threadCount.getText().toString().trim();
        threadCount = Integer.parseInt(threadCount_str);

        //清空控件中的所有子控件
        ll_progress_layout.removeAllViews();

        //根据线程数添加相应数量的ProgressBar
        for (int i = 0; i < threadCount; i++) {

            ProgressBar progressbar = (ProgressBar) View.inflate(mContext, R.layout.progressbar_layout, null);
            map.put(i, progressbar);//将ProgressBar放到map中，方便在线程中获取并设置进度
            //child: 子控件
            ll_progress_layout.addView(progressbar);
        }

        new Thread(new Runnable() {

            @Override
            public void run() {
                download();
            }
        }).start();


    }

    private void download() {


        try {
            //1.请求url地址获取服务端资源的大小
            URL url = new URL(path);
            HttpURLConnection openConnection = (HttpURLConnection) url.openConnection();
            openConnection.setRequestMethod("GET");
            openConnection.setConnectTimeout(10 * 1000);

            int code = openConnection.getResponseCode();
            Log.e("download","download:"+code);

            if (code == 200) {
                //获取资源的大小
                int filelength = openConnection.getContentLength();
                Log.e("download","download:"+code);
                //2.在本地创建一个与服务端资源同样大小的一个文件（占位）
                RandomAccessFile randomAccessFile = new RandomAccessFile(new File(getFileName(path)), "rw");
                randomAccessFile.setLength(filelength);//设置随机访问文件的大小3
                //3.要分配每个线程下载文件的开始位置和结束位置。
                blockSize = filelength / threadCount;//计算出每个线程理论下载大小
                for (int threadId = 0; threadId < threadCount; threadId++) {
                    int startIndex = threadId * blockSize;//计算每个线程下载的开始位置
                    int endIndex = (threadId + 1) * blockSize - 1;//计算每个线程下载的结束位置
                    //如果是最后一个线程，结束位置需要单独计算
                    if (threadId == threadCount - 1) {
                        endIndex = filelength - 1;
                    }

                    //4.开启线程去执行下载
                    new DownloadThread(threadId, startIndex, endIndex).start();


                }


            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public class DownloadThread extends Thread {


        private int threadId;
        private int startIndex;
        private int endIndex;
        private int lastPostion;
        private int currentThreadTotalProgress;

        public DownloadThread(int threadId, int startIndex, int endIndex) {
            this.threadId = threadId;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.currentThreadTotalProgress = endIndex - startIndex + 1;
        }

        @Override
        public void run() {
            Log.e("222222", "run: 222");
            //获取当前线程对应ProgressBar
            ProgressBar progressBar = map.get(threadId);

            synchronized (DownloadThread.class) {

                runningTrheadCount = runningTrheadCount + 1;//开启一线程，线程数加1
            }

            //分段请求网络连接，分段保存文件到本地
            try {
                URL url = new URL(path);
                HttpURLConnection openConnection = (HttpURLConnection) url.openConnection();
                openConnection.setRequestMethod("GET");
                openConnection.setConnectTimeout(10 * 1000);


                System.out.println("理论上下载：  线程：" + threadId + "，开始位置：" + startIndex + ";结束位置:" + endIndex);


                //读取上次下载结束的位置,本次从这个位置开始直接下载。
//				File file2 = new File(getFilePath()+threadId+".txt");


                if (SharedUtils.getLastPosition(mContext, threadId) != -1) {
					/*			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
					String lastPostion_str = bufferedReader.readLine();
					lastPostion = Integer.parseInt(lastPostion_str);//读取文件获取上次下载的位置

					 */

                    lastPostion = SharedUtils.getLastPosition(mContext, threadId);

                    //说明该线程已经下载完成
                    if (lastPostion == endIndex + 1) {

                        progressBar.setProgress(currentThreadTotalProgress);
                        runningTrheadCount = runningTrheadCount - 1;

                    }

                    //设置分段下载的头信息。  Range:做分段数据请求用的。
                    openConnection.setRequestProperty("Range", "bytes:" + lastPostion + "-" + endIndex);//bytes:0-500:请求服务器资源中0-500之间的字节信息  501-1000:
                    System.out.println("实际下载：  线程：" + threadId + "，开始位置：" + lastPostion + ";结束位置:" + endIndex);
//					bufferedReader.close();
                } else {

                    lastPostion = startIndex;
                    //设置分段下载的头信息。  Range:做分段数据请求用的。
                    openConnection.setRequestProperty("Range", "bytes:" + lastPostion + "-" + endIndex);//bytes:0-500:请求服务器资源中0-500之间的字节信息  501-1000:
                    System.out.println("实际下载：  线程：" + threadId + "，开始位置：" + lastPostion + ";结束位置:" + endIndex);
                }

                if (openConnection.getResponseCode() == 206) {//200：请求全部资源成功， 206代表部分资源请求成功
                    InputStream inputStream = openConnection.getInputStream();
                    //请求成功将流写入本地文件中，已经创建的占位那个文件中

                    RandomAccessFile randomAccessFile = new RandomAccessFile(new File(getFileName(path)), "rw");
                    randomAccessFile.seek(lastPostion);//设置随机文件从哪个位置开始写。
                    //将流中的数据写入文件
                    byte[] buffer = new byte[1024 * 100];
                    int length = -1;
                    int total = 0;//记录本次线程下载的总大小

                    while ((length = inputStream.read(buffer)) != -1) {
                        randomAccessFile.write(buffer, 0, length);


                        total = total + length;
                        //去保存当前线程下载的位置，保存到文件中
                        int currentThreadPostion = lastPostion + total;//计算出当前线程本次下载的位置
						/*//创建随机文件保存当前线程下载的位置
						File file = new File(getFilePath()+threadId+".txt");
						RandomAccessFile accessfile = new RandomAccessFile(file, "rwd");
						accessfile.write(String.valueOf(currentThreadPostion).getBytes());
						accessfile.close();*/

                        SharedUtils.setLastPosition(mContext, threadId, currentThreadPostion);

                        //计算线程下载的进度并设置进度
                        int currentprogress = currentThreadPostion - startIndex;
                        progressBar.setMax(currentThreadTotalProgress);//设置进度条的最大值
                        progressBar.setProgress(currentprogress);//设置进度条当前进度


                    }
                    //关闭相关的流信息
                    inputStream.close();
                    randomAccessFile.close();

                    System.out.println("线程：" + threadId + "，下载完毕");

                    //当所有线程下载结束，删除存放下载位置的文件。
                    synchronized (DownloadThread.class) {
                        runningTrheadCount = runningTrheadCount - 1;//标志着一个线程下载结束。
                        if (runningTrheadCount == 0) {

                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    Toast.makeText(mContext, "下载完毕", Toast.LENGTH_SHORT).show();
                                }
                            });

                            System.out.println("所有线程下载完成");
                            for (int i = 0; i < threadCount; i++) {
                                File file = new File(getFilePath() + i + ".txt");
                                System.out.println(file.getAbsolutePath());
                                file.delete();
                            }
                        }

                    }


                }


            } catch (Exception e) {
                e.printStackTrace();
            }


            super.run();
        }

    }


    public String getFileName(String url) {

//        return Environment.getExternalStorageDirectory() + "/" + url.substring(url.lastIndexOf("/"));

        return getApplicationContext().getFilesDir().getAbsolutePath() + "/" + url.substring(url.lastIndexOf("/"));

    }

    public String getFilePath() {

//        return Environment.getExternalStorageDirectory() + "/";
        return getApplicationContext().getFilesDir().getAbsolutePath() + "/";

    }

}

