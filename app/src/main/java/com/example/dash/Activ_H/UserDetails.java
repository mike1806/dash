package com.example.dash.Activ_H;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.dash.R;

public class UserDetails extends AppCompatActivity {

    TextView userName;
    ImageView userAvatar;
    TextView userSurname;
    TextView userEmail;
    TextView userUsername;
    TextView userCreated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);


        //find views of XML positions

        userName = findViewById(R.id.name);
        userAvatar = findViewById(R.id.avatar);
        userSurname = findViewById(R.id.surname);
        userEmail = findViewById(R.id.email);
        userUsername = findViewById(R.id.username);
        userCreated = findViewById(R.id.created);

        // get intent

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String avatar = intent.getStringExtra("avatar");
        String surname = intent.getStringExtra("surname");
        String email = intent.getStringExtra("email");
        String username = intent.getStringExtra("username");
        String created = intent.getStringExtra("created");
        //setters

        userName.setText(name);
        userSurname.setText(surname);
        userEmail.setText(email);
        userUsername.setText(username);
        userCreated.setText(created);
        //to display photo of particular user from database
        Glide.with(this)
            .load(avatar)
            .into(userAvatar);
        }

    }

