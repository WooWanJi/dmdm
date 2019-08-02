package com.example.dmdm;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class firstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Button btn_search = (Button) findViewById(R.id.search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext(), checklistActivity.class); // 현재화면 제어권자, 다음 넘어갈 클래스
                //intent.putExtra("message", "반갑습니다.");
                startActivity(intent);
            }
        });
        Button btn_history = (Button) findViewById(R.id.history);
        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext(), historyActivity.class); // 현재화면 제어권자, 다음 넘어갈 클래스
                //intent.putExtra("message", "반갑습니다.");
                startActivity(intent);
            }
        });
    }
}
