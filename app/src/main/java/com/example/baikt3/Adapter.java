package com.example.baikt3;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter extends BaseAdapter {
    Activity activity;
    List<Nuoc> nuoc;

    public Adapter(Activity activity, List<Nuoc> phepTinhs) {
        this.activity = activity;
        this.nuoc = nuoc;
    }

    @Override
    public int getCount() {
        int size = nuoc.size();
        return size;
    }

    @Override
    public Object getItem(int i) {
        return nuoc.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = activity.getLayoutInflater();

        view = inflater.inflate(R.layout.nuoc_item, null);

        TextView tvName = (TextView) view.findViewById(R.id.tvPhepTinh);
        ImageView img = (ImageView)view.findViewById(R.id.iconPhepTinh);
        tvName.setText(nuoc.get(i).getTenNuoc());
        img.setImageResource(nuoc.get(i).getIcon());

        return view;
    }
}
