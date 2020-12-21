package com.hatz.trafficker;



import java.util.Random;

public class Deal {
    private boolean available=true;
    private int country;
    private  int productName;
    private  int price;
    private  int quantity;
    private  int vehicle;
    private  int protection;
    private  int bribe;
    private  long transportCost;
    private long productValue;
    private long profit;
    public  Deal(){
        dealOfTheDay();
    }
    public void dealOfTheDay()
    {
        available=true;
        Random random=new Random();
        country=random.nextInt(5);
        productName=random.nextInt(5);
        if(productName==0)
        price=300+random.nextInt(200);
        if(productName==1)
        price=800+random.nextInt(400);
        if(productName==2)
        price=1000+random.nextInt(2000);
        if (productName==3)
        price=7000+random.nextInt(3000);
        if (productName==4)
        price=12000+random.nextInt(5000);
        quantity=random.nextInt(10)*10+300;
        vehicle=1;
        protection=0;
        bribe=0;
        calculateCosts();
    }
    private void calculateCosts()
    {
        productValue=quantity*price;
        if(vehicle==0)
            transportCost=0;
        if(vehicle==1)
            transportCost=30000;
        if (vehicle==2)
            transportCost=100000;
        if (vehicle==3)
            transportCost=500000;
        transportCost+=bribe*50000+protection*60000;
        profit=productValue-transportCost;
    }
    public String successRate()
    {
        Random random=new Random();
        if(bribe==0)
        {
            if(random.nextInt(9)>4) // 50% to lose for bribe=0
                return "Busted";
        }
        if(bribe==1)
        {
            if(random.nextInt(9)>6) // 30% to lose for bribe=1
                return "Busted";
        }
        if(bribe==2)
        {
            if(random.nextInt(9)>8) // 10% to lose for bribe=2
                return "Busted";
        }



        if(protection==0)
        {
            if (random.nextInt(9)>4) // 50% to lose for protection=0
                return "Robbed";
        }
        if (protection == 1)
        {
            if(random.nextInt(9)>7) //20% to lose for protection =1
                return "Robbed";
        }
        return "Success";
    }


    public int getCountry() {
        return country;
    }

    public int getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getVehicle() {
        return vehicle;
    }

    public int getProtection() {
        return protection;
    }

    public int getBribe() {
        return bribe;
    }

    public long getTransportCost() {
        return transportCost;
    }

    public long getProductValue() {
        return productValue;
    }

    public long getProfit() {
        return profit;
    }

    public void setVehicle(int vehicle) {
        this.vehicle = vehicle;
        calculateCosts();
    }

    public void setProtection(int protection) {

        this.protection = protection;
        calculateCosts();

    }

    public void setBribe(int bribe) {
        this.bribe = bribe;
        calculateCosts();

    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
