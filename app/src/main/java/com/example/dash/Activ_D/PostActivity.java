package com.example.dash.Activ_D;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dash.R;
import com.example.dash.network.RetrofitInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String core_URL = "https://node-borky-fkppa.run-eu-central1.goorm.io/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(core_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);


        findViewById(R.id.new_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newUserAction();
            }
        });

    }
    private void newUserAction() {

        View view = getLayoutInflater().inflate(R.layout.registration_form, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view).show();

        Button registerButton = view.findViewById(R.id.new_post);
        final EditText usernameEdit = view.findViewById(R.id.usernameEdit);
        final EditText passwordEdit = view.findViewById(R.id.passwordEdit);
        final EditText nameEdit = view.findViewById(R.id.nameEdit);
        final EditText surnameEdit = view.findViewById(R.id.surnameEdit);
        final EditText emailEdit = view.findViewById(R.id.emailEdit);
        final EditText avatarEdit = view.findViewById(R.id.avatarEdit);
        final EditText isAdminEdit = view.findViewById(R.id.isAdminEdit);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, String> map = new HashMap<>();

                map.put("username", usernameEdit.getText().toString());
                map.put("password", passwordEdit.getText().toString());
                map.put("name", nameEdit.getText().toString());
                map.put("surname", surnameEdit.getText().toString());
                map.put("email", emailEdit.getText().toString());
                map.put("avatar", avatarEdit.getText().toString());
                map.put("isAdmin", isAdminEdit.getText().toString());

                Call<Void> call = retrofitInterface.registrationProcess(map);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if (response.code() == 200) {
                            Toast.makeText(PostActivity.this,
                                    "New User registered", Toast.LENGTH_LONG).show();
                        } else if (response.code() == 400) {
                            Toast.makeText(PostActivity.this,
                                    "User exist in MongoDB", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(PostActivity.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }
    @Override
    public void onBackPressed() {
        final android.app.AlertDialog.Builder constructor = new android.app.AlertDialog.Builder(PostActivity.this);
        constructor.setMessage("Exit Registration?");
        constructor.setCancelable(true);
        constructor.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });
        constructor.setPositiveButton("Close this window", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        android.app.AlertDialog alertDialog = constructor.create();
        alertDialog.show();
    }


}
