package com.example.lauren.drawboarddemo170107;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {


    private ImageView iV_board;
    private Bitmap board_bg_bitmap;
    private Bitmap board_bg_bitmap_copy;
    private Canvas mCanvas;
    private Paint mPaint;
    private Context mContext;
    private  int idnex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        iV_board = (ImageView) findViewById(R.id.iView_board);

        //图片
        board_bg_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.bg);

        //赋值原图
        board_bg_bitmap_copy = Bitmap.createBitmap(board_bg_bitmap.getWidth(),board_bg_bitmap.getHeight(),board_bg_bitmap.getConfig());
        mCanvas = new Canvas(board_bg_bitmap_copy); //以 copy 为基础创建一块画布
        mPaint = new Paint();//创建画笔
        mCanvas.drawBitmap(board_bg_bitmap,new Matrix(),mPaint); //开始画
        mCanvas.drawLine(20,30,50,60,mPaint);

        //把 copy显示到 iV_board上
        iV_board.setImageBitmap(board_bg_bitmap_copy);


        iV_board.setOnTouchListener(new View.OnTouchListener() {
            int startX = 0;
            int startY = 0;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();

                switch (action){
                    case MotionEvent.ACTION_DOWN: //按下
                        startX = (int) motionEvent.getX();
                        startY = (int) motionEvent.getY();
                        Log.e("ACTION_DOWN","按下方法,x"+startX+"y"+startY);
                        break;
                    case MotionEvent.ACTION_MOVE:

                        int stopX = (int) motionEvent.getX();
                        int stopY = (int) motionEvent.getY();
                        Log.e("ACTION_MOVE","移动,x"+stopX+"y"+stopY);

                        //画线
                        mCanvas.drawLine(startX,startY,stopX,stopY,mPaint);

                        startX = stopX;
                        startY = stopY;

                        iV_board.setImageBitmap(board_bg_bitmap_copy);

                        break;
                    case MotionEvent.ACTION_UP: //抬起
                        break;
                    default:
                        break;
                }

                return true; //一定要改成 true
            }
        });

    }




    public void Onclick1(View v){
        mPaint.setStrokeWidth(10.f);

    }
    public void Onclick2(View v){
        mPaint.setColor(Color.RED);
    }

    public void Onclick3(View v){
        idnex++;

        try {
            final File file = new File(Environment.getExternalStorageDirectory().getPath(),"saveImage3"+idnex+".png");
            FileOutputStream fos = new FileOutputStream(file);
            board_bg_bitmap_copy.compress(Bitmap.CompressFormat.PNG, 100, fos);



            refreshDCIM(file);
//            refreshDCIM(file.getPath());

            fos.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public  void  refreshDCIM(final File file){

               final Uri data = Uri.parse("file://" + file.getPath()); //必须加file://
//                final Uri data = Uri.parse(file.getPath());
                Log.e("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",""+data.getPath());

                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                mediaScanIntent.setData(data);
                mContext.sendBroadcast(mediaScanIntent);

    }

    /**
     * 扫描指定文件夹的文件，解决保存图谱按后，系统不能即使更新相册的问题
     *
     * @param
     */
    public void refreshDCIM(String _file) {

        final Uri data = Uri.parse("file://" + _file);
        Log.e("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx2",""+data.getPath());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                scanIntent.setData(data);
                mContext.sendBroadcast(scanIntent);
            }
        });
    }

}
