// LocationParser.java
package com.example.final_assignment;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

public class LocationParser {
    private Context context;

    public LocationParser(Context context) {
        this.context = context;
    }

    public String parseAndCalculateDistance(String geoUri) {
        // Parse geo URI and extract latitude and longitude
        Location targetLocation = parseGeoUri(geoUri);

        // Get current location
        Location currentLocation = getCurrentLocation();

        // Calculate distance
        double distance = calculateDistance(currentLocation, targetLocation);

        // Format and return location details
        return "Current Location: " + currentLocation.getLatitude() + ", " +
                currentLocation.getLongitude() + "\nTarget Location: " +
                targetLocation.getLatitude() + ", " + targetLocation.getLongitude() +
                "\nDistance: " + distance + " km";
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
        // Implement the logic to get the current location
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        try {
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastKnownLocation != null) {
                return lastKnownLocation;
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    private double calculateDistance(Location currentLocation, Location targetLocation) {
        // Implement the logic to calculate distance between two locations
        if (currentLocation != null && targetLocation != null) {
            return currentLocation.distanceTo(targetLocation) / 1000; // in kilometers
        }
        return 0;
    }
}


