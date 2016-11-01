package com.example.dimkysik.multi2.AVP;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dimkysik.multi2.R;

public class Video extends Activity {

    TextView tvOut;
    TextView tvLon;
    TextView tvLat;
    TextView tvOut2;
    TextView tvLon2;
    TextView tvLat2;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);

        tvOut = (TextView) findViewById(R.id.textView3);
        tvLon = (TextView) findViewById(R.id.textView4);
        tvLat = (TextView) findViewById(R.id.textView5);

       tvOut2 = (TextView) findViewById(R.id.textView6);
        tvLon2 = (TextView) findViewById(R.id.textView7);
        tvLat2 = (TextView) findViewById(R.id.textView8);

         tvOut.setText("");
         tvLon.setText("");
         tvLat.setText("");
         tvOut2.setText("");
         tvLon2.setText("");
         tvLat2.setText("");

        LocationManager mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        LocationListener mlocListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                tvOut.setText("onLocationChanged");
                tvLat.append(" " + location.getLatitude());
                tvLon.append(" " + location.getLongitude());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
                showMessage("onProviderDisabled: ok");
                tvOut.setText("onProviderDisabled:GPS is turned on...");
            }
        };

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }else{
            showMessage("permission: ok");
        }
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mlocListener);

        if (mlocManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            tvOut.setText("GPS is turned on...");

        } else {
            tvOut.setText("GPS is not turned on...");
        }

        LocationManager mlocManager2 = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        LocationListener mlocListener2 = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                tvOut2.setText("onLocationChanged");
                tvLat2.append(" " + location.getLatitude());
                tvLon2.append(" " + location.getLongitude());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                //tvOut.setText("onStatusChanged");
            }

            @Override
            public void onProviderEnabled(String provider) {
                showMessage("onProviderEnabled: ok");
            }

            @Override
            public void onProviderDisabled(String provider) {
                showMessage("onProviderDisabled: ok");
                tvOut2.setText("onProviderDisabled:GPS is turned on...");
            }
        };
        mlocManager2.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener2);

        if (mlocManager2.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            tvOut2.setText("GPS is turned on...");

        } else {
            tvOut2.setText("GPS is not turned on...");
        }




        //showMessage("create: ok");
    }
    private void showMessage(String text) {
    Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
    toast.setGravity(Gravity.CENTER, 0, 0);
    toast.show();
}
}
