package com.isen.math_hunt.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.isen.math_hunt.R;
import com.isen.math_hunt.entities.Proposition;

import java.util.List;

public class QcmAdapter extends ArrayAdapter<Proposition> {

    private Context mContext;
    private List<Proposition> propositionList;
    private int mSelectedPosition = -1;
    private RadioButton mSelectedRB;


    public QcmAdapter(@NonNull Context context, List<Proposition> propositions) {
        super(context, 0, propositions);
        mContext = context;
        this.propositionList = propositions;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        ViewHolder holder;

        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.qcm_item, parent, false);

            holder = new ViewHolder();

            Proposition currentAnswer = propositionList.get(position);


            holder.name = (TextView) listItem.findViewById(R.id.answerMcqTextView);
            holder.radioBtn = (RadioButton) listItem.findViewById(R.id.radioButtonAnswer);

            listItem.setTag(holder);
        } else {
            holder = (ViewHolder) listItem.getTag();
        }

        holder.radioBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (position != mSelectedPosition && mSelectedRB != null) {
                    mSelectedRB.setChecked(false);
                }

                mSelectedPosition = position;
                mSelectedRB = (RadioButton) v;
            }
        });


        if (mSelectedPosition != position) {
            holder.radioBtn.setChecked(false);
        } else {
            holder.radioBtn.setChecked(true);
            if (mSelectedRB != null && holder.radioBtn != mSelectedRB) {
                mSelectedRB = holder.radioBtn;
            }
        }

        holder.name.setText(getItem(position).getText());


        return listItem;
    }

    private class ViewHolder {
        TextView name;
        RadioButton radioBtn;
    }

}
