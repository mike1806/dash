package com.example.dash.network;

import com.example.dash.model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIinterface {

    @GET("/endpointUser")
    Call<List<Users>> getUserList();

}
