package com.example.placeholder_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class FoodDiary extends AppCompatActivity {
    private FoodCounter foodCounter;

    // Creating a counter and checking if same day as
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_diary);
        checkDateMatch();
        Update();
    }

    //Checks if current date is same and creates foodCounters based off
    public void checkDateMatch(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Date d = new Date();
        String currentDate = DateFormat.format("dd-MM-yyyy ", d.getTime()).toString();
        String lastTimeSavedDate = preferences.getString("foodDate", "");
        if(!(preferences.contains("allTimeCalories"))){
            foodCounter = new FoodCounter(0, 0);
        } else if(lastTimeSavedDate.equals(currentDate)){
            foodCounter = new FoodCounter(preferences.getInt("todayCalories", 0), preferences.getInt("allTimeCalories", 0));
        } else{
            foodCounter = new FoodCounter(0, preferences.getInt("allTime", 0));
        }
    }

    // Takes user input and adds it to counter.
    public void addCalories(View v){
        EditText editTextNumber2 = findViewById(R.id.editTextNumber2);
        if(!(editTextNumber2.getText().toString().equals(""))){
            int cals = Integer.parseInt(editTextNumber2.getText().toString());
            foodCounter.plus(cals);
            Update();
        }
    }

    // Updates the activity UI
    public void Update(){
        TextView calorieAllView = findViewById(R.id.calAllTextView);
        TextView calorieTodayView = findViewById(R.id.calTodayTextView);
        EditText editTextNumber2 = findViewById(R.id.editTextNumber2);
        editTextNumber2.setText("");
        calorieAllView.setText(Integer.toString(foodCounter.getAllTimeValue()) + " kcal.");
        calorieTodayView.setText(Integer.toString(foodCounter.getTodayValue()) + " kcal.");
    }

    // Method for moving between activities when button is clicked
    public void goNext(View v){
        if(v == findViewById(R.id.sportsButton)){
            Intent food = new Intent(FoodDiary.this, SportsDiary.class);
            startActivity(food);
        }else if(v == findViewById(R.id.breathingButton)){
            Intent breath = new Intent(FoodDiary.this, BreathingExcercise.class);
            startActivity(breath);
        }
    }

    // Saving values and date to default preferences.
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        Date d = new Date();
        editor.putString("foodDate", DateFormat.format("dd-MM-yyyy ", d.getTime()).toString());
        editor.putInt("todayCalories", foodCounter.getTodayValue());
        editor.putInt("allTimeCalories", foodCounter.getAllTimeValue());
        editor.apply();
    }
}