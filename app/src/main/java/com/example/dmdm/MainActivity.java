package com.example.dmdm;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    private EditText Id, Pw;
    Button joinBtn, loginBtn;

    NetworkTask networkTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Id= (EditText)findViewById(R.id.editEmail);
        Pw= (EditText)findViewById(R.id.editPasswd);
        loginBtn = (Button)findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(btnListener);

        Button b = (Button) findViewById(R.id.joinBtn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        joinActivity.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });
        Button scanner = (Button) findViewById(R.id.scanBtn);
        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        ScannerActivity.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다
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
                URL url = new URL("http://220.76.34.131:8080/dmdm/login.jsp");
                HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setRequestProperty("Context_Type", "application/x-www-form-urlencoded");
                urlConn.setRequestMethod("POST");

                OutputStreamWriter osw = new OutputStreamWriter(urlConn.getOutputStream());
                sendMsg = "c_id="+strings[0]+"&pw="+strings[1];
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

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.loginBtn:
                    String loginid = Id.getText().toString();
                    String loginpw = Pw.getText().toString();
                    try {
                        networkTask = new NetworkTask();
                        String result = networkTask.execute(loginid, loginpw, "login").get();
                        result = result.replace("\t","");
                        if (result.equals("1")) {
                            Toast.makeText(MainActivity.this, "로그인", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, checklistActivity.class);
                            startActivity(intent);
                            finish();
                        } else if (result.equals("2")) {
                            Toast.makeText(MainActivity.this, "아이디 또는 비밀번호가 틀렸습니다", Toast.LENGTH_LONG).show();
                            Id.setText("");
                            Pw.setText("");
                        } else if (result.equals("0")) {
                            Log.e("*********", "*************");
                            Toast.makeText(MainActivity.this, "회원정보 없음", Toast.LENGTH_LONG).show();
                            Id.setText("");
                            Pw.setText("");
                        }
                    } catch (Exception e) {
                    }
                    break;


            }
            networkTask = null;
        }
    };
}

