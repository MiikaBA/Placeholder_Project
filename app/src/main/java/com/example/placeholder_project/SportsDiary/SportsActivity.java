package com.example.placeholder_project.SportsDiary;

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

    @Override
    public String toString(){
        if(actType == 1){
            return "Juoksu" + " Päivämäärä";
        }else if(actType == 2){
            return "Lihaskunto" + " Päivämäärä";
        }else if(actType == 3){
            return "Pyöräily" + " Päivämäärä";
        }else{
            return "Tuntematon aktiviteetti";
        }
    }
}
