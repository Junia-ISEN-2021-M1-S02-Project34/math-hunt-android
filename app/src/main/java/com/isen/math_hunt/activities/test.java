package com.isen.math_hunt.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.isen.math_hunt.R;
import com.isen.math_hunt.entities.Enigma;
import com.isen.math_hunt.model.EnigmaList;
import com.isen.math_hunt.model.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class test extends AppCompatActivity {

    ListView listView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        textView = findViewById(R.id.TextViewTest);
        //getEnigmasById("606321de67829e7540fbea1b");

    }

    private void getEnigmas() {
        Call<EnigmaList> call = RetrofitClient.getInstance().getMyApi().getEnigmas();
        call.enqueue(new Callback<EnigmaList>() {
            @Override
            public void onResponse(Call<EnigmaList> call, Response<EnigmaList> response) {
                EnigmaList enigma = response.body();

            }

            @Override
            public void onFailure(Call<EnigmaList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getEnigmasById(String id) {
        Call<Enigma> call = RetrofitClient.getInstance().getMyApi().getEnigmaById(id);
        call.enqueue(new Callback<Enigma>() {
            @Override
            public void onResponse(Call<Enigma> call, Response<Enigma> response) {
                Enigma enigma = response.body();
                Log.e("Coucou", String.valueOf(enigma.getName()));

            }

            @Override
            public void onFailure(Call<Enigma> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

