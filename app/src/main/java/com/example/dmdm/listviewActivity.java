package com.example.dmdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.apache.http.message.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class listviewActivity extends AppCompatActivity {

    private String jdbUrl = "jdbc:mysql://localhost:3306/dmdm";
    private String dbId = "root";
    private String dbPw = "12345";
    ListView listview;
    ArrayAdapter<String> adapter;
    //Handler handler = null;
    //MainActivity.NetworkTask networkTask = null;
    MyAsyncTask myAsyncTask = null;


    public class MyAsyncTask extends AsyncTask<String, Void, ArrayList<String>>
    {
        ArrayList<String> arrayList = new ArrayList<String>();
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
        try{
            myAsyncTask = new MyAsyncTask();
            result = myAsyncTask.execute().get();
        } catch (Exception e){

        }
        listview = (ListView)findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, result);


        listview.setAdapter(adapter);


    }




}