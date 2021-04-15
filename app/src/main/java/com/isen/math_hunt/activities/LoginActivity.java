package com.isen.math_hunt.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.isen.math_hunt.R;
import com.isen.math_hunt.entities.Login;
import com.isen.math_hunt.model.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private String teamId = "6075624ad113d40016e1a33c";
    private String gameId = "6059e4165375a204b13e1e8a";

    String loginUsername;
    String loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final TextInputLayout userTextField = findViewById(R.id.answerTextField);
        final TextInputLayout passwordTextField = findViewById(R.id.passewordTextField);
        final Button loginButton = findViewById(R.id.loginButton);
        final Button adminButton = findViewById(R.id.adminButton);

        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                Bundle b = new Bundle();
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
                finish();
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, WaitingActivity.class);
                Bundle b = new Bundle();
                b.putString("TEAM_ID", teamId);
                b.putString("GAME_ID", gameId);
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
                finish();

            }
        });
    }


    private void loginTeam(String username, String password) {
        Call<Login> call = RetrofitClient.getInstance().getMathHuntApiService().loginTeam(username, password);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {

                try {
                    Login login = response.body();

                    loginUsername = login.getUsername();
                    loginPassword = login.getPassword();
                    // teamId
                    // accessToken
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Coucou", t.getMessage());

            }
        });


    }

}