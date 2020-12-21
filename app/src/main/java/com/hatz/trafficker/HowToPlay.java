package com.hatz.trafficker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class HowToPlay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(HowToPlay.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}