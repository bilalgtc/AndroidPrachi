package com.example.androidtestapplication.Adapter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.example.androidtestapplication.Exampleclass;
import com.example.androidtestapplication.ModelClass;
import com.example.androidtestapplication.ProductDetailActivity;
import com.example.androidtestapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerModelAdapter extends RecyclerView.Adapter<RecyclerModelAdapter.ViewHolder> {
    public Context context;
    public ArrayList<Exampleclass> arrdesign;
    ImageView delete, update;
    String IdKey, image, name, company, price, COLOR, Details;
    boolean isEditMode = false;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    FirebaseUser currentuser;


    public RecyclerModelAdapter(Context context, ArrayList<Exampleclass> arrdesign) {
        this.context = context;
        this.arrdesign = arrdesign;
        firebaseDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Exampleclass exampleclass = arrdesign.get(position);
        holder.productname.setText(exampleclass.getName());
        holder.companyname.setText(exampleclass.getStore());
        holder.price.setText(exampleclass.getPrice());
        image = exampleclass.getImage();
        Picasso.get().load(image).into(holder.imageview);



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddProductActivity.class);
                intent.putExtra("IdKey", arrdesign.get(position).getId());
                intent.putExtra("Image", arrdesign.get(position).getImage());
                intent.putExtra("Name", arrdesign.get(position).getName());
                intent.putExtra("Company", arrdesign.get(position).getStore());
                intent.putExtra("Price", arrdesign.get(position).getPrice());
                intent.putExtra("COLOR", arrdesign.get(position).getColor());
                intent.putExtra("Details", arrdesign.get(position).getDetails());
                Log.e("image=====>", arrdesign.get(position).getImage());
                intent.putExtra("isEditMode", true); // Update existing data
                context.startActivity(intent);
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase.getInstance().getReference().child("Product Description")
                        .child(exampleclass.getId()).removeValue();




//                String userid = mAuth.getUid();
//                databaseReference = FirebaseDatabase.getInstance().getReference()
//                        .child("Product Details / " + userid ).child(exampleclass.getId());
//                databaseReference.child(exampleclass.getId()).removeValue();
//
//            // String productid = String.valueOf(FirebaseDatabase.getInstance().getReference().getRef());
//                String productid = FirebaseDatabase.getInstance().getReference().getKey();
//           //  Log.e(TAG, productid);
//                String userid = mAuth.getUid();
//              FirebaseDatabase.getInstance().getReference()
//                      //.child(productid)
//                      .removeValue();
              //  Exampleclass exampleclass = new Exampleclass();
              //  int id = Integer.parseInt(arrdesign.get(position).getId());
              //  String id = arrdesign.get(position).getId();
             //   databaseReference.child("Product Details").child(id).removeValue();



            //  FirebaseDatabase database = FirebaseDatabase.getInstance();
             // DatabaseReference databaseReference = database.getReference().child();
//                String idbase = databaseReference.getKey();
//                mAuth = FirebaseAuth.getInstance();
//                String userid = mAuth.getUid();
//                String id = databaseReference.getPath("Product Details / " + userid );
//                databaseReference.child(id).removeValue();
//                Toast.makeText(context, "Record deleted successfully", Toast.LENGTH_SHORT).show();
//                notify();

//                FirebaseDatabase.getInstance().getReference().child("Product Details \n")
//                        .child()

                // \\***********\\ //

//                DatabaseReference dbref= FirebaseDatabase.getInstance().getReference().child("Product Details \n");
//
//              //  databaseReference = FirebaseDatabase.getInstance().getReference("Product Details \n");
//                dbref.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(context, "Failed to delete data", Toast.LENGTH_SHORT).show();
//                    }
//                });

            }

        });


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoprow, parent, false);
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
