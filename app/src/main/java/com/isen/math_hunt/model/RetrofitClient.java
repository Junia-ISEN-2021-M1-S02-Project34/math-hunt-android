package com.isen.math_hunt.model;

import com.isen.math_hunt.api.MathHuntApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient instance = null;
    private MathHuntApiService mathHuntApiService;

    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(MathHuntApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mathHuntApiService = retrofit.create(MathHuntApiService.class);
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public MathHuntApiService getMathHuntApiService() {
        return mathHuntApiService;
    }


}