package com.hatz.trafficker;

import androidx.annotation.NonNull;

public class Drug {
    private String name;
    private int quantity;
    private int price;

    public Drug(String name, int quantity, int price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
    public Drug(Drug drug)
    {
        this.name=drug.getName();
        this.quantity=drug.getQuantity();
        this.price=drug.getPrice();
    }


    @NonNull
    @Override
    public String toString() {
        if(name.equals("Cocaine"))
            return name+"                  "+quantity+"                                    "+price+"$";
        if(name.equals("Crack"))
            return name+"                      "+quantity+"                                    "+price+"$";
        if(name.equals("Hashish"))
            return name+"                 "+quantity+"                                    "+price+"$";
        if(name.equals("Ecstasy"))
            return name+"                  "+quantity+"                                    "+price+"$";
        if(name.equals("Heroin"))
            return name+"                    "+quantity+"                                 "+price+"$";
        if(name.equals("Ice"))
            return name+"                          "+quantity+"                                    "+price+"$";
        if(name.equals("MDA"))
            return name+"                       "+quantity+"                                    "+price+"$";
        if(name.equals("LSD"))
            return name+"                         "+quantity+"                                    "+price+"$";
        if(name.equals("Opium"))
            return name+"                    "+quantity+"                                    "+price+"$";
        if(name.equals("PCP"))
            return name+"                        "+quantity+"                                    "+price+"$";
        if(name.equals("Weed"))
            return name+"                      "+quantity+"                                    "+price+"$";
        if(name.equals("Special K"))
            return name+"                "+quantity+"                                    "+price+"$";
        if(name.equals("Speed"))
            return name+"                      "+quantity+"                                    "+price+"$";
        return name+" "+quantity+"       "+price+"$";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
