package com.csf.taskLoaders;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

import dto.PlaceDto;
import services.GidService;

public class GidAzimuthTask extends AsyncTaskLoader<List<PlaceDto>> {

    private String lat;
    private String lng;
    private String azimuth;

    public GidAzimuthTask(Context context, String lat, String lng, String azimuth) {
        super(context);

        this.lat = lat;
        this.lng = lng;
        this.azimuth = azimuth;
    }

    @Override
    public List<PlaceDto> loadInBackground() {
        return GidService.getInstance().getPlaces(lat, lng, azimuth);
    }

}
