package com.premom.www.premom3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button m_refButton; //뒤로가기

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setContentView(R.layout.activity_main);

    }

    public void sl_BtnClick(View v){
        Intent ref_Intent;
        ref_Intent = new Intent(this, LoginActivity.class);
        startActivity(ref_Intent);

        finish();

    }

    public void m1_BtnClick(View v){
        Intent m1_Intent;
        m1_Intent = new Intent(this, SeatActivity.class);
        startActivity(m1_Intent);

        finish();

    }

    public void m2_BtnClick(View v){
        Intent m2_Intent;
        m2_Intent = new Intent(this, SettingActivity.class);
        startActivity(m2_Intent);

        finish();

    }

    public void m3_BtnClick(View v){
        Intent m3_Intent;
        m3_Intent = new Intent(this, SettingActivity.class);
        startActivity(m3_Intent);

        finish();

    }



}