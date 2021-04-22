package com.isen.math_hunt.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.isen.math_hunt.R;
import com.isen.math_hunt.activities.SuccessActivity;
import com.isen.math_hunt.entities.Hint;
import com.isen.math_hunt.entities.Team;
import com.isen.math_hunt.fragments.EnigmaFragment;
import com.isen.math_hunt.fragments.HintFragment;
import com.isen.math_hunt.model.HintId;
import com.isen.math_hunt.model.ProgressionPost;
import com.isen.math_hunt.model.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HintAdapter extends ArrayAdapter<Hint> {

    private final Context mContext;
    private final List<Hint> hintList;
    private boolean showHintText = Boolean.FALSE;
    private List<String> usedHintsIds;
    private String teamId;
    private String token;

    private  HintAdapter hintAdapter;


    public HintAdapter(@NonNull Context context, List<Hint> hints, List<String> usedHintsIds, String teamId, String token) {
        super(context, 0, hints);
        mContext = context;
        this.hintList = hints;
        this.usedHintsIds = usedHintsIds;
        this.teamId = teamId;
        this.token = token;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.item_hint, parent, false);

        Hint currentHint = hintList.get(position);
        Log.d("TAG", "getView: " + currentHint.get_id());

        TextView hintName = (TextView) listItem.findViewById(R.id.hintNameTextView);
        hintName.setText(currentHint.getName());

        TextView hintPenalty = (TextView) listItem.findViewById(R.id.hintPenaltyTextView);
        hintPenalty.setText(String.valueOf("Pénalité : " + currentHint.getPenalty()));

        Button getHintButton = (Button) listItem.findViewById(R.id.getHintButton);
        getHintButton.setText("Bloqué");
        getHintButton.setBackgroundColor(getContext().getColor(R.color.buttonDisable));


        TextView hintTextView = (TextView) listItem.findViewById(R.id.hintTextTextView);
        hintTextView.setVisibility(View.GONE);

        if (usedHintsIds != null) {
            for (String hintId : usedHintsIds) {
                if (hintId.equals(currentHint.get_id())) {
                    getHintButton.setText("Ouvrir");
                    getHintButton.setBackgroundColor(getContext().getColor(R.color.mathHuntTheme));

                }

            }
        }
        hintTextView.setText(currentHint.getText());


        getHintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View parentRow = (View) v.getParent();
                ListView listView = (ListView) parentRow.getParent();
                final int position = listView.getPositionForView(parentRow);

                Hint currentHint = hintList.get(position);

                if (usedHintsIds != null) {
                    if (usedHintsIds.contains(currentHint.get_id())) {
                        showHint(hintTextView);
                    } else {
                        AlertDialog.Builder builder
                                = new AlertDialog
                                .Builder(getContext());

                        builder.setTitle("Débloquer indice");
                        builder.setMessage("Voulez vous vraiment débloquer cette indice ?");
                        builder.setCancelable(false);
                        builder
                                .setPositiveButton(
                                        "Oui",
                                        new DialogInterface
                                                .OnClickListener() {

                                            @Override
                                            public void onClick(DialogInterface dialog,
                                                                int which) {
                                                // When the user click yes button
                                                // then app will close
                                                //Todo : update la bdd

                                                getHintButton.setText("Ouvrir");
                                                getHintButton.setBackgroundColor(getContext().getColor(R.color.mathHuntTheme));
                                                updateTeamUsedHint(teamId, new HintId(hintList.get(position).get_id()));
                                                usedHintsIds.add(hintList.get(position).get_id());


                                                if (usedHintsIds==null){
                                                    List<String> newUsedHintsId = new ArrayList<>();
                                                    newUsedHintsId.add(hintList.get(position).get_id());
                                                    hintAdapter = new HintAdapter(getContext(), hintList, newUsedHintsId, teamId, token);
                                                    listView.setAdapter(hintAdapter);
                                                    dialog.cancel();
                                                }else{
                                                    usedHintsIds.add(hintList.get(position).get_id());
                                                    hintAdapter = new HintAdapter(getContext(), hintList, usedHintsIds, teamId,token);
                                                    listView.setAdapter(hintAdapter);
                                                    dialog.cancel();
                                                }
                                            }
                                        });
                        builder
                                .setNegativeButton(
                                        "Annuler",
                                        new DialogInterface
                                                .OnClickListener() {

                                            @Override
                                            public void onClick(DialogInterface dialog,
                                                                int which) {
                                                // When the user click yes button
                                                // then app will close
                                                dialog.cancel();
                                            }
                                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    }
                } else {
                    AlertDialog.Builder builder
                            = new AlertDialog
                            .Builder(getContext());

                    builder.setTitle("Débloquer indice");
                    builder.setMessage("Voulez vous vraiment débloquer cette indice ?");
                    builder.setCancelable(false);
                    builder
                            .setPositiveButton(
                                    "Oui",
                                    new DialogInterface
                                            .OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog,
                                                            int which) {
                                            // When the user click yes button
                                            // then app will close
                                            getHintButton.setText("Ouvrir");
                                            getHintButton.setBackgroundColor(getContext().getColor(R.color.mathHuntTheme));
                                            updateTeamUsedHint(teamId, new HintId(hintList.get(position).get_id()));
                                            Log.d("Need", "onClick: " + hintList.get(position).get_id());

                                            if (usedHintsIds==null){
                                                List<String> newUsedHintsId = new ArrayList<>();
                                                newUsedHintsId.add(hintList.get(position).get_id());
                                                hintAdapter = new HintAdapter(getContext(), hintList, newUsedHintsId, teamId,token);
                                                listView.setAdapter(hintAdapter);
                                                dialog.cancel();
                                            }else{
                                                usedHintsIds.add(hintList.get(position).get_id());
                                                hintAdapter = new HintAdapter(getContext(), hintList, usedHintsIds, teamId,token);
                                                listView.setAdapter(hintAdapter);
                                                dialog.cancel();
                                            }


                                        }
                                    });
                    builder
                            .setNegativeButton(
                                    "Annuler",
                                    new DialogInterface
                                            .OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog,
                                                            int which) {
                                            // When the user click yes button
                                            // then app will close
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }

            }
        });


        return listItem;
    }

    public void showHint(TextView hintTextView) {
        showHintText = !showHintText;
        hintTextView.setVisibility(showHintText ? View.VISIBLE : View.GONE);
    }

    private void updateTeamUsedHint(String id, HintId hintId) {


        Call<Team> call = RetrofitClient.getInstance().getMathHuntApiService().updateTeamUsedHint(id, hintId,token);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {

                try {
                    Team currentTeam = response.body();

                    Bundle hintBundle = new Bundle();
                    hintBundle.putString("TEAM_ID", teamId);
                    hintBundle.putString("CURRENT_ENIGMA_ID", currentTeam.getCurrentEnigmaId());


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}
