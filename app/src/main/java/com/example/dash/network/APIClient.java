package com.example.dash.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {


    public static String url_user = "https://node-borky-fkppa.run-eu-central1.goorm.io/";

    public static Retrofit getClient() {

        Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl(url_user)
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();
        //   .addConverterFactory(ScalarsConverterFactory.create())
        //   .addCallAdapterFactory(RxJav
        return retrofit;
    }

    public static APIinterface apIinterface (){

        return getClient().create(APIinterface.class);
    }
}
