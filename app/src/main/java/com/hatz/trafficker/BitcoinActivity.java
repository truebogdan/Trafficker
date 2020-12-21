package com.hatz.trafficker;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


@RequiresApi(api = Build.VERSION_CODES.O)
public class BitcoinActivity extends AppCompatActivity {
     TextView btcPrice;
     TextView totalCash,totalBitcoin;
     You me=MainActivity.getMe();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitcoin);
        init();



    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(BitcoinActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void init()
    {
        btcPrice = findViewById(R.id.btcPrice);
        String btcformat=String.format("%,d",me.getBtcPrice());
        btcPrice.setText(" 1 BTC = $"+btcformat);
        totalCash=findViewById(R.id.totalCash);
        String cash=String.format("%,d",me.getCashMoney());
        totalCash.setText(cash);
        totalBitcoin=findViewById(R.id.totalBitcoin);
        String bitcoin=String.format("%,f",me.getBtc());
        totalBitcoin.setText(bitcoin);
        final EditText cashToBtcEditText=findViewById(R.id.CashToBtcEditText);
        Button convertBtn1=findViewById(R.id.convertButton1);
        convertBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cashToBtcEditText.getText().toString().equals("")){
                    long cashAmount=Integer.parseInt(cashToBtcEditText.getText().toString());
                    if(me.cashToBtc(cashAmount))
                    {
                        init();
                    }
                }

            }
        });
        final  EditText btcToCashEditText=findViewById(R.id.BtcToCashEditText);
        btcToCashEditText.setText(""+me.getBtc());
        Button convertBtn2=findViewById(R.id.convertButton2);
        convertBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!btcToCashEditText.getText().toString().equals(""))
                {
                    double btcAmount=Double.parseDouble(btcToCashEditText.getText().toString());
                    if(me.btcToCash(btcAmount))
                    {
                        init();
                    }

                }
            }
        });


    }
}
