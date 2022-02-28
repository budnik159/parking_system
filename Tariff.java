package com.parking;

import java.util.ArrayList;

public class Tariff {
    //TODO: вернуть два поля Минуты, Цена
    private final int time;
    private final int cost;

    public Tariff(int time, int cost) {
        this.time = time;
        this.cost = cost;
    }

    public int getTime() {
        return time;
    }

    public int getCost() {
        return cost;
    }

   /* public int getMaxTime(){
        return time.get(time.size() - 1);
    }

    public int getMaxCost(){
        return cost.get(cost.size() - 1);
    }*/

    /*public int getFreeLeaveTime(){
        return time.get(0);
    }*/
}
