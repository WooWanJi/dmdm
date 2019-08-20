package com.example.dmdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;

import static android.content.ContentValues.TAG;

public class listviewActivity extends AppCompatActivity {

    private String jdbUrl = "jdbc:mysql://localhost:3306/dmdm";
    private String dbId = "root";
    private String dbPw = "12345";
    ListView listview;
    ArrayAdapter<String> adapter;
    //Handler handler = null;
    //MainActivity.NetworkTask networkTask = null;
    MyAsyncTask myAsyncTask = null;
    ArrayList<String> arrayList = new ArrayList<String>();
    private String p_name;
    SQLiteDatabase db;

    public class MyAsyncTask extends AsyncTask<String, Void, ArrayList<String>>
    {
        String sendMsg, receiveMsg;
        @Override
        protected ArrayList<String> doInBackground(String... params) {
            try{
                //서버 연동
                String str;
                URL url = new URL("http://220.76.34.131:8080/dmdm/listviewJson.jsp");
                HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setRequestProperty("Context_Type", "application/x-www-form-urlencoded");
                urlConn.setRequestMethod("POST");

                OutputStreamWriter osw = new OutputStreamWriter(urlConn.getOutputStream());
                osw.write("BigCatego="+params[0]);//SmallCatego에 배열의 0번째요소 삽입
                osw.flush();

                if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStreamReader tmp = new InputStreamReader(urlConn.getInputStream());
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }
                    receiveMsg = buffer.toString();
                    //Log.i("결과", receiveMsg+"");

                    //JsonArray로 파싱 receiveMsg
                   // JSONObject jsonObject = new JSONObject();
                    JSONArray jsonArray = new JSONArray(receiveMsg);
                    for(int i=0;i<jsonArray.length();i++){
                        // Log.d(i+":", jsonArray.get(i).toString());
                        arrayList.add(jsonArray.get(i).toString());

                    }
                } else{

                }


                //받은걸 jsonArray으로 받아서 ArrayList<String>에 저장

            } catch (Exception e){

            }

            return arrayList;
        }



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ArrayList<String> result = new ArrayList<String>();
        Intent intent = getIntent();
        final String SmallCatego = intent.getStringExtra("BigCatego");

        DatabaseHelper databaseHelper = new DatabaseHelper(this, "DB", null, 1);
        db = databaseHelper.getWritableDatabase();
        try{
            myAsyncTask = new MyAsyncTask();
            result = myAsyncTask.execute(SmallCatego).get();
        } catch (Exception e){

        }
        //listview = (ListView)findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, result);
       // ArrayList<String> result = new ArrayList<String>();
        listview = (ListView)findViewById(R.id.listview);
        //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, result);



        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                dbInsert("checklist", arrayList.get(position));
                Intent intent = new Intent();
                //intent.putExtra("result", "some value");
                setResult(RESULT_OK, intent);
                finish();

            }
        });

        listview.setAdapter(adapter);


    }

    void dbInsert(String tableName, String p_name){
        Log.d(TAG, "Insert Data" + p_name);
        ContentValues contentValues = new ContentValues();
        contentValues.put("p_name", p_name);
        long id = db.insert(tableName, null, contentValues);
        //Log.d(TAG, "id: "+p_id);
    }


}
