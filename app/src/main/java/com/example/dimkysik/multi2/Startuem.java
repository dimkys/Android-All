package com.example.dimkysik.multi2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.dimkysik.multi2.AVP.Audio;
import com.example.dimkysik.multi2.AVP.Camers;
import com.example.dimkysik.multi2.AVP.Picture;
import com.example.dimkysik.multi2.AVP.Video;
import com.example.dimkysik.multi2.gesture.Gesture;

/**
 * Created by DimkySik on 29.09.2016.
 */

public class Startuem extends AppCompatActivity implements View.OnClickListener,
        GestureDetector.OnGestureListener
{
    private TextView textViev;
    private String str;
    private Display display;
    private GestureDetectorCompat gestureDCompat;//=new GestureDetectorCompat();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startuem);
        Intent intent = getIntent();
        gestureDCompat = new GestureDetectorCompat(this, this);
       // gestureDCompat.setOnDoubleTapListener(this);
        textViev = (TextView) findViewById(R.id.TextViewGestue);
        getWindow().setBackgroundDrawableResource(new Integer(intent.getStringExtra("col")));
        display=getWindowManager().getDefaultDisplay();
    }


    public void onClick(View view) {
       // textViev.setText("Кнопа");
        final CharSequence[] items = {"Ехал(Аудио)", "Зомби", "Через(GPS)", "Зомби",
                "Видит(Картины)", "Зомби", "В Зомби(Жесты пользователя)", "Зомби",
                "Сунул(Камера)", "Зомби", "В Зомби", "Зомби",
                "Зомби", "Зомби", "Зомби", "Зомби"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Просто кнопки");

        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Intent intent;
                switch(which){
                    case 0:{
                       Intent intent =new Intent(Startuem.this,Audio.class);
                        intent.putExtra("col",R.color.White+"");
                        startActivity(intent);
                    break;}
                    case 2:{
                        Intent  intent=new Intent(Startuem.this,Video.class);
                        intent.putExtra("col",R.color.White+"");
                        startActivity(intent);
                    break;}
                    case 4:{
                        Intent intent=new Intent(Startuem.this,Picture.class);
                        intent.putExtra("col",R.color.White+"");
                        startActivity(intent);
                    break;}
                    case 6:{
                        Intent intent=new Intent(Startuem.this,Gesture.class);
                        intent.putExtra("col",R.color.White+"");
                        startActivity(intent);
                        break;}
                    case 8:{
                        Intent intent=new Intent(Startuem.this,Camers.class);
                        intent.putExtra("col",R.color.White+"");
                        startActivity(intent);
                        break;}
                    default: textViev.setText(which+"");
                        break;
                }
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
/*
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        str = "onSingleTapConfirmed:\n";
        textViev.setText("");
        for (String s : e.toString().split(",")
                ) {
            str += s + "\n";
        }
        textViev.setText(str);
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        str = "onSingleTapConfirmed:\n";
        textViev.setText("");
        for (String s : e.toString().split(",")
                ) {
            str += s + "\n";
        }
        textViev.setText(str);
        //textViev.setText("onSingleTapConfirmed:"+e.toString().split(","));
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        str = "onDoubleTapEvent:\n";
        textViev.setText("");
        for (String s : e.toString().split(",")
                ) {
            str += s + "\n";
        }
        textViev.setText(str);// textViev.setText("onDoubleTapEvent:"+e.toString());
        return true;
    }
*/
    @Override
    public boolean onDown(MotionEvent e) {
        str = "onDown:\n";
        textViev.setText("");
        for (String s : e.toString().split(",")
                ) {
            str += s + "\n";
        }
        textViev.setText(str);// textViev.setText("onDown:"+e.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        str = "onShowPress:\n";
        textViev.setText("");
        for (String s : e.toString().split(",")
                ) {
            str += s + "\n";
        }
        textViev.setText(str);//textViev.setText("onShowPress:"+e.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        str = "onSingleTapUp:\n";
        textViev.setText("");
        for (String s : e.toString().split(",")
                ) {
            str += s + "\n";
        }
        textViev.setText(str); //textViev.setText("onSingleTapUp:"+e.toString());
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        str = "onScroll:\n";
        textViev.setText("");
        for (String s : e1.toString().split(",")
                ) {
            str += s + "\n";
        }
        textViev.setText(str);// textViev.setText("onScroll:"+e1.toString());
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

        str = "onLongPress:\n";
        textViev.setText("");
        for (String s : e.toString().split(",")
                ) {
            str += s + "\n";
        }
        textViev.setText(str);//textViev.setText("onLongPress:"+e.toString());
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        str = "onFling:\n";
        textViev.setText("");
        for (String s : e1.toString().split(",")
                ) {
            str += s + "\n";
        }
        for (String s : e2.toString().split(",")
                ) {
            str += s + "\n";
        }
        str += velocityX + "\n";
        str += velocityY + "\n";
        textViev.setText(str);
        if (e2.getEventTime() - e1.getEventTime() < 500&&(Math.abs(e1.getX() - e2.getX()) > display.getWidth()/2|| Math.abs(e1.getY() - e2.getY()) >  display.getHeight()/3)) {
            if (Math.abs(e1.getX() - e2.getX()) > Math.abs(e1.getY() - e2.getY())) {
                if (e1.getX() < e2.getX() ) {
                    getWindow().setBackgroundDrawableResource(R.color.Blue);
                } else {
                    getWindow().setBackgroundDrawableResource(R.color.Green);
                }
            } else {
                if (e1.getY() < e2.getY() ) {
                    getWindow().setBackgroundDrawableResource(R.color.Red);
                } else {
                    getWindow().setBackgroundDrawableResource(R.color.Orange);
                }
            }
        }return true;
    }

    public boolean onTouchEvent(MotionEvent e){
        this.gestureDCompat.onTouchEvent(e);
        return super.onTouchEvent(e);
    }

}
