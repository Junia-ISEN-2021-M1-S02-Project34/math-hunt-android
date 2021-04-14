package com.isen.math_hunt.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.isen.math_hunt.R;
import com.isen.math_hunt.entities.EnigmasProgression;
import com.isen.math_hunt.entities.Progression;

import java.util.List;

/**
 * geoGroupAdapter
 */
public class ProgressionAdapter extends ArrayAdapter<Progression> {

    private final Context mContext;
    private final List<Progression> progressionList;
    private boolean showEnigmaList = Boolean.FALSE;

    /**
     * @param context   view
     * @param progressions you want to use for display
     */
    public ProgressionAdapter(@NonNull Context context, List<Progression> progressions) {
        super(context, 0, progressions);
        mContext = context;
        progressionList = progressions;
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
            listItem = LayoutInflater.from(mContext).inflate(R.layout.item_progression_geo_group, parent, false);

        Progression progression = progressionList.get(position);

        TextView geoGroupName = (TextView) listItem.findViewById(R.id.geoGroupName_textView);
        geoGroupName.setText(progression.getGeoGroupName());

        TextView geoGroupScore = (TextView) listItem.findViewById(R.id.geoGroupScore_textView);
        geoGroupScore.setText(Integer.toString(progression.getGeoGroupScoreValue()));

        ImageButton geoGroupDrawerButton = (ImageButton) listItem.findViewById(R.id.geoGroupDrawer_button);
        ListView enigmaListView = (ListView) listItem.findViewById(R.id.enigmaListView);
        enigmaListView.setVisibility(View.GONE);
        enigmaListView.setClickable(false);

        geoGroupDrawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEnigmaList = !showEnigmaList;
                enigmaListView.setVisibility(showEnigmaList ? View.VISIBLE : View.GONE);

            }
        });


        List<EnigmasProgression> enigmaList = (List<EnigmasProgression>) progression.getEnigmasProgression();


        ViewGroup.LayoutParams layout = enigmaListView.getLayoutParams();
        layout.height = dpToPx(40, mContext) * enigmaList.size();
        enigmaListView.setLayoutParams(layout);

        EnigmaAdapter enigmaAdapter = new EnigmaAdapter(getContext(), enigmaList);
        enigmaListView.setAdapter(enigmaAdapter);

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
