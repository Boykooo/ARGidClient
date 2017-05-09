package com.csf.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

import dto.PlaceDto;
import services.GidService;

public class GidAzimuthLoader extends AsyncTaskLoader<List<PlaceDto>> {

    private String lat;
    private String lng;
    private String azimuth;

    public GidAzimuthLoader(Context context, String lat, String lng, String azimuth) {
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
