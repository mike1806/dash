package com.example.dash.network;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JasonAtlasCloudNewPost {

    @POST("/bieganieAndroid")
    Call<Void> createPost (@Body HashMap<String, String> map);

}





 /*   @FormUrlEncoded
    @POST("endpointProject")
    Call<ResponseBody> createUser(
            @Field("isAdmin") String isAdmin,
            @Field("_id") Integer id,
            @Field("username") String username,
            @Field("name") String name,
            @Field("surname") String surname,
            @Field("email") String email,
            @Field("avatar") String avatar

            );
}
*/