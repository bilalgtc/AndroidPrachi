package com.example.androidtestapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "USERENTRY";
    private static final String TABLE_NAME = "USERDETAILS";
    private static final String COLUMN1 = "NAME";
    private static final String COLUMN2 = "EMAIL";
    private static final String COLUMN3 = "PHONE";
    private static final String COLUMN4 = "PASSWORD";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(NAME TEXT, EMAIL TEXT PRIMARY KEY, PHONE STRING, PASSWORD TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

    }

    public boolean registeruser(String fullname, String emailaddress, String phonenumber, String password ){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN1, fullname);
        contentValues.put(COLUMN2, emailaddress);
        contentValues.put(COLUMN3, phonenumber);
        contentValues.put(COLUMN4, password);


        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return  false;
        else
            return true;
    }
    public boolean uservalidation(String emailaddress){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from USERDETAILS where EMAIL = ?", new String[] {emailaddress});
        if(cursor.getCount()>0){
            return  true;
        }else{
            return false;
        }
    }


    public boolean checkuser(String emailaddress, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from USERDETAILS  where  EMAIL = ? and PASSWORD = ?" , new String[] {emailaddress, password});

        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }


}
