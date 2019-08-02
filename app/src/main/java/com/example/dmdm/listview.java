package com.example.dmdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class listview extends AppCompatActivity {

    private String jdbUrl = "jdbc:mysql://localhost:3306/dmdm";
    private String dbId = "root";
    private String dbPw = "12345";
    ListView listview;
    ArrayAdapter<String> adapter;
    //Handler handler = null;
    //MainActivity.NetworkTask networkTask = null;
    MyAsyncTask myAsyncTask;
    //static  String query = "select * from catego";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        listview = (ListView)findViewById(R.id.listview);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listview.setAdapter(adapter);
    }

    @Override
    protected void onStart(){
        super.onStart();
        //handler.sendEmptyMessage(0);
    }

    public class MyAsyncTask extends AsyncTask<String, Void, ArrayList<String>>
    {
        @Override
        protected  void onPreExecute(){
            super.onPreExecute();
        }
        @Override
        protected ArrayList<String> doInBackground(String... params) {
            ArrayList<String> list = new ArrayList<String>();
            ResultSet reset = null;
            Connection conn = null;

            try{
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                Log.e("*********", "*************1");
                String query = "select * from catego";
                Log.e("*********", "*************2");
                conn = DriverManager.getConnection(jdbUrl, dbId, dbPw);
                Log.e("*********", "*************3");
                Statement stmt = conn.createStatement();
                reset = stmt.executeQuery(query);
                Log.e("*********", "*************");

                while(reset.next()){
                    if(isCancelled()) break;
                    final String str = reset.getString(0);
                    list.add(str);
                }
                conn.close();
            } catch (Exception e){
                Log.w("에러싫어", ""+e.getMessage());
            }

            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<String> list){
            adapter.clear();
            adapter.addAll(list);
            adapter.notifyDataSetChanged();
            handler.sendEmptyMessageDelayed(0, 1000);
        }

        @Override
        protected  void onCancelled(){
            super.onCancelled();
        }
    }

    public Handler handler = new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            myAsyncTask = new MyAsyncTask();
            myAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
        }
    };
}
