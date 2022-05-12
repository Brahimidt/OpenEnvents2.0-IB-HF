package com.example.openevents20;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface retrofitInterface {

        @POST("api/v2/users")
        Call<RegisterRequest>Register(@Body() RegisterRequest obj);
}
