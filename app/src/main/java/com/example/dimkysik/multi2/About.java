package com.example.dimkysik.multi2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


/**
 * Created by DimkySik on 29.09.2016.
 */

public class About extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           setContentView(R.layout.about);
        Intent intent = getIntent();
        getWindow().setBackgroundDrawableResource(new Integer(intent.getStringExtra("col")));
    }
}
