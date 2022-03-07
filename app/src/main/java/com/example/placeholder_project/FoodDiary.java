package com.example.placeholder_project;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.placeholder_project.SportsDiary.Activities.SportsDiary;
import java.util.Date;
import java.util.Locale;

public class FoodDiary extends AppCompatActivity {
    private FoodCounter foodCounter;
    String currentDate;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_diary);
        checkDateMatch();
        Update();
    }

    // Checks if the day has changed since last time and creates foodCounters based off that.
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void checkDateMatch(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        currentDate = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(new Date());
        boolean todayChecking = preferences.getBoolean(currentDate, false);
        if (!todayChecking) {
            // New day
            foodCounter = new FoodCounter(0, preferences.getInt("allTimeCalories", 0));
        } else {
            // Same day
            foodCounter = new FoodCounter(preferences.getInt("todayCalories", 0), preferences.getInt("allTimeCalories", 0));
        }
    }

    // Takes the input from editTextNumber component, adds it to counter and updates Ui.
    public void addCalories(View v){
        EditText editTextNumber2 = findViewById(R.id.editTextNumber2);
        // If statement for checking that input is not empty
        if(!(editTextNumber2.getText().toString().equals(""))){
            int cals = Integer.parseInt(editTextNumber2.getText().toString());
            foodCounter.plus(cals);
            Update();
        }
    }

    // Updates the activity UI elements
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
            // Sports Activity
            Intent food = new Intent(FoodDiary.this, SportsDiary.class);
            food.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivityIfNeeded(food, 0   );
        }else if(v == findViewById(R.id.breathingButton)){
            // Breathing Activity
            Intent breath = new Intent(FoodDiary.this, BreathingExcercise.class);
            breath.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivityIfNeeded(breath, 0);
        }
    }

    // Saving values and date to shared preferences.
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(currentDate, true);
        editor.putInt("todayCalories", foodCounter.getTodayValue());
        editor.putInt("allTimeCalories", foodCounter.getAllTimeValue());
        editor.apply();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onResume(){
        super.onResume();
        checkDateMatch();
        Update();
    }
}