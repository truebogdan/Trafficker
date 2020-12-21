package com.hatz.trafficker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

@RequiresApi(api = Build.VERSION_CODES.O)
public class GunShop extends AppCompatActivity {
private You you=MainActivity.getMe();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gun_shop);
        init();

    }
    private void init()
    {
        Button buyPistol=findViewById(R.id.buyPistolButton);
        if (you.getGuns().contains(new Gun("Pistol",0,0)))
        {
            buyPistol.setEnabled(false);
            buyPistol.setText("BOUGHT");
        }

        Button buyMP5=findViewById(R.id.buyMP5Button);
        if (you.getGuns().contains(new Gun("MP5",0,0))) {
            buyMP5.setEnabled(false);
            buyMP5.setText("BOUGHT");
        }
        Button buySMG=findViewById(R.id.buttonBuySMG);
        if (you.getGuns().contains(new Gun("MP5-A4",0,0)))
        {  buySMG.setEnabled(false);
            buySMG.setText("BOUGHT");}
        Button buySMG2=findViewById(R.id.buySMG2Button);
        if (you.getGuns().contains(new Gun("MP5-SD",0,0)))
        {   buySMG2.setEnabled(false);
            buySMG2.setText("BOUGHT");}
        Button buyAK=findViewById(R.id.buyAkButton);
        if (you.getGuns().contains(new Gun("AK-47",0,0)))
        { buyAK.setEnabled(false);
            buyAK.setText("BOUGHT");}
        Button buySniper=findViewById(R.id.buySniperButton);
        if (you.getGuns().contains(new Gun("Auto Sniper",0,0)))
        {buySniper.setEnabled(false);
            buySniper.setText("BOUGHT");}
    }

    @Override
    public void onBackPressed() {
        Intent intent= new Intent(GunShop.this,MainActivity.class);
        startActivity(intent);
    }
    public void buyPistol(View view)
    {
       if( you.buyGun(new Gun("Pistol",35,5000)))
           init();
    }
    public void buyMP5(View view)
    {
       if( you.buyGun(new Gun("MP5",40,10000)))
           init();
    }
    public void buyMP7(View view)
    {
       if( you.buyGun(new Gun("MP5-A4",60,50000)))
           init();
    }
    public void buyMP7S(View view)
    {
        if(you.buyGun(new Gun("MP5-SD",80,100000)))
            init();
    }
    public void buyAK47(View view)
    {
        if(you.buyGun(new Gun("AK-47",150,500000)))
            init();
    }
    public void buySniper(View view)
    {

        if(you.buyGun(new Gun("Auto Sniper",300,1000000)))
            init();
    }

}