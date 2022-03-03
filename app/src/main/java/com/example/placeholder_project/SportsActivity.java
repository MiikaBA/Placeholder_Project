package com.example.placeholder_project;

public class SportsActivity {

    private int actType;
    private int timeSpent;
    private int calsBurnt;
    private String description;

    public SportsActivity(int type, int time, int cals, String desc){
        actType = type;
        timeSpent = time;
        calsBurnt = cals;
        description = desc;
    }

    public int getTimeSpent(){
        return timeSpent;
    }

    public int getActType() {
        return actType;
    }

    public int getCalsBurnt() {
        return calsBurnt;
    }

    public String getDescription() {
        return description;
    }
}
