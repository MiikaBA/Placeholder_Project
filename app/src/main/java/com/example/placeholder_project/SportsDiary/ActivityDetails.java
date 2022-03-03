package com.example.placeholder_project.SportsDiary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.placeholder_project.R;

public class ActivityDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle b = getIntent().getExtras();
        int i = b.getInt(ActivityInspection.EXTRA, 0);

        Log.i("DBG", Integer.toString(i));
        //TODO implement ActivityDetails
    }
}