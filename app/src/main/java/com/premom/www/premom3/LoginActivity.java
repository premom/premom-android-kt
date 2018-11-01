package com.premom.www.premom3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {
    Button m_refButton;
    EditText user_id;
    EditText user_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_login);

        this.m_refButton =(Button)findViewById(R.id.fl_button01);
        this.user_id =(EditText)findViewById(R.id.user_id);
        this.user_pw =(EditText)findViewById(R.id.user_pw);

    }
    public void fl_BtnClick(View v){

        if(user_id.getText().toString().equals("test") && user_pw.getText().toString().equals("test") ) {
            Intent ref_Intent;


            ref_Intent = new Intent(this, MainActivity.class);

            startActivity(ref_Intent);
            LoginActivity.this.finish();
        } else {
            Toast.makeText(this, "아이디 또는 패스워드가 잘못되었습니다.", Toast.LENGTH_SHORT).show();
        }


    }
}
