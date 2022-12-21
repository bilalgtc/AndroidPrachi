package com.example.androidtestapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ProductAddDatabase extends SQLiteOpenHelper {
    private static final String DATABASENAME = "TEST";
    private static final String COLUMN2 = "PRODUCTNAME";
    private static final String COLUMN3 = "STORE";
    private static final String COLUMN4 = "PRICE";
 //   private static final String COLUMN5 = "COLOR";
 //   private static final String COLUMN6 = "DETAILS";
  //  private static final String  COLUMN7 = "IMAGE";
    private static final String TABLENAME  = "MYPRODUCT";
    public ProductAddDatabase(@Nullable Context context) {
        super(context, DATABASENAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE IF NOT EXISTS " + TABLENAME + "( PRODUCTNAME Text, STORE TEXT, PRICE Text, COlOR int , DETAILS TEXT, IMAGE Bolb not null) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLENAME);
            onCreate(db);
    }

    public boolean insertdata(String PRODUCTNAME, String STORE, String PRICE){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN2, PRODUCTNAME);
        contentValues.put(COLUMN3, STORE);
        contentValues.put(COLUMN4, PRICE);
        long res = db.insert(TABLENAME, null, contentValues);
        if(res == -1){
            return false;
        }else {
            return  true;
        }
    }



}


