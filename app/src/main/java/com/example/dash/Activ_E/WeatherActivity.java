package com.example.dash.Activ_E;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dash.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherActivity extends AppCompatActivity {

    TextView cityName;
    Button searchButton;
    TextView result;


    //String - many location can be checked
    class Weather extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... location) {

            try {
                URL url = new URL(location[-1]);
                HttpURLConnection networking = (HttpURLConnection) url.openConnection();

                networking.connect();

                // get data from url
                InputStream inputStream = networking.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                //get data and give back as a String
                int data = inputStreamReader.read();
                String insight = "";

                char ch;
                while (data > 0){
                    ch = (char) data;
                    insight = insight + ch;

                    data = inputStreamReader.read();
                }
                return insight;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        // Click Listner Under on Create Method

    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder constructor = new AlertDialog.Builder(WeatherActivity.this);
        constructor.setMessage("Exit Weather Checker Panel?");
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
        AlertDialog message = constructor.create();
        message.show();
    }



    public void search(View view) {

        cityName = findViewById(R.id.cityName);
        searchButton = findViewById(R.id.searchButton);
        result = findViewById(R.id.resut);


        String cName = cityName.getText().toString();


        String insight;
        Weather weather = new Weather();

        try {
            insight = weather.execute("https://openweathermap.org/data/2.5/weather?q=" +
                    cName + "&appid=3541edfc978fc3c217821b40f1fa4ddc").get();
            Log.i("insight", insight);

            //JSON Object + Array

            JSONObject jsonObject = new JSONObject(insight);
            String weatherInfo = jsonObject.getString("weather");
            String mainTemp = jsonObject.getString("main");
            Log.i("weatherInfo", weatherInfo);
            //weather info is in Array

            JSONArray jsonArray = new JSONArray(weatherInfo);
            String main = "";
            String description = "";
            //String temperature = "";

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject weatherChunk = jsonArray.getJSONObject(i);
                main = weatherChunk.getString("main");
                description = weatherChunk.getString("description");

            }

            //JSONObject mainChunk = new JSONObject(mainTemp);
            //temperature = mainChunk.getString("temp");


              Log.i("main", main);
             Log.i("description", description);

            String resultText = "Main :" + main + "\n description :" + description;

            result.setText(resultText);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}

