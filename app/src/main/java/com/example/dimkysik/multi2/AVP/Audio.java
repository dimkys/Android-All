package com.example.dimkysik.multi2.AVP;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dimkysik.multi2.R;

import java.io.File;

/**
 * Created by DimkySik on 06.10.2016.
 */

public class Audio extends Activity implements OnPreparedListener, CompoundButton.OnCheckedChangeListener,OnCompletionListener {

    MediaPlayer mediaPlayer;
    CheckBox chbLoop;
    Spinner spinner;
    String[] list;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio);

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, loadListAudio());
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner = (Spinner) findViewById(R.id.spinnerAudio);
            spinner.setAdapter(adapter);
            spinner.setPrompt("Музычка");
            spinner.setSelection(0);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                }

                public void onNothingSelected(AdapterView<?> arg0) {
                }
            });

        chbLoop = (CheckBox) findViewById(R.id.checkBoxAudio);
        chbLoop.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                                               @Override
                                               public void onCheckedChanged(CompoundButton buttonView,
                                                                            boolean isChecked) {
                                                   if (mediaPlayer != null)
                                                       mediaPlayer.setLooping(isChecked);
                                               }
                                           }
        );
    }
    public String[] loadListAudio(){
        File rootFolder = new File("/sdcard/download/Sleeping bear");
        String s="";
        int i=0;
        File[] filesArray = rootFolder.listFiles();
        list=new String[filesArray.length];
       for (File f : filesArray){
           list[i]=f.getPath();
            i++;
        }
        return list;
    }

    public void onClickStart(View view) {
        try {
            releaseMP();
        String DATA;
        DATA=spinner.getSelectedItem().toString();


            mediaPlayer=new MediaPlayer();
            mediaPlayer.setDataSource(DATA);
            mediaPlayer.setDisplay(((SurfaceView) findViewById(R.id.surfaceViewVideo)).getHolder());
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.prepareAsync();

        } catch (Exception e) {
            showMessage("OmHEIKa BocnpOHsBegeHHn:"+e.getMessage());

        }

        if (mediaPlayer == null)
            return;

        try {
        mediaPlayer.setLooping(chbLoop.isChecked());
        mediaPlayer.setOnCompletionListener(this);
        } catch (Exception e) {
            showMessage("OmHEIKa BocnpOHsBegeHHn:"+e.getMessage());

        }
    }

    private void showMessage(String text) {

        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private void releaseMP() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onClick(View view) {
        if (mediaPlayer == null)
            return;
        switch (view.getId()) {
            case R.id.buttonPause:
                if (mediaPlayer.isPlaying())
                   // showMessage("Клацнул:");
                    mediaPlayer.pause();
                break;
            case R.id.buttonStart:
                if (!mediaPlayer.isPlaying())
                    //showMessage("Клацнул:");
                    mediaPlayer.start();
                break;
            case R.id.buttonStop:
                //showMessage("Клацнул:");
                    mediaPlayer.stop();
                break;
            default:showMessage("Клацнул:"+view.getId());
                break;
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMP();

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }
}