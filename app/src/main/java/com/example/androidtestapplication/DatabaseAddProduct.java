package com.example.androidtestapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;

public class DatabaseAddProduct extends SQLiteOpenHelper {
    private static final String DATABASENAME = "PRODUCTDETAILS";
    private  static final String  TableName = "PRODUCT";
    private static final String COLUMN2 = "PRODUCTNAME";
    private static final String COLUMN3 = "STORE";
    private static final String COLUMN4 = "PRICE";
    private static final String COLUMN5 = "COLOR";
    private static final String COlUMN51 = "BLACK";
    private static final String COlUMN52 = "SILVER";
    private static final String COlUMN53 = "BLUE";
    private static final String COLUMN6 = "DETAILS";
    private static final String  COLUMN7 = "IMAGE";
    public DatabaseAddProduct(@Nullable Context context) {
        super(context, DATABASENAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE IF NOT EXISTS " + TableName + "( PRODUCTNAME Text, STORE TEXT, PRICE Text, COlOR int , DETAILS TEXT, IMAGE Bolb )" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL(" DROP TABLE IF EXISTS " + TableName);
            onCreate(db);
    }

    // insert data
    // location

    public  boolean insertproductdata( String PRODUCTNAME, String STORE, String PRICE){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN2, PRODUCTNAME);
        contentValues.put(COLUMN3, STORE);
        contentValues.put(COLUMN4, PRICE);
//        , int COLOR, String  DETAILS, byte IMAGE
//        contentValues.put(COLUMN5, COLOR);
//
//        contentValues.put(COLUMN6, DETAILS);
//        contentValues.put(COLUMN7, IMAGE);
        long result = db.insert(TableName, null, contentValues);

       //  long res = db.insert(TableName, null, contentValues);
        if(result == -1){
            return false;
        }else {
            return  true;
        }
    }

public  boolean radiooption(int COLOR){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
    contentValues.put(COLUMN5, COLOR);
    long res = db.insert(TableName, null, contentValues);
    if(res == -1){
        return false;
    }else {
        return  true;
    }

}

public boolean imagestore(Byte IMAGE){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN7, IMAGE);
    long res = db.insert(TableName, null, contentValues);
    if(res == -1){
        return false;
    }else {
        return  true;
    }


}


}
