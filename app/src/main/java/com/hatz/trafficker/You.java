package com.hatz.trafficker;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class You {
    private String name;
    private int age;
    private long cashMoney;
    private int hp;
    private int respect;
    private int imageId;
    private ArrayList<Drug> inventory;
    private ArrayList<Gun> guns;
    private LocalDate date;
    private double btc;
    private int btcPrice;
    private int agentsKilled;
    private int rivalsKilled;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public You() {
        btc=0;
        randomBtcPrice();
        imageId=0;
        name="Antonio Juarez";
        age=25;
        cashMoney=1750;
        hp=100;
        respect=0;
        guns=new ArrayList<>();
        guns.add(new Gun("Knife",10,0));
        inventory=new ArrayList<>();
        inventory.add(new Drug("Weed",3,0));
        date=LocalDate.of(2019,1,19);
        agentsKilled=0;
        rivalsKilled=0;

    }

    public int getBtcPrice() {
        return btcPrice;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public double getBtc() {
        return btc;
    }

    public boolean cashToBtc(long cash)
    {
        if(cash<=cashMoney)
        {
            btc=btc+(float)cash/btcPrice;
            cashMoney-=cash;
            return true;
        }
        return  false;

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean btcToCash(double btc)
    {
        if(btc<=this.btc)
        {
            cashMoney+=btc*btcPrice;
            this.btc-=btc;
            return true;
        }
        return  false;


    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ArrayList<Drug> getInventory() {
        return inventory;
    }

    public String getName() {
        return name;
    }

    public void setCashMoney(long cashMoney) {
        this.cashMoney = cashMoney;
    }

    public boolean buyDrug(Drug drug, int amount)
    {
        for(Drug d: inventory)
        {
            if(drug.getPrice()*amount<=cashMoney && drug.getQuantity()>=amount && amount>0 && d.getName().equals(drug.getName()))
            {   int total=amount*drug.getPrice()+d.getQuantity()*d.getPrice();
               d.setQuantity(d.getQuantity()+amount);
                d.setPrice(total/d.getQuantity());
               cashMoney=cashMoney-drug.getPrice()*amount;
               return  true;
            }


        }
        if(amount >0 && drug.getPrice()*amount<=cashMoney && drug.getQuantity()>=amount){
         Drug drugcopy=new Drug(drug);
         drugcopy.setQuantity(amount);
        inventory.add(drugcopy);
        cashMoney=cashMoney-drugcopy.getPrice()*amount;

        return true;
    }
    else
        return false;

    }
    public boolean sellDrug(Drug drg,int amount,int price)
    {
        if(amount<=0)
            return false;
        Drug drug=new Drug(drg);
        int index=0;
        for(Drug d:inventory)
        {
            if(d.getName().equals(drug.getName()))
            {    if(d.getQuantity()==amount)
                 {
                     inventory.remove(index);
                     cashMoney+=amount*price;
                     return true;
                 }

                else if(amount<d.getQuantity())
                    {
                        d.setQuantity(d.getQuantity()-amount);
                        cashMoney+=amount*price;
                        return true;
                    }

                return false;
            }

            index++;
        }
        return false;
    }
    public boolean sellDrugToDeals(Drug drg,int amount,long profit)
    {
        if(amount<=0)
            return false;
        Drug drug=new Drug(drg);
        int index=0;
        for(Drug d:inventory)
        {
            if(d.getName().equals(drug.getName()))
            {    if(d.getQuantity()==amount)
            {
                inventory.remove(index);
                cashMoney+=profit;
                return true;
            }

            else if(amount<d.getQuantity())
            {
                d.setQuantity(d.getQuantity()-amount);
                cashMoney+=profit;
                return true;
            }

                return false;
            }

            index++;
        }
        return false;
    }
    public boolean canSellThisDrug(String drugName,int amount)
    {
        for(Drug d: inventory)
            if(d.getName().equals(drugName))
            {
                return d.getQuantity() >= amount;
            }
        return false;
    }
    public boolean buyGun(Gun gun)
    {
        if(gun.getPrice()<=cashMoney)
        {
            cashMoney-=gun.getPrice();
            guns.add(gun);
            return true;
        }
        return false;
    }
    public void setInventory(ArrayList<Drug> inventory) {
        this.inventory = inventory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCashMoney() {
        return cashMoney;
    }

    public ArrayList<Gun> getGuns() {
        return guns;
    }
    public String[] GunsToString()
    {
        String[] gunstring= new String[guns.size()];
        for (int i =0;i<guns.size();i++)
        {
            gunstring[i]=guns.get(i).getName();
        }
        return  gunstring;
    }
    public boolean heal()
    {
        if(cashMoney>=(100-hp)*1000)
        {
            hp=100;
            cashMoney-=(100-hp)*100;
            return  true;
        }
            return  false;
    }

    public int getAgentsKilled() {
        return agentsKilled;
    }

    public int getRivalsKilled() {
        return rivalsKilled;
    }
    public void incrementAgentsKilled(int nr)
    {
        agentsKilled+=nr;
    }
    public  void incrementRivalsKilled(int nr)
    {
        rivalsKilled=+nr;
    }
    public void randomBtcPrice()
    {
        Random random=new Random();
        btcPrice=19000+random.nextInt(6000);
    }
}
