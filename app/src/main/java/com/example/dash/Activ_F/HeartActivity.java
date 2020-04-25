package com.example.dash.Activ_F;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dash.R;

public class HeartActivity extends AppCompatActivity {


    //variables for countdown water machine
    TextView timerTextView;
    SeekBar timerSeekBar;
    boolean counterIsActive = false;
    Button goButton;
    CountDownTimer countDownTimer;
    boolean waterIsShowing = true;
    //variables for navigation
    Button moveButton;
    Button backButton;

    //animation for main screen illustration on click photo - blue/red


    //code for update water level
    @SuppressLint("SetTextI18n")
    public void resetTimer() {
        timerTextView.setText("02:30:00");
        // 9000 seconds = 1440 minutes = 24 hours
        timerSeekBar.setProgress(9000);
        timerSeekBar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("Set your new hydration level and run");
        counterIsActive = false;
    }

    //method for when button is clicked
    public void buttonClicked(View view) {

        if (counterIsActive) {
            resetTimer();
        } else {

            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            goButton.setText("Still thirsty? \n Add water");
            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long l) {updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {

                    resetTimer();
                }
            }.start();
        }
    }

    @SuppressLint("SetTextI18n")
    public void updateTimer(int secondsLeft) {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - (minutes * 60);

        String secondString = Integer.toString(seconds);

        if (seconds <= 9) {
            secondString = "0" + secondString;
        }
        timerTextView.setText(Integer.toString(minutes) + ":" + secondString);
    }

    //originally seekBar updater
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timerSeekBar = findViewById(R.id.timerSeekBar);
        timerTextView = findViewById(R.id.countdownTextView);
        goButton = findViewById(R.id.goButton);

        timerSeekBar.setMax(86400);
        timerSeekBar.setProgress(9000);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });




    }
}
