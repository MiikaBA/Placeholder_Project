package com.example.placeholder_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPref = getSharedPreferences("Prefs", MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!sharedPref.getString("name", "").matches("")) {
            Intent intent = new Intent(this, SportsDiary.class);
            startActivity(intent);
        }
    }

    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPref = getSharedPreferences("Prefs", MODE_PRIVATE);

        if(!sharedPref.getString("name", "").matches("")) {
            Intent intent = new Intent(this, SportsDiary.class);
            startActivity(intent);
        }
    }

    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = getSharedPreferences("Prefs", MODE_PRIVATE);

        if (!sharedPref.getString("name", "").matches("")) {
            Intent intent = new Intent(this, SportsDiary.class);
            startActivity(intent);
        }
    }
    public void Submit(View view) {
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
        } else {
            return;
        }
    }
}