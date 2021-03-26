package com.isen.math_hunt.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.isen.math_hunt.R;
import com.isen.math_hunt.entities.GeoGroup;

import java.util.ArrayList;
import java.util.List;

public class GeoGroupAdapter extends ArrayAdapter<GeoGroup> {

    private final Context mContext;
    private final List<GeoGroup> geoGroupsList;

    public GeoGroupAdapter(@NonNull Context context, ArrayList<GeoGroup> list) {
        super(context, 0 , list);
        mContext = context;
        geoGroupsList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.geo_group_item,parent,false);

        GeoGroup currentGeoGroup = geoGroupsList.get(position);

        TextView geoGroupName = (TextView) listItem.findViewById(R.id.geoGroupName_textView);
        geoGroupName.setText(currentGeoGroup.getName());

        TextView geoGroupScore = (TextView) listItem.findViewById(R.id.geoGroupScore_textView);
        geoGroupScore.setText(Integer.toString(currentGeoGroup.getScore()));

        ImageButton geoGroupDrawer = (ImageButton) listItem.findViewById(R.id.geoGroupDrawer_button);

        return listItem;
    }
}
