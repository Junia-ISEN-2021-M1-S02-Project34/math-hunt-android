package com.isen.math_hunt.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.isen.math_hunt.R;
import com.isen.math_hunt.entities.Login;
import com.isen.math_hunt.entities.LoginResponse;
import com.isen.math_hunt.model.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private String teamId = "60783b356336f10016689b6c";
    private String gameId = "6059e4165375a204b13e1e8a";

    String loginTeamId;
    String loginAccessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final TextInputLayout userTextField = findViewById(R.id.userIdTextField);
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
                if (userTextField.getEditText().getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Team Id vide", Toast.LENGTH_LONG).show();
                } else if (passwordTextField.getEditText().getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Passeword vide", Toast.LENGTH_LONG).show();

                } else {
                    Login login = new Login(userTextField.getEditText().getText().toString(),passwordTextField.getEditText().getText().toString());
                    Log.d("PROUT", "Username: " + login.getUsername());
                    Log.d("PROUT", "Password: " + login.getPassword());


                    loginTeam(login);
                }

            }
        });
    }




        private void loginTeam(Login login) {
        Call<LoginResponse> call = RetrofitClient.getInstance().getMathHuntApiService().loginTeam(login);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                try {
                    LoginResponse login = response.body();
                    Log.d("PROUT", "onResponse: " + response);

                    loginTeamId = login.getTeamId();
                    loginAccessToken = login.getAccessToken();

                    Intent intent = new Intent(LoginActivity.this, WaitingActivity.class);
                    Bundle b = new Bundle();
                    b.putString("TEAM_ID", loginTeamId);
                    b.putString("ACCESS_TOKEN", loginAccessToken);
                    intent.putExtras(b);
                    startActivity(intent);
                    finish();


                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Coucou", t.getMessage());

            }
        });


    }

}