package com.isen.math_hunt.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.isen.math_hunt.R;
import com.isen.math_hunt.entities.Enigma;

import java.util.ArrayList;
import java.util.List;

/**
 * EnigmaAdapter
 */
public class EnigmaAdapter extends ArrayAdapter<Enigma> {

    private final Context mContext;
    private final List<Enigma> enigmaList;

    /**
    @context : view context
    @enigmas : enigmas you want to use for display
     */
    public EnigmaAdapter(@NonNull Context context, ArrayList<Enigma> enigmas) {
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

        Enigma currentEnigma = enigmaList.get(position);

        TextView enigmaName = (TextView) listItem.findViewById(R.id.teamNameTextView);
        enigmaName.setText(currentEnigma.getName());

        TextView enigmaValueScore = (TextView) listItem.findViewById(R.id.teamScoreTextView);
        enigmaValueScore.setText(Integer.toString(currentEnigma.getScoreValue()));


        return listItem;
    }
}
