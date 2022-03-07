package com.example.placeholder_project.SportsDiary.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.placeholder_project.BreathingExcercise;
import com.example.placeholder_project.FoodDiary;
import com.example.placeholder_project.R;
import com.example.placeholder_project.SportsDiary.Classes.ActivitySingleton;
import com.example.placeholder_project.SportsDiary.Classes.SportsActivity;
import com.example.placeholder_project.SportsDiary.Classes.SportsCounter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SportsDiary extends AppCompatActivity {

    private SportsCounter SC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_diary);

        updateUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    public void updateUI() {
        SharedPreferences counterVals = getSharedPreferences("CounterVals" , Activity.MODE_PRIVATE);
        int TS = counterVals.getInt("TimeVal", 0);
        int CB = counterVals.getInt("CalorieVal", 0);
        if(TS > 0 || CB > 0){
            Button reset = findViewById(R.id.diaryResetButton);
            reset.setVisibility(View.VISIBLE);
        }else{
            Button reset = findViewById(R.id.diaryResetButton);
            reset.setVisibility(View.INVISIBLE);
        }
        SC = new SportsCounter(TS, CB);

        TextView calTV = findViewById(R.id.calTextView);
        calTV.setText(Integer.toString(SC.getCaloriesBurnt()) + " kcal.");

        TextView timeTV = findViewById(R.id.timeTextView);
        timeTV.setText(Integer.toString(SC.getTimeSpent()) + " min.");
    }

    public void resetDiary(View v){
        SC = new SportsCounter(0, 0);
        SharedPreferences counterVals = getSharedPreferences("CounterVals" , Activity.MODE_PRIVATE);
        int TS = 0;
        int CB = 0;
        SharedPreferences.Editor valEditor = counterVals.edit();
        valEditor.putInt("TimeVal", TS);
        valEditor.putInt("CalorieVal", CB);
        valEditor.apply();

        ActivitySingleton.getInstance().resetActivities();
        List<SportsActivity> activities = ActivitySingleton.getInstance().getActivities();
        SharedPreferences Prefs = getSharedPreferences("ActivityList", MODE_PRIVATE);
        SharedPreferences.Editor listEditor = Prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(activities);
        listEditor.putString("Actlist", json);
        listEditor.apply();

        updateUI();
    }

    public void goNext(View v){ //haha lol xd
        if(v == findViewById(R.id.foodButton)){
            Intent food = new Intent(SportsDiary.this, FoodDiary.class);
            food.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivityIfNeeded(food, 0);
        }else if(v == findViewById(R.id.breathingButton)){
            Intent breath = new Intent(SportsDiary.this, BreathingExcercise.class);
            breath.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivityIfNeeded(breath, 0);
        }else if(v == findViewById(R.id.inspectionButton)){
            Intent inspection = new Intent(SportsDiary.this, ActivityInspection.class);
            startActivity(inspection);
        }else if(v == findViewById(R.id.additionButton)){
            Intent addition = new Intent(SportsDiary.this, ActivityAddition.class);
            startActivity(addition);
        }
    }
}