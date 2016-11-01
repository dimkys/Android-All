package com.example.dimkysik.multi2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;




/**
 * Created by DimkySik on 29.09.2016.
 */

public class Seting extends Activity{
    RadioButton red,green,blue,white;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seting);

        red = (RadioButton) findViewById(R.id.radioButtonRed);
        green = (RadioButton) findViewById(R.id.radioButtonGreen);
        blue = (RadioButton) findViewById(R.id.radioButtonBlue);
        white = (RadioButton) findViewById(R.id.radioButtonWhite);
        Intent intent = getIntent();
        getWindow().setBackgroundDrawableResource(new Integer(intent.getStringExtra("col")));
    }

        public void onClick(View view)
        {
            Intent intent=new Intent();
            switch(view.getId())
            {
                case R.id.radioButtonRed:intent.putExtra("colorStr","Red");
                    break;
                case    R.id.radioButtonGreen:intent.putExtra("colorStr","Green");
                    break;
                case    R.id.radioButtonBlue:intent.putExtra("colorStr","Blue");
                    break;
                case    R.id.radioButtonWhite:intent.putExtra("colorStr","White");
                    break;
            }
            setResult(RESULT_OK,intent);
            finish();
        }



}
