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

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class ScannerActivity extends AppCompatActivity {

    private Button buttonScan, buttonAdd;
    private TextView Smallcatego,p_name, p_price;
    

    ArrayList<String> arrayList = new ArrayList<String>();
    NetworkTask networkTask = null;

    private IntentIntegrator qrScan;
    private TextView p_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        //View Objects
        buttonScan = (Button) findViewById(R.id.buttonScan);
        p_name = (TextView) findViewById(R.id.name);
        Smallcatego = (TextView) findViewById(R.id.category);
        p_price = (TextView)  findViewById(R.id.price);
        p_id = (TextView) findViewById(R.id.p_id);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);

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

        buttonAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    //바코드번호를 execute해서 networkTask함수로 넘어옴
    public class NetworkTask extends AsyncTask<String, Void, ArrayList<String>> {
        String sendMsg, receiveMsg;
        @Override
        protected ArrayList<String> doInBackground(String... strings) {
            //이 함수의 반환형을 리턴에 맞도록 변경 step 4
            try {
                String str;
                String json = "";
                URL url = new URL("http://210.121.81.97:8080/dmdm/scanner.jsp");
                HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setRequestProperty("Context_Type", "application/x-www-form-urlencoded");
                urlConn.setRequestMethod("POST");

                OutputStreamWriter osw = new OutputStreamWriter(urlConn.getOutputStream());
                sendMsg = "p_id="+strings[0]; //p_id=08054974645 sendMsg에 p_id와 string[0]번째 요소로 입력됨
                osw.write(sendMsg);
                osw.flush();

                //jsp파일과 연동
                if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStreamReader tmp = new InputStreamReader(urlConn.getInputStream());
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();

                    //jsp파일의 결과를 한줄씩 읽어서 str 저장 후 buffer에 str을 추가
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    //receiveMsg는 json형식의 buffer로 저장
                    receiveMsg = buffer.toString();
                    /*
                    String을 json형식으로 바꿔야돼 그래 맞아 이게 step 1이야
                     */
                    //알맞은 위치에서 선언한 arraylist에 json값 넣기 step2
                    JSONArray jsonArray = new JSONArray(receiveMsg);
                    for(int i=0;i<jsonArray.length();i++){
                        arrayList.add(jsonArray.get(i).toString());
                    }

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
            //arraylist형식으로 리턴
            return arrayList;
            /*
                    우리가 쓰려고 만들어 놓은 arrayList를 리턴해야하잔아 그 arrayList 어디서 값채워 넣어? step2
                    arrayList 리턴
                    step 3


            */
       }
    }

    //바코드 스캔하면 onActivityResult 함수로 넘어옴
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
                    networkTask = new NetworkTask();
                    ArrayList<String> product = networkTask.execute(result.getContents()).get(); //바코드번호를 execute해서 networkTask함수로 넘어감
                    //product를 arrayList 로 바꾸기 step 5

                    //리턴하면 여기로 돌아옴, product에 맞는 텍스트뷰를 입력해서 채워줌
                    /*
                     product.get(0):  smallcatego
                     1:
                     2:
                     그걸 각각에 맞는 텍스트뷰에 채워 넣어줘
                     step 6
                     */

                    //Smallcatego = product.get(0).toString();
                    Smallcatego.setText(product.get(0));
                    p_name.setText(product.get(1));
                    p_price.setText(product.get(2));


                } catch (Exception e) {
                    e.printStackTrace();
                    //Toast.makeText(MainActivity.this, result.getContents(), Toast.LENGTH_LONG).show();

                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}