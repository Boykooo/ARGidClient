package com.csf.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.csf.dialogs.PlacesDialog;
import com.csf.loaders.GidCrdTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

import dto.PlaceDto;
import services.GidService;

public class ArActivity extends Activity {

    private GidService gidService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ar_activity);

        this.gidService = GidService.getInstance();
    }

    public void spartakButton(View view) throws ExecutionException, InterruptedException {
        showPlacebyCrd("51.661873", "39.204396");
    }

    public void koltButton(View view) throws ExecutionException, InterruptedException {
        showPlacebyCrd("51.661896", "39.202152");
    }

    private void showPlacebyCrd(String lat, String lng) throws ExecutionException, InterruptedException {
        GidCrdTask task = new GidCrdTask();
        List<PlaceDto> places = task.execute(lat, lng).get();

//        List<PlaceDto> places = null;
//        final Context context = this;
//        GidService.getInstance().getApi().getPlaces(lat, lng).enqueue(new Callback<List<PlaceDto>>() {
//            @Override
//            public void onResponse(Call<List<PlaceDto>> call, Response<List<PlaceDto>> response) {
//                PlacesDialog.newInstance(response.body()).show(getFragmentManager(), "placesDialog");
//            }
//
//            @Override
//            public void onFailure(Call<List<PlaceDto>> call, Throwable t) {
//                AlertManager.getBaseMessage("qwe", "Error", context);
//            }
//        });


        PlacesDialog.newInstance(places).show(getFragmentManager(), "placesDialog");
    }
}

