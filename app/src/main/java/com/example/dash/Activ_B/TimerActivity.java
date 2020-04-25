package com.example.dash.Activ_B;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dash.R;
import com.example.dash.network.JasonAtlasCloudNewPost;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TimerActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private JasonAtlasCloudNewPost jasonAtlasCloudNewPost;
    private String core_URL = "https://node-borky-fkppa.run-eu-central1.goorm.io/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_post);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        retrofit = new Retrofit.Builder()
                .baseUrl(core_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        jasonAtlasCloudNewPost = retrofit.create(JasonAtlasCloudNewPost.class);


        findViewById(R.id.new_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPostToWeb();
            }
        });

    }


    private void newPostToWeb() {

        View view = getLayoutInflater().inflate(R.layout.new_post_form, null);

        AlertDialog.Builder constructor = new AlertDialog.Builder(this);
        constructor.setView(view).show();

        Button postButton = view.findViewById(R.id.new_post);
        final EditText image1Edit = view.findViewById(R.id.image1Edit);
        final EditText name1Edit = view.findViewById(R.id.name1Edit);
        final EditText description1Edit = view.findViewById(R.id.description1Edit);
        final EditText authorEdit = view.findViewById(R.id.authorEdit);

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, String> map = new HashMap<>();

                map.put("image", image1Edit.getText().toString());
                map.put("name", name1Edit.getText().toString());
                map.put("description", description1Edit.getText().toString());
                map.put("author", authorEdit.getText().toString());


                Call<Void> call = jasonAtlasCloudNewPost.createPost(map);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if (response.code() == 200) {
                            Toast.makeText(TimerActivity.this,
                                    "New Post successfully added", Toast.LENGTH_LONG).show();
                        } else if (response.code() == 400) {
                            Toast.makeText(TimerActivity.this,
                                    "Post exist in DB", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(TimerActivity.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }
    @Override
    public void onBackPressed() {
        final android.app.AlertDialog.Builder constructor = new android.app.AlertDialog.Builder(TimerActivity.this);
        constructor.setMessage("Do you to exit add user section?");
        constructor.setCancelable(true);
        constructor.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });
        constructor.setPositiveButton("Yes, close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        android.app.AlertDialog message = constructor.create();
        message.show();
    }

}


