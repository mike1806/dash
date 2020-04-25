package com.example.dash.Activ_A;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.dash.Activ_H.Adapter;
import com.example.dash.R;
import com.example.dash.model.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Item> items;
    private static String core_URL = "https://node-borky-fkppa.run-eu-central1.goorm.io/endpointProject";
    private Button buttonRemove;

    private EditText editTextRemove;
    Adapter tool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);

        recyclerView = findViewById(R.id.itemList);
        items = new ArrayList<>();
        extractItems();

            buttonRemove = findViewById(R.id.button_remove);


            buttonRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = Integer.parseInt(editTextRemove.getText().toString());
                    removeItem(position);
                }
            });
        }
    @Override
    public void onBackPressed() {
        final AlertDialog.Builder constructor = new AlertDialog.Builder(CalendarActivity.this);
        constructor.setMessage("Exit Posts view?");
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
        AlertDialog alertDialog = constructor.create();
        alertDialog.show();
    }

    public void removeItem(int position) {
        items.remove(position);
        tool.notifyItemRemoved(position);
    }

    private void extractItems() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, core_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject itemObject = response.getJSONObject(i);

                        Item thing = new Item();
                        thing.setName(itemObject.getString("name").toString());
                        thing.setDescription(itemObject.getString("description".toString()));
                        thing.setImage(itemObject.getString("image"));
                        //thing.setSongURL(songObject.getString("url"));
                        items.add(thing);

                    } catch (JSONException bug) {
                        bug.printStackTrace();
                    }
                }


                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                tool = new Adapter(getApplicationContext(),items);
                recyclerView.setAdapter(tool);

                tool.setOnItemClickListener(new Adapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                    }

                    @Override
                    public void onDeleteClick(int position) {
                        removeItem(position);
                    }
                }
                );



            }
        }



        , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });

        queue.add(jsonArrayRequest);

    }




}