package com.example.placeholder_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FoodDiary extends AppCompatActivity {
    private TextView calorieAllView, calorieTodayView;
    private FoodCounter foodCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_diary);

        int tanaan = 176;
        int kaikki = 15682;
        foodCounter = new FoodCounter(tanaan, kaikki);
        Update();
    }

    public void addCalories(View v){
        foodCounter.plus(842);
        Update();
    }

    public void Update(){
        calorieAllView = findViewById(R.id.calAllTextView);
        calorieTodayView = findViewById(R.id.calTodayTextView);
        calorieAllView.setText(Integer.toString(foodCounter.getAllTimeValue()));
        calorieTodayView.setText(Integer.toString(foodCounter.getTodayValue()));
    }

    public void goNext(View v){
        if(v == findViewById(R.id.sportsButton)){
            Intent food = new Intent(FoodDiary.this, SportsDiary.class);
            startActivity(food);
        }else if(v == findViewById(R.id.breathingButton)){
            Intent breath = new Intent(FoodDiary.this, BreathingExcercise.class);
            startActivity(breath);
        }
    }
}