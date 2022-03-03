package com.example.placeholder_project;

import java.util.ArrayList;
import java.util.List;

public class ActivitySingleton {
    private ArrayList<SportsActivity> activities;
    private static final ActivitySingleton ourInstance = new ActivitySingleton();

    public static ActivitySingleton getInstance() {
        return ourInstance;
    }

    private ActivitySingleton() { //Initiates President list.
        activities = new ArrayList<>();
        activities.add(new SportsActivity(1, 34, 456, "ripsakka lenkki XDDDDD"));

    }

    public List<SportsActivity> getActivities() {
        return activities;
    }

    public SportsActivity getPresident(int pos){
        return activities.get(pos);
    }
}
