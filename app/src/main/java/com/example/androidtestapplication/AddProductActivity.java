package com.example.androidtestapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.androidtestapplication.Database.CRUD_DATA;
import com.squareup.picasso.Picasso;

public class AddProductActivity extends AppCompatActivity {


    LinearLayout toviewimage;
    EditText et1product, et2product, et3product, et4product;
    RadioGroup radioGroup;
    RadioButton rb1, rb2, rb3, rb4;
    ImageView back, addimage;
    AppCompatButton addproductbutton;
    private boolean isEditMode = false;
    int SELECT_IMAGE_CODE = 1;
    // Permission Constants //
    private static final int REQUEST_CAM_CODE = 100;
    private static final int REQUEST_GALLERY_CODE = 101;
    // image pick constants //
    private static final int IMAGE_PICK_CAMERA_CODE = 102;
    private static final int IMAGE_PICK_GALLERY_CODE = 103;
    // arrays of permissions //
    private String[] camerapermissions;
    private String[] storagepermissions;
    // Variables contain data to save//
    Uri imageUri;
    String COLOR, IdKey, image;


    // DATABASE
    CRUD_DATA database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        et1product = findViewById(R.id.et1product);
        et2product = findViewById(R.id.et2product);
        et3product = findViewById(R.id.et3product);
        radioGroup = findViewById(R.id.radiogroup);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        et4product = findViewById(R.id.et4product);
        back = findViewById(R.id.addproductbackbutton);
        addimage = findViewById(R.id.addimage);
        addproductbutton = findViewById(R.id.addproductbutton);
        toviewimage = findViewById(R.id.toviewimage);

        // Initialize DATABASE
        database = new CRUD_DATA(this);


        //Init permissions arrays //
        camerapermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagepermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};


        // Intent for Main Activity //
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(AddProductActivity.this, MainActivity.class);
                startActivity(inext);
            }
        });

        Intent i = getIntent();
        isEditMode = i.getBooleanExtra("isEditMode", false);

        if (isEditMode) {
            // Update Data
            IdKey = i.getStringExtra("IdKey");
            //  Log.e(TAG + "onCreate: ID ", IdKey);
            String name = i.getStringExtra("Name");
            String company = i.getStringExtra("Company");
            String price = i.getStringExtra("Price");
            image = i.getStringExtra("Image");

            // Log.e("image=====>", image);
            if (image == null) {
                Toast.makeText(this, "No image", Toast.LENGTH_SHORT).show();

            } else {
                Picasso.get().load(image);
            }
            String Details = i.getStringExtra("Details");
            COLOR = i.getStringExtra("COLOR");
            // Set Data
            et1product.setText(name);
            et2product.setText(company);
            et3product.setText(price);
            et4product.setText(Details);

            if (COLOR.equals("Green")) {
                rb1.setChecked(true);
            } else {
                rb1.setChecked(false);
            }

            if (COLOR.equals("Black")) {
                rb2.setChecked(true);
            } else {
                rb2.setChecked(false);
            }

            if (COLOR.equals("Silver")) {
                rb3.setChecked(true);
            } else {
                rb3.setChecked(false);
            }

            if (COLOR.equals("Blue")) {
                rb4.setChecked(true);
            } else {
                rb4.setChecked(false);
            }


        } else {
            // Add Data
            Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
        }


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {
                    case R.id.rb1:
                        COLOR = rb1.getText().toString();

                        break;
                    case R.id.rb2:
                        COLOR = rb2.getText().toString();
                        break;
                    case R.id.rb3:
                        COLOR = rb3.getText().toString();

                        break;
                    case R.id.rb4:
                        COLOR = rb4.getText().toString();
                        break;
                }

            }
        });

// Intent for Main Activity SetUp//
        // DATABASE //

        addproductbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String PRODUCTNAME = et1product.getText().toString();
                String STORE = et2product.getText().toString();
                String PRICE = et3product.getText().toString();
                String Details = et4product.getText().toString();


                if (isEditMode) {

                    if (imageUri == null) {
                        Picasso.get().load(image);
                    } else {
                        Toast.makeText(AddProductActivity.this, "Image not Updated", Toast.LENGTH_SHORT).show();
                    }
                    if(PRODUCTNAME.isEmpty() || STORE.isEmpty() || PRICE.isEmpty()  || Details.isEmpty() || image == null){
                        Toast.makeText(AddProductActivity.this, "None of the field should be empty", Toast.LENGTH_SHORT).show();
                    }else {

                        boolean update = database.updatedata(IdKey, PRODUCTNAME, STORE, PRICE, COLOR, Details, String.valueOf(image));

                        if (update) {

                            Toast.makeText(AddProductActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddProductActivity.this, MainActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(AddProductActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else {

                    if(PRODUCTNAME.isEmpty() || STORE.isEmpty() || PRICE.isEmpty()  || Details.isEmpty() || imageUri == null){
                        Toast.makeText(AddProductActivity.this, "None of the field should be empty", Toast.LENGTH_SHORT).show();
                    }
                    else {

                        boolean addData = database.addData(PRODUCTNAME, STORE, PRICE, COLOR, Details, String.valueOf(imageUri));
                        if (addData) {
                          //  validStr(PRODUCTNAME, STORE, PRICE, COLOR, Details, image);
                            Toast.makeText(AddProductActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddProductActivity.this, MainActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(AddProductActivity.this, "Updation Failed None of the field should be empty", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
               // Toast.makeText(AddProductActivity.this, "Clickeddddd", Toast.LENGTH_SHORT).show();
            }
        });




        // DATABASE SETUP//


        // Add Image From Gallery //

        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagepickdialogue();
            }
        });

    }




    private void imagepickdialogue() {
        // Options to Display in Dailogue //
        String[] options = {"Camera", "Gallery"};
        // Dailogue
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // title
        builder.setTitle("Pick Image From");
        // set items
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // handle clicks
                if (i == 0) {
                    // Camera Clicked
                    if (!checkcamerapermissions()) {
                        requestcamerapermission();
                    } else {
                        // Permission already granted
                        pickFromCamera();
                    }
                } else if (i == 1) {
                    if (!checkstoragepermission()) {
                        requeststoragepermission();
                    } else {
                        // Permission already granted
                        pickFromGallery();
                    }
                }
            }
        });
        // Create / Show Dailog
        builder.create().show();

    }

    private void pickFromGallery() {
        // INTENT TO PICK IMAGE FROM GALLERY, THE IMAGE WILL BE RETURNED IN ONACTIVITYRESULT METHOD
        Intent galleryintent = new Intent(Intent.ACTION_PICK);
        galleryintent.setType("image/*"); // we want only iMAGE;
        startActivityForResult(galleryintent, IMAGE_PICK_GALLERY_CODE);
    }

    private void pickFromCamera() {
        // INTENT TO PICK IMAGE FROM CAMERA , THE IMAGE WILL BE RETURNED IN ONACTIVITYRESULT METHOD
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Image title");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image description");
        // put image uri
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        // Intent to open camera for image
        Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraintent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(cameraintent, IMAGE_PICK_CAMERA_CODE);

    }

    private boolean checkstoragepermission() {
        // Check if storage permission is enabled or not //
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void requeststoragepermission() {
        // request the storage permission //
        ActivityCompat.requestPermissions(this, storagepermissions, REQUEST_GALLERY_CODE);
    }

    private boolean checkcamerapermissions() {
        // Check if camera permission is enabled or not //
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }

    private void requestcamerapermission() {
        // request the storage permission //
        ActivityCompat.requestPermissions(this, camerapermissions, REQUEST_CAM_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Result of permission allowed/ Denied

        switch (requestCode) {
            case REQUEST_CAM_CODE: {
                if (grantResults.length > 0) {
                    // If allowed returns true otherwise false //
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (cameraAccepted && storageAccepted) {
                        // both permission allowed
                        pickFromCamera();
                    } else {
                        Toast.makeText(this, "Camera & Storage Permissions are required", Toast.LENGTH_SHORT).show();
                    }
                }

            }
            break;
            case REQUEST_GALLERY_CODE: {
                if (grantResults.length > 0) {
                    // If allowed returns true otherwise false //
                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (storageAccepted) {
                        // Storage Permission allowed
                        pickFromGallery();
                    } else {
                        Toast.makeText(this, "Storage Permission is required...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // picked from gallery //
            if (requestCode == IMAGE_PICK_GALLERY_CODE) {
                imageUri = data.getData();
             //   toviewimage.setBackgroundResource( imageUri);

            } else if (requestCode == IMAGE_PICK_CAMERA_CODE) {
                imageUri = data.getData();

            }

        } else {
            Toast.makeText(this, "Blank", Toast.LENGTH_SHORT).show();
        }
        // Log.e(TAG, "onActivityResult: Click ", String.valueOf(imageUri) );

    }


}
