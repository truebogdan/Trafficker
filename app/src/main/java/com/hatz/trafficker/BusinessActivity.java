package com.hatz.trafficker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class BusinessActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BusRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        adapter = new BusRecViewAdapter(this);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        ArrayList<Business> businesses = new ArrayList<>();
        businesses.add(new Business("Weed Farm", 10000,"Weed", 20000,5));
        businesses.add(new Business("PCP Lab", 50000,"PCP", 40000,5));
        businesses.add(new Business("LSD Lab",200000,"LSD",500000,4));
        businesses.add(new Business("Heroin Lab",2000000,"Heroin",2000000,3));
        businesses.add(new Business("Crack Lab",5000000,"Crack",10000000,5));
        adapter.setBusinesses(businesses);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(BusinessActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void openDeals(View view)
    {
        Intent intent=new Intent(BusinessActivity.this,DealsActivity.class);
        startActivity(intent);
        finish();
    }
    public void openMarket(View view) {
        Intent intent = new Intent(BusinessActivity.this, MarketActivity.class);
        startActivity(intent);
        finish();
    }

    public void openHome(View view) {
        Intent intent = new Intent(BusinessActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

}