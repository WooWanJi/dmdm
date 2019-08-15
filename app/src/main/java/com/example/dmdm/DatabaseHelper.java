package com.example.dmdm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class DatabaseHelper extends SQLiteOpenHelper {
    static final String table_name = "checklist";
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
        Log.d(TAG, "생성자 호출");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        Log.d(TAG, "table create");
        String createQuery = "CREATE TABLE " +table_name+
                "( c_id text not null, "+
                "SmallCatego text not null, "+
                "p_name text not null, "+
                "p_id integer not null, "+
                "amunt integer not null);";
                //"check (boolean));";
        sqLiteDatabase.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){
        Log.d(TAG, "table onUpgrade");
        String createQuery = "DROP TABLE IF EXISTS " + table_name +";";
        sqLiteDatabase.execSQL(createQuery);
        onCreate(sqLiteDatabase);
    }

}
