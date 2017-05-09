package com.csf.loaders;

import android.os.AsyncTask;

import java.util.List;

import dto.PlaceDto;
import services.GidService;

public class GidCrdTask extends AsyncTask<String, String, List<PlaceDto>> {
    @Override
    protected List<PlaceDto> doInBackground(String... params) {
        return GidService.getInstance().getPlaces(params[0], params[1]);
    }
}
