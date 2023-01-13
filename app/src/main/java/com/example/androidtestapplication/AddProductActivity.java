package com.example.androidtestapplication;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.androidtestapplication.Database.CRUD_DATA;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddProductActivity extends AppCompatActivity {

    EditText et1product, et2product, et3product, details1, details2;
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
    String COLOR, IdKey;

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
        details1 = findViewById(R.id.details1);
        details2 = findViewById(R.id.details2);
        back = findViewById(R.id.addproductbackbutton);
        addimage = findViewById(R.id.addimage);
        addproductbutton = findViewById(R.id.addproductbutton);

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
        isEditMode = i.getBooleanExtra("isEditMode", true);

        if (isEditMode == true) {
            // Update Data
            IdKey = i.getStringExtra("IdKey");
            //  Log.e(TAG + "onCreate: ID ", IdKey);
            String name = i.getStringExtra("Name");
            String company = i.getStringExtra("Company");
            String price = i.getStringExtra("Price");
            String image = i.getStringExtra("image");
            Picasso.get().load(image);
            COLOR = i.getStringExtra("COLOR");
            // Set Data
            et1product.setText(name);
            et2product.setText(company);
            et3product.setText(price);

        } else {
            // add data

        }

//          startActivity(i);

//        if(COLOR.equals("Green")){
//            rb1.setChecked(true);
//        }
//
//        if(COLOR.equals("Black")){
//            rb2.setChecked(true);
//        }
//
//        if(COLOR.equals("Silver")) {
//            rb3.setChecked(true);
//        }
//
//            if (COLOR.equals("Blue")) {
//                rb4.setChecked(true);
//            }


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

                if (isEditMode == false) {
                    boolean addData = database.addData(PRODUCTNAME, STORE, PRICE, COLOR, String.valueOf(imageUri));

                    if (addData == true) {
                        Toast.makeText(AddProductActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddProductActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(AddProductActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    boolean update = database.updatedata(IdKey, PRODUCTNAME, STORE, PRICE, COLOR, String.valueOf(imageUri));
                    if (update == true) {
                        Toast.makeText(AddProductActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddProductActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(AddProductActivity.this, "Updation Failed", Toast.LENGTH_SHORT).show();
                    }
                }


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
//                try {
//                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }


                //         database.storeimage(String.valueOf(imageUri));

                //     Bundle extras = data.getExtras();
                //bitmap = (Bitmap) extras.get("data");

                //  databaseAddProduct.imagestore(bitmap);
                //   imageUri = data.getData();
                //  bitmap = (Bitmap) imageUri.get("Data");
                //    String[]  media = {MediaStore.Images.Media.DATA};

//                       try {
//                           Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
//                       } catch (IOException e) {
//                           e.printStackTrace();
//                       }


            } else if (requestCode == IMAGE_PICK_CAMERA_CODE) {
                imageUri = data.getData();
//                try {
//                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                //     database.storeimage(String.valueOf(imageUri));


//


                //   Bundle extras = data.getExtras();
                //  Bitmap bitmap = (Bitmap) extras.get("data");

//                       ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                       bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                       byte[] byteArray = stream.toByteArray();

                // Log.e("Byteimagearray",""+byteArray.toString());
                // Bundle extras = data.getExtras();

                // Bitmap bitmap = BitmapFactory.decodeFile(String.valueOf(imageUri));
                //   bitmap.compress(Bitmap.CompressFormat.PNG, 0 , byteArrayOutputStream)

                //bitmap = (Bitmap) extras.get("data");
                //  database.storeimage(bitmap);
            }

        } else {
            Toast.makeText(this, "Blank", Toast.LENGTH_SHORT).show();
        }
        // Log.e(TAG, "onActivityResult: Click ", String.valueOf(imageUri) );

    }


//    public  class util{
//        public Bitmap getImage(byte[] image){
//            return BitmapFactory.decodeByteArray(image, 0, image.length);
//        }
//       // public static byte[] getbytes(InputStream inputStream) throws IOException{
//         //   ByteArrayInputStream byteb
    // }
    //   }
//    public class DbBitmapUtility {

    // convert from bitmap to byte array
//    public byte[] getBytes(Bitmap bitmap) {
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
//        return stream.toByteArray();
//    }
//
//      // convert from byte array to bitmap
/////                   public static Bitmap getImage(byte[] image) {
////                     return BitmapFactory.decodeByteArray(image, 0, image.length);
////                       }
////                    }
//}

//public class DbBitmapUtility {
//
//    // convert from bitmap to byte array
//    public static byte[] getBytes(Bitmap bitmap) {
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(CompressFormat.PNG, 0, stream);
//        return stream.toByteArray();
//    }
//
//    // convert from byte array to bitmap
//    public static Bitmap getImage(byte[] image) {
//        return BitmapFactory.decodeByteArray(image, 0, image.length);
//    }
//}
// Toast.makeText(this, "Blank", Toast.LENGTH_SHORT).show();


}
