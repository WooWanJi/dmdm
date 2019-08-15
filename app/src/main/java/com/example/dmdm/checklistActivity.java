package com.example.dmdm;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import static android.content.ContentValues.TAG;

public class checklistActivity extends AppCompatActivity {

    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList = new ArrayList<String>();
    ListView listview;

    static ArrayList<String> arrayIndex =  new ArrayList<String>();
    static ArrayList<String> arrayData = new ArrayList<String>();
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        ListView listView = (ListView) findViewById(R.id.db_list_view);
        listView.setAdapter(arrayAdapter);

        DatabaseHelper databaseHelper = new DatabaseHelper(this, "DB", null, 1);
        db = databaseHelper.getWritableDatabase();

        Button btn_search = (Button) findViewById(R.id.search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), categoryActivity.class); // 현재화면 제어권자, 다음 넘어갈 클래스
                //intent.putExtra("message", "반갑습니다.");
                startActivity(intent);
            }
        });

    }

    void dbInsert(String tableName, String p_name, String SmallCatego, String c_id, Integer p_id, Integer amount, Boolean check){
        Log.d(TAG, "Insert Data" + p_name);
        ContentValues contentValues = new ContentValues();
        contentValues.put("상품명", p_name);
        long id = db.insert(tableName, null, contentValues);
        Log.d(TAG, "id: "+p_id);
    }

    void dbDelete(String tableName, String p_name){
        Log.d(TAG, "Delete Data "+p_name);
        String p_nameArr[] = {p_name};
        int n = db.delete(tableName, "P_NAME = ?", p_nameArr);
        Log.d(TAG, "n: "+n);
    }

    void dbSearch(String tableName){
        Cursor cursor = null;
        try{
            cursor = db.query(tableName, null, null, null, null, null, null);
            if(cursor != null){
                while(cursor.moveToNext()){
                    String p_name = cursor.getString(cursor.getColumnIndex("p_name"));
                    Log.d(TAG, "상품명 "+p_name);
                }
            }
        }finally {
            if(cursor != null){
                cursor.close();
            }
        }
    }
}
