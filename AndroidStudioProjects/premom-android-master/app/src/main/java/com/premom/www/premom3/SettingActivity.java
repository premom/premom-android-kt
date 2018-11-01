package com.premom.www.premom3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    public void Btn_to_Main(View v){
        Intent intent;
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        finish();

    }
}
