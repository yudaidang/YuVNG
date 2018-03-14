package com.example.cpu11268_local.yuvng;


import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        actionBNav(bottomNavigationView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        actionFAB(fab);



    }

    private void actionFAB( FloatingActionButton fab){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation anim = (AnimationSet) AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_anim);
                view.startAnimation(anim);
            }
        });
    }

    private void actionBNav( BottomNavigationView bnv){
        BottomNavigationViewHelper.disableShiftMode(bnv);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.action_home:

                        break;
                    case R.id.action_contact:
                        fm = getSupportFragmentManager();
                        fragmentTransaction = fm.beginTransaction();
                        ContactFragment contactFragment = new ContactFragment();
                        fragmentTransaction.replace(R.id.container, contactFragment);
                        fragmentTransaction.commit();
                        break;
                    case R.id.action_message:
                        fm = getSupportFragmentManager();
                        fragmentTransaction = fm.beginTransaction();
                        SmsFragment smsFragment = new SmsFragment();
                        fragmentTransaction.replace(R.id.container, smsFragment);
                        fragmentTransaction.commit();
                        break;
                    case R.id.action_setting:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }



}