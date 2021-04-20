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
import com.isen.math_hunt.entities.Admin;
import com.isen.math_hunt.entities.Login;

public class AdminLoginActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;


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




                    Intent intent = new Intent(AdminLoginActivity.this, AdminActivity.class);
                    Bundle b = new Bundle();
                    intent.putExtras(b); //Put your id to your next Intent
                    startActivity(intent);
                    finish();

                    //loginTeam(login);
                }
            }
        });

    }
}

