package com.example.placeholder_project.SportsDiary.Classes;

import java.util.ArrayList;
import java.util.List;

public class ActivitySingleton {
    private ArrayList<SportsActivity> activities;
    private static final ActivitySingleton ourInstance = new ActivitySingleton();

    public static ActivitySingleton getInstance() {
        return ourInstance;
    }

    private ActivitySingleton() {
        activities = new ArrayList<>();
        activities.add(new SportsActivity(1, 34, 456, "ripsakka lenkki XDDDDD"));

    }

    public List<SportsActivity> getActivities() {
        return activities;
    }

    public SportsActivity getActivity(int pos){
        return activities.get(pos);
    }

    public void addActivity(String type, int time, int cals, String desc){
        int finalType;
        if(type.equals("juoksu") || type.equals("Juoksu")){
            finalType = 1;
        }else if(type.equals("lihaskunto") || type.equals("Lihaskunto")){
            finalType = 2;
        }else if(type.equals("pyöräily") || type.equals("Pyöräily")){
            finalType = 3;
        }else{finalType = 4;}

        activities.add(new SportsActivity(finalType, time, cals, desc));
    }
}
