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
    private int REQUEST_TEST = 1;
    ArrayAdapter<String> arrayAdapter;

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
        //databaseHelper.onCreate(db);

        Button btn_search = (Button) findViewById(R.id.search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), categoryActivity.class); // 현재화면 제어권자, 다음 넘어갈 클래스
                //intent.putExtra("message", "반갑습니다.");
                startActivityForResult(intent, REQUEST_TEST);
            }
        });
        //finish();

    }
    void dbDelete(String tableName, String p_name){
        Log.d(TAG, "Delete Data "+p_name);
        String p_nameArr[] = {p_name};
        int n = db.delete(tableName, "P_NAME = ?", p_nameArr);
        Log.d(TAG, "n: "+n);
    }
    //이름 가져오기

    void dbSelect(){
        try{
            String sql = "select p_name from checklist";
            Cursor resultset = db.rawQuery(sql, null);
            int count = resultset.getCount();
            String[] result = new String[count];

            for(int i=0;i<count;i++){
                resultset.moveToNext();
                String SmallCatego = resultset.getString(0);
                result[i] = SmallCatego+"";
            }
            System.out.println("select ok");
            arrayAdapter.clear();
            arrayAdapter.addAll(result);
        }catch (Exception e){

        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_TEST) {
            if (resultCode == RESULT_OK) {
                dbSelect();
            } else {   // RESULT_CANCEL

            }
//        } else if (requestCode == REQUEST_ANOTHER) {
//            ...
        }
    }
    /*void dbSelect(){
        Cursor c = db.query("checklist", null, null,null, null, null, null);
        while(c.moveToNext()){
            String SmallCatego = c.getString(c.getColumnIndex("SmallCatego"));

        }
    }*/

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
    }//도와줘서 항상 고마워

}
