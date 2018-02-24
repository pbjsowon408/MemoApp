package com.example.pbjso.memo;

/**
 * Created by pbjso on 2/21/2018.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase  extends SQLiteOpenHelper {
    //
    private static final int DATABASE_VERSION = 4;

    //
    private static final String DATABASE_NAME = "memo.db";

    public DataBase(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //

        String CREATE_TABLE_MEMO = "CREATE TABLE " + Memo.TABLE  + "("
                + Memo.DB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Memo.DB_title + " TEXT, "
                + Memo.DB_content + " Text, "
                + Memo.DB_date + " TEXT )";

        db.execSQL(CREATE_TABLE_MEMO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //
        db.execSQL("DROP TABLE IF EXISTS " + Memo.TABLE);

        //
        onCreate(db);
    }
}