package com.isen.math_hunt.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.isen.math_hunt.R;
import com.isen.math_hunt.entities.GeoGroup;
import com.isen.math_hunt.entities.Team;
import com.isen.math_hunt.model.RetrofitClient;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Locale;



public class GeoGroupActivity extends AppCompatActivity implements LocationListener {


    private static final long LOCATION_REFRESH_TIME = 5000;
    private static final float LOCATION_REFRESH_DISTANCE = 5;

    private Number geoGroupPosX; // latitude
    private Number geoGroupPosY; // longitude
    private Number geoGroupRadius; // radius en metres


    //private Button button_location;
    private TextView text_location;
    private Button geoGroupContinueButton;

    private ImageView geoGroupImageView;

    private String teamId;
    private String token;

    private String currentGeoGroupId;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_groupe);

        progressDialog = new ProgressDialog(GeoGroupActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();


        geoGroupContinueButton = findViewById(R.id.geoGroupContinueButton);
        text_location = findViewById(R.id.text_location);
        //button_location = findViewById(R.id.button_location);

        geoGroupImageView = (ImageView) findViewById(R.id.geoGroupImageView);

        getLocation();


        Bundle b = getIntent().getExtras();
        teamId = b.getString("TEAM_ID");
        token = b.getString("ACCESS_TOKEN");

        getTeamById(teamId);
        geoGroupContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GeoGroupActivity.this, GameActivity.class);
                Bundle b = new Bundle();
                b.putString("TEAM_ID", teamId);
                b.putString("ACCESS_TOKEN", token);
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
                finish();
            }
        });

    }

    private void getTeamById(String id) {
        Call<Team> call = RetrofitClient.getInstance().getMathHuntApiService().getTeamById(id, token);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {

                try {
                    Team team = response.body();
                    currentGeoGroupId = team.getCurrentGeoGroupId();

                    getGeoGroupById(currentGeoGroupId);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Log.d("onFailure", t.getMessage());
            }
        });
    }

    public void switchActivity(Class activity) {
        Intent myIntent = new Intent(this, activity);
        startActivity(myIntent);
    }

    @Override
    public void onLocationChanged(Location location) {
        // en metres
        int dist = (int) distance(geoGroupPosX, location.getLatitude(), geoGroupPosY, location.getLongitude());
        if (dist > 2000) { // changer la valeur par geoGroupRadius
            geoGroupContinueButton.setEnabled(false);
            geoGroupContinueButton.setText("Encore un peu de marche!");

        } else {
            geoGroupContinueButton.setEnabled(true);
            geoGroupContinueButton.setText("Vous y êtes! ");
        }
        //Toast.makeText(this, "vous êtes à : " + dist + "m", Toast.LENGTH_SHORT).show();
        text_location.setText("vous êtes à " + dist + "m");
        Log.d("tag","heho la");
        try {
            Geocoder geocoder = new Geocoder(GeoGroupActivity.this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            String address = addresses.get(0).getAddressLine(0);
            Log.d("tag",address);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     * <p>
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     *
     * @returns Distance in Meters
     */
    public static double distance(Number lat1, Number lat2, Number lon1, Number lon2) {

        final int R = 6371; // Radius of the earth
        //double height = 0;
        double latDistance = Math.toRadians(lat2.doubleValue() - lat1.doubleValue());
        double lonDistance = Math.toRadians(lon2.doubleValue() - lon1.doubleValue());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1.doubleValue())) * Math.cos(Math.toRadians(lat2.doubleValue()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        distance = Math.pow(distance, 2)  /* + Math.pow(height, 2) */;

        return Math.sqrt(distance);
    }

    // On recupere la geolocalisation
    @SuppressLint("MissingPermission")
    private void getLocation() {

        try {
            LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME, LOCATION_REFRESH_DISTANCE, GeoGroupActivity.this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getGeoGroupById(String id) {
        Call<GeoGroup> call = RetrofitClient.getInstance().getMathHuntApiService().getGeoGroupById(id,token);
        call.enqueue(new Callback<GeoGroup>() {
            @Override
            public void onResponse(Call<GeoGroup> call, Response<GeoGroup> response) {

                try {
                    progressDialog.dismiss();
                    GeoGroup geoGroup = response.body();

                    geoGroupPosX = geoGroup.getPositionX();
                    geoGroupPosY = geoGroup.getPositionY();

                    Picasso.with(GeoGroupActivity.this).load(geoGroup.getPictureUrl()).fit().into(geoGroupImageView);


                    //geoGroupImageView.setImageDrawable(LoadImageFromWebOperations(geoGroup.getPictureUrl()));
                    //Log.d("IMAGE", "onResponse: " + geoGroup.getPictureUrl());


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GeoGroup> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Coucou", t.getMessage());

            }
        });
    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }


    }



}
