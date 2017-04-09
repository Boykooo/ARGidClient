package com.csf.activities;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import dto.PlaceDto;
import services.GidService;

/**
 * Created by Andrey on 09.04.2017.
 */

public class ArActivity extends Activity {

    private GidService gidService;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ar_activity);

        textView = (TextView) findViewById(R.id.hello);
        this.gidService = new GidService();
    }

    public void spartakButton(View view) {

    }

    public void koltButton(View view) {

    }

    public void test(View view) throws Exception {
        //lat=51.661896&lng=39.202152
        String lat = "51.661896";
        String lng = "39.202152";

        GidServiceTask task = new GidServiceTask();

        List<PlaceDto> places = task.execute(lat, lng).get();
        textView.setText(places.get(0).getName());
    }

    private class GidServiceTask extends AsyncTask<String, String, List<PlaceDto>> {
        @Override
        protected List<PlaceDto> doInBackground(String... params) {
            return gidService.getPlaces(params[0], params[1]);
        }
    }
}






















