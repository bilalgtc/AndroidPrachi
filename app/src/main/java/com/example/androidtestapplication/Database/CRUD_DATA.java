package com.example.androidtestapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.SyncStateContract;

import androidx.annotation.Nullable;

import com.example.androidtestapplication.FetchRecord;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import kotlin.jvm.internal.Ref;

public class CRUD_DATA extends SQLiteOpenHelper {
    private static final String DATABASENAME = "ABOUTPRODUCT";
    private static final String TableName = "TESTDATA";
    private static final String COLUMN1 = "PRODUCTNAME";
    private static final String COLUMN2 = "STORE";
    private static final String COLUMN3 = "PRICE";
    private static final String COLUMN4 = "COLOR";
    // private static final String COlUMN51 = "BLACK";
    ///  private static final String COlUMN52 = "SILVER";
    //  private static final String COlUMN53 = "BLUE";
    private static final String COLUMN5 = "DETAILS";
    private static final String COLUMN6 = "IMAGE";

    public CRUD_DATA(@Nullable Context context) {
        super(context, DATABASENAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE IF NOT EXISTS " + TableName + " ( PRODUCTNAME Text, STORE TEXT, PRICE Text, COlOR int , DETAILS TEXT, IMAGE String ) ");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TableName);
        onCreate(db);

    }

    public boolean addData(String PRODUCTNAME, String STORE, String PRICE,int COLOR ,String IMAGE) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN1, PRODUCTNAME);
        contentValues.put(COLUMN2, STORE);
        contentValues.put(COLUMN3, PRICE);
        contentValues.put(COLUMN4, COLOR);
      contentValues.put(COLUMN6, IMAGE);
        long result = db.insert(TableName, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

//    public boolean storeimage(String IMAGE) throws SQLiteException {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//            contentValues.put(COLUMN6, IMAGE);
//
////        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream();
////        IMAGE.compress(Bitmap.CompressFormat.JPEG, 100, arrayInputStream);
//
//        //if(cursor.getCount()>0)
//
//        // Byte[] IMAGE = cursor.getBolb(1);
//        // Bitmap bitmap = BitmapFactory.decodeByteArray(IMAGE, 0, IMAGE.length);
//
//
////        contentValues.put(COLUMN6, IMAGE);
////        Bitmap bitmap = (Bitmap) BitmapFactory.decodeByteArray(IMAGE, 0, IMAGE.length);
////        ByteArrayOutputStream baos = new ByteArrayOutputStream();
////        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);    // Could be Bitmap.CompressFormat.PNG or Bitmap.CompressFormat.WEBP
////        byte[] bai = baos.toByteArray();
//        // contentValues.put( storeimage()  ,IMAGE);
//        Long result = db.insert(TableName, null, contentValues);
//        if (result == -1) {
//            return false;
//        } else {
//            return true;
//        }
//    }

    // Fetch Data

    public ArrayList<FetchRecord> FetchData() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TableName, null);
        ArrayList<FetchRecord> records = new ArrayList<>();
        while (cursor.moveToNext()) {
//                FetchRecord record = new FetchRecord();
//                record.getName();

        }


//        ArrayList<FetchRecord> recordsList = new ArrayList<>();
//        // Qurey to select recored
//
//        String selectquery = "SELECT * FROM" + Constants.TableName + "Data";
        return records;
    }


}
