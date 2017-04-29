package com.csf.utility;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Andrey on 29.04.2017.
 */

public class LocationService implements LocationListener{

    private LocationManager locationManager;
    private Context context;
    private FragmentActivity fragmentActivity;
    private GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener;

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 2; // 2 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

    public LocationService(Context context, FragmentActivity fragmentActivity, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.context = context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        this.fragmentActivity = fragmentActivity;
        this.onConnectionFailedListener = onConnectionFailedListener;
    }

    public String getLocation() {


//        List<String> providers = locationManager.getProviders(true);
//        Location bestLocation = null;
//        for (String provider : providers) {
//            Location loc = locationManager.getLastKnownLocation(provider);
//            if (loc == null) {
//                continue;
//            }
//            if (bestLocation == null || loc.getAccuracy() < bestLocation.getAccuracy()) {
//                // Found best last known location: %s", l);
//                bestLocation = loc;
//            }
//        }

//        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//        if (!isGPSEnabled && !isNetworkEnabled){
//            return null;
//        }
//        else {
//            if (isGPSEnabled){
//                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
//                Log.d("GPS Enabled", "GPS Enabled");
//                Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//
//                if (locationGPS != null){
//                    return String.valueOf(locationGPS.getLatitude()) + " " + String.valueOf(locationGPS.getLongitude());
//                }
//                else {
//                    Log.d("Location", "locationGPS is null");
//                }
//            }
//        }


        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(context)
                .enableAutoManage(fragmentActivity, onConnectionFailedListener)
                .addApi(LocationServices.API)
                .addScope(Drive.SCOPE_FILE)
                .build();

        googleApiClient.connect();

        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        return location.getLatitude() + " " + location.getLongitude();

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
