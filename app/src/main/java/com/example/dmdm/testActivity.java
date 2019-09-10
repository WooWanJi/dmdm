package com.example.camera;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import java.util.Scanner;

public class testActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //툴바
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("담아담아");
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // ?꾩옱 ?붾㈃???쒖뼱沅뚯옄
                        MainActivity.class); // ?ㅼ쓬 ?섏뼱媛??대옒??吏??
                startActivity(intent); // ?ㅼ쓬 ?붾㈃?쇰줈 ?섏뼱媛꾨떎
            }
        });
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
//오른쪽 상단 토큰버튼(바코드 스캔)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_barcodeScan) {
            Intent intent = new Intent(
                    getApplicationContext(), // ?꾩옱 ?붾㈃???쒖뼱沅뚯옄
                    ScannerActivity.class); // ?ㅼ쓬 ?섏뼱媛??대옒??吏??
            startActivity(intent); // ?ㅼ쓬 ?붾㈃?쇰줈 ?섏뼱媛꾨떎

        }
        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
            // Handle navigation view item clicks here.

        switch (item.getItemId()) {
//            case R.id.checklist:
//                Intent checklist = new Intent(getApplicationContext(), testActivity.class);
//                startActivity(checklist);
//                break;
            case R.id.nav_logout:
            Intent logout = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(logout);
            break;
            case R.id.nav_hist:
                Intent hist = new Intent(getApplicationContext(), historyActivity.class);
                startActivity(hist);
                break;
            case R.id.nav_mypage:
                Intent mypage = new Intent(getApplicationContext(), mypageActivity.class);
                startActivity(mypage);
                break;
        }
            int id = item.getItemId();
            Fragment fragment = null;

//        String title = getString(R.string.app_name);
//            if (id == R.id.nav_mypage) {
//            fragment = new homeFragment();
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.content_test, fragment)
//                    .commit();
//            title = "Homes";
//        } else if (id == R.id.nav_hist) {
//            fragment = new histFragment();
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.content_test, fragment)
//                    .commit();
//            title = "hist";
//        } else if (id == R.id.nav_logout) {
//
//        }
//        if (fragment != null ) {
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.replace(R.id.content_test, fragment);
//            ft.commit();
//        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);     //기능을 수행하고 네비게이션 닫기
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
