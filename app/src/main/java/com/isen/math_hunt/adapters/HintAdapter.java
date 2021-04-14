package com.isen.math_hunt.adapters;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.isen.math_hunt.R;
import com.isen.math_hunt.entities.EnigmasProgression;
import com.isen.math_hunt.entities.Hint;
import com.isen.math_hunt.entities.Progression;
import com.isen.math_hunt.entities.Team;
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
    private List<Progression> progressions;
    private List<String> usedHintsIds;
    private Team currentTeam;
    private ImageView lockImageView;
    private Hint currentHint;
    private TextView hintTextView;
    List<EnigmasProgression> enigmasProgressions;


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

        currentHint = hintList.get(position);


        TextView hintName = (TextView) listItem.findViewById(R.id.hintNameTextView);
        hintName.setText(currentHint.getName());

        TextView hintPenalty = (TextView) listItem.findViewById(R.id.hintPenaltyTextView);
        hintPenalty.setText(String.valueOf("Pénalité : " + currentHint.getPenalty()));

        lockImageView = (ImageView) listItem.findViewById(R.id.lockImageView);
        lockImageView.setClickable(false);
        lockImageView.setFocusable(false);


        hintTextView = (TextView) listItem.findViewById(R.id.hintTextTextView);
        hintTextView.setVisibility(View.GONE);


        hintTextView.setText(currentHint.getText());
        hintTextView.setFocusable(false);
        hintTextView.setClickable(false);

        lockImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHintText = !showHintText;
                hintTextView.setVisibility(showHintText ? View.VISIBLE : View.GONE);
            }
        });

        for (String hint :usedHintsIds) {
            if (hint.equals(currentHint.get_id())){
                lockImageView.setClickable(true);
                lockImageView.setFocusable(true);
                lockImageView.setImageResource(R.drawable.ic_unlock);
            }
        }


        return listItem;
    }




}
