package com.hatz.trafficker;

import java.util.ArrayList;
import java.util.Random;

public class Market {
    ArrayList<Drug>drugsmarket;
    public Market(){
        drugsmarket=new ArrayList<>();
        drugsmarket.add(new Drug("Cocaine",0,0));
        drugsmarket.add(new Drug("Crack",0,0));
        drugsmarket.add(new Drug("Ecstasy",0,0));
        drugsmarket.add(new Drug("Hashish",0,0));
        drugsmarket.add(new Drug("Heroin",0,0));
        drugsmarket.add(new Drug("LSD",0,0));
        drugsmarket.add(new Drug("Ice",0,0));
        drugsmarket.add(new Drug("MDA",0,0));
        drugsmarket.add(new Drug("Opium",0,0));
        drugsmarket.add(new Drug("PCP",0,0));
        drugsmarket.add(new Drug("Weed",0,0));
        drugsmarket.add(new Drug("Special K",0,0));
        drugsmarket.add(new Drug("Speed",0,0));
        randomizeMarket();
    }
    public void addStock(Drug drug,int amount)
    {
        for (Drug d:drugsmarket)
        {
            if(d.getName().equals(drug.getName()))
                d.setQuantity(d.getQuantity()+amount);
        }
    }
    public Drug getDrugByName(Drug drug)
    {
        for(Drug d:drugsmarket)
        {
            if(d.getName().equals(drug.getName()))
                return d;
        }
        return null;
    }
    public void  randomizeMarket(){
        Random rd=new Random();
        for (Drug d:drugsmarket)
        {   if(d.getName().equals("Cocaine"))
            d.setPrice(7000+rd.nextInt(3000));
            if(d.getName().equals("Ecstasy"))
                d.setPrice(1000+rd.nextInt(1500));
            if(d.getName().equals("Crack"))
                d.setPrice(5000+rd.nextInt(2000));
            if(d.getName().equals("Hashish"))
                d.setPrice(500+rd.nextInt(1500));
            if(d.getName().equals("Heroin"))
                d.setPrice(4000+rd.nextInt(2000));
            if(d.getName().equals("Ice"))
                d.setPrice(4000+rd.nextInt(2000));
            if(d.getName().equals("MDA"))
                d.setPrice(500+rd.nextInt(1000));
            if(d.getName().equals("LSD"))
                d.setPrice(500+rd.nextInt(1200));
            if(d.getName().equals("Opium"))
                d.setPrice(300+rd.nextInt(300));
            if(d.getName().equals("PCP"))
                d.setPrice(250+rd.nextInt(250));
            if(d.getName().equals("Weed"))
                d.setPrice(120+rd.nextInt(80));
            if(d.getName().equals("Special K"))
                d.setPrice(100+rd.nextInt(90));
            if(d.getName().equals("Speed"))
                d.setPrice(2000+rd.nextInt(1500));

            d.setQuantity(1+rd.nextInt(100));

        }
    }
}
