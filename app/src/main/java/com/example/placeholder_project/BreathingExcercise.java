package com.example.placeholder_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.placeholder_project.SportsDiary.Activities.SportsDiary;
import java.util.Locale;

public class  BreathingExcercise extends AppCompatActivity {

    //Sets starting time (in milliseconds).
    private static final long START_TIME = 180000;

     private Button buttonstart_pause;
     private Button button_reset;
     private TextView textViewCountdown;
     private CountDownTimer countDownTimer;
     private boolean timerRunning;

     private long timeLeft = START_TIME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathing_excercise);

        textViewCountdown = findViewById(R.id.textView8);

        buttonstart_pause = findViewById(R.id.button_startpause);
        button_reset = findViewById(R.id.button_reset);

        //Starts/Pauses timer.
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
        //Resets timer.
        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });

        updateCountDownText();
    }

    //Updates timer after every 1 second.
    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateCountDownText();
            }

            //Changes Pause button back to Start button. Also writes text after timer hits 0.
            @Override
            public void onFinish() {
                timerRunning = false;
                buttonstart_pause.setText("Start");
                buttonstart_pause.setVisibility(View.INVISIBLE);
                button_reset.setVisibility(View.VISIBLE);

                textViewCountdown.setText("Great job!");

            }
        }.start();
        //When timer is running, Reset button is hidden. Start button = Pause.
        timerRunning = true;
        buttonstart_pause.setText("Pause");
        button_reset.setVisibility(View.INVISIBLE);
    }

    //Stops timer, Pause button = Start. Reset button is visible.
    private void pauseTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        buttonstart_pause.setText("Start");
        button_reset.setVisibility(View.VISIBLE);
        buttonstart_pause.setVisibility(View.VISIBLE);

    }
    //Resets timer.
    private void resetTimer() {
        timeLeft = START_TIME;
        updateCountDownText();
        button_reset.setVisibility(View.VISIBLE);
    }

    //Converts milliseconds to minutes and seconds.
    private void updateCountDownText() {
        int minutes = (int) (timeLeft / 1000) / 60;
        int seconds = (int) (timeLeft / 1000) % 60;

        //Updates timers value. String.format converts minutes and seconds into a time string.
        String timeLeft = String.format(Locale.getDefault(), "%2d:%02d", minutes, seconds);
        textViewCountdown.setText(timeLeft);
    }

    // Method for moving between activities when button is clicked
    public void goNext(View v){
        if(v == findViewById(R.id.foodButton)){
            Intent food = new Intent(BreathingExcercise.this, FoodDiary.class);
            food.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivityIfNeeded(food, 0);

        }else if(v == findViewById(R.id.sportsButton)) {
            Intent sport = new Intent(BreathingExcercise.this, SportsDiary.class);
            sport.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivityIfNeeded(sport, 0);
        }
    }
}
