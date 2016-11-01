package com.example.dimkysik.multi2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceView;

import com.example.dimkysik.multi2.gif.decoder.GifRun;

import java.util.Timer;

import static java.lang.Thread.sleep;

/**
 * Created by DimkySik on 29.09.2016.
 */

public class Exit extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           setContentView(R.layout.exit);
        Intent intent = getIntent();
        getWindow().setBackgroundDrawableResource(new Integer(intent.getStringExtra("col")));
        SurfaceView sV=(SurfaceView)findViewById(R.id.surfaceView);
        GifRun w = new  GifRun();
        w.LoadGiff(sV, this, R.drawable.punch);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    sleep(2000);		//Приостанавливает поток на 1 секунду
                }catch(InterruptedException e){}
                finish();
            }
        });
        t.start();
        //finish();

    }


}
