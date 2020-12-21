package com.hatz.trafficker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class HospitalActivity extends AppCompatActivity {
    private Button payButton;
    AdView hospitalbanner;
    AdRequest adRequest ;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        hospitalbanner=findViewById(R.id.hospitalbanner);
        adRequest = new AdRequest.Builder().build();
        hospitalbanner.loadAd(adRequest);
        payButton = findViewById(R.id.payButton);
        if (MainActivity.getMe().getHp() == 100) {
            payButton.setText("YOU ARE FULL HP");
            payButton.setEnabled(false);
        } else {
            payButton.setText("PAY $" + (100 - MainActivity.getMe().getHp())+ "K FOR TREATMENT ");

        }


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(HospitalActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void heal(View view) {
        if (MainActivity.getMe().heal()) {
            payButton.setText("YOU ARE FULL HP");
            payButton.setEnabled(false);
        }
    }
}