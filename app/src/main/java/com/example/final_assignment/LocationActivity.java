// LocationActivity.java
package com.example.final_assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LocationActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView txtLocationDetails;

    private Button goBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        txtLocationDetails = findViewById(R.id.txtLocationDetails);

        String geoUri = getIntent().getStringExtra("geoUri");

        LocationParser locationParser = new LocationParser(getApplicationContext());
        String locationDetails = locationParser.parseAndCalculateDistance(geoUri);

        txtLocationDetails.setText(locationDetails);

        initViews();
    }

    private void initViews() {
        goBackButton = findViewById(R.id.goToMainActivity);
        goBackButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        Intent intent = new Intent(LocationActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
