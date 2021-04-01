package com.isen.math_hunt.api;

import com.isen.math_hunt.entities.Admin;
import com.isen.math_hunt.entities.Answer;
import com.isen.math_hunt.entities.Game;
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

public interface MathHuntApiService {

    String BASE_URL = Constant.URL_API;


    //================================================================================
    // AdminCall
    //================================================================================

    @POST("/admins/create/admin")
    Call<Enigma> createAdmin(@Body Admin admin);

    @Headers({"Content-Type: application/json"})
    @PUT("/admins/update/admin/{username}")
    Call<Enigma> updateAdmin(@Path("username") String username, @Body Admin body);

    //================================================================================
    // AnswerCall
    //================================================================================

    @GET("/answers/get/answers")
    Call<Answer> getAllAnswers();

    @GET("/answers/get/answer/{id}")
    Call<Answer> getAnswerById(@Path("id") String id);

    @GET("/answers/get/answer/enigma/{id}")
    Call<Answer> getAnswerByEnigmaId(@Path("id") String id);

    @Headers({"Content-Type: application/json"})
    @PUT("/answers/update/answer/{id}")
    Call<Answer> updateAnswer(@Path("id") String id, @Body Answer body);

    @DELETE("/answers/delete/answer/{id}")
    Call<ResponseBody> deleteAnswer(@Path("id") String id);


    //================================================================================
    // EnigmaCall
    //================================================================================

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

    //================================================================================
    // GameCall
    //================================================================================

    @GET("/games/get/games")
    Call<Game> getAllGames();

    @GET("/games/get/game/{id}")
    Call<Game> getGameById(@Path("id") String id);

    @Headers({"/gamesContent-Type: application/json"})
    @PUT("/start/game/{id}")
    Call<Game> startGame(@Path("id") String id, @Body Game body);

}
