package com.example.dmdm;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class joinActivity extends AppCompatActivity {
    private EditText editTextId;
    private EditText editTextPw;
    private EditText editTextNm;
    private EditText editTextPhone;
    String Id = "";
    String Pw = "";
    String Nm = "";
    String Phone = "";
    String sUrl = "http://220.93.8.187:8080/dmdm/insert.jsp";
    NetworkTask networkTask = new NetworkTask();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        setContentView(R.layout.activity_join);

        editTextId= (EditText)findViewById(R.id.Tjoin_email);
        editTextPw= (EditText)findViewById(R.id.Tjoin_pw);
        editTextNm = (EditText)findViewById(R.id.Tc_name);
        editTextPhone= (EditText)findViewById(R.id.Tc_num);

        Button sign_up = (Button) findViewById(R.id.signUp);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Id = editTextId.getText().toString();
                Pw = editTextPw.getText().toString();
                Nm = editTextNm.getText().toString();
                Phone = editTextPhone.getText().toString();

                networkTask.execute();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class); //
                startActivity(intent); //
            }
        });
    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... parms){
            HttpURLConnection urlConn = null;
            String page = "";

            try{
                StringBuffer sbUrl = new StringBuffer();
                URL url = new URL(sbUrl.append(sUrl).append("?").append("c_id=").append(Id).append("&pw=").append(Pw).append("&c_name=").append(Nm).append("&c_num=").append(Phone).toString());
                urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setRequestMethod("GET");
                urlConn.setRequestProperty("Accept-Charset", "UTF-8");
                urlConn.setRequestProperty("Context_Type", "application/x-www-form-urlencoded;cahrset=UTF-8");

                if(urlConn.getResponseCode() != HttpURLConnection.HTTP_OK)
                    return null;

                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));

                String line;

                while ((line = reader.readLine() )!= null) {
                    page += line;
                }
                return page;
            }catch(MalformedURLException e){
                System.out.println(page);
                e.printStackTrace();
            }catch (IOException e){
                System.out.println(page);
                e.printStackTrace();
            }finally {
                if(urlConn != null)
                    urlConn.disconnect();
            }
            return  null;
        }
        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);
        }
    }
}

