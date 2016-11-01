package com.example.dimkysik.multi2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import static java.lang.Thread.sleep;

public class ListActivity extends AppCompatActivity {

    public Button start,exit,about,setting;
    int idColor=R.color.White;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        start=(Button)findViewById(R.id.buttonStaruem);
        setting=(Button)findViewById(R.id.buttonSetting);
        about=(Button)findViewById(R.id.buttonAbout);
        exit=(Button)findViewById(R.id.buttonExit);
    }

    public void onClickButtom(View view)
    {
        switch(view.getId())
        {
            case R.id.buttonStaruem:
                Intent intent=new Intent(ListActivity.this,Startuem.class);
                intent.putExtra("col",idColor+"");
                startActivity(intent);
                break;
            case R.id.buttonSetting:
                Intent intent1=new Intent(ListActivity.this,Seting.class);
                intent1.putExtra("col",idColor+"");
                startActivityForResult(intent1,1);
                break;
            case R.id.buttonAbout:
                Intent intent2=new Intent(ListActivity.this,About.class);
                intent2.putExtra("col",idColor+"");
                startActivity(intent2);
                break;
            case R.id.buttonExit:
                Intent intent3=new Intent(ListActivity.this,Exit.class);
                intent3.putExtra("col",idColor+"");
                startActivity(intent3);
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
                break;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null)
        {
            return;
        }

        String colorStr=data.getStringExtra("colorStr");
        switch(colorStr)
        {
            case "Red":
                getWindow().setBackgroundDrawableResource(R.color.Red);
                idColor=R.color.Red;
                break;
            case "Green":
                getWindow().setBackgroundDrawableResource(R.color.Green);
                idColor=R.color.Green;
                break;
            case "Blue":
                getWindow().setBackgroundDrawableResource(R.color.Blue);
                idColor=R.color.Blue;
                break;
            case "White":
                getWindow().setBackgroundDrawableResource(R.color.White);
                idColor=R.color.White;
                break;
            default: break;
        }
    }
}
