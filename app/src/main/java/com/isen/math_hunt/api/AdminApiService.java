package com.isen.math_hunt.api;

import com.isen.math_hunt.interfaces.Constant;
import com.isen.math_hunt.entities.Admin;
import com.isen.math_hunt.entities.Enigma;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AdminApiService {

    String BASE_URL = Constant.URL_API;

    @POST("/admins/create/admin")
    Call<Enigma> createAdmin(@Body Admin admin);

    @Headers({"Content-Type: application/json"})
    @PUT("/admins/update/admin/{username}")
    Call<Enigma> updateAdmin(@Path("username") String username, @Body Admin body);

}
