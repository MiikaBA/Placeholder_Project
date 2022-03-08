package com.example.placeholder_project.SportsDiary.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.placeholder_project.R;
import com.example.placeholder_project.SportsDiary.Classes.ActivitySingleton;

public class ActivityAddition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);
        getSupportActionBar().hide();
    }

    //Retrieves users input from EditText views.
    //Adds time and calories to SportsCounter and creates SportsActivity.
    public void createActivity(View v){
        if(v == findViewById(R.id.creationButton)){
            EditText textToSave = findViewById(R.id.typeET);
            String type = textToSave.getText().toString();
            if (type.matches("")) {
                Toast.makeText(this, "You did not enter a type", Toast.LENGTH_SHORT).show();
                return;
            }

            textToSave = findViewById(R.id.timeET);
            String timeS = textToSave.getText().toString();
            if (timeS.matches("")) {
                Toast.makeText(this, "You did not enter a duration", Toast.LENGTH_SHORT).show();
                return;
            }
            int time = Integer.parseInt(textToSave.getText().toString());

            textToSave = findViewById(R.id.calsET);
            String calories = textToSave.getText().toString();
            if (calories.matches("")) {
                Toast.makeText(this, "You did not enter burnt calories", Toast.LENGTH_SHORT).show();
                return;
            }
            int cals = Integer.parseInt(textToSave.getText().toString());

            textToSave = findViewById(R.id.descET);
            String desc = textToSave.getText().toString();
            if (desc.matches("")) {
                Toast.makeText(this, "You did not enter a description", Toast.LENGTH_SHORT).show();
                return;
            }

            ActivitySingleton.getInstance().addActivity(type, time, cals, desc);

            SharedPreferences counterVals = getSharedPreferences("CounterVals" , Activity.MODE_PRIVATE);
            int TS = time + counterVals.getInt("TimeVal", 0);
            int CB = cals + counterVals.getInt("CalorieVal", 0);
            SharedPreferences.Editor editor = counterVals.edit();
            editor.putInt("TimeVal", TS);
            editor.putInt("CalorieVal", CB);
            editor.apply();

            Intent next = new Intent(ActivityAddition.this, SportsDiary.class);
            next.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivityIfNeeded(next, 0);

        }
    }
}