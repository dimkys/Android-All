package com.example.dimkysik.multi2.AVP;

/**
 * Created by DimkySik on 07.10.2016.
 */
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.view.Gravity;
import android.view.SurfaceView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.app.Activity;

import com.example.dimkysik.multi2.R;

public class CameraActivity extends Activity //implements OnPreparedListener, OnCheckedChangeListener
{
        MediaPlayer mediaPlayer;
        CheckBox chbLoop;

@Override

protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camers);
      //  chbLoop = (CheckBox) ﬁndViewById(R.id.chb_Loop);
        chbLoop.setOnCheckedChangeListener(new OnCheckedChangeListener () {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mediaPlayer != null)
                    mediaPlayer.setLooping(isChecked);
            }
        });
        }

/*


public void onClickStart(View view) {
        releaseMP();

        String DATA=((EditText)ﬁndViewById(R.id.et_MediaPath)).getText().toString();
        try {
        mediaPlayer : new MediaPlayer();
        mediaPlayer.setDataSource(DATA);
        mediaPlayer.setDisplay(((SurfaceView) ﬁndVrewById(R.id.surfaceView1)).getHolder());
        mediaPlayer.setAudioStreamType(AudioManager. STREAM_MUSIC);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.prepareAsync();
        } catch (Exception e) {
        showMessage("Ошибка воспроизвеения");
        }
        if (mediaPlayer == null)
        return;

        mediaPlayer.setLooping(chbLoop.isChecked());
        mediaPlayer.setOnCompletionListener(this);

        }

private void showMessage(String text) {

    Toast toast = Toast.makeText(getApplicationContext(), text,
            Toast.LENGTH_SHORT);

    toast.setGravity(Gravity.CENTER, 0, 0);

    toast.show();
}
    private void releaseMP() {
        if (mediaPlayer !=null) {
            try {
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onClick(View view) {
        if (mediaPlayer== null)
        return;
        switch (view.getId()) {
            case R.id.biPause:
                if (mediaPlayer.isPlaying())
                    mediaPlayer.pause();
                break;
            case R.id.b_Resume:
                if (!mediaPlayer.isPlaying())
                    mediaPlayer.start();
                break;
            case R.id.b_Stop:
                mediaPlayer.stop();
                break;
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }


    public void onCompletion(MediaPlayer mp) {}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMP();

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }*/
}