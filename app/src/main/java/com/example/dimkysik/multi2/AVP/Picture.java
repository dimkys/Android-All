package com.example.dimkysik.multi2.AVP;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dimkysik.multi2.R;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by DimkySik on 06.10.2016.
 */

public class Picture extends Activity {
    int currentImage=0;
    ArrayList<String> images;
    ImageView imageView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.picture);

    }

    @Override
    public void onResume(){
        super.onResume();
        currentImage=0;
        Log.d("myLogs ", "onResume cI="+currentImage);
        images=new ArrayList<>();
        imageView=(ImageView)findViewById(R.id.imageViewPicture);
        try{
            File imagesDirectory=new File("/sdcard/MyPhoto/");
            images=searchImage(imagesDirectory);
            if(images.size()>0){
                //showMessage("картины есть");
            }else{
                showMessage("картины неть");
            }
            updatePhoto(Uri.parse(images.get(currentImage)));
        }catch(Exception e){
            showMessage(e+"");
        }

    }

    @Override
    protected void onPause()
    {
        //showMessage("Почистили");
        super.onPause();
        //images.clear();
        Log.d("myLogs ", "onPause c="+currentImage);
    }

    private ArrayList<String> searchImage(File dir){
        ArrayList< String> imagesFinded=new ArrayList<> ();
        for(File f:dir.listFiles()){
            if(!f.isDirectory()) {
                String ﬁleExt = getFileExt(f.getAbsolutePath());
                if (ﬁleExt.equals("png") || ﬁleExt.equals("jpg") || ﬁleExt.equals("jpeg")) {
                    Log.d("1nyLogs", "CIJaHn Hai/iger-r " + f.getAbsolutePath());
                    imagesFinded.add(f.getAbsolutePath());
                }
            }
    }
    return imagesFinded;

}

    public static String getFileExt(String ﬁlename){
        return ﬁlename.substring(ﬁlename.lastIndexOf(".") + 1);
    }

    private void showMessage(String text) {
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void updatePhoto(Uri uri){
        try{
           //showMessage(""+images.get(currentImage));
            imageView.setImageURI(uri);
            //imageView.setImageURI(Uri.parse("sdcard/MyPhoto/8.png"));
        }catch(Exception e){
            showMessage(e+"Хоп не робит");
        }
    }

    public void onNext(View v){
        if(currentImage+1<images.size() && images.size()>0){
            currentImage++;
            try {
                updatePhoto(Uri.parse(images.get(currentImage)));
            }catch (Exception e){
                showMessage(e+"Нету");
            }
        }
    }

    public void onPrevious(View v){
        if(currentImage>0 && images.size()>0){
            currentImage--;
            updatePhoto(Uri.parse(images.get(currentImage)));
        }
    }
}
