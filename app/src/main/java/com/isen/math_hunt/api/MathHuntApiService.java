package com.isen.math_hunt.api;

import com.isen.math_hunt.entities.Admin;
import com.isen.math_hunt.entities.Answer;
import com.isen.math_hunt.entities.Game;
import com.isen.math_hunt.entities.Hint;
import com.isen.math_hunt.entities.Login;
import com.isen.math_hunt.entities.LoginResponse;
import com.isen.math_hunt.entities.Proposition;
import com.isen.math_hunt.entities.Team;
import com.isen.math_hunt.interfaces.Constant;
import com.isen.math_hunt.entities.Enigma;
import com.isen.math_hunt.entities.GeoGroup;
import com.isen.math_hunt.model.AttemptsNumber;
import com.isen.math_hunt.model.EnigmaList;
import com.isen.math_hunt.model.FullEnigma;
import com.isen.math_hunt.model.GetAllGames;
import com.isen.math_hunt.model.GetGameById;
import com.isen.math_hunt.model.HintId;
import com.isen.math_hunt.model.HintList;
import com.isen.math_hunt.model.ProgressionPost;
import com.isen.math_hunt.model.GeoGroupList;
import com.isen.math_hunt.model.RankingResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MathHuntApiService {

    String BASE_URL = Constant.URL_API;


    //================================================================================
    // AdminCall
    //================================================================================

    @POST("admins/create/admin")
    Call<Enigma> createAdmin(@Body Admin admin);

    @Headers({"Content-Type: application/json"})
    @PUT("admins/update/admin/{username}")
    Call<Enigma> updateAdmin(@Path("username") String username, @Body Admin body);

    //================================================================================
    // AnswerCall
    //================================================================================

    @GET("answers/get/answers")
    Call<Answer> getAllAnswers();

    @GET("answers/get/answer/{id}")
    Call<Answer> getAnswerById(@Path("id") String id);

    @GET("answers/get/answer/enigma/{id}")
    Call<Answer> getAnswerByEnigmaId(@Path("id") String id);



    //================================================================================
    // EnigmaCall
    //================================================================================

    @POST("enigmas/create/enigma")
    Call<Enigma> createEnigma(@Body Enigma enigma);

    @GET("enigmas/get/enigmas")
    Call<EnigmaList> getEnigmas();

    @GET("enigmas/get/enigma/{id}")
    Call<Enigma> getEnigmaById(@Path("id") String id);

    @GET("enigmas/get/full/enigma/{id}")
    Call<FullEnigma> getFullEnigmaById(@Path("id") String id,@Header("x-access-token") String token);

    @GET("enigmas/get/enigmas/geoGroup/{id}")
    Call<Enigma> getEnigmasByGeoGroupId(@Path("id") String id);



    //================================================================================
    // GameCall
    //================================================================================

    @GET("games/get/games")
    Call<GetAllGames> getAllGames(@Header("x-access-token") String token);

    @GET("games/get/game/{id}")
    Call<GetGameById> getGameById(@Path("id") String id,@Header("x-access-token") String token);

    @Headers({"Content-Type: application/json"})
    @PUT("games/start/game/{id}")
    Call<ResponseBody> startGame(@Path("id") String id,@Header("x-access-token") String token);

    @Headers({"Content-Type: application/json"})
    @PUT("games/stop/game/{id}")
    Call<ResponseBody> stopGame(@Path("id") String id,@Header("x-access-token") String token);

    @GET("games/get/teams/ranking/game/{id}")
    Call<RankingResponse> getRanking(@Path("id") String id, @Header("x-access-token") String token);

    //================================================================================
    // TeamCall
    //================================================================================

    @GET("teams/get/teams")

    Call<Team> getAllTeams();

    @GET("teams/get/team/{id}")
    Call<Team> getTeamById(@Path("id") String id,@Header("x-access-token") String token);

    @GET("teams/get/teams/game/{id}")
    Call<Team> getTeamsByGameId(@Path("id") String id);



    @Headers({"Content-Type: application/json"})
    @PUT("teams/update/team/progression/{id}")
    Call<Team> updateTeamProgression(@Path("id") String id, @Body ProgressionPost body,@Header("x-access-token") String token);

    @Headers({"Content-Type: application/json"})
    @PUT("teams/update/team/used/hint/{id}")
    Call<Team> updateTeamUsedHint(@Path("id") String id, @Body HintId hintId,@Header("x-access-token") String token);

    @Headers({"Content-Type: application/json"})
    @PUT("teams/update/team/attemptsNumber/{id}")
    Call<Integer> updateAttemptsNumber(@Path("id") String id,@Header("x-access-token") String token);

    //================================================================================
    // HintCall
    //================================================================================

    @GET("hints/get/hints")
    Call<Hint> getAllHints();

    @GET("hints/get/hint/{id}")
    Call<Hint> getHintById(@Path("id") String id,@Header("x-access-token") String token);

    @GET("hints/get/hints/enigma/{id}")
    Call<HintList> getHintsByEnigmaId(@Path("id") String id,@Header("x-access-token") String token);


    @Headers({"Content-Type: application/json"})
    @PUT("hints/update/hint/{id}")
    Call<Hint> updateHint(@Path("id") String id, @Body Hint body);

    //================================================================================
    // PropositionCall
    //================================================================================

    @GET("propositions/get/propositions")
    Call<Proposition> getAllPropositions();

    @GET("propositions/get/proposition/answer/{id}")
    Call<Proposition> getPropositionsByAnswerId(@Path("id") String id);

    @GET("propositions/get/proposition/{id}")
    Call<Proposition> getPropositionById(@Path("id") String id);

    @Headers({"Content-Type: application/json"})
    @PUT("propositions/update/proposition/{id}")
    Call<Proposition> updateProposition(@Path("id") String id, @Body Hint body);


    //================================================================================
    // geoGroupCall
    //================================================================================

    @GET("geoGroups/get/geoGroups")
    Call<GeoGroupList> getAllGeoGroups();

    @GET("geoGroups/get/geoGroup/{id}")
    Call<GeoGroup> getGeoGroupById(@Path("id") String id,@Header("x-access-token") String token);

    //================================================================================
    // Login
    //================================================================================

    @POST("auth/sign-in/team")
    Call<LoginResponse> signInTeam(@Body Login login);

    @POST("auth/sign-in/admin")
    Call<LoginResponse> signInAdmin(@Body Login login);

    //================================================================================
    //
    //================================================================================

}
