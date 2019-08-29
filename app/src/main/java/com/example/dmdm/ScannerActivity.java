package com.example.dmdm;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class ScannerActivity extends AppCompatActivity {
    //view Objects
    private Button buttonScan;
    private TextView category, name, price, p_id;
    NetworkTask networkTask = null;


    //qr code scanner object
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        //View Objects
        buttonScan = (Button) findViewById(R.id.buttonScan);
        name = (TextView) findViewById(R.id.name);
        category = (TextView) findViewById(R.id.category);
        price = (TextView)  findViewById(R.id.price);
        p_id = (TextView) findViewById(R.id.p_id);

        //intializing scan object
        qrScan = new IntentIntegrator(this);

        //button onClick
        buttonScan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //scan option
                qrScan.setPrompt("Scanning...");
                //qrScan.setOrientationLocked(false);
                qrScan.initiateScan();
            }
        });
    }

    public class NetworkTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        @Override
        protected String doInBackground(String... strings) {
            //Log.e("*********", "*************2");
            try {
                String str;
                URL url = new URL("http://220.76.34.131:8080/dmdm/product.jsp");
                HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setRequestProperty("Context_Type", "application/x-www-form-urlencoded");
                urlConn.setRequestMethod("POST");

                OutputStreamWriter osw = new OutputStreamWriter(urlConn.getOutputStream());
                sendMsg = "p_id="+strings[0];
                osw.write(sendMsg);
                osw.flush();

                if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStreamReader tmp = new InputStreamReader(urlConn.getInputStream());
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();

                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                } else{
                    Log.i("결과", urlConn.getResponseCode()+"에러");
                }

                osw.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
            return receiveMsg;
        }
    }
                    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //qrcode 가 없으면
            if (result.getContents() == null) {
                Toast.makeText(ScannerActivity.this, "취소!", Toast.LENGTH_SHORT).show();
            } else {
                //qrcode 결과가 있으면
                Toast.makeText(ScannerActivity.this, "스캔완료!", Toast.LENGTH_SHORT).show();
                try {
                    //data를 json으로 변환
                    //JSONObject obj = new JSONObject(result.getContents());
                    //name.setText(obj.getString("name"));

                } catch (Exception e) {
                    e.printStackTrace();
                    //Toast.makeText(MainActivity.this, result.getContents(), Toast.LENGTH_LONG).show();

                } finally {
                    category.setText(result.getContents());
                    name.setText(result.getContents());
                    price.setText(result.getContents());
                }
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}