package com.isen.math_hunt.model;

import com.isen.math_hunt.api.EnigmaApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient instance = null;
    private EnigmaApiService enigmaApiService;

    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(EnigmaApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        enigmaApiService = retrofit.create(EnigmaApiService.class);
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public EnigmaApiService getMyApi() {
        return enigmaApiService;
    }
}