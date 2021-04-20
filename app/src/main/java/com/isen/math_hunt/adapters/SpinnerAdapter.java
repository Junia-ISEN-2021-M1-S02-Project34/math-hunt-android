package com.isen.math_hunt.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.isen.math_hunt.R;

import java.util.List;

public class SpinnerAdapter extends BaseAdapter {
    Context context;
    List<String> gameName;
    LayoutInflater inflter;

    public SpinnerAdapter(Context applicationContext, List<String> countryNames) {
        this.context = applicationContext;
        this.gameName = countryNames;
        inflter = (LayoutInflater.from(applicationContext));
    }


    @Override
    public int getCount() {
        return gameName.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.spinner_item, null);
        TextView names = (TextView) view.findViewById(R.id.GameName);
        names.setText(gameName.get(i));
        return view;
    }
}