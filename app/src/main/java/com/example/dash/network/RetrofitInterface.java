package com.example.dash.network;


import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/registrarAndroid")
    Call<Void> registrationProcess (@Body HashMap<String, String> map);

}
