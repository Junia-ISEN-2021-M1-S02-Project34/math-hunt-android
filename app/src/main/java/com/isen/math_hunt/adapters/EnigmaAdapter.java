package com.isen.math_hunt.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.isen.math_hunt.R;
import com.isen.math_hunt.entities.EnigmasProgression;

import java.util.List;

/**
 * EnigmaAdapter
 */
public class EnigmaAdapter extends ArrayAdapter<EnigmasProgression> {

    private final Context mContext;
    private final List<EnigmasProgression> enigmaList;

    /**
    @context : view context
    @enigmas : enigmas you want to use for display
     */
    public EnigmaAdapter(@NonNull Context context, List<EnigmasProgression> enigmas) {
        super(context, 0, enigmas);
        mContext = context;
        this.enigmaList = enigmas;
    }

    /**
    get view Element
    @return listItem
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.item_progression_enigma, parent, false);

        EnigmasProgression currentEnigma = enigmaList.get(position);

        TextView enigmaName = (TextView) listItem.findViewById(R.id.teamNameTextView);
        enigmaName.setText(currentEnigma.getEnigmaName());

        ImageView enigmaValidationImageView = (ImageView) listItem.findViewById(R.id.enigmaValidationImageView);

        if (currentEnigma.isDone()){
            enigmaValidationImageView.setImageResource(R.drawable.ic_done_black_24dp);
        }else  enigmaValidationImageView.setImageResource(R.drawable.ic_close_black_24dp);

        TextView enigmaValueScore = (TextView) listItem.findViewById(R.id.geoGroupScore_textView);
        enigmaValueScore.setText(Integer.toString(currentEnigma.getScore()) + "/" + Integer.toString(currentEnigma.getScoreValue()));


        return listItem;
    }
}
