package com.premom.www.premom3;

import android.content.Intent;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class SettingActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        addPreferencesFromResource(R.xml.setting_preference);

    }

//    public void Btn_to_Main(View v){
//        Intent intent;
//        intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//
//        finish();
//
//    }
}
