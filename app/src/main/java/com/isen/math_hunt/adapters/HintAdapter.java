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
import com.isen.math_hunt.entities.Hint;

import java.util.ArrayList;
import java.util.List;

public class HintAdapter extends ArrayAdapter<Hint> {

    private final Context mContext;
    private final List<Hint> hintList;

    public HintAdapter(@NonNull Context context, ArrayList<Hint> hints) {
        super(context, 0, hints);
        mContext = context;
        this.hintList = hints;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.hint_item,parent,false);

        Hint currentHint = hintList.get(position);

        TextView hintName = (TextView)listItem.findViewById(R.id.hintNameTextView);
        hintName.setText(currentHint.getName());

        TextView hintPenalty = (TextView) listItem.findViewById(R.id.hintPenaltyTextView);
        hintPenalty.setText(String.valueOf(currentHint.getPenalty()));

        return listItem;
    }

}
