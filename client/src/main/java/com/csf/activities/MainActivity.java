package com.csf.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.csf.base.LocationActivity;
import com.google.android.gms.location.LocationServices;

import services.GidService;

public class MainActivity extends LocationActivity{

    private GidService gidService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gidService = GidService.getInstance();
    }

    public void move(View view) {
        Intent intent = new Intent(this, ArActivity.class);
        startActivity(intent);
    }

    public void locate(View view) {
        Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (googleApiClient.isConnected())
        if (lastLocation != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            String loc = lastLocation.getLatitude() + " " + lastLocation.getLongitude();
            builder.setTitle(loc);

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }
}
