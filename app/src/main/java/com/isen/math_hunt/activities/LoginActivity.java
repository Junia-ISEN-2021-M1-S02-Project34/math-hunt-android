package com.isen.math_hunt.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
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
    private ProgressDialog progressDialog;

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

        if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(LoginActivity.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }

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
                    Login login = new Login(userTextField.getEditText().getText().toString(), passwordTextField.getEditText().getText().toString());


                    progressDialog = new ProgressDialog(LoginActivity.this);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.show();

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
                    progressDialog.dismiss();
                    loginTeamId = login.getTeamId();
                    loginAccessToken = login.getAccessToken();
                    Toast.makeText(getApplicationContext(), "Tu es bien connecté", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginActivity.this, WaitingActivity.class);
                    Bundle b = new Bundle();
                    b.putString("TEAM_ID", loginTeamId);
                    b.putString("ACCESS_TOKEN", loginAccessToken);

                    intent.putExtras(b);
                    startActivity(intent);
                    finish();


                } catch (Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Vérifie tes identifiants ", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

}