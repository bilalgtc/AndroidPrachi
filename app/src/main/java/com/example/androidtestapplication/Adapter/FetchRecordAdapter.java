package com.example.androidtestapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidtestapplication.FetchRecord;
import com.example.androidtestapplication.R;

import java.util.ArrayList;

public class FetchRecordAdapter extends  RecyclerView.Adapter<FetchRecordAdapter.RecordHolder>{
    // Varibales
    private  Context context;
    private ArrayList<FetchRecord> recordList;


    // Constructor
    public FetchRecordAdapter(Context context, ArrayList<FetchRecord> recordList) {
        this.context = context;
        this.recordList = recordList;
    }

    @NonNull
    @Override
    public RecordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate Layout
        View view = LayoutInflater.from(context).inflate(R.layout.shoprow, parent, false);
        return new RecordHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordHolder holder, int position) {
        // get data, set data, handle view clicks in this method

        // get data
        FetchRecord data = recordList.get(position);
        String name = data.getName();
        String company = data.getCompany();
        String price = data.getPrice();

        // set data
        holder.name.setText(name);
        holder.company.setText(company);
        holder.price.setText(price);


    }

    @Override
    public int getItemCount() {
        return recordList.size(); // Return Size of List
    }

    class RecordHolder extends RecyclerView.ViewHolder{
        // Views

        TextView name, company, price;

        public RecordHolder(@NonNull View itemView) {
            super(itemView);

            // init views
            name = itemView.findViewById(R.id.nametextview1);
            company = itemView.findViewById(R.id.companytextview2);
            price = itemView.findViewById(R.id.pricetextview3);
        }
    }


}
