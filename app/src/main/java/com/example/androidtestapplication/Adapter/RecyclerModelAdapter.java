package com.example.androidtestapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidtestapplication.AddProductActivity;
import com.example.androidtestapplication.ModelClass;
import com.example.androidtestapplication.R;

import java.util.ArrayList;

public class RecyclerModelAdapter extends RecyclerView.Adapter<RecyclerModelAdapter.ViewHolder> {
    public Context context;
    public ArrayList<ModelClass> arrdesign;
    ImageView delete, update;

    public RecyclerModelAdapter(Context context, ArrayList<ModelClass> arrdesign) {
        this.context = context;
        this.arrdesign = arrdesign;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.shoprow, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // to perform deletion
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To implement update query
                Intent intent = new Intent(context, AddProductActivity.class);
                context.startActivity(intent);
              //  Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });


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

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView productname, companyname, price;

        public ViewHolder(View view) {
            super(view);

            image = view.findViewById(R.id.imageview);
            productname = view.findViewById(R.id.nametextview1);
            companyname = view.findViewById(R.id.companytextview2);
            price = view.findViewById(R.id.pricetextview3);
            delete = view.findViewById(R.id.imageviewdustbin);
            update = view.findViewById(R.id.imageviewpencil);

        }

    }


}
