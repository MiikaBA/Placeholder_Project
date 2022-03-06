package com.example.placeholder_project;

public class FoodCounter {

    private int today, all;

    public FoodCounter(int today, int all) {
        this.today = today; this.all = all;
    }

    public void plus(int x){
        today += x;
        all += x;

    }

    public int getAllTimeValue(){
        return all;
    }

    public int getTodayValue(){
        return today;
    }
}
