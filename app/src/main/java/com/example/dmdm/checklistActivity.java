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
import android.widget.ImageButton;
import android.widget.ListView;
import java.util.ArrayList;
import static android.content.ContentValues.TAG;

/*
        2019 08 28 코딩 목표 arrayAdapter를 전부 커스텀 어뎁터로 교체하는 것이 목표
 */
public class checklistActivity extends AppCompatActivity {
    private int REQUEST_TEST = 1;

    /* step 1
    커스텀 어뎁터 변수 선언
    */
    ListView listView;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);
       // arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.activity_list_item); //지워야지***************************************

        listView = (ListView) findViewById(R.id.listview1);

        DatabaseHelper databaseHelper = new DatabaseHelper(this, "DB", null, 1);
        db = databaseHelper.getWritableDatabase();
        //databaseHelper.onCreate(db);

        ImageButton btn_search = (ImageButton) findViewById(R.id.search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), categoryActivity.class); // 현재화면 제어권자, 다음 넘어갈 클래스
                //intent.putExtra("message", "반갑습니다.");
                startActivityForResult(intent, REQUEST_TEST);
            }
        });
        ImageButton scanner = (ImageButton) findViewById(R.id.scanBtn);
        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        ScannerActivity.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다
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
            /*arrayAdapter.clear();
            arrayAdapter.addAll(result);*/

            // step 2 커스텀 어레이 생성(new)
            CustomChoiceListViewAdapter customChoiceListViewAdapter = new CustomChoiceListViewAdapter();
            // step 3 리스트 뷰에 커스텀 어뎁터 넣기
            listView.setAdapter(customChoiceListViewAdapter);
            // step 4 result의 내용을 커스텀어뎁터 변수에 addItem(result의 자료형과 addItem 메소드의 파라미터를 잘보고 넣는 방법을 응용)
            for(int i=0;i<result.length ;i++) {
                customChoiceListViewAdapter.addItem(result[i]);
            }
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

        }
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
