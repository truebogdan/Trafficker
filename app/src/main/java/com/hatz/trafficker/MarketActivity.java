package com.hatz.trafficker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MarketActivity extends AppCompatActivity {
    You me = MainActivity.getMe();
    ListView marketlist;
     Market market=MainActivity.getMarket();
    AlertDialog.Builder dialogbuilder;
    AlertDialog dialog;
    EditText buyamount;
    Button close, buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        initView();
        //updateStock();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MarketActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void initView() {
        marketlist = findViewById(R.id.marketlist);
        ArrayAdapter<Drug> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, market.drugsmarket);
        marketlist.setAdapter(adapter);
        marketlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Drug drug=new Drug(market.drugsmarket.get(position).getName(),market.drugsmarket.get(position).getQuantity(),market.drugsmarket.get(position).getPrice());
                createPopupBuy(drug,position);

            }
        });

    }

    public void openHome(View view) {
        Intent intent = new Intent(MarketActivity.this, MainActivity.class);
        startActivity(intent);
finish();
    }
    public void openBusinesses(View view)
    {
        Intent intent=new Intent(MarketActivity.this,BusinessActivity.class);
        startActivity(intent);
        finish();
    }
    public void openDeals(View view)
    {
        Intent intent=new Intent(MarketActivity.this,DealsActivity.class);
        startActivity(intent);
        finish();
    }
    public void updateStock() {
        marketlist = findViewById(R.id.marketlist);
        ArrayAdapter<Drug> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, market.drugsmarket);
        marketlist.setAdapter(adapter);

    }

    public void createPopupBuy(final Drug drug,final int position) {
        dialogbuilder = new AlertDialog.Builder(this);
        final View popupView = getLayoutInflater().inflate(R.layout.buypopup, null);
        buyamount = (EditText) popupView.findViewById(R.id.sellamount);
        long a=me.getCashMoney() / drug.getPrice();
        if(a<drug.getQuantity())
        buyamount.setText(""+a);
        else buyamount.setText(""+drug.getQuantity());
        buy = (Button) popupView.findViewById(R.id.sellbtn);
        close = (Button) popupView.findViewById(R.id.closebtn);
        dialogbuilder.setView(popupView);
        dialog = dialogbuilder.create();
        dialog.show();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buyamount.getText().toString().equals("")) {
                    Toast.makeText(MarketActivity.this, "This field can't be empty.", Toast.LENGTH_SHORT).show();
                } else {
                    int x=Integer.parseInt(buyamount.getText().toString());
                    if(me.buyDrug(drug,x))
                    {
                        market.drugsmarket.get(position).setQuantity(drug.getQuantity()-x);
                        updateStock();
                        Toast.makeText(MarketActivity.this, ""+x+" "+drug.getName()+" purchased.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                    else
                        Toast.makeText(MarketActivity.this, "You can't do that.", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }


}