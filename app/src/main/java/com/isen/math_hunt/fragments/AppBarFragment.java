package com.isen.math_hunt.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.isen.math_hunt.R;
import com.isen.math_hunt.activities.LoginActivity;


public class AppBarFragment extends Fragment {


    public AppBarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        @SuppressLint("InflateParams") View mView = inflater.inflate(R.layout.widget_app_bar, null);


        ImageButton logOutButton = (ImageButton) mView.findViewById(R.id.logOutButton);

        logOutButton.setOnClickListener(v -> createLogOutDialog());
        return mView;
    }


    public void createLogOutDialog() {
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(getActivity());

        builder.setTitle("Êtes vous sûr ?");
        builder.setMessage("Souhaitez-vous vraiment vous déconnecter ?");
        builder.setCancelable(false);
        builder
                .setPositiveButton(
                        "Oui",
                        (dialog, which) -> {
                            Intent intent = new Intent(getContext(), LoginActivity.class);
                            startActivity(intent);
                            dialog.cancel();
                        });
        builder.setNegativeButton(
                "Non",
                (dialog, which) -> dialog.cancel());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
