package com.csf.activities;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;

import com.csf.base.LocationActivity;
import com.csf.utility.AlertManager;
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
        if (googleApiClient.isConnected()) {
            if (lastLocation != null) {
                String loc = lastLocation.getLatitude() + " " + lastLocation.getLongitude();
                AlertManager.getBaseMessage("Текущее местоположение", loc, this).show();
            }
        }
        else {
            AlertManager.getBaseMessage("Текущее местоположение", this).show();
        }
    }
}
