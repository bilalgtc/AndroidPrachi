package com.example.androidtestapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class MyAdapter extends PagerAdapter {
Context context;
ArrayList<Integer> arraylist;
LayoutInflater layoutInflater;

public MyAdapter(Context context, ArrayList<Integer> arrayList ){
    this.context = context;
    this.arraylist = arrayList;
    layoutInflater = LayoutInflater.from(context);
}

    @Override
    public int getCount() {
        return arraylist.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
    View view = layoutInflater.inflate(R.layout.item, container, false);
        ImageView imageView = view.findViewById(R.id.img);
        imageView.setImageResource(arraylist.get(position));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
