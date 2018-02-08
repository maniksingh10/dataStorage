package com.example.android.pets.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.pets.data.Contract.Entry;

/**
 * Created by manik on 08-02-2018.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME ="pets.db";
    public static final int DB_VER=1;



    public DbHelper(Context context) {
        super(context, DB_NAME,null,DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE "+ Entry.TABLE_NAME+"("
                +Entry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +Entry.CL_NAME+" TEXT NOT NULL, "
                +Entry.Cl_BREED+" TEXT, "
                +Entry.CL_GENDER+" INTEGER NOT NULL, "
                +Entry.CL_WEIGHT+" INTEGER NOT NULL DEFAULT 0);";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
