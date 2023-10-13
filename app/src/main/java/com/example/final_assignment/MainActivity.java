package com.example.final_assignment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button scanButton;

    private static final int REQUEST_CAMERA_PERMISSION = 201;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        scanButton = findViewById(R.id.scanButton);
        scanButton.setOnClickListener(this);
    }

//    @Override
//    public void onClick(View v) {
//        // For testing purposes, manually set a geo URL
//        String geoUri = "geo:37.7749,-122.4194"; // Example geo URL for San Francisco
//
//        // Pass the geo URI to the LocationActivity for testing
//        startLocationActivity(geoUri);
//    }

    @Override
    public void onClick(View v) {
        if (checkPermissions()) {
            startActivity(new Intent(MainActivity.this, ScanActivity.class));
        } else {
            requestPermissions();
        }
    }

    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(
                MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                        MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(MainActivity.this, new
                String[]{Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CAMERA_PERMISSION);
    }
}
