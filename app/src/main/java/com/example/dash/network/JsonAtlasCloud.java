package com.example.dash.network;

import com.example.dash.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonAtlasCloud {


    @GET("shop/products/endpointProduct")
    Call<List<Post>> getPosts();

    @POST("shop/products/endpointProduct")
    Call<Post>creatUser(@Body Post post);

}