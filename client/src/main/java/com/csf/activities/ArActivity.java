package com.csf.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.csf.base.BaseActivity;
import com.csf.dialogs.PlacesDialog;

import java.util.List;
import java.util.concurrent.ExecutionException;

import dto.PlaceDto;
import services.GidService;

public class ArActivity extends BaseActivity {

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
        GidServiceTask task = new GidServiceTask();
        List<PlaceDto> places = task.execute(lat, lng).get();

        PlacesDialog.newInstanse(places).show(getFragmentManager(), "placesDialog");
    }

    private class GidServiceTask extends AsyncTask<String, String, List<PlaceDto>> {
        @Override
        protected List<PlaceDto> doInBackground(String... params) {
            return gidService.getPlaces(params[0], params[1]);
        }
    }
}

