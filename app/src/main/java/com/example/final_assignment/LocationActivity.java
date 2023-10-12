// LocationActivity.java
package com.example.final_assignment;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LocationActivity extends AppCompatActivity {
    private TextView txtLocationDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        txtLocationDetails = findViewById(R.id.txtLocationDetails);

        // Get geo URI from intent
        String geoUri = getIntent().getStringExtra("geoUri");

        // Parse geo URI and calculate distance
        LocationParser locationParser = new LocationParser(getApplicationContext());
        String locationDetails = locationParser.parseAndCalculateDistance(geoUri);

        // Display location details
        txtLocationDetails.setText(locationDetails);
    }
}
