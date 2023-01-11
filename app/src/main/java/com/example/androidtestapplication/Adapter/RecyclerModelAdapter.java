package com.example.androidtestapplication.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidtestapplication.AddProductActivity;
import com.example.androidtestapplication.Database.DataBaseHelper;
import com.example.androidtestapplication.ModelClass;
import com.example.androidtestapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerModelAdapter extends RecyclerView.Adapter<RecyclerModelAdapter.ViewHolder> {
    public Context context;
    public ArrayList<ModelClass> arrdesign;
    ImageView delete, update;
    String key, image, name, company, price, COLOR;
    boolean flag;

    public RecyclerModelAdapter(Context context, ArrayList<ModelClass> arrdesign) {
        this.context = context;
        this.arrdesign = arrdesign;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ModelClass model = arrdesign.get(position);
         key = model.getKey();
         image = model.getImage();
         name = model.getModelname();
         company = model.getComapnyname();
         price = model.getPrice();
         COLOR = model.getColor();

        Picasso.get().load(image).into(holder.imageview);
        holder.productname.setText(name);
        holder.companyname.setText(company);
        holder.price.setText(price);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // To implement update query
                Intent intent = new Intent(context, AddProductActivity.class);
                intent.putExtra("IdKey", key );
                intent.putExtra("Image", image );
                intent.putExtra("Name", name );
                intent.putExtra("Company", company );
                intent.putExtra("Price", price );
                intent.putExtra("COLOR", COLOR);
                intent.putExtra("flag", true);

                Log.e("image=====>",arrdesign.get(position).getImage());

                //    intent.putExtra("Color", color );
                context.startActivity(intent);
                //  Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // to perform deletion
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.shoprow, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return arrdesign.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageview;
        TextView productname, companyname, price;

        public ViewHolder(View view) {
            super(view);

            imageview = view.findViewById(R.id.imageview);
            productname = view.findViewById(R.id.nametextview1);
            companyname = view.findViewById(R.id.companytextview2);
            price = view.findViewById(R.id.pricetextview3);
            delete = view.findViewById(R.id.imageviewdustbin);
            update = view.findViewById(R.id.imageviewpencil);

        }

    }


}
