package com.example.placeholder_project.SportsDiary;

public class SportsCounter {
    private int caloriesBurnt;
    private int timeSpent;

    public SportsCounter(int timeSpent, int caloriesBurnt){
        this.timeSpent = timeSpent;
        this.caloriesBurnt = caloriesBurnt;
    }

    public void addTime(int timeToAdd){
        timeSpent = timeSpent + timeToAdd;
    }

    public void addCalories(int caloriesToAdd){
        caloriesBurnt = caloriesBurnt + caloriesToAdd;
    }

    //returns value.
    public int getTimeSpent(){
        return timeSpent;
    }

    public int getCaloriesBurnt(){
        return caloriesBurnt;
    }
}