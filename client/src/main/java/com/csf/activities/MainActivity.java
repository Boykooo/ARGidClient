package com.csf.activities;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.TextView;

import com.csf.base.LocationActivity;
import com.csf.dialogs.PlacesDialog;
import com.csf.loaders.GidAzimuthLoader;
import com.csf.utility.AlertManager;
import com.google.android.gms.location.LocationServices;

import java.util.List;
import java.util.concurrent.ExecutionException;

import dto.PlaceDto;

public class MainActivity extends LocationActivity implements SensorEventListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);
    }

    public void moveToArActivity(View view) {
        Intent intent = new Intent(this, ArActivity.class);
        startActivity(intent);
    }

    public void locate(View view) throws ExecutionException, InterruptedException {
        Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (lastLocation != null) {
            String lat = String.valueOf(lastLocation.getLatitude());
            String lng = String.valueOf(lastLocation.getLongitude());

            Bundle args = new Bundle();
            args.putString("lat", lat);
            args.putString("lng", lng);
            args.putString("azimuth", String.valueOf(azimuth));

            getSupportLoaderManager().initLoader(1, args, this).forceLoad();

        } else {
            AlertManager.getBaseMessage("Ошибка", "Не удается получить текущее местоположение. Возможно отключен GPS", this).show();
        }
    }

    @Override
    public Loader<List<PlaceDto>> onCreateLoader(int id, Bundle args) {
        return new GidAzimuthLoader(this, args.getString("lat"), args.getString("lng"), args.getString("azimuth"));
    }

    @Override
    public void onLoadFinished(Loader<List<PlaceDto>> loader, List<PlaceDto> data) {
        PlacesDialog.newInstance(data).show(getFragmentManager(), "placesDialog");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        this.azimuth = event.values[0];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

}
