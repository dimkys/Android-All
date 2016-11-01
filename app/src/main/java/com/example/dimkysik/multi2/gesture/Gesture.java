package com.example.dimkysik.multi2.gesture;

import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dimkysik.multi2.R;

import java.util.ArrayList;
import java.util.Random;

public class Gesture extends AppCompatActivity implements OnGesturePerformedListener{

    GestureLibrary gLib;
    GestureOverlayView gestureOverlayView;
    TextView textViewGes;
    Random random=new Random();
    int Number;
    String str="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Number=rand();
        textViewGes=(TextView)findViewById(R.id.textView2);
        setContentView(R.layout.gesture);
        gLib= GestureLibraries.fromRawResource(this,R.raw.gesture);
        if(!gLib.load()){
            finish();
        }
        gestureOverlayView=(GestureOverlayView)findViewById(R.id.gestureOverlayViev);
                gestureOverlayView.addOnGesturePerformedListener(this);
    }

    public void onMyButtonClick(View view)
    {
        Prov();
    }
    int rand()
    {
        return random.nextInt(100);
    }
    public void Prov(){
        textViewGes=(TextView)findViewById(R.id.textView2);
        try {
            if(!textViewGes.getText().toString().equals("")) {
            int i=0;

             i= Integer.parseInt(textViewGes.getText().toString());

            if(!(i>0&&i<=100)){
                Toast.makeText(this, "Что за фигня чувак?", Toast.LENGTH_SHORT).show();
                return;
            }
            if (i == Number) {
                Toast.makeText(this, "Угадаль чертяга", Toast.LENGTH_SHORT).show();
                Number = rand();
                Toast.makeText(this, "Играем", Toast.LENGTH_SHORT).show();
                str="";
                return;
            }
            if (i > Number) {
                Toast.makeText(this, "Многовато будет", Toast.LENGTH_SHORT).show();
                return;
            }
            if (i < Number) {
                Toast.makeText(this, "Выше нос", Toast.LENGTH_SHORT).show();
                return;
            }

        }else{
            Toast.makeText(this, "Пшол вон холоп", Toast.LENGTH_SHORT).show();
        }
        }catch (Exception e){
    }
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, android.gesture.Gesture gesture) {
        ArrayList<Prediction> predictions=gLib.recognize(gesture);
        if(predictions.size()>0){
            Prediction prediction=predictions.get(0);
            if(prediction.score>1.0){
              try {
                  switch (prediction.name) {
                      case "s": {
                          Prov();
                          str="";
                          break;
                      }
                      default:{
                         str+=prediction.name;
                          break;
                      }
                  }
                 textViewGes.setText(str);
              }catch (Exception e){
                  //showMessage(e.getMessage()+"Ошибка чет2");
              }
            }
        }
    }
    private void showMessage(String text) {

        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
