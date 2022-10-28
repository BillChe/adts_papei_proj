package com.example.adts_papei_proj.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.adts_papei_proj.R;
import com.example.adts_papei_proj.data.model.UserTestResult;

import java.util.List;

public class ListAdapter extends ArrayAdapter<UserTestResult> {

    private int resourceLayout;
    private Context mContext;

    public ListAdapter(Context context, int resource, List<UserTestResult> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        UserTestResult p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.id);
            TextView tt2 = (TextView) v.findViewById(R.id.categoryId);
            TextView tt3 = (TextView) v.findViewById(R.id.description);

            if (tt1 != null) {
                tt1.setText(p.getUid());
            }

            if (tt2 != null) {
                tt2.setText(p.getScore());
            }

            if (tt3 != null) {
                tt3.setText(String.valueOf(p.getDate()));
            }
        }

        return v;
    }

}