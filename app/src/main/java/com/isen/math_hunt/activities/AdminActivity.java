package com.isen.math_hunt.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.isen.math_hunt.R;
import com.isen.math_hunt.adapters.SpinnerAdapter;
import com.isen.math_hunt.entities.EnigmasProgression;
import com.isen.math_hunt.entities.Game;
import com.isen.math_hunt.entities.Progression;
import com.isen.math_hunt.model.GetAllGames;
import com.isen.math_hunt.model.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private GetAllGames games;
    private List<String> gameName = new ArrayList<>();
    ;
    private Spinner spin;
    private ProgressDialog progressDialog;

    private String currentChoice;
    private List<String> gamesId = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        progressDialog = new ProgressDialog(AdminActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        spin = (Spinner) findViewById(R.id.spinnerParty);
        spin.setOnItemSelectedListener(this);

        Button adminStartButton = (Button) findViewById(R.id.adminStartButton);

        adminStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        getAllGames();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String currentChoice = gameName.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void getAllGames() {
        Call<GetAllGames> call = RetrofitClient.getInstance().getMathHuntApiService().getAllGames();
        call.enqueue(new Callback<GetAllGames>() {
            @Override
            public void onResponse(Call<GetAllGames> call, Response<GetAllGames> response) {

                try {
                    progressDialog.dismiss();
                    games = response.body();


                    getGameName(games.getGames());

                    SpinnerAdapter customAdapter = new SpinnerAdapter(getApplicationContext(), gameName);
                    spin.setAdapter(customAdapter);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<GetAllGames> call, Throwable t) {
                Log.d("TAG", t.getMessage());

            }
        });
    }

    public void getGameName(final List<Game> gameList) {
        gameList.stream().forEach(
                game -> {
                    gameName.add(game.getName());
                    gamesId.add(game.get_id());
                });
    }

}

