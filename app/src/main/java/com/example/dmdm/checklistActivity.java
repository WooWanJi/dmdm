package com.example.dmdm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class checklistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> listView,
                                    View itemView,
                                    int position,
                                    long id) {
                if (position == 0) {
                    Intent intent = new Intent(checklistActivity.this,
                            nodleActivity.class);
                    startActivity(intent);
                }
                /*if (position == 1) {
                    Intent intent = new Intent(checklistActivity.this,
                            paperActivity.class);
                    startActivity(intent);
                }*/
            }
        };

        ListView listview = (ListView) findViewById(R.id.checklist) ;
        listview.setOnItemClickListener(itemClickListener);
    }
}