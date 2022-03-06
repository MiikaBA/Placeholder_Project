package com.example.placeholder_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class BreathingExcercise extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 180000;

     private Button buttonstart_pause;
     private Button button_reset;
     private TextView textViewCountdown;

     private CountDownTimer countDownTimer;
     private boolean timerRunning;

     private long timeLeftinMillis = START_TIME_IN_MILLIS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathing_excercise);

        textViewCountdown = findViewById(R.id.textView8);

        buttonstart_pause = findViewById(R.id.button_startpause);
        button_reset = findViewById(R.id.button_reset);

        buttonstart_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timerRunning){
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });

        updateCountDownText();
    }
    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftinMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftinMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                buttonstart_pause.setText("Start");
                buttonstart_pause.setVisibility(View.INVISIBLE);
                button_reset.setVisibility(View.VISIBLE);
                textViewCountdown.setText("Great job!");

            }
        }.start();
        timerRunning = true;
        buttonstart_pause.setText("Pause");
        button_reset.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        buttonstart_pause.setText("Start");
        button_reset.setVisibility(View.VISIBLE);
        buttonstart_pause.setVisibility(View.VISIBLE);

    }
    private void resetTimer() {
        timeLeftinMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        button_reset.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftinMillis / 1000) / 60;
        int seconds = (int) (timeLeftinMillis / 1000) % 60;

        String timeLeft = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        textViewCountdown.setText(timeLeft);
    }

    public void goNext(View v){
        if(v == findViewById(R.id.foodsButton)){
            Intent food = new Intent(BreathingExcercise.this, FoodDiary.class);
            startActivity(food);
        }else if(v == findViewById(R.id.sportButton)){
            Intent sport = new Intent(BreathingExcercise.this, SportsDiary.class);
            startActivity(sport);
        }
    }
}