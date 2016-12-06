package com.lauren.saveandgetxml20161206;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lauren.utils.SmsUtils;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.amin_layout);
        mContext = this;


        Button btn_backup = (Button)findViewById(R.id.btn_backup);
        Button btn_restore = (Button)findViewById(R.id.btn_restore);

        btn_backup.setOnClickListener(this);
        btn_restore.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_backup:
                if (SmsUtils.backupSms(mContext)) {
                    Toast.makeText(mContext, "短信备份成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "短信备份失败", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_restore:

                int reaultNum = SmsUtils.restoreSms(mContext);
                Toast.makeText(mContext, "成功恢复" + reaultNum + "条数据", Toast.LENGTH_SHORT).show();

                break;
            default:
                break;
        }
    }
}
