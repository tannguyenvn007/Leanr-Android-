package com.example.admin.activity19a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {
    private static String DATABASE_NAME="class19.db";
    private String TABLE_NAME="users";
    private String CREATE_TABLE = "create table users (username text, password text)";
    private String DELETE_TABLE = "drop table if exists users";


    public MyDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }

    public  void insertUser(String name, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username",name);
        values.put("password", pass);
        db.insert(TABLE_NAME,null,values);
    }

    public ArrayList<User> getUsers(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from users";
        Cursor cursor = db.rawQuery(sql, null);

        ArrayList<User> userlists = new ArrayList<>();
        while (cursor.moveToNext()){
            String u = cursor.getString(cursor.getColumnIndexOrThrow("username"));
            String p = cursor.getString(cursor.getColumnIndexOrThrow("password"));
            User user=new User(u,p);
            userlists.add(user);
        }
        return userlists;
    }
}
