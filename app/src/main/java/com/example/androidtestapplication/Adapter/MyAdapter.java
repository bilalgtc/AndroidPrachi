package com.example.androidtestapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.androidtestapplication.R;

import java.util.ArrayList;

public class MyAdapter extends PagerAdapter {
    Context context;
    ArrayList<Integer> arraylist;
    LayoutInflater layoutInflater;
    String[] text;

    public MyAdapter(Context context, ArrayList<Integer> arrayList, String[] text) {
        this.context = context;
        this.arraylist = arrayList;
        this.text = text;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.item, container, false);
        TextView txt1 = view.findViewById(R.id.textview);
        ImageView imageView = view.findViewById(R.id.img);
        imageView.setImageResource(arraylist.get(position));
        txt1.setText(text[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}