package com.example.danamontask.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.danamontask.Model.User;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "test.db";

    //DataApi Data
    public static final String TABLE_USER = "user";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USER_NAME = "username";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_ROLE = "role";
    public static final String COLUMN_USER_PASSWORD = "password";

   /* //DataApi Data
    public static final String TABLE_ADMIN = "admin";
    public static final String COLUMN_ADMIN_ID = "id";
    public static final String COLUMN_ADMIN_NAME = "username";
    public static final String COLUMN_ADMIN_EMAIL = "email";
    public static final String COLUMN_ADMIN_ROLE = "role";
    public static final String COLUMN_ADMIN_PASSWORD = "password";*/

    public DatabaseHelper(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + " (" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_USER_NAME + " TEXT, " +
                COLUMN_USER_EMAIL + " TEXT, " +
                COLUMN_USER_ROLE + " TEXT, " +
                COLUMN_USER_PASSWORD + " TEXT " + " )";

        /*String CREATE_ADMIN_TABLE = "CREATE TABLE " + TABLE_ADMIN + " (" +
                COLUMN_ADMIN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_ADMIN_NAME + " TEXT, " +
                COLUMN_ADMIN_EMAIL + " TEXT, " +
                COLUMN_ADMIN_ROLE + " TEXT, " +
                COLUMN_ADMIN_PASSWORD + " TEXT " + " )";*/

        String DATA_USER = "INSERT INTO " + TABLE_USER + "(username, email, role, password)" +
                "VALUES ('Admiral', 'giordano2010@gmail.com', 'Admin', 'aaaa'), " +
                "('Ano', 'admiralsultano55@gmail.com', 'Normal User', 'aaaa')";

        /*String DATA_ADMIN = "INSERT INTO " + TABLE_ADMIN + "(username, email, password)" +
                "VALUES ('Sultano', 'giordano2011@gmail.com', 'aaaa'), " +
                "('Harly', 'admiralsultano56@gmail.com', 'aaaa')";*/

        db.execSQL(CREATE_USER_TABLE);
        //db.execSQL(CREATE_ADMIN_TABLE);
        db.execSQL(DATA_USER);
        //db.execSQL(DATA_ADMIN);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMIN);
        onCreate(db);

    }

    public void deleteData(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?", new String[]{String.valueOf(id)});

    }

    public ArrayList<User> listUser(){

        String sql = "select * from " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<User> storeData = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){

            do {
                int id = Integer.parseInt(cursor.getString(0));
                String userName = cursor.getString(1);
                String email = cursor.getString(2);
                String role = cursor.getString(3);
                String password = cursor.getString(4);
                storeData.add(new User(id, userName, email, role, password));
            } while (cursor.moveToNext());

        }

        cursor.close();
        return storeData;

    }

}
