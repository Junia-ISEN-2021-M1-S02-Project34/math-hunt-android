package com.isen.math_hunt;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.isen.math_hunt.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MygeoGroupRecyclerViewAdapter extends RecyclerView.Adapter<MygeoGroupRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;

    public MygeoGroupRecyclerViewAdapter(List<DummyItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_geo_group, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.score.setText("COUCOU");
        holder.name.setText("COUCOU flavinou ");
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView score;
        public final TextView name;
        public final ImageButton drawerButton;
        public final RecyclerView list;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            score = (TextView) view.findViewById(R.id.score);
            name = (TextView) view.findViewById(R.id.name);
            drawerButton = (ImageButton) view.findViewById(R.id.drawerButton);
            list = (RecyclerView) view.findViewById(R.id.list);
            List<MyenigmaRecyclerViewAdapter> your_array_list = new ArrayList<MyenigmaRecyclerViewAdapter>();
            your_array_list.add("foo");
            your_array_list.add("bar");

            // This is the array adapter, it takes the context of the activity as a // first parameter, the type of list view as a second parameter and your // array as a third parameter.
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<MyenigmaRecyclerViewAdapter>(
                    this,
                    R.layout.fragment_enigma,
                    your_array_list );


            list.setAdapter(arrayAdapter);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + name.getText() + "'";
        }
    }
}