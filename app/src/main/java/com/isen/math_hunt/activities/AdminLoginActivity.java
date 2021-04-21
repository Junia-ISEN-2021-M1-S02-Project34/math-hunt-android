package com.isen.math_hunt.activities;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.isen.math_hunt.R;
import com.isen.math_hunt.entities.Login;
import com.isen.math_hunt.entities.LoginResponse;
import com.isen.math_hunt.model.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminLoginActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    private String adminLoginAccessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);


        Button backButton = (Button) findViewById(R.id.backButton);
        Button adminLoginButton = (Button) findViewById(R.id.AdminLoginButton);

        final TextInputLayout userTextField = findViewById(R.id.userIdTextField);
        final TextInputLayout passwordTextField = findViewById(R.id.passwordTextField);
        final Button loginButton = findViewById(R.id.AdminLoginButton);
        final Button adminButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLoginActivity.this, LoginActivity.class);
                Bundle b = new Bundle();
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
                finish();
            }
        });

        adminLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userTextField.getEditText().getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Team Id vide", Toast.LENGTH_LONG).show();
                } else if (passwordTextField.getEditText().getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Passeword vide", Toast.LENGTH_LONG).show();

                } else {
                    Login login = new Login(userTextField.getEditText().getText().toString(), passwordTextField.getEditText().getText().toString());


                    progressDialog = new ProgressDialog(AdminLoginActivity.this);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.show();

                    signInAdmin(login);

                    //loginTeam(login);
                }
            }
        });

    }

    private void signInAdmin(Login login) {
        Call<LoginResponse> call = RetrofitClient.getInstance().getMathHuntApiService().signInAdmin(login);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                try {
                    LoginResponse login = response.body();
                    progressDialog.dismiss();
                    adminLoginAccessToken = login.getAccessToken();
                    Toast.makeText(getApplicationContext(), "Tu es bien connecté", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AdminLoginActivity.this, AdminActivity.class);
                    Bundle b = new Bundle();
                    b.putString("ACCESS_TOKEN", adminLoginAccessToken);

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

