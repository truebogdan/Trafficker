package com.hatz.trafficker;

import androidx.annotation.Nullable;

public class Gun {
    private String name;
    private int damage;
    private int price;

    public Gun(String name, int damage,int price) {
        this.name = name;
        this.damage = damage;
        this.price=price;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return ((Gun) obj).getName().equals(name);
    }

}
