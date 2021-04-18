package com.silvertak.androidreview;

public class Car {

    String company;
    String name;
    int price;
    double fuelEfficiency;
    boolean isDisel;
    boolean isGasoline;
    boolean isLpg;
    boolean isHybrid;

    public Car(String company, String name, int price, double fuelEfficiency, boolean isDisel, boolean isGasoline, boolean isLpg, boolean isHybrid) {
        this.company = company;
        this.name = name;
        this.price = price;
        this.fuelEfficiency = fuelEfficiency;
        this.isDisel = isDisel;
        this.isGasoline = isGasoline;
        this.isLpg = isLpg;
        this.isHybrid = isHybrid;
    }

    public String getCompany() {
        return company;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public double getFuelEfficiency() {
        return fuelEfficiency;
    }

    public boolean isDisel() {
        return isDisel;
    }

    public boolean isGasoline() {
        return isGasoline;
    }

    public boolean isLpg() {
        return isLpg;
    }

    public boolean isHybrid() {
        return isHybrid;
    }

    @Override
    public String toString() {
        return "Car{" +
                "company='" + company + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", fuelEfficiency=" + fuelEfficiency +
                ", isDisel=" + isDisel +
                ", isGasoline=" + isGasoline +
                ", isLpg=" + isLpg +
                ", isHybrid=" + isHybrid +
                '}';
    }

}