package com.example.androidtestapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerModelAdapter extends RecyclerView.Adapter<RecyclerModelAdapter.ViewHolder> {
     Context context;
    ArrayList<ModelClass> arrdesign;
    RecyclerModelAdapter(Context context, ArrayList<ModelClass> arrdesign){
        this.context = context;
        this.arrdesign = arrdesign;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.shoprow, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.image.setImageResource(arrdesign.get(position).image);
        holder.productname.setText(arrdesign.get(position).modelname);
        holder.companyname.setText(arrdesign.get(position).comapnyname);
        holder.price.setText(arrdesign.get(position).price);
    }

    @Override
    public int getItemCount() {
        return arrdesign.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image, imagepencil, imagedustbin;
        TextView productname, companyname, price;

        public ViewHolder(View view){
            super(view);

            image = view.findViewById(R.id.imageview);
            productname = view.findViewById(R.id.textview1);
            companyname = view.findViewById(R.id.textview2);
            price = view.findViewById(R.id.textview3);

        }

    }


}
