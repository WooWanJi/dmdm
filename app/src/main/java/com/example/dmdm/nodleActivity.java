package com.example.dmdm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class nodleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nodle);
        ArrayAdapter<Menu> listadapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, Menu.menus);
        ListView listMenus = (ListView)findViewById(R.id.nodle_list);
        listMenus.setAdapter(listadapter);

        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(nodleActivity.this, firstActivity.class);
                        intent.putExtra(listActivity.EXTRA_LISTID, (int) id);
                        startActivity(intent);
                    }
                };
    }
}
