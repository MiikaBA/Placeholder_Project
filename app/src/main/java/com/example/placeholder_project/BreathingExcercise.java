package com.example.placeholder_project;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BreathingExcercise extends AppCompatActivity {

    public int counter;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathing_excercise);
        button = (Button) findViewById(R.id.additionButton);
        textView = (TextView) findViewById(R.id.textView8);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new CountDownTimer(180000,1000) {
                    @Override
                    public void onTick(long l) {
                    textView.setText(String.valueOf(counter));
                    counter++;
                    }

                    @Override
                    public void onFinish() {
                    textView.setText("YOU DID A GREAT JOB!");
                    }

                }.start();
            }
        });
    }
}