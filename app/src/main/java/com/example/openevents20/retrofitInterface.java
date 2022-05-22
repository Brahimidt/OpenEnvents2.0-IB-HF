package com.example.openevents20;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface retrofitInterface {

        @POST("api/v2/users")
        Call<RegisterRequest>Register(@Body() RegisterRequest obj);

        @GET("api/v2/users")
        Call<List<UserRequest>>Users(@Header("Authorization") String accessToken);

        @POST("api/v2/users/login")
        Call<Token>Login(@Body() LoginRequest obj);

        @GET("api/v2/events")
        Call<List<EventsRequest>>Events(@Header("Authorization") String accessToken);

        @GET("api/v2/users/{email})")
        Call<List<ProfileRequest>>Profile(@Header("Authorization") String accessToken, @Path("email") String email);
}
