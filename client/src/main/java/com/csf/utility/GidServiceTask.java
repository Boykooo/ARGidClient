package com.csf.utility;

import android.os.AsyncTask;

import java.util.List;

import dto.PlaceDto;
import services.GidService;

public class GidServiceTask extends AsyncTask<String, String, List<PlaceDto>> {
    @Override
    protected List<PlaceDto> doInBackground(String... params) {
        return GidService.getInstance().getPlaces(params[0], params[1]);
    }
}
