package com.csf.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.csf.dialogs.PlacesDialog;
import com.csf.loaders.GidCrdTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

import dto.PlaceDto;

public class ArActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ar_activity);

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

        PlacesDialog.newInstance(places).show(getFragmentManager(), "placesDialog");
    }
}

