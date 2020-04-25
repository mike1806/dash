package com.example.dash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.dash.Activ_A.CalendarActivity;
import com.example.dash.Activ_B.TimerActivity;
import com.example.dash.Activ_C.StatsActivity;
import com.example.dash.Activ_D.PostActivity;
import com.example.dash.Activ_E.WeatherActivity;
import com.example.dash.Activ_F.HeartActivity;
import com.example.dash.Activ_G.WwwActivity;
import com.example.dash.Activ_H.DbActivity;
import com.example.dash.Activ_I.MusicActivity;
import com.example.dash.Activ_J.CloseActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private CardView calendarButton, timerButton, statsButton, postButton, wetterButton, healthButton, wwwButton, dbButton, musicButton, closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //define variables
        calendarButton = (CardView) findViewById(R.id.calendar_button);
        timerButton = (CardView) findViewById(R.id.timer_button);
        statsButton = (CardView) findViewById(R.id.stats_button);
        postButton = (CardView) findViewById(R.id.post_button);
        wetterButton = (CardView) findViewById(R.id.wetter_button);
        healthButton = (CardView) findViewById(R.id.heart_button);
        wwwButton = (CardView) findViewById(R.id.www_button);
        dbButton = (CardView) findViewById(R.id.db_button);
        musicButton = (CardView) findViewById(R.id.music_button);
        closeButton = (CardView) findViewById(R.id.close_button);

        //add listener
        calendarButton.setOnClickListener(this);
        timerButton.setOnClickListener(this);
        statsButton.setOnClickListener(this);
        postButton.setOnClickListener(this);
        wetterButton.setOnClickListener(this);
        healthButton.setOnClickListener(this);
        wwwButton.setOnClickListener(this);
        dbButton.setOnClickListener(this);
        musicButton.setOnClickListener(this);
        closeButton.setOnClickListener(this);

    }
        @Override

        public void onClick(View v) {
            Intent i;

            switch (v.getId()) {
            case R.id.calendar_button : i = new Intent(this, CalendarActivity.class); startActivity(i); break;
            case R.id.timer_button : i = new Intent(this, TimerActivity.class); startActivity(i); break;
            case R.id.stats_button : i = new Intent(this, StatsActivity.class); startActivity(i); break;
            case R.id.post_button : i = new Intent(this, PostActivity.class); startActivity(i); break;
            case R.id.wetter_button : i = new Intent(this, WeatherActivity.class); startActivity(i); break;
            case R.id.heart_button : i = new Intent(this, HeartActivity.class); startActivity(i); break;
            case R.id.www_button : i = new Intent(this, WwwActivity.class); startActivity(i); break;
            case R.id.db_button : i = new Intent(this, DbActivity.class); startActivity(i); break;
            case R.id.music_button : i = new Intent(this, MusicActivity.class); startActivity(i); break;
            case R.id.close_button : i = new Intent(this, CloseActivity.class); startActivity(i); break;
            default: break;

        }

            }

        public void clickToExit (View v){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        }

    }

