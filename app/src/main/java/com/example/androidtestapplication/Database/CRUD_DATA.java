package com.example.androidtestapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.RadioButton;

import androidx.annotation.Nullable;

import com.example.androidtestapplication.ModelClass;

import java.util.ArrayList;

public class CRUD_DATA extends SQLiteOpenHelper {
    RadioButton rb1, rb2, rb3, rb4;
    private static final String DATABASENAME = "ABOUTPRODUCT";
    private static final String TableName = "TESTDATA";
    private static final String  key = "IdKey";
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
        db.execSQL(" CREATE TABLE IF NOT EXISTS " + TableName + " ( IdKey Integer primary key autoincrement , PRODUCTNAME Text, STORE TEXT, PRICE Text, COlOR String , DETAILS TEXT, IMAGE String ) ");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TableName);
        onCreate(db);

    }

    public boolean addData(String PRODUCTNAME, String STORE, String PRICE, String COLOR, String IMAGE) {
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


    // Fetch Data


    public ArrayList<ModelClass> FetchData() {
        SQLiteDatabase db = this.getReadableDatabase();
        //  ContentValues contentValues = new ContentValues();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TableName, null);
        ArrayList<ModelClass> arrdesign = new ArrayList<>();
        while (cursor.moveToNext()) {

            ModelClass model = new ModelClass();
            model.image = cursor.getString(6);
            model.modelname = cursor.getString(1);
            model.comapnyname = cursor.getString(2);
            model.price = cursor.getString(3);
            model.color = cursor.getString(4);

            arrdesign.add(model);

        }
        return arrdesign;
    }

    public boolean updatedata( String Name, String Store, String Price, String Image, String COLOR) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN1, Name);
        cv.put(COLUMN2, Store);
        cv.put(COLUMN3, Price);
        cv.put(COLUMN6, Image);
        cv.put(COLUMN4, COLOR);

     //   db.update(TableName, cv, TableName , "name?" ,    );
        long result = db.update(TableName , cv, "PRODUCTNAME" , new String[] {Name});
        if(result == -1){
            return false;
        }else {
            return true;
        }

    //   db.update(TableName, cv, TableName , "name?" ,    );return true;

    }


}
