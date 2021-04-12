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
import com.isen.math_hunt.entities.Team;

import java.util.List;

public class RankAdapter extends ArrayAdapter<Team> {

    private Context mContext;
    private List<Team> rankList;

    public RankAdapter(@NonNull Context context, List<Team> rankList) {
        super(context, 0, rankList);
        mContext = context;
        this.rankList = rankList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.item_ranking,parent,false);

        Team currentTeam = rankList.get(position);

        TextView teamNameTextView = (TextView)listItem.findViewById(R.id.teamNameTextView);
        teamNameTextView.setText(currentTeam.getUsername());

        TextView teamScoreTextView = (TextView) listItem.findViewById(R.id.teamScoreTextView);
        teamScoreTextView.setText(String.valueOf(currentTeam.getScore()));


        return listItem;
    }
}