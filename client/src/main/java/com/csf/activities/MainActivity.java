package com.csf.activities;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.location.Location;
import android.os.Bundle;
import android.view.View;

import com.csf.base.LocationActivity;
import com.csf.dialogs.PlacesDialog;
import com.csf.utility.AlertManager;
import com.csf.utility.GidServiceTask;
import com.google.android.gms.location.LocationServices;

import java.util.List;
import java.util.concurrent.ExecutionException;

import dto.PlaceDto;

public class MainActivity extends LocationActivity implements LoaderManager.LoaderCallbacks<List<PlaceDto>> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


            Bundle bundle = new Bundle();
            bundle.putString("lat", lat);
            bundle.putString("lng", lng);

            GidServiceTask task = new GidServiceTask();
            List<PlaceDto> places = task.execute(lat, lng).get();
            PlacesDialog.newInstanse(places).show(getFragmentManager(), "placesDialog");


            //final Context context = this;

//            gidService.getApi().getPlaces(lat, lng).enqueue(new Callback<List<PlaceDto>>() {
//                @Override
//                public void onResponse(Call<List<PlaceDto>> call, Response<List<PlaceDto>> response) {
//                    AlertManager.getBaseMessage("Текущее местоположение", response.body().get(0).getName(), context).show();
//                }
//
//                @Override
//                public void onFailure(Call<List<PlaceDto>> call, Throwable t) {
//                    AlertManager.getBaseMessage("Текущее местоположение","Ошибка", context).show();
//                }
//            });

            //AlertManager.getBaseMessage("Текущее местоположение", loc, this).show();
        } else {
            AlertManager.getBaseMessage("Ошибка", "Не удается получить текущее местоположение. Возможно отключен GPS", this).show();
        }
    }


    @Override
    public Loader<List<PlaceDto>> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<PlaceDto>> loader, List<PlaceDto> data) {
        PlacesDialog.newInstanse(data).show(getFragmentManager(), "placesDialog");
    }

    @Override
    public void onLoaderReset(Loader<List<PlaceDto>> loader) {

    }

}
