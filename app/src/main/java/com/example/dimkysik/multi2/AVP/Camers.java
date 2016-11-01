package com.example.dimkysik.multi2.AVP;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.hardware.Camera.Size;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.dimkysik.multi2.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by DimkySik on 06.10.2016.
 */

public class Camers extends Activity {
    private Camera camera;
    private SurfaceHolder surfaceHolder;
    private SurfaceView surfaceView;
    private View shotBtm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camers);
        try {


            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            surfaceView = (SurfaceView) findViewById(R.id.surfaceViewCamera);
            surfaceHolder = surfaceView.getHolder();
            surfaceHolder.addCallback(new MyCallback(this));
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

            shotBtm = findViewById(R.id.imageButtonSnap);
            shotBtm.setOnClickListener(new MyViewListener());
            //Intent intent = getIntent();
        } catch (Exception e) {

        }
    }
   @Override
    protected void onResume() {
        super.onResume();
        camera = camera.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (camera != null) {
            camera.setPreviewCallback(null);
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

    public class MyCallback implements SurfaceHolder.Callback {
        Activity host;
        MyCallback(Activity act) {
            host = act;
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                camera.setPreviewDisplay(holder);
                camera.setPreviewCallback(new MyPreviewCallback());
            } catch (IOException e) {
                Log.d("myLogs", "Ошибка камеры");
                e.printStackTrace();
            }
            Size previewSize = camera.getParameters().getPreviewSize();
            float aspect = (float) previewSize.width/ previewSize.height;

            int previewSurfaceWidth = surfaceView.getWidth();
            int previewSurfaceHeight = surfaceView.getHeight();
            ViewGroup.LayoutParams lp = surfaceView.getLayoutParams();

            if (host.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
                camera.setDisplayOrientation(90);
                lp.height = previewSurfaceHeight;
               lp.width = (int) (previewSurfaceHeight / aspect);
            } else {
                camera.setDisplayOrientation(0);
                lp.width = previewSurfaceWidth;
                lp.height = (int) (previewSurfaceWidth / aspect);
            }
            surfaceView.setLayoutParams(lp);
            camera.startPreview();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }

    public class MyViewListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v == shotBtm) {
                camera.autoFocus(new MyAutoFocusCallback());
            }
        }
    }

    public class MyAutoFocusCallback implements Camera.AutoFocusCallback {
        @Override
        public void onAutoFocus(boolean paramBoolean, Camera paramCamera) {
            if (paramBoolean) {
                paramCamera.takePicture(null, null, null, new MyPictureCallback());
            }
        }
    }

    public class MyPictureCallback implements Camera.PictureCallback {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            try {
                File saveDir = new File("/sdcard/MyPhoto/");
                if (!saveDir.exists()) {
                    saveDir.mkdirs();
                }
                FileOutputStream os = new FileOutputStream(String.format("/sdcard/MyPhoto/%d.jpg", System.currentTimeMillis()));
                os.write(data);
                os.close();
            } catch (Exception e) {
                Log.d("logs", "err cam");
            }
            camera.startPreview();
        }
    }

    public class MyPreviewCallback implements Camera.PreviewCallback {
        @Override
        public void onPreviewFrame(byte[] data, Camera camera) {

        }
    }
}

/*
public class Camers extends AppCompatActivity {

    private Camera camera;
    private SurfaceHolder surfaceHolder;
    private SurfaceView surfaceView;
    private View btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camers);

        surfaceView = (SurfaceView) findViewById(R.id.surfaceViewCamera);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(new MyCallback(this));

        btn = (ImageButton) findViewById(R.id.imageButtonSnap);
        btn.setOnClickListener(new MyViewListener());
    }

    @Override
    protected void onResume() {
        super.onResume();
        camera = camera.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (camera != null) {
            camera.setPreviewCallback(null);
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

    class MyCallback implements SurfaceHolder.Callback {

        Activity host;

        MyCallback(Activity act) {
            host = act;
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                camera.setPreviewDisplay(holder);
                camera.setPreviewCallback(new MyPreviewCallback());
            } catch (IOException e) {
                Log.d("myLogs", "Camera Error");
                e.printStackTrace();
            }
            Camera.Size previewSize = camera.getParameters().getPreviewSize();
            float aspect = (float) previewSize.width / previewSize.height;

            int previewSurfaceWidth = surfaceView.getWidth();
            int previewSurfaceHeight = surfaceView.getHeight();

            ViewGroup.LayoutParams lp = surfaceView.getLayoutParams();

            // здесь корректируем размер отображаемого preview, чтобы не было искажений

            if (host.getResources().getConfiguration().orientation !=
                    Configuration.ORIENTATION_LANDSCAPE) {
                // портретный вид
                camera.setDisplayOrientation(90);
                lp.height = previewSurfaceHeight;
                //lp.width = (int) (previewSurfaceHeight / aspect);
            } else {
                // ландшафтный
                camera.setDisplayOrientation(0);
                lp.width = previewSurfaceWidth;
                //lp.height = (int) (previewSurfaceWidth / aspect);
            }
            surfaceView.setLayoutParams(lp);
            camera.startPreview();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }

    }

    class MyPreviewCallback implements Camera.PreviewCallback {

        @Override
        public void onPreviewFrame(byte[] paramArrayOfByte, Camera paramCamera) {
            // здесь можно обрабатывать изображение, показываемое в preview
        }
    }

    class MyViewListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v == btn) {
                // либо делаем снимок непосредственно здесь
                // либо включаем обработчик автофокуса

                //camera.takePicture(null, null, null, this);
                camera.autoFocus(new MyAutoFocusCallback());
            }
        }
    }

    class MyAutoFocusCallback implements Camera.AutoFocusCallback {

        @Override
        public void onAutoFocus(boolean paramBoolean, Camera paramCamera) {
            if (paramBoolean) {
                // если удалось сфокусироваться, делаем снимок
                paramCamera.takePicture(null, null, null, new MyPictureCallback());
            }
        }
    }

    class MyPictureCallback implements Camera.PictureCallback {

        @Override
        public void onPictureTaken(byte[] paramArrayOfByte, Camera paramCamera) {
            // сохраняем полученные jpg в папке /sdcard/CameraExample/
            // имя файла - System.currentTimeMillis()
            try {
                File saveDir = new File("/sdcard/CameraExample/");
                if (!saveDir.exists()) {
                    saveDir.mkdirs();
                }

                FileOutputStream os = new FileOutputStream(String.format("/sdcard/CameraExample/%d.jpg", System.currentTimeMillis()));
                os.write(paramArrayOfByte);
                os.close();
            } catch (Exception e) {
                Log.d("logs", "err cam");
            }

            // после того, как снимок сделан, показ превью отключается.
            // Необходимо включить его
            paramCamera.startPreview();
        }
    }
}*/