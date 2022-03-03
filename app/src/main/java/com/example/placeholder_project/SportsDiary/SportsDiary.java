package com.example.placeholder_project.SportsDiary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.placeholder_project.BreathingExcercise;
import com.example.placeholder_project.FoodDiary;
import com.example.placeholder_project.R;

public class SportsDiary extends AppCompatActivity {

    private SportsCounter SC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_diary);

        //TODO korvaa preferensseistä haetulla tiedolla.
        SharedPreferences counterVals = getSharedPreferences("CounterVals" , Activity.MODE_PRIVATE);
        int TS = counterVals.getInt("TimeVal", 1);
        int CB = counterVals.getInt("CalorieVal", 2);

        SC = new SportsCounter(TS, CB);
        updateUI();
    }
    public void updateUI() {
        TextView calTV = findViewById(R.id.calTextView);
        calTV.setText(Integer.toString(SC.getCaloriesBurnt()) + " kcal.");

        TextView timeTV = findViewById(R.id.timeTextView);
        timeTV.setText(Integer.toString(SC.getTimeSpent()) + " min.");
    }

    public void goNext(View v){ //haha lol xd
        if(v == findViewById(R.id.foodButton)){
            Intent food = new Intent(SportsDiary.this, FoodDiary.class);
            startActivity(food);
        }else if(v == findViewById(R.id.breathingButton)){
            Intent breath = new Intent(SportsDiary.this, BreathingExcercise.class);
            startActivity(breath);
        }
    }
}