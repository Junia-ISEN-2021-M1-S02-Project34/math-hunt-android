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
import com.isen.math_hunt.model.RankingResponse;

import java.util.List;

public class RankAdapter extends ArrayAdapter<RankingResponse.Rank> {

    private Context mContext;
    private List<RankingResponse.Rank> rankList;

    public RankAdapter(@NonNull Context context, List<RankingResponse.Rank> rankList) {
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

        RankingResponse.Rank currentRank = rankList.get(position);

        TextView teamNameTextView = (TextView)listItem.findViewById(R.id.teamNameTextView);
        teamNameTextView.setText(currentRank.getUserName());

        TextView teamScoreTextView = (TextView) listItem.findViewById(R.id.geoGroupScore_textView);
        teamScoreTextView.setText(String.valueOf(currentRank.getScore()));


        return listItem;
    }
}