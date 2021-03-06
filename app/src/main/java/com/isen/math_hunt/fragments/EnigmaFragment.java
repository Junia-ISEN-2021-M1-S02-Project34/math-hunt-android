package com.isen.math_hunt.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;
import com.isen.math_hunt.R;
import com.isen.math_hunt.activities.GameActivity;
import com.isen.math_hunt.activities.GeoGroupActivity;
import com.isen.math_hunt.activities.SuccessActivity;
import com.isen.math_hunt.adapters.QcmAdapter;
import com.isen.math_hunt.entities.EnigmasProgression;
import com.isen.math_hunt.entities.Progression;
import com.isen.math_hunt.entities.Proposition;
import com.isen.math_hunt.entities.Team;
import com.isen.math_hunt.interfaces.Constant;
import com.isen.math_hunt.interfaces.RadioButtonDataTransfertInterface;
import com.isen.math_hunt.model.FullEnigma;
import com.isen.math_hunt.model.ProgressionPost;
import com.isen.math_hunt.model.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EnigmaFragment extends Fragment implements RadioButtonDataTransfertInterface, LocationListener {

    private ListView enigmaListView;
    private TextView enigmaTitleTextView;
    private TextView questionTextView;
    private TextView descriptionEnigma;
    private QcmAdapter qcmAdapter;
    private TextInputLayout answerTextField;
    private List<Proposition> propositionList = new ArrayList<>();
    private String currentMcqAnswerValue = "";
    private int teamEnigmaScoreValue;
    private String userAnswer;
    private Boolean isMcq = false;
    private String enigmaAnswer;
    private int currentEnigmaScore;
    private TextView attemptsTextView;

    private List<Progression> progressionList;
    private int dist;


    private int attemptsEnigmaValue;
    private int attemptsNumber;
    private AlertDialog alertDialog;


    private String teamId;
    private String currentEnigmaId;
    private String currentGeoGroupId;
    private String nextGeoGroup;

    private ImageView enigmaImageView;

    private Number posX;
    private Number posY;


    private String token;

    private ProgressDialog localisationProgressDialog;

    private ProgressBar  progressBar;

    private TextView enigmaScorePrevision;




    public EnigmaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);

        localisationProgressDialog = new ProgressDialog(getActivity());
        localisationProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        localisationProgressDialog.show();


        View mView = inflater.inflate(R.layout.fragment_enigma, null);

        progressBar = (ProgressBar) mView.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        enigmaListView = (ListView) mView.findViewById(R.id.answersListView);
        answerTextField = (TextInputLayout) mView.findViewById(R.id.answerTextField);
        TextView scoreTextView = (TextView) mView.findViewById(R.id.scoreTextView);
        enigmaTitleTextView = (TextView) mView.findViewById(R.id.enigmaTitleTextField);
        descriptionEnigma = (TextView) mView.findViewById(R.id.enigmaDescriptionTextField);
        questionTextView = (TextView) mView.findViewById(R.id.questionTextField);
        Button validateButton = (Button) mView.findViewById(R.id.validateButton);
        attemptsTextView = (TextView) mView.findViewById(R.id.attemptsTextView);
        enigmaImageView = (ImageView) mView.findViewById(R.id.enigmaImageView);
        enigmaScorePrevision = (TextView) mView.findViewById(R.id.enigmaScorePrevision);
        enigmaListView.setVisibility(View.GONE);
        answerTextField.setVisibility(View.GONE);
        enigmaImageView.setVisibility(View.GONE);

        SharedPreferences myPrefs = this.getActivity().getSharedPreferences("USER_PREFERENCES",Context.MODE_PRIVATE);
        teamId = myPrefs.getString("TEAM_ID","");
        token = myPrefs.getString("ACCESS_TOKEN","");
        currentEnigmaId = myPrefs.getString("CURRENT_ENIGMA_ID","");
        currentGeoGroupId = myPrefs.getString("CURRENT_GEOGROUP_ID","");
        attemptsNumber = myPrefs.getInt("ATTEMPTS_NUMBER",0);

        int score = myPrefs.getInt("SCORE",0);



        scoreTextView.setText("Score : " + score);

        getTeamById(teamId, token);

        getLocation();


        alertDialog = createDialog("");

        validateButton.setOnClickListener(v -> {




            if (isMcq) {
                userAnswer = currentMcqAnswerValue.toLowerCase();

            } else
                userAnswer = answerTextField.getEditText().getText().toString().toLowerCase();

            if (userAnswer.equalsIgnoreCase(enigmaAnswer)) {
                ProgressionPost progressionPost = new ProgressionPost(currentEnigmaId, true);
                updateTeamProgression(teamId, progressionPost, token);


            } else if (userAnswer.isEmpty()) {
                AlertDialog.Builder builder
                        = new AlertDialog
                        .Builder(getActivity());

                builder.setTitle("Oups ...");
                builder.setMessage("Il semble que ta r??ponse est vide !");
                builder.setCancelable(false);
                builder
                        .setPositiveButton(
                                "R??essayer",
                                (dialog, which) -> {
                                    // When the user click yes button
                                    // then app will close
                                    dialog.cancel();
                                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            } else {
                updateAttemptsNumber(teamId, token);
            }


        });

        return mView;
    }


    private void getFullEnigmaById(String id, String token) {
        Call<FullEnigma> call = RetrofitClient.getInstance().getMathHuntApiService().getFullEnigmaById(id, token);
        call.enqueue(new Callback<FullEnigma>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<FullEnigma> call, Response<FullEnigma> response) {

                try {
                    progressBar.setVisibility(View.INVISIBLE);
                    getScoreProgression();

                    FullEnigma fullEnigma = response.body();
                    posX = fullEnigma.getEnigma().getPositionX();
                    posY = fullEnigma.getEnigma().getPositionY();

                    enigmaTitleTextView.setText(fullEnigma.getEnigma().getName());
                    questionTextView.setText(fullEnigma.getEnigma().getQuestion());
                    descriptionEnigma.setText(fullEnigma.getEnigma().getDescription());
                    enigmaAnswer = fullEnigma.getAnswer().getSolution();
                    attemptsTextView.setText("Nombre d'essai(s) restant :" + ((fullEnigma.getAnswer().getAttemptsNumber() - attemptsNumber)));
                    attemptsEnigmaValue = fullEnigma.getAnswer().getAttemptsNumber();
                    currentEnigmaScore = fullEnigma.getEnigma().getScoreValue();

                    teamEnigmaScoreValue = currentEnigmaScore + teamEnigmaScoreValue;
                    enigmaScorePrevision.setText("Vous pouvez gagner : " + (teamEnigmaScoreValue));


                    if (fullEnigma.getAnswer().isMcq()) {
                        if (fullEnigma.getEnigma().getPictureUrl() != null) {
                            Picasso.with(getContext()).load(fullEnigma.getEnigma().getPictureUrl()).fit().into(enigmaImageView);
                            enigmaImageView.setVisibility(View.VISIBLE);
                        }
                        enigmaListView.setVisibility(View.VISIBLE);
                        propositionList = fullEnigma.getProposition();
                        propositionList.add(new Proposition(fullEnigma.getAnswer().getSolution()));
                        Collections.shuffle(propositionList);
                        qcmAdapter = new QcmAdapter(getActivity(), propositionList, getActivity(), EnigmaFragment.this);
                        enigmaListView.setAdapter(qcmAdapter);
                        isMcq = true;
                    } else {
                        if (fullEnigma.getEnigma().getPictureUrl() != null) {
                            Log.d("proutit", "onResponse: " + fullEnigma.getEnigma().getPictureUrl());
                            Picasso.with(getContext()).load(fullEnigma.getEnigma().getPictureUrl()).fit().into(enigmaImageView);
                            enigmaImageView.setVisibility(View.VISIBLE);
                        }
                        answerTextField.setVisibility(View.VISIBLE);

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<FullEnigma> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void updateTeamProgression(String id, ProgressionPost progressionPost, String token) {
        Call<Team> call = RetrofitClient.getInstance().getMathHuntApiService().updateTeamProgression(id, progressionPost, token);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {

                try {
                    Team currentTeam = response.body();
                    Log.d("FINISH", "onResponse: " + currentTeam.getGameFinished());

                    currentEnigmaId = currentTeam.getCurrentEnigmaId();
                    nextGeoGroup = currentTeam.getCurrentGeoGroupId();


                    if (!progressionPost.isSuccess()) {


                        Intent intent;

                        if (!currentGeoGroupId.equals(nextGeoGroup)) {
                            intent = new Intent(getContext(), GeoGroupActivity.class);

                        } else {
                            intent = new Intent(getContext(), GameActivity.class);

                        }
                        Bundle b = new Bundle();
                        b.putString("TEAM_ID", teamId);
                        b.putString("ACCESS_TOKEN", token);

                        intent.putExtras(b); //Put your id to your next Intent
                        startActivity(intent);
                    } else {
                        if (currentTeam.getGameFinished()) {
                            Intent intent = new Intent(getContext(), SuccessActivity.class);
                            Bundle b = new Bundle();
                            b.putString("TEAM_ID", teamId);
                            b.putString("ACCESS_TOKEN", token);
                            intent.putExtras(b); //Put your id to your next Intent
                            startActivity(intent);
                        } else {
                            Log.d("TAG", "onResponse: " + response);
                            currentEnigmaId = currentTeam.getCurrentEnigmaId();
                            nextGeoGroup = currentTeam.getCurrentGeoGroupId();
                            AlertDialog alertDialog = createGoodAnswerDialog();
                            alertDialog.show();

                        }
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(@NonNull Call<Team> call,@NonNull Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Prout", t.getMessage());

            }
        });
    }

    private void updateAttemptsNumber(String id, String token) {


        Call<Integer> call = RetrofitClient.getInstance().getMathHuntApiService().updateAttemptsNumber(id, token);
        call.enqueue(new Callback<Integer>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<Integer> call,@NonNull Response<Integer> response) {

                try {

                    attemptsNumber = response.body();
                    attemptsTextView.setText("Nombre d'essaies restant :" + (attemptsEnigmaValue - attemptsNumber));


                    Log.d("attemptsNumber", "Quand j'ai update la valeur est attempt : " + attemptsNumber);


                    if ((teamEnigmaScoreValue - (currentEnigmaScore / 4)) < 0) {
                        teamEnigmaScoreValue = 0;
                    } else {
                        teamEnigmaScoreValue = (teamEnigmaScoreValue - (currentEnigmaScore / 4));

                    }

                    enigmaScorePrevision.setText("Vous pouvez gagner : " + Integer.toString(teamEnigmaScoreValue));

                    if (attemptsNumber >= attemptsEnigmaValue) {
                        AlertDialog.Builder builder
                                = new AlertDialog
                                .Builder(getActivity());

                        builder.setTitle("Mince vous n'avez plus d'essai disponible");
                        builder.setMessage("Passer ?? l'??nigme suivante ");
                        builder.setCancelable(false);
                        builder
                                .setPositiveButton(
                                        "Continuer",
                                        (dialog, which) -> {

                                            ProgressionPost progressionPost = new ProgressionPost(currentEnigmaId, false);
                                            updateTeamProgression(teamId, progressionPost, token);


                                            dialog.cancel();

                                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    } else {
                        AlertDialog alertDialog = createBadAnswerDialog();
                        alertDialog.show();
                    }

                    ((GameActivity) getActivity()).updateAttemptsNumber(attemptsNumber);


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Prout", t.getMessage());

            }
        });
    }


    @Override
    public void onSetValues(Boolean isChecked, String value) {
        this.currentMcqAnswerValue = value;
    }

    public AlertDialog createGoodAnswerDialog() {
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(getActivity());

        builder.setTitle("Bravo vous avez r??ussi cette enigme");
        builder.setMessage("vous avez gagn?? " + teamEnigmaScoreValue + " cl??s \n \n Maintenant passer ?? l'??nigme suivante" );
        builder.setCancelable(false);
        builder
                .setPositiveButton(
                        "Continuer",
                        (dialog, which) -> {

                            Intent intent;

                            if (!currentGeoGroupId.equals(nextGeoGroup)) {
                                intent = new Intent(getContext(), GeoGroupActivity.class);

                            } else {
                                intent = new Intent(getContext(), GameActivity.class);

                            }
                            Bundle b = new Bundle();
                            b.putString("TEAM_ID", teamId);
                            b.putString("ACCESS_TOKEN", token);
                            intent.putExtras(b); //Put your id to your next Intent
                            startActivity(intent);
                            dialog.cancel();

                        });
        AlertDialog alertDialog = builder.create();

        return alertDialog;
    }

    public AlertDialog createBadAnswerDialog() {
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(getActivity());

        builder.setTitle("Mince mauvaise r??ponse");
        builder.setMessage("Il vous reste " + (attemptsEnigmaValue - attemptsNumber) + " essais");
        builder.setCancelable(false);
        builder
                .setPositiveButton(
                        "Continuer", (dialog, which) -> dialog.cancel());

        return builder.create();
    }


    // On recupere la geolocalisation
    @SuppressLint("MissingPermission")
    private void getLocation() {

        try {
            LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, EnigmaFragment.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onLocationChanged(@NonNull Location location) {

        List<Address> addresses = null;
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());

        try {
            if (posX !=null || posY !=null){
                addresses = geocoder.getFromLocation(posX.doubleValue(), posY.doubleValue(), 1);

                dist = (int) distance(posX, location.getLatitude(), posY, location.getLongitude());
                String address = addresses.get(0).getAddressLine(0);


                localisationProgressDialog.dismiss();

                while(address == null){
                    alertDialog.dismiss();
                }
                if(dist > Constant.ENIGMA_RADIUS) {
                    alertDialog.dismiss();
                    alertDialog = createDialog(address);
                    alertDialog.show();
                }else{
                    alertDialog.dismiss();
                }
            }



        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public static double distance(Number lat1, Number lat2, Number lon1, Number lon2) {

        final int R = 6371; // Radius of the earth
        double latDistance = Math.toRadians(lat2.doubleValue() - lat1.doubleValue());
        double lonDistance = Math.toRadians(lon2.doubleValue() - lon1.doubleValue());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1.doubleValue())) * Math.cos(Math.toRadians(lat2.doubleValue()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000;

        distance = Math.pow(distance, 2) ;

        return Math.sqrt(distance);
    }

    public AlertDialog createDialog(String address) {

        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(getContext());

        builder.setTitle("Tu y es presque !");
        builder.setMessage("L'??nigme se trouve ?? l'adresse suivante : \n" + address + "\nVous ??tes ?? : " + dist + "m");
        builder.setCancelable(false);
        alertDialog = builder.create();
        return alertDialog;
    }

    private void getTeamById(String id, String token) {
        Call<Team> call = RetrofitClient.getInstance().getMathHuntApiService().getTeamById(id, token);
        call.enqueue(new Callback<Team>() {

            @Override
            public void onResponse(@NonNull Call<Team> call,@NonNull Response<Team> response) {

                try {
                    Team currentTeam = response.body();
                    assert currentTeam != null;
                    currentGeoGroupId = currentTeam.getCurrentGeoGroupId();
                    currentEnigmaId = currentTeam.getCurrentEnigmaId();
                    progressionList = currentTeam.getProgression();
                    getFullEnigmaById(currentEnigmaId, token);

                    boolean gameIsFinish = currentTeam.getGameFinished();

                    if (gameIsFinish){
                        Intent intent = new Intent(getContext(), SuccessActivity.class);
                        Bundle b = new Bundle();
                        b.putString("TEAM_ID", teamId);
                        b.putString("ACCESS_TOKEN", token);
                        intent.putExtras(b); //Put your id to your next Intent
                        startActivity(intent);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Team> call, @NonNull Throwable t) {
                Log.d("TAG", t.getMessage());

            }
        });
    }

    public void getScoreProgression() {
        progressionList.stream().filter(progression -> progression.getGeoGroupId().equals(currentGeoGroupId)).forEach(
                progression -> {
                    List<EnigmasProgression> enigmasProgressionList = progression.getEnigmasProgression();

                    enigmasProgressionList.stream().filter(enigmasProgression -> enigmasProgression.getEnigmaId().equals(currentEnigmaId)).forEach(
                            enigmasProgression -> teamEnigmaScoreValue = enigmasProgression.getScore()
                    );
                }
        );
    }
}