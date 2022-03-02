package com.example.placeholder_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent nextActivity = new Intent(MainActivity.this, SportsDiary.class);
        startActivity(nextActivity);
    }

    public void etene(View view) {
        Intent intent = new Intent(this, SportsDiary.class);
        startActivity(intent);
    }
}