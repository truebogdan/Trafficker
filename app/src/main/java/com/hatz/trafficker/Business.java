package com.hatz.trafficker;

public class Business {
    private boolean locked;
    private String name;
    private int price;
    private String drugname;
    private int level;
    private int production;
    private int upgradePriceLvl;

    public Business(String name, int price, String drugname,int upgradePriceLvl,int production) {
        this.name = name;
        this.price = price;
        this.drugname = drugname;
        this.production=production;
        this.upgradePriceLvl=upgradePriceLvl;
        this.level=0;
    }

    public boolean isLocked() {
        return locked;
    }

    public void unlock()
    {
        this.locked =false;
    }
    public int getPrice() {
        return price;
    }

    public int getLevel() {
        return level;
    }

    public int getProduction() {
        return production;
    }

    public void upgradeLvl()
    {
        if(level==0)
        {
            level=1;
            production=production*2;
            upgradePriceLvl=upgradePriceLvl*2;
        }
        else if(level==1)
        {
            level=2;
            production=production*3;
            upgradePriceLvl=upgradePriceLvl*3;
        }
        else if (level==2)
        {
            level=3;
            production=production*4;
        }

    }

    public Drug prodDrug()
    {
        return new Drug(drugname,production*(level+1),0);
    }

    public int getUpgradePriceLvl() {
        return upgradePriceLvl;
    }

    public String getName() {
        return name;
    }
}
