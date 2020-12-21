package com.hatz.trafficker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@RequiresApi(api = Build.VERSION_CODES.O)
public class DealsActivity extends AppCompatActivity {
    int a=0;
    TextView transportCost,productValue,profit,succestxt;
    Deal deal=MainActivity.mydeal;
    private Drug drug;
    Button sendTransport;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deals);
        init();

    }

    @Override
    public void onBackPressed() {
        Intent intent= new Intent(DealsActivity.this,MainActivity.class);
        startActivity(intent);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void init(){
        sendTransport=findViewById(R.id.sendtransport);
        if(!deal.isAvailable())
        {
            sendTransport.setEnabled(false);
            sendTransport.setText("Already sent transport");
        }

        succestxt=findViewById(R.id.succesTextView);
        String []countries=getResources().getStringArray(R.array.countries);
        String []productsArray=getResources().getStringArray(R.array.productName);
        TextView country=findViewById(R.id.country);
        TextView product=findViewById(R.id.product);
        TextView price=findViewById(R.id.price);
        TextView quantity=findViewById(R.id.quantity);
        Spinner protection=findViewById(R.id.protspinner);
        Spinner bribe=findViewById(R.id.bribespinner);
        transportCost=findViewById(R.id.transcost);
        productValue=findViewById(R.id.prodvalue);
        profit=findViewById(R.id.profit);

        String temp=countries[deal.getCountry()];
        country.setText(temp);
        temp=productsArray[deal.getProductName()];
        product.setText((temp));
        temp=String.format("%,d",deal.getPrice());
        price.setText(temp);
        quantity.setText(""+deal.getQuantity());
        drug=new Drug(productsArray[deal.getProductName()],deal.getQuantity(),deal.getPrice());
        //temp=String.format("%,d",deal.getTransportCost());
       // transportCost.setText(temp);
        temp=String.format("%,d",deal.getProductValue());
        productValue.setText(temp+"$");
        updateView();
       // temp=String.format("%,d",deal.getProfit());
        //profit.setText(temp);

        protection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                deal.setProtection(position);
                updateView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        bribe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                deal.setBribe(position);
                updateView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public void openMarket(View view) {
        Intent intent = new Intent(DealsActivity.this, MarketActivity.class);
        startActivity(intent);
        finish();
    }

    public void openHome(View view) {
        Intent intent = new Intent(DealsActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
    public void openBusinesses(View view)
    {
        Intent intent=new Intent(DealsActivity.this,BusinessActivity.class);
        startActivity(intent);
        finish();
    }
    public void updateView(){
        String temp=String.format("%,d",deal.getTransportCost());
        transportCost.setText(temp+"$");
        temp=String.format("%,d",deal.getProfit());
        if(deal.getProfit()>0)
            profit.setTextColor(Color.GREEN);
        else
            profit.setTextColor(Color.RED);
        profit.setText(temp+"$");

    }
    public void sendTransport(View view)
    {
        String[] products=getResources().getStringArray(R.array.productName);
        if(MainActivity.getMe().getCashMoney()>=deal.getTransportCost() && MainActivity.getMe().canSellThisDrug(products[deal.getProductName()],deal.getQuantity()))
        { String success=deal.successRate();
            Toast.makeText(this, success, Toast.LENGTH_SHORT).show();
        if(success.equals("Robbed"))
        {
            MainActivity.getMe().setCashMoney(MainActivity.getMe().getCashMoney()-deal.getTransportCost());
            succestxt.setText("You got robbed by rivals.");
            succestxt.setTextColor(Color.RED);
            MainActivity.getMe().sellDrug(drug,drug.getQuantity(),0);
            sendTransport.setEnabled(false);
            sendTransport.setText("Already sent transport");
            deal.setAvailable(false);
            MediaPlayer mediaPlayer=MediaPlayer.create(DealsActivity.this,R.raw.m4soundeffect);
            mediaPlayer.start();
        }
         else if(success.equals("Busted"))

        {  MediaPlayer mediaPlayer=MediaPlayer.create(DealsActivity.this,R.raw.policesirne);
            mediaPlayer.start();
            MainActivity.getMe().setCashMoney(MainActivity.getMe().getCashMoney()-deal.getTransportCost());
            succestxt.setText("Police seize all your drugs.");
            succestxt.setTextColor(Color.RED);
            MainActivity.getMe().sellDrug(drug,drug.getQuantity(),0);
            sendTransport.setEnabled(false);
            sendTransport.setText("Already sent transport");
            deal.setAvailable(false);

        }
        else
        {
            if(MainActivity.getMe().sellDrugToDeals(drug,drug.getQuantity(),deal.getProfit()))
            {
                MainActivity.getMe().setCashMoney(MainActivity.getMe().getCashMoney()-deal.getTransportCost());

                succestxt.setText("The transport arrived at the destination.");
                succestxt.setTextColor(Color.GREEN);
                sendTransport.setEnabled(false);
                sendTransport.setText("Already sent transport");
                deal.setAvailable(false);
            }

            else
                Toast.makeText(this, "You can't do that.", Toast.LENGTH_SHORT).show();

        }


        }
        else Toast.makeText(this, "You can't do that.", Toast.LENGTH_SHORT).show();

    }
}