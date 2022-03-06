package com.example.placeholder_project.SportsDiary.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.placeholder_project.R;
import com.example.placeholder_project.SportsDiary.Classes.ActivitySingleton;
import com.example.placeholder_project.SportsDiary.Classes.SportsActivity;

public class ActivityDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle b = getIntent().getExtras();
        int i = b.getInt(ActivityInspection.EXTRA, 0);

        Log.i("DBG", Integer.toString(i));
        SportsActivity actToShow = ActivitySingleton.getInstance().getActivity(i);

        TextView toSet = findViewById(R.id.actTypeLV);
        toSet.setText(actToShow.getActType());

        toSet = findViewById(R.id.timeLV);
        toSet.setText(Integer.toString(actToShow.getTimeSpent()) + " min.");

        toSet = findViewById(R.id.calLV);
        toSet.setText(Integer.toString(actToShow.getCalsBurnt()) + " kcal.");

        toSet = findViewById(R.id.descLV);
        toSet.setText(actToShow.getDescription());
    }
}