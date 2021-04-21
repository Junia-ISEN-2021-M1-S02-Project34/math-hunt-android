package com.isen.math_hunt.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.isen.math_hunt.R;
import com.isen.math_hunt.activities.GameActivity;
import com.isen.math_hunt.activities.GeoGroupActivity;
import com.isen.math_hunt.activities.LoginActivity;
import com.isen.math_hunt.activities.SuccessActivity;


public class AppBarFragment extends Fragment {


    public AppBarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View mView = inflater.inflate(R.layout.widget_app_bar, null);


        ImageButton logOutButton = (ImageButton) mView.findViewById(R.id.logOutButton);

        logOutButton.setOnClickListener(v -> {
            Log.d("buttondd", "onCreateView: ");
            AlertDialog logOutDialog = createLogOutDialog();
            logOutDialog.show();
        });
        return mView;
    }


    public AlertDialog createLogOutDialog() {
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
                (dialog, which) -> {

                    dialog.cancel();

                });
        AlertDialog alertDialog = builder.create();

        return alertDialog;
    }

}
