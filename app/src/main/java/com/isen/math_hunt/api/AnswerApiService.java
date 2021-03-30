package com.isen.math_hunt.api;

import com.isen.math_hunt.interfaces.Constant;
import com.isen.math_hunt.entities.Answer;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AnswerApiService {

    String BASE_URL = Constant.URL_API;


    @POST("/answers/create/answer")
    Call<Answer> createAnswer(@Body Answer answer);

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

}