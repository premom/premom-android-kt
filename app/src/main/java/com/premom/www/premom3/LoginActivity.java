package com.premom.www.premom3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;


public class LoginActivity extends AppCompatActivity {
    Button m_refButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_login);

        this.m_refButton =(Button)findViewById(R.id.fl_button01);

    }
    public void fl_BtnClick(View v){
        Intent ref_Intent;

        ref_Intent = new Intent(packageContext: this, TestNextIntentActivity.class);

        startActivity(ref_Intent);

    }
}
