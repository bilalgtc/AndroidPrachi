package com.example.androidtestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import org.jetbrains.annotations.Nullable;

public class AddProductActivity extends AppCompatActivity {

    ImageView back, addimage;
    int SELECT_IMAGE_CODE =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        back = findViewById(R.id.addproductbackbutton);
        addimage = findViewById(R.id.addimage);

        // Intent for Main Activity //
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(AddProductActivity.this, MainActivity.class);
                startActivity(inext);
            }
        });
// Intent for Main Activity SetUp//


        // Add Image From Gallery //
//        addimage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMAGE_CODE);
//
//            }
//        });
   //     super.onActivityResult(requestCode, resultCode, data);

        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent icamera  =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            }
        });
//        @Override
//        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
//            super.onActivityResult(requestCode, resultCode, data);
//        }

        // Add Image From Gallery SetUp//

    }
}