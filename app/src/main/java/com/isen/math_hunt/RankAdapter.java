package com.isen.math_hunt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RankAdapter extends ArrayAdapter<Rank> {

    private Context mContext;
    private List<Rank> classementList = new ArrayList<>();

    public RankAdapter(@NonNull Context context, List<Rank> classementList) {
        super(context, 0);
        mContext = context;
        this.classementList = classementList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        Rank currentRank = classementList.get(position);

        TextView rank = (TextView)listItem.findViewById(R.id.textView_rank);
        rank.setText(currentRank.getRank());

        TextView name = (TextView) listItem.findViewById(R.id.textView_name);
        name.setText(currentRank.getTeamName());

        TextView release = (TextView) listItem.findViewById(R.id.textView_points);
        release.setText(currentRank.getPoints());

        return listItem;
    }
}
