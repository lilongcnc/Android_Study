package com.example.lauren.tearclothesdemo170108;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iView_above;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iView_above = (ImageView) findViewById(R.id.iView_above);


        Bitmap srcBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.pre19);


        final Bitmap aboveBitmap = Bitmap.createBitmap(srcBitmap.getWidth(),srcBitmap.getHeight(),srcBitmap.getConfig());
        Canvas canvas = new Canvas(aboveBitmap);
        Paint paint = new Paint();
        canvas.drawBitmap(srcBitmap,new Matrix(),paint);

        iView_above.setImageBitmap(aboveBitmap);


        iView_above.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch (action){
                    case MotionEvent.ACTION_MOVE:
//                        Log.e("ACTION_MOVE","ACTION_MOVE");

                        //假设半径为7
                        int radius = 10;
                        for (int i= -radius; i< radius; i++){
                            for (int j= -radius; j < radius; j++){
                                if (Math.sqrt(i*i+j*j)<7){
                                    Log.e("xxxxx","x"+(int)motionEvent.getX()+"y"+(int)motionEvent.getX()+j);
                                    aboveBitmap.setPixel((int)motionEvent.getX()+i,(int)motionEvent.getY()+j, Color.TRANSPARENT);
                                }
                            }
                        }


                        //更新UI
                        iView_above.setImageBitmap(aboveBitmap);

                        break;
                    default:
                        break;
                }
                return true;
            }
        });


    }
}
