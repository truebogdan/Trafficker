package com.hatz.trafficker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;


@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {

    Gun selectedgun;
    Spinner gunspinner;
    AlertDialog.Builder dialogbuilder;
    AlertDialog dialog;
    Button closebtn;
    Button sellbtn;
    EditText sellamount;
    ListView inventory;
    TextView cashMoney;
    TextView name,date,yearsTextView;
    ImageView profile;
    static Deal mydeal=new Deal();

    static You me=new You();
    static Market market=new Market();
    static ArrayList<Business> businesses =new ArrayList<>();
    private boolean game_over=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSaves();
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }

        });

    }
    private void getSaves()
    {
        SharedPreferences sharedPreferencess=getSharedPreferences("call",MODE_PRIVATE);
        if(!sharedPreferencess.contains("savenik"))
            continuePopUp();
        else
            me.setName(sharedPreferencess.getString("savenik","Tony Juarez"));
        SharedPreferences sharedPreferences=getSharedPreferences("mySaves",0);
        Gson gson= new Gson();
        String json;
        if(sharedPreferences.contains("cash_key"))
        {
            me.setCashMoney(sharedPreferences.getLong("cash_key",0));

        }
        if(sharedPreferences.contains("inventory_key"))
        {

            json=sharedPreferences.getString("inventory_key","");
            Type type= new TypeToken<ArrayList<Drug>>(){}.getType();
            ArrayList<Drug> drugArrayList=gson.fromJson(json,type);
            me.setInventory(drugArrayList);
        }
        if(sharedPreferences.contains("businesses_key"))
        {
            json=sharedPreferences.getString("businesses_key","");
            Type type=new TypeToken<ArrayList<Business>>(){}.getType();
            businesses=gson.fromJson(json,type);
        }
        if(sharedPreferences.contains("market_key"))
        {
            json=sharedPreferences.getString("market_key","");
            market=gson.fromJson(json, Market.class);
        }
        if (sharedPreferences.contains("deal_key"))
        {
            json=sharedPreferences.getString("deal_key","");
            mydeal=gson.fromJson(json,Deal.class);
        }
        if(sharedPreferences.contains("you_key"))
        {
            json=sharedPreferences.getString("you_key","");
            me=gson.fromJson(json,You.class);
        }
        if(sharedPreferences.contains("year_key"))
        {
           int year=sharedPreferences.getInt("year_key",6);
           int month=sharedPreferences.getInt("month_key",6);
           int day=sharedPreferences.getInt("day_key",6);
            me.setDate(LocalDate.of(year,month,day));
        }
        initView();
        clearSaves(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!game_over) {

            MenuInflater inflater=getMenuInflater();
            inflater.inflate(R.menu.menu,menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.save_game && !game_over)
        {
            savePreferences();
            Toast.makeText(this, "Saved.", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId()==R.id.bitcoin_wallet && !game_over)
        {
            openBitcoinWallet();
        }
        if (item.getItemId()==R.id.hospital && !game_over)
        {
            openHospital();
        }
        if (item.getItemId()==R.id.gun_shop && !game_over)
        {
            openGunShop();
        }
        if (item.getItemId()==R.id.htp && !game_over)
        {
            openHowToPlay();
        }


        return super.onOptionsItemSelected(item);
    }

    private void openHowToPlay() {
        Intent intent = new Intent(MainActivity.this,HowToPlay.class);
        startActivity(intent);
        finish();
    }

    private void openBitcoinWallet() {
        Intent intent= new Intent(MainActivity.this,BitcoinActivity.class);
        startActivity(intent);
        finish();
    }
    private void openHospital()
    {
        Intent intent= new Intent(MainActivity.this,HospitalActivity.class);
        startActivity(intent);
        finish();
    }
    private void openGunShop()
    {
        Intent intent= new Intent(MainActivity.this,GunShop.class);
        startActivity(intent);
        finish();
    }
    public void initView()

    {  gunspinner=findViewById(R.id.gunspinner);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,me.GunsToString());
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);;
        gunspinner.setAdapter(arrayAdapter);
        gunspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedgun=me.getGuns().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ProgressBar hpProgressBar=findViewById(R.id.progressBar);
        hpProgressBar.setProgress(me.getHp());
        yearsTextView=findViewById(R.id.yearsTextView);
        yearsTextView.setText(""+me.getAge());
        profile=findViewById(R.id.imageView3);
        date=findViewById(R.id.date);
        date.setText(me.getDate().toString());
        cashMoney=findViewById(R.id.moneyCash);
        //cashMoney.setText(""+me.getCashMoney());
        name=findViewById(R.id.name);
        name.setText(me.getName());
        inventory=findViewById(R.id.inventory);
        //ArrayAdapter<Drug> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,me.getInventory());
        //inventory.setAdapter(adapter);
        updateinventory();
        inventory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sellPopup(me.getInventory().get(position));

            }
        });
    }

    @Override
    public void onBackPressed() {

    }

    public void continuePopUp(){
        dialogbuilder=new AlertDialog.Builder(this);
        final View popupview=getLayoutInflater().inflate(R.layout.continue_popup,null);
        dialogbuilder.setView(popupview);
        dialog=dialogbuilder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Button continuebtn=popupview.findViewById(R.id.continueButton);
        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                storyPopUp();
            }
        });

    }
    public void storyPopUp()
    {
        dialogbuilder=new AlertDialog.Builder(this);
        final View popupview=getLayoutInflater().inflate(R.layout.story_popup,null);
        dialogbuilder.setView(popupview);
        dialog=dialogbuilder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Button continuebtn=popupview.findViewById(R.id.continuestory);
        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                nicknamePopup();
            }
        });
    }
    public void nicknamePopup()
    {
        dialogbuilder=new AlertDialog.Builder(this);
        final View popupview=getLayoutInflater().inflate(R.layout.nickname_popup,null);
        dialogbuilder.setView(popupview);
        dialog=dialogbuilder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Button continuebtn=popupview.findViewById(R.id.continuenick);
        final EditText nickEditText=popupview.findViewById(R.id.editTextNick);
        final TextView warning=popupview.findViewById(R.id.warning);
        warning.setVisibility(View.GONE);
        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nickEditText.getText().toString().equals(""))
                    warning.setVisibility(View.VISIBLE);
                else
                {
                    SharedPreferences sharedPreferences=getSharedPreferences("call",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("savenik",nickEditText.getText().toString());
                    editor.commit();
                    me.setName(nickEditText.getText().toString());
                    updateinventory();
                    warning.setVisibility(View.GONE);
                    dialog.dismiss();
                    savePreferences();
                }


            }
        });
    }
  /* public void profilePopup(){
        dialogbuilder=new AlertDialog.Builder(this);
        final View popupview=getLayoutInflater().inflate(R.layout.profile_popup,null);
        dialogbuilder.setView(popupview);
        dialog=dialogbuilder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Button continuebtn=popupview.findViewById(R.id.continueprofile);
        final ImageView choiceImage=popupview.findViewById(R.id.choiceImage);
        continuebtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                profile.setImageResource(choiceImage);
                updateinventory();
                dialog.dismiss();

            }
        });

}*/
    public void imageSelect(View view)
    {

    }
    public void sellPopup(final Drug drug)
    {
        dialogbuilder=new AlertDialog.Builder(this);
        final View popupview=getLayoutInflater().inflate(R.layout.sellpopup,null);
        sellamount=(EditText)popupview.findViewById(R.id.sellamount);
        sellamount.setText(""+drug.getQuantity());
        closebtn=(Button) popupview.findViewById(R.id.closebtn);
        sellbtn=(Button)popupview.findViewById(R.id.sellbtn);
        dialogbuilder.setView(popupview);
        dialog=dialogbuilder.create();
        dialog.show();
        closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        sellbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!sellamount.getText().toString().equals(""))
                {
                    int x=Integer.parseInt(sellamount.getText().toString());
                    if(me.sellDrug(drug,x,market.getDrugByName(drug).getPrice())&& !sellamount.getText().toString().equals(""))
                    {
                        updateinventory();
                        market.addStock(drug,x);
                        Toast.makeText(MainActivity.this, ""+x+" "+drug.getName()+" sold.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                    else
                        Toast.makeText(MainActivity.this, "You can't do that.", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this, "You can't do that.", Toast.LENGTH_SHORT).show();


            }
        });
    }
    public void updateinventory()
    {

        ArrayAdapter<Drug> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,me.getInventory());
        inventory.setAdapter(adapter);
        String cash=String.format("%,d",me.getCashMoney());
        cashMoney.setText(cash);
        name.setText(me.getName());
        yearsTextView.setText(""+me.getAge());
        ProgressBar hpbar=findViewById(R.id.progressBar);
        hpbar.setProgress(me.getHp());


    }


    public void savePreferences()
    {
        Gson gson=new Gson();
        String json;
        SharedPreferences sharedPreferences=getSharedPreferences("mySaves",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        /*//save cash
        editor.putLong("cash_key",me.getCashMoney());
        //save inventory
        json=gson.toJson(me.getInventory());
        editor.putString("inventory_key",json);*/
        //save businesses
        json=gson.toJson(businesses);
        editor.putString("businesses_key",json);
        //save market
        json=gson.toJson(market);
        editor.putString("market_key",json);
        //save you
        json=gson.toJson(me);
        editor.putString("you_key",json);
        //save date
        editor.putInt("year_key",me.getDate().getYear());
        editor.putInt("month_key",me.getDate().getMonthValue());
        editor.putInt("day_key",me.getDate().getDayOfMonth());
        //save deal
        json=gson.toJson(mydeal);
        editor.putString("deal_key",json);
        editor.commit();
        Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
    }
    public void openMarket(View view)
    {
        Intent intent=new Intent(MainActivity.this,MarketActivity.class);
        startActivity(intent);
        finish();
    }
    public void openDeals(View view)
    {
        Intent intent=new Intent(MainActivity.this,DealsActivity.class);
        startActivity(intent);
        finish();
    }
    public void openBusinesses(View view)
    {
        Intent intent=new Intent(MainActivity.this,BusinessActivity.class);
        startActivity(intent);
        finish();
    }
    public static You getMe()
    {
        return me;
    }

    public static Market getMarket() {
        return market;
    }
    public  void nextMonth(View view)
    {
        market.randomizeMarket();
        me.randomBtcPrice();
        me.setDate(me.getDate().plusMonths(1));
        date.setText(me.getDate().toString());
        me.setAge(25+me.getDate().getYear()-2019);
        for(Business b:businesses)
        {
            me.buyDrug(b.prodDrug(),b.getProduction());
        }
        updateinventory();
        mydeal.dealOfTheDay();
        if(me.getAge()>=75)
        {
           gameOver();
        }
        else
        {
            randomEvent();
        }


    }
    private void gameOver()
    {
        game_over=true;
        onCreateOptionsMenu(null);
        setTheme(R.style.AppThemeNoActionBar);
        setContentView(R.layout.gameover_layout);
        TextView years=findViewById(R.id.yearsAsBarontextview);
        years.setText("You survived "+(me.getAge()-25)+" years as a drug baron.");
        long totalValue=me.getCashMoney()+(long)(me.getBtc()*20000);
        TextView assets=findViewById(R.id.totalvalue);
        if (totalValue>999999999)
            assets.setText("You raised $"+((double)totalValue/1000000000)+"B.");

        else if(totalValue>999999)
            assets.setText("You raised $"+((double)totalValue/1000000)+"M.");
        else
            assets.setText("You raised $"+((double)totalValue/1000)+"K.");

        TextView rivalsKilled=findViewById(R.id.rivalskilled);
        rivalsKilled.setText("You killed " + me.getRivalsKilled() + " rivals  and " + me.getAgentsKilled() + " DEA agents.");


    }
    public static boolean buybusiness(Business b){
        if(b.getPrice()<=me.getCashMoney())
        {
            me.setCashMoney(me.getCashMoney()-b.getPrice());
            businesses.add(b);
            return true;
        }
        return false;

    }
    public static int havethisbusiness(Business business)
    {
        for (Business b: businesses)
        {
            if(b.getName().equals(business.getName()))
               return businesses.indexOf(b);
        }
        return  -1;
    }
    public static boolean upgradebusiness(Business business){
        for(Business b:businesses)
        {
            if(b.getName().equals(business.getName()))
            {
                if(b.getUpgradePriceLvl()<=me.getCashMoney())
                {
                    me.setCashMoney(me.getCashMoney()-b.getUpgradePriceLvl());
                    b.upgradeLvl();
                    return true;
                }

            }

        }
        return false;
    }
    public void clearSaves(View view){

       SharedPreferences sharedPreferences=getSharedPreferences("mySaves",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
    public void restartGame(View view)
    {
        me=new You();
        businesses=new ArrayList<>();
        market=new Market();
        mydeal=new Deal();
        setContentView(R.layout.activity_main);
        clearSaves(null);
        getSaves();
        this.game_over=false;
    }
    private void randomEvent()
    {
        Random random=new Random();
        int randomValue=random.nextInt(100);
        if(randomValue<5)
        {
            fightPopUp("police");
        }
        if(randomValue>94)
        {
           fightPopUp("rivals");
        }

    }
    private void fightPopUp(final String enemy)
    {
        dialogbuilder=new AlertDialog.Builder(this);
        View popView=getLayoutInflater().inflate(R.layout.fightpopup_layout,null);
        dialogbuilder.setView(popView);
        dialog = dialogbuilder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        final TextView storyTextView=popView.findViewById(R.id.storyTextView);
        if(enemy.equals("police"))
        {
            storyTextView.setText("The DEA has entered your warehouse and wants to confiscate everything.");
            MediaPlayer mediaPlayer=MediaPlayer.create(MainActivity.this,R.raw.policesirne);
            mediaPlayer.start();
        }

        else
        {
            storyTextView.setText("Your rivals sent thieves to steal your money.");
            MediaPlayer mediaPlayer=MediaPlayer.create(MainActivity.this,R.raw.m4soundeffect);
            mediaPlayer.start();
        }
        final String selectedgunname=selectedgun.getName();
        final MediaPlayer[] mediaPlayer = new MediaPlayer[1];
        final Random random=new Random();
        final int totalEnemies=random.nextInt(5)+1;
        final TextView enemiesTextView=popView.findViewById(R.id.totalEnemies);
        enemiesTextView.setText(enemiesTextView.getText().toString()+totalEnemies);
        final TextView yourhp=popView.findViewById(R.id.hpTextView);
        final TextView enemyhpTextView=popView.findViewById(R.id.enemyHp);
        final int[] enemyhp = {50 * totalEnemies};
        yourhp.setText(""+me.getHp());
        enemyhpTextView.setText(""+ enemyhp[0]);
        final Button atackButton=popView.findViewById(R.id.atackButton);
        final Button surrenderButton=popView.findViewById(R.id.surrederButton);
        final Button okbutton=popView.findViewById(R.id.okButton);
        surrenderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(enemy.equals("police"))
                {
                    if(me.getInventory().isEmpty())
                    {
                        storyTextView.setText("DEA did not find anything illegal in your warehouse.");
                        updateinventory();
                    }
                    else
                    {
                        storyTextView.setText("The DEA confiscated all your products.");
                        me.setInventory(new ArrayList<Drug>());
                        updateinventory();
                    }
                    surrenderButton.setVisibility(View.GONE);
                    atackButton.setVisibility(View.GONE);
                    okbutton.setVisibility(View.VISIBLE);
                }
                else
                {
                    if(me.getCashMoney()==0)
                    {
                        storyTextView.setText("The thieves found no money.");
                        updateinventory();
                    }
                    else
                    {
                        storyTextView.setText("The thieves stole all your money and left.");
                        me.setCashMoney(0);
                        updateinventory();
                    }
                    surrenderButton.setVisibility(View.GONE);
                    atackButton.setVisibility(View.GONE);
                    okbutton.setVisibility(View.VISIBLE);
                }
            }
        });
        okbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (me.getHp()==0)
                    gameOver();
            }
        });
        atackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int chance=random.nextInt(3);
                if(chance==0)
                    storyTextView.setText("You attacked them but you missed.");
                else
                {

                    if(selectedgunname.equals("Knife"))
                        mediaPlayer[0] =MediaPlayer.create(MainActivity.this,R.raw.knifesoundeffect);
                    if(selectedgunname.equals("Pistol"))
                        mediaPlayer[0] =MediaPlayer.create(MainActivity.this,R.raw.pistolsoundeffect);
                    if(selectedgunname.equals("MP5"))
                        mediaPlayer[0] =MediaPlayer.create(MainActivity.this,R.raw.mp5soundeffect);
                    if(selectedgunname.equals("MP5-A4"))
                        mediaPlayer[0] =MediaPlayer.create(MainActivity.this,R.raw.mp5a4soundeffect);
                    if(selectedgunname.equals("MP5-SD"))
                        mediaPlayer[0] =MediaPlayer.create(MainActivity.this,R.raw.mp5sdsoundeffect);
                    if(selectedgunname.equals("AK-47"))
                        mediaPlayer[0] =MediaPlayer.create(MainActivity.this,R.raw.ak47soundeffect);
                    if(selectedgunname.equals("Auto Sniper"))
                        mediaPlayer[0] =MediaPlayer.create(MainActivity.this,R.raw.autosnipersoundeffect);
                         mediaPlayer[0].start();

                    storyTextView.setText("You attacked them with "+selectedgun.getDamage() +" damage.");
                    enemyhp[0] -=selectedgun.getDamage();
                    enemyhpTextView.setText(""+ enemyhp[0]);
                    if (enemyhp[0]<0)
                        enemyhpTextView.setText(""+0);
                }
                if(enemyhp[0]<=0)
                {
                    if (enemy.equals("police"))
                    {
                        storyTextView.setText("You killed all the DEA agents.");
                        me.incrementAgentsKilled(totalEnemies);
                    }

                    else
                    {
                        storyTextView.setText("You killed all the thieves.");
                        me.incrementRivalsKilled(totalEnemies);
                    }

                    enemiesTextView.setText("Total enemies: 0");
                    surrenderButton.setVisibility(View.GONE);
                    atackButton.setVisibility(View.GONE);
                    okbutton.setVisibility(View.VISIBLE);
                    updateinventory();
                }
                else
                {
                    chance=random.nextInt(3);
                    if (chance==0)
                        storyTextView.setText(storyTextView.getText().toString()+"They attacked you but they missed you.");
                    else
                    {
                        storyTextView.setText(storyTextView.getText().toString()+"They attacked you with 25 damage.");
                        me.setHp(me.getHp()-25);
                        yourhp.setText(""+me.getHp());
                    }
                    if(me.getHp()<=0)
                    {
                        storyTextView.setText("You are dead");
                        surrenderButton.setVisibility(View.GONE);
                        atackButton.setVisibility(View.GONE);
                        okbutton.setVisibility(View.VISIBLE);
                    }

                }



            }
        });

    }

}