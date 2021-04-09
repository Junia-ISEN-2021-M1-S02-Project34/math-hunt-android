package com.isen.math_hunt.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.isen.math_hunt.R;
import com.isen.math_hunt.entities.Enigma;
import com.isen.math_hunt.entities.GeoGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * geoGroupAdapter
 */
public class GeoGroupAdapter extends ArrayAdapter<GeoGroup> {

    private final Context mContext;
    private final List<GeoGroup> geoGroupsList;
    private boolean showEnigmaList = Boolean.FALSE;

    /**
     * @param context   view
     * @param geoGroups you want to use for display
     */
    public GeoGroupAdapter(@NonNull Context context, ArrayList<GeoGroup> geoGroups) {
        super(context, 0, geoGroups);
        mContext = context;
        geoGroupsList = geoGroups;
    }

    /**
     * get view Element
     *
     * @param position of items
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.geo_group_item, parent, false);

        GeoGroup currentGeoGroup = geoGroupsList.get(position);

        TextView geoGroupName = (TextView) listItem.findViewById(R.id.geoGroupName_textView);
        geoGroupName.setText(currentGeoGroup.getName());

        TextView geoGroupScore = (TextView) listItem.findViewById(R.id.geoGroupScore_textView);
        //geoGroupScore.setText(Integer.toString(currentGeoGroup.getScore()));

        ImageButton geoGroupDrawerButton = (ImageButton) listItem.findViewById(R.id.geoGroupDrawer_button);
        ListView enigmaListView = (ListView) listItem.findViewById(R.id.enigmaListView);
        enigmaListView.setVisibility(View.GONE);
        geoGroupDrawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEnigmaList = !showEnigmaList;
                enigmaListView.setVisibility(showEnigmaList ? View.VISIBLE : View.GONE);

            }
        });

/*
        ArrayList<Enigma> enigmaList = (ArrayList<Enigma>) currentGeoGroup.getEnigmaList();


        ViewGroup.LayoutParams layout = enigmaListView.getLayoutParams();
        layout.height = dpToPx(40, mContext) * enigmaList.size();
        enigmaListView.setLayoutParams(layout);

        EnigmaAdapter enigmaAdapter = new EnigmaAdapter(getContext(), enigmaList);
        enigmaListView.setAdapter(enigmaAdapter);
*/
        return listItem;
    }

    /**
     * Convert dp to pixel
     *
     * @param dp      wanted
     * @param context view
     * @return pixel conversion
     */
    public static int dpToPx(int dp, Context context) {
        float density = context.getResources()
                .getDisplayMetrics()
                .density;
        return Math.round((float) dp * density);
    }
}
