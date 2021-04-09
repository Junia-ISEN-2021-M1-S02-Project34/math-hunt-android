package com.isen.math_hunt.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.isen.math_hunt.R;
import com.isen.math_hunt.entities.Enigma;
import com.isen.math_hunt.entities.GeoGroup;
import com.isen.math_hunt.model.GeoGroupList;
import com.isen.math_hunt.model.RetrofitClient;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeoGroupActivity extends AppCompatActivity implements LocationListener{


    private static final long LOCATION_REFRESH_TIME = 5000;
    private static final float LOCATION_REFRESH_DISTANCE = 5;

    private double latIsen = 50.63418574831333;
    private double lonIsen = 3.048819125188862;

    private double latFlandres = 50.636597029006495;
    private double lonFlandres = 3.0694448466392066;

    private double geoGroupPosX;
    private double geoGroupPosY;

    private int dist;


    //private Button button_location;
    private TextView text_location;
    private LocationManager locationManager;
    private Button  geoGroupContinueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_groupe);

        geoGroupContinueButton = findViewById(R.id.geoGroupContinueButton);
        text_location = findViewById(R.id.text_location);
        //button_location = findViewById(R.id.button_location);

        if (ContextCompat.checkSelfPermission(GeoGroupActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(GeoGroupActivity.this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }

        getLocation();
        getGeoGroupById("605af6b76df41210dd14d0ef");

        geoGroupContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivity(EnigmaActivity.class);
            }
        });

    }

    // On recupere la geolocalisation avec cette fonction
    @SuppressLint("MissingPermission")
    private void getLocation() {

        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,LOCATION_REFRESH_TIME,LOCATION_REFRESH_DISTANCE,GeoGroupActivity.this);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void switchActivity(Class activity) {
        Intent myIntent = new Intent(this, activity);
        startActivity(myIntent);
    }

    @Override
    public void onLocationChanged(Location location) {
        dist = (int) distance(latFlandres,location.getLatitude(),lonFlandres,location.getLongitude());
        if (dist>20){
            geoGroupContinueButton.setEnabled(false);
        }
        else {
            geoGroupContinueButton.setEnabled(true);
        }
        //Toast.makeText(this, "vous êtes à : " + dist + "m", Toast.LENGTH_SHORT).show();
        text_location.setText("vous êtes à " + dist + "m");
        try {
            Geocoder geocoder = new Geocoder(GeoGroupActivity.this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            String address = addresses.get(0).getAddressLine(0);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     * @returns Distance in Meters
     */
    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2) {

        final int R = 6371; // Radius of the earth
        double height = 0;
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

    private void getGeoGroupById(String id) {
        Call<GeoGroup> call = RetrofitClient.getInstance().getMathHuntApiService().getGeoGroupById(id);
        call.enqueue(new Callback<GeoGroup>() {
            @Override
            public void onResponse(Call<GeoGroup> call, Response<GeoGroup> response) {

                try {
                    GeoGroup geoGroup = response.body();
                    Log.e("Coucou", String.valueOf(geoGroup));

                    //geoGroupPosX = geoGroup.getPositionX();
                    //geoGroupPosY = geoGroup.getPositionY();
                }
                catch (Exception e) {
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

}
