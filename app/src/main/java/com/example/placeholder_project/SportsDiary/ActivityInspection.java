package com.example.placeholder_project.SportsDiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.placeholder_project.R;
import com.example.placeholder_project.SportsDiary.Classes.SportsActivity;

public class ActivityInspection extends AppCompatActivity {
    public static final String EXTRA = "com.example.placeholder_project.ListPos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection);

        generateListView();
    }

    public void generateListView(){ //Generates ListView with a ArrayAdapter and adds listener to it.
        ListView lv = findViewById(R.id.activityLV);

        lv.setAdapter(new ArrayAdapter<SportsActivity>(
                this,
                android.R.layout.simple_list_item_1,
                ActivitySingleton.getInstance().getActivities()));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override //Reacts to click by creating Intent and starting new activity.
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent nextActivity = new Intent(ActivityInspection.this, ActivityDetails.class);
                nextActivity.putExtra(EXTRA, i);
                startActivity(nextActivity);
            }
        });


    }
}