package com.example.dash.network;

import com.example.dash.model.PostUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonAtlasCloudUsers {


    @GET("/endpointUser")
    Call<List<PostUser>> getPosts();


}