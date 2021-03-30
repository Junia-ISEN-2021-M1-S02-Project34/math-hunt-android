package com.isen.math_hunt.api;

import com.isen.math_hunt.interfaces.Constant;
import com.isen.math_hunt.entities.Enigma;
import com.isen.math_hunt.entities.GeoGroup;
import com.isen.math_hunt.model.EnigmaList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EnigmaApiService {

    String BASE_URL = Constant.URL_API;


    @POST("/enigmas/create/enigma")
    Call<Enigma> createEnigma(@Body Enigma enigma);

    @GET("enigmas/get/enigmas")
    Call<EnigmaList> getEnigmas();

    @GET("enigmas/get/enigma/{id}")
    Call<Enigma> getEnigmaById(@Path("id") String id);

    @GET("/enigmas/get/enigmas/geoGroup/{id}")
    Call<GeoGroup> getEnigmasByGeoGroupId(@Path("id") String id);

    @Headers({"Content-Type: application/json"})
    @PUT("/enigmas/update/enigma/{id}")
    Call<Enigma> updateEnigma(@Path("id") String id, @Body Enigma body);

    @DELETE("/enigmas/delete/enigma/{id}")
    Call<ResponseBody> deleteEnigma(@Path("id") String id);

}
