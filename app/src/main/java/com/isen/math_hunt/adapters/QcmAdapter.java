package com.isen.math_hunt.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.isen.math_hunt.R;
import com.isen.math_hunt.entities.Answer;
import com.isen.math_hunt.entities.Proposition;

import java.util.ArrayList;
import java.util.List;

public class QcmAdapter extends ArrayAdapter<Proposition> {

    private Context mContext;
    private List<Proposition> answerList;

    public QcmAdapter(@NonNull Context context, ArrayList<Proposition> propositions) {
        super(context, 0 , propositions);
        mContext = context;
        this.answerList = propositions;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.qcm_item,parent,false);

        Proposition currentAnswer = answerList.get(position);


        RadioButton radioButtonAnswer = (RadioButton) listItem.findViewById(R.id.radioButtonAnswer);
        radioButtonAnswer.setText(currentAnswer.getText());


        return listItem;
    }
}
