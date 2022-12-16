package com.example.androidtestapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    private  static final String DATABASE_NAME = "USER_RECORD";
    private  static final String TABLE_NAME = "USER_DATA";
    private  static final String COLUMN1 = "FULL_NAME";
    private  static final String COLUMN2 = "EMAIL_ADDRESS";
    private  static final String COLUMN3 = "PHONE_NUMBER";
    private  static final String COLUMN4 = "PASSWORD";
    //private  static final String COLUMN5 = "CONFIRM_PASSWORD";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(FULL_NAME TEXT, EMAIL_ADDRESS TEXT PRIMARY KEY, PHONE_NUMBER STRING, PASSWORD TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
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

    public boolean checkuser(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from user_data where email = ? and password = ?" , new String[] {email, password} );
        cursor.close();
        db.close();
        if(cursor.getCount()>0)
            return true;
        else
            return false;



    }





}
