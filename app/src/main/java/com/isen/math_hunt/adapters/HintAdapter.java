package com.isen.math_hunt.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    private boolean showHintText = Boolean.FALSE;


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
            listItem = LayoutInflater.from(mContext).inflate(R.layout.item_hint,parent,false);

        Hint currentHint = hintList.get(position);

        TextView hintName = (TextView)listItem.findViewById(R.id.hintNameTextView);
        hintName.setText(currentHint.getName());

        TextView hintPenalty = (TextView) listItem.findViewById(R.id.hintPenaltyTextView);
        hintPenalty.setText(String.valueOf(currentHint.getPenalty()));

        Button getHintButton = (Button)listItem.findViewById(R.id.getHintButton);

        TextView hintTextView = (TextView) listItem.findViewById(R.id.hintTextTextView);
        hintTextView.setVisibility(View.GONE);


        getHintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHintText = !showHintText;
                hintTextView.setVisibility(showHintText ? View.VISIBLE : View.GONE);
            }
        });

        hintTextView.setText(currentHint.getText());


        return listItem;
    }

}
