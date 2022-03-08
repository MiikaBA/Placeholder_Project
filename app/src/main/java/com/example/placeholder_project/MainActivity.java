package com.example.placeholder_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.placeholder_project.SportsDiary.Activities.SportsDiary;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //If there's not saved name, skip this activity and go to SportsDiary
        SharedPreferences sharedPref = getSharedPreferences("Prefs", MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        if(!sharedPref.getString("name", "").matches("")) {
            Intent intent = new Intent(this, SportsDiary.class);
            startActivity(intent);
        }
    }

    protected void onResume() {
        SharedPreferences sharedPref = getSharedPreferences("Prefs", MODE_PRIVATE);
        //Closes the app when you return to this activity, except on the first time
        super.onResume();
        if(!sharedPref.getString("name", "").matches("")) {
            onBackPressed();
        }
    }
    public void Submit(View view) {
        //Checks the input fields and saves them into shared preferences, if none of them are empty
        SharedPreferences sharedPref = getSharedPreferences("Prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        TextView name = findViewById(R.id.nameEdit);
        TextView age = findViewById(R.id.ageEdit);
        TextView weight = findViewById(R.id.weightEdit);
        TextView height = findViewById(R.id.heightEdit);

        String nameString = name.getText().toString();
        String ageString = age.getText().toString();
        String weightString = weight.getText().toString();
        String heightString = height.getText().toString();

        if(!nameString.matches("") && !ageString.matches("") && !weightString.matches("") && !heightString.matches("")) {
            editor.putString("name", nameString);
            editor.putString("age", ageString);
            editor.putString("weight", weightString);
            editor.putString("height", heightString);
            editor.commit();

            Intent intent = new Intent(this, SportsDiary.class);
            startActivity(intent);
        }
    }
}

