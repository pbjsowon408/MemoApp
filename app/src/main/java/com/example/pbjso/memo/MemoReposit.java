package com.example.pbjso.memo;

/**
 * Created by pbjso on 2/21/2018.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;

public class MemoReposit {
    private DataBase dataBase;

    public MemoReposit(Context context) {
        dataBase = new DataBase(context);
    }

    public int insert(Memo memo) {

        //
        SQLiteDatabase db = dataBase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Memo.DB_title, memo.title);
        values.put(Memo.DB_content, memo.content);
        values.put(Memo.DB_date, memo.date);

        //
        long memo_Id = db.insert(Memo.TABLE, null, values);
        db.close();
        //
        return (int) memo_Id;
    }

    public void delete(int memo_Id) {

        SQLiteDatabase db = dataBase.getWritableDatabase();
        //

        db.delete(Memo.TABLE, Memo.DB_ID + "= ?", new String[] { String.valueOf(memo_Id) });
        db.close(); //
    }

    public void update(Memo memo) {

        SQLiteDatabase db = dataBase.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Memo.DB_title, memo.title);
        values.put(Memo.DB_content,memo.content);
        values.put(Memo.DB_date, memo.date);

        db.update(Memo.TABLE, values, Memo.DB_ID + "= ?", new String[] { String.valueOf(memo.memo_ID) });
        db.close(); //
    }

    public ArrayList<HashMap<String, String>>  getMemoList() {
        //
        SQLiteDatabase db = dataBase.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Memo.DB_ID + "," +
                Memo.DB_title + "," +
                Memo.DB_content + "," +
                Memo.DB_date + " FROM " +
                Memo.TABLE;

        ArrayList<HashMap<String, String>> memoList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        //

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> memo = new HashMap<String, String>();
                memo.put("id", cursor.getString(cursor.getColumnIndex(Memo.DB_ID)));
                memo.put("title", cursor.getString(cursor.getColumnIndex(Memo.DB_title)));
                memo.put("date", cursor.getString(cursor.getColumnIndex(Memo.DB_date)));
                memoList.add(memo);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return memoList;

    }

    public Memo getMemoById(int Id){
        SQLiteDatabase db = dataBase.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Memo.DB_ID + "," +
                Memo.DB_title + "," +
                Memo.DB_content + "," +
                Memo.DB_date + " FROM " +
                Memo.TABLE + " WHERE " +
                Memo.DB_ID + "=?";

        int iCount =0;
        Memo memo = new Memo();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                memo.memo_ID =cursor.getInt(cursor.getColumnIndex(Memo.DB_ID));
                memo.title = cursor.getString(cursor.getColumnIndex(Memo.DB_title));
                memo.content = cursor.getString(cursor.getColumnIndex(Memo.DB_content));
                memo.date = cursor.getString(cursor.getColumnIndex(Memo.DB_date));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return memo;
    }

}

