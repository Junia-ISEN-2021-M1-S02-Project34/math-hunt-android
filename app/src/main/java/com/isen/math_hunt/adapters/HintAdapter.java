package com.isen.math_hunt.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.isen.math_hunt.R;
import com.isen.math_hunt.entities.Hint;

import java.util.ArrayList;
import java.util.List;

public class HintAdapter extends ArrayAdapter<Hint> {

    private final Context mContext;
    private final List<Hint> hintList;
    private boolean showHintText = Boolean.FALSE;
    private List<String> usedHintsIds;
    private TextView hintTextView;


    public HintAdapter(@NonNull Context context, List<Hint> hints, List<String> usedHintsIds) {
        super(context, 0, hints);
        mContext = context;
        this.hintList = hints;
        this.usedHintsIds = usedHintsIds;
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


        hintTextView = (TextView) listItem.findViewById(R.id.hintTextTextView);
        hintTextView.setVisibility(View.GONE);

        for (String hintId : usedHintsIds
        ) {
            if (hintId.equals(currentHint.get_id())) {
                getHintButton.setText("Ouvrir");
                getHintButton.setBackgroundColor(getContext().getColor(R.color.mathHuntTheme));

            }

        }
        hintTextView.setText(currentHint.getText());


        getHintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (String hintId : usedHintsIds
                ) {
                    if (hintId.equals(currentHint.get_id())) {
                        showHint();

                    }else{
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
                                                usedHintsIds.add(currentHint.get_id());
                                                notifyDataSetChanged();
                                                showHint();
                                                dialog.cancel();
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
            }
        });



        return listItem;
    }

    public void showHint() {
        showHintText = !showHintText;
        hintTextView.setVisibility(showHintText ? View.VISIBLE : View.GONE);
    }

}
