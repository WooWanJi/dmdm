/*
package com.example.dmdm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class listActivity extends AppCompatActivity {
    public static String EXTRA_LISTID = "listId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();
        int listId = (Integer) getIntent().getExtras().get(EXTRA_LISTID);
        Menu menu = Menu.menus[listId];

        TextView name = (TextView)findViewById(R.id.name);
        name.setText(menu.getName());

        Button btn_add = (Button) findViewById(R.id.add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext(), firstActivity.class); // 현재화면 제어권자, 다음 넘어갈 클래스
                //intent.putExtra("message", "반갑습니다.");
                startActivity(intent);
            }
        });

    }
}
*/
