// LocationParser.java
package com.example.final_assignment;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class LocationParser {
    private Context context;

    public LocationParser(Context context) {
        this.context = context;
    }

    public String parseAndCalculateDistance(String geoUri) {
        Location targetLocation = parseGeoUri(geoUri);
        Location currentLocation = getCurrentLocation();

        double distance = calculateDistance(currentLocation, targetLocation);

        String formattedDistance = String.format("%.2f", distance);

        return "Current Location: " + (currentLocation != null ? currentLocation.getLatitude() + ", " +
                currentLocation.getLongitude() : "N/A") +
                "\nTarget Location: " +
                targetLocation.getLatitude() + ", " + targetLocation.getLongitude() +
                "\nDistance: " + formattedDistance + " km";
    }

    private Location parseGeoUri(String geoUri) {
        Location location = new Location("");
        if (geoUri.startsWith("geo:")) {
            String[] parts = geoUri.substring(4).split(",");
            if (parts.length == 2) {
                try {
                    double latitude = Double.parseDouble(parts[0]);
                    double longitude = Double.parseDouble(parts[1]);
                    location.setLatitude(latitude);
                    location.setLongitude(longitude);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        return location;
    }

    private Location getCurrentLocation() {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        try {
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        Log.d("LocationParser", "Location updated: " + location.getLatitude() + ", " + location.getLongitude());
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
                });

                Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (lastKnownLocation != null) {
                    return lastKnownLocation;
                }
            } else {
                Log.e("LocationParser", "GPS provider not enabled");
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    private double calculateDistance(Location currentLocation, Location targetLocation) {
        if (currentLocation != null && targetLocation != null) {
            return currentLocation.distanceTo(targetLocation) / 1000; // in km
        }
        return 0;
    }
}
