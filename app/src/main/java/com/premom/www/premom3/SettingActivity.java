package com.premom.www.premom3;

import android.app.Service;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class SettingActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

    }

    public void inform_btn(View v){
        Intent inform_Intent;
        inform_Intent = new Intent(this, InformActivity.class);
        startActivity(inform_Intent);

        finish();
    }

    public void service_btn(View v){
        Intent service_Intent;
        service_Intent = new Intent(this, ServiceHelpActivity.class);
        startActivity(service_Intent);

        finish();
    }

    public void logout_btn(View v){
        Intent logout_Intent;
        logout_Intent = new Intent(this, LogoutActivity.class);
        startActivity(logout_Intent);

        finish();
    }

    }






