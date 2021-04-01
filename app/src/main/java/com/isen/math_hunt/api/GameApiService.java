package com.isen.math_hunt.api;

import com.isen.math_hunt.interfaces.Constant;
import com.isen.math_hunt.entities.Game;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GameApiService {

    String BASE_URL = Constant.URL_API;



    @GET("/games/get/games")
    Call<Game> getAllGames();

    @GET("/games/get/game/{id}")
    Call<Game> getGameById(@Path("id") String id);

    @Headers({"/gamesContent-Type: application/json"})
    @PUT("/start/game/{id}")
    Call<Game> startGame(@Path("id") String id, @Body Game body);

}
