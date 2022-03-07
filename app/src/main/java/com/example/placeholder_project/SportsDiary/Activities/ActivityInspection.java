package com.example.placeholder_project.SportsDiary.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.placeholder_project.R;
import com.example.placeholder_project.SportsDiary.Classes.ActivitySingleton;
import com.example.placeholder_project.SportsDiary.Classes.SportsActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ActivityInspection extends AppCompatActivity {
    public static final String EXTRA = "com.example.placeholder_project.ListPos";
    private List<SportsActivity> activities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection);

        Log.i("DBG", "onCreate");
    }

    @Override
    protected void onResume() { //Makes sure ListView is up to date
        super.onResume();
        retrieveActivities();
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

    public void retrieveActivities(){   //Retrieves activities list from SharedPreferences and sends it to ActivitySingleton.
        SharedPreferences sharedPreferences = getSharedPreferences("ActivityList", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Actlist", null);
        Type type = new TypeToken<ArrayList<SportsActivity>>() {}.getType();
        this.activities = gson.fromJson(json, type);
        if (activities == null) {
            activities = ActivitySingleton.getInstance().getActivities();
        }else if(activities.size() < ActivitySingleton.getInstance().getActivities().size()){
            activities = ActivitySingleton.getInstance().getActivities();
        }else{
            ActivitySingleton.getInstance().reInitiateArray(activities);
        }

    }

    public void saveActivities(){   //Saves activities list to SharedPreferences.
        List<SportsActivity> activities = ActivitySingleton.getInstance().getActivities();
        SharedPreferences Prefs = getSharedPreferences("ActivityList", MODE_PRIVATE);
        SharedPreferences.Editor editor = Prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(activities);
        editor.putString("Actlist", json);
        editor.apply();
    }

    @Override
    protected void onPause() {  //Calls saveActivities whenever
        super.onPause();
        Log.i("DBG", "Inspect onPause");
        saveActivities();
    }
}