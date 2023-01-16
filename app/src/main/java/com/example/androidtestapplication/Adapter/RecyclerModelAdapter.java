package com.example.androidtestapplication.Adapter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

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
import com.example.androidtestapplication.Database.CRUD_DATA;
import com.example.androidtestapplication.Database.DataBaseHelper;
import com.example.androidtestapplication.ModelClass;
import com.example.androidtestapplication.ProductDetailActivity;
import com.example.androidtestapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerModelAdapter extends RecyclerView.Adapter<RecyclerModelAdapter.ViewHolder> {
    public Context context;
    public ArrayList<ModelClass> arrdesign;
    ImageView delete, update;
    String IdKey, image, name, company, price, COLOR;
    boolean isEditMode = false;
    // DATABASE
    CRUD_DATA database;



    public RecyclerModelAdapter(Context context, ArrayList<ModelClass> arrdesign) {
        this.context = context;
        this.arrdesign = arrdesign;

        database = new CRUD_DATA(context);

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        holder.imageview.setOnClickListener(new View.OnClickListener() {
                 @Override
            public void onClick(View v) {
               Intent intent  = new Intent(context, ProductDetailActivity.class);
                     intent.putExtra("Image", arrdesign.get(position).getImage());
                     intent.putExtra("Name", arrdesign.get(position).getModelname());
                     intent.putExtra("Company", arrdesign.get(position).getComapnyname());
                     intent.putExtra("Price", arrdesign.get(position).getPrice());
                     intent.putExtra("COLOR", arrdesign.get(position).getColor());
               context.startActivity(intent);





            }
        });
        ModelClass model = arrdesign.get(position);
        IdKey = model.getIdKey();
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


                Intent intent = new Intent(context, AddProductActivity.class);
                intent.putExtra("IdKey", arrdesign.get(position).getIdKey());
               Log.e(TAG + "onCreate: ID ", IdKey);
                intent.putExtra("Image", arrdesign.get(position).getImage());
                intent.putExtra("Name", arrdesign.get(position).getModelname());
                intent.putExtra("Company", arrdesign.get(position).getComapnyname());
                intent.putExtra("Price", arrdesign.get(position).getPrice());
                intent.putExtra("COLOR", arrdesign.get(position).getColor());
                 //intent.putExtra("COLOR", );

                Log.e("image=====>", arrdesign.get(position).getImage());
                intent.putExtra("isEditMode", true); // Update existing data

                context.startActivity(intent);
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.deletedata(IdKey);
                arrdesign.remove(position);
                notifyItemRemoved(position);

                Toast.makeText(context, "Record Deleted", Toast.LENGTH_SHORT).show();

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
