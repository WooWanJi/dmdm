/*
package com.example.dmdm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqlite.DataBases;

import static com.example.sqlite.DataBases.CreateDB.amount;
import static com.example.sqlite.DataBases.CreateDB.check;

public class DbOpenHelper{
    private static final String DATABASE_NAME = "InnerDatabases(SQLite).db";
    private static final int DATABASE_VERSION = 1;
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    private class DatabaseHelper extends SQLiteOpenHelper{
        public DatabaseHelper(Context context, String SmallCatego, SQLiteDatabase.CursorFactory factory, int version){
            super(context, SmallCatego, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(com.example.sqlite.DataBases, DataBases.CreateDB._CREATE);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS "+DataBases.CreateDB._TABLENAME);
            onCreate(db);
        }
    }

    public DbOpenHelper(Context context){this.mCtx = context;}

    public DbOpenHelper open() throws SQLException{
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void create(){
        mDBHelper.onCreate(mDB);
    }

    public void close(){ mDB.close();}

    public long insertColumn(String c_id, String SmallCatego, int p_id, String p_name){
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.c_id, c_id);
        values.put(DataBases.CreateDB.SmallCatego, SmallCatego);
        values.put(DataBases.CreateDB.p_id, p_id);
        values.put(DataBases.CreateDB.p_name, p_name);
        values.put(check, check);
        values.put(amount, amount);
        return  mDB.insert(DataBases.CreateDB._TABLENAME, null, values);
    }

    public boolean updateColumn(String c_id, String SmallCatego, int p_id, String p_name){
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.c_id, c_id);
        values.put(DataBases.CreateDB.SmallCatego, SmallCatego);
        values.put(DataBases.CreateDB.p_id, p_id);
        values.put(DataBases.CreateDB.p_name, p_name);
        return mDB.update(DataBases.CreateDB._TABLENAME, values, "_id=" + c_id, null) > 0;
    }

    public boolean deleteColumn(long id){
        return mDB.delete(DataBases.CreateDB._TABLENAME, "_id="+id, null) > 0;
    }

    public Cursor selectColumns(){
        return mDB.query(DataBases.CreateDB._TABLENAME, null, null, null, null, null, null);
    }
    public Cursor sortColumn(String sort){
//        Cursor c = mDB.rawQuery( "SELECT * FROM checklist ORDER BY c_id desc", null);
        return null;
    }
}*/
