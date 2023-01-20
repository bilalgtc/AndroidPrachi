package com.example.androidtestapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener {


    EditText et1product, et2product, et3product, et4product;
    RadioGroup radioGroup;
    RadioButton rb1, rb2, rb3, rb4;
    ImageView back, addimage, toviewimageuri, cloudimage;
    TextView txtremove;
    AppCompatButton addproductbutton;
    private boolean isEditMode = false;
    int SELECT_IMAGE_CODE = 1;
    private final int CAMERA_REQ_CODE = 1;
    private static final int REQUEST_GALLERY_CODE = 101;
    private static final int IMAGE_PICK_CAMERA_CODE = 102;
    private static final int IMAGE_PICK_GALLERY_CODE = 103;
    private String[] camerapermissions;
    private String[] storagepermissions;
    Uri imageUri;
    String COLOR, IdKey, image;
    Bitmap bitmap;
    private String currentPhotoPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        oninit();
        back.setOnClickListener(this);
        addproductbutton.setOnClickListener(this);
        addimage.setOnClickListener(this);
        listner();
        intentextras();

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
                    // Camera    Clicked
                    if (!checkcamerapermissions()) {
                        ActivityCompat.requestPermissions(AddProductActivity.this, camerapermissions, CAMERA_REQ_CODE);
                    } else {
                        // Permission already granted
                        pickFromCamera();
                    }
                } else if (i == 1) {
                    if (!checkstoragepermission()) {
                        ActivityCompat.requestPermissions(AddProductActivity.this, storagepermissions, REQUEST_GALLERY_CODE);
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
        Intent galleryintent = new Intent(Intent.ACTION_PICK);
        galleryintent.setType("image/*"); // we want only iMAGE;
        startActivityForResult(galleryintent, IMAGE_PICK_GALLERY_CODE);
    }

    private void pickFromCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photoFile = null;
        try {
            photoFile = createImageFile();
        } catch (IOException ex) {
        }
        if (photoFile != null) {
            imageUri = FileProvider.getUriForFile(this,
                    "com.example.androidtestapplication.fileprovider",
                    photoFile);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(takePictureIntent, IMAGE_PICK_CAMERA_CODE);
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private boolean checkstoragepermission() {
        // Check if storage permission is enabled or not //
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }


    private boolean checkcamerapermissions() {
        // Check if camera permission is enabled or not //
        boolean result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Result of permission allowed/ Denied
        switch (requestCode) {
            case CAMERA_REQ_CODE: {
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
                cloudimage.setVisibility(View.GONE);
                txtremove.setVisibility(View.GONE);
                toviewimageuri.setVisibility(View.VISIBLE);
                toviewimageuri.setImageURI(imageUri);
            } else if (requestCode == IMAGE_PICK_CAMERA_CODE) {

                Toast.makeText(this, currentPhotoPath, Toast.LENGTH_SHORT).show();
                Log.e("path", "" + currentPhotoPath);
                File imgFile = new File(currentPhotoPath);
                if (imgFile.exists()) {
                    Uri uri = Uri.fromFile(imgFile);
                    toviewimageuri.setVisibility(View.VISIBLE);
                    Picasso.get().load(uri).into(toviewimageuri);
                    toviewimageuri.setImageURI(imageUri);
                    toviewimageuri.setVisibility(View.VISIBLE);
                    cloudimage.setVisibility(View.GONE);
                    txtremove.setVisibility(View.GONE);
                }


            } else {
                Toast.makeText(this, "Blank Image Please Select Image", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void oninit() {
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
        toviewimageuri = findViewById(R.id.viewimageuri);
        cloudimage = findViewById(R.id.cloudremove);
        txtremove = findViewById(R.id.txtremove);
        camerapermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagepermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
    }

    public void intentextras() {
        Intent i = getIntent();
        isEditMode = i.getBooleanExtra("isEditMode", false);

        if (isEditMode) {
            // Update Data
            IdKey = i.getStringExtra("IdKey");
            // Set Data
            et1product.setText(i.getStringExtra("Name"));
            et2product.setText(i.getStringExtra("Company"));
            et3product.setText(i.getStringExtra("Price"));
            et4product.setText(i.getStringExtra("Details"));
            COLOR = i.getStringExtra("COLOR");
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
            toviewimageuri.setImageURI(Uri.parse(image));
            image = i.getStringExtra("Image");
            if (image == null) {
                Toast.makeText(this, "No image", Toast.LENGTH_SHORT).show();

            } else {
                Picasso.get().load(image);
            }
            cloudimage.setVisibility(View.GONE);
            txtremove.setVisibility(View.GONE);
            toviewimageuri.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.addproductbackbutton:
                Intent inext = new Intent(AddProductActivity.this, MainActivity.class);
                startActivity(inext);
                break;
            case R.id.addimage:
                imagepickdialogue();
                break;
            case R.id.addproductbutton:
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
                    if (PRODUCTNAME.isEmpty()) {
                        Toast.makeText(AddProductActivity.this, "PRODUCT NAME IS EMPTY", Toast.LENGTH_SHORT).show();
                        //  PRODUCTNAME.isEmpty() || STORE.isEmpty() || PRICE.isEmpty() || Details.isEmpty() || image == null) {
                        Toast.makeText(AddProductActivity.this, "None of the field should be empty", Toast.LENGTH_SHORT).show();
                    } else if (STORE.isEmpty()) {
                        Toast.makeText(AddProductActivity.this, "STORE IS EMPTY", Toast.LENGTH_SHORT).show();
                    } else if (PRICE.isEmpty()) {
                        Toast.makeText(AddProductActivity.this, "PRICE IS EMPTY", Toast.LENGTH_SHORT).show();
                    } else if (Details.isEmpty()) {
                        Toast.makeText(AddProductActivity.this, "Details is Empty", Toast.LENGTH_SHORT).show();
                    } else if (image == null) {
                        Toast.makeText(AddProductActivity.this, "PLEASE INSERT PRODUCT IMAGE", Toast.LENGTH_SHORT).show();
                    } else {


                    }

                } else {

                    if (PRODUCTNAME.isEmpty()) {
                        Toast.makeText(AddProductActivity.this, "PRODUCT NAME IS EMPTY", Toast.LENGTH_SHORT).show();
                        //  PRODUCTNAME.isEmpty() || STORE.isEmpty() || PRICE.isEmpty() || Details.isEmpty() || image == null) {
                        Toast.makeText(AddProductActivity.this, "None of the field should be empty", Toast.LENGTH_SHORT).show();
                    } else if (STORE.isEmpty()) {
                        Toast.makeText(AddProductActivity.this, "STORE IS EMPTY", Toast.LENGTH_SHORT).show();
                    } else if (PRICE.isEmpty()) {
                        Toast.makeText(AddProductActivity.this, "PRICE IS EMPTY", Toast.LENGTH_SHORT).show();
                    } else if (Details.isEmpty()) {
                        Toast.makeText(AddProductActivity.this, "Details is Empty", Toast.LENGTH_SHORT).show();
                    } else if (imageUri == null) {
                        Toast.makeText(AddProductActivity.this, "PLEASE INSERT PRODUCT IMAGE", Toast.LENGTH_SHORT).show();
                    } else {

                    }
                }
                break;
        }

    }

    public void listner() {
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
    }


}
