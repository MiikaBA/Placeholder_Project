package com.example.placeholder_project.SportsDiary.Classes;

import android.app.Activity;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ActivitySingleton {
    private List<SportsActivity> activities;
    private static final ActivitySingleton ourInstance = new ActivitySingleton();

    public static ActivitySingleton getInstance() {
        return ourInstance;
    }

    private ActivitySingleton() {
        activities = new ArrayList<>();

    }

    public List<SportsActivity> getActivities() {
        return activities;
    }

    public SportsActivity getActivity(int pos){
        return activities.get(pos);
    }

    public void reInitiateArray(List<SportsActivity> l){
        activities = new ArrayList<>();
        for(int i = 0; i < l.size(); i++){
            activities.add(new SportsActivity(l.get(i).getActType(),
                    l.get(i).getTimeSpent(),
                    l.get(i).getCalsBurnt(),
                    l.get(i).getDescription()));
        }
    }

    public void resetActivities(){
        activities = new ArrayList<>();
    }

    public void addActivity(String type, int time, int cals, String desc){
        int finalType;
        if(type.equals("running") || type.equals("Running")){
            finalType = 1;
        }else if(type.equals("weights") || type.equals("Weights")){
            finalType = 2;
        }else if(type.equals("Cycling") || type.equals("cycling")){
            finalType = 3;
        }else{finalType = 4;}

        activities.add(new SportsActivity(finalType, time, cals, desc));
    }


}
