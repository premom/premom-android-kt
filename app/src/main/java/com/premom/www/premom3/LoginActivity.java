package com.premom.www.premom3;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.app.Activity;
import android.app.ProgressDialog;
import android.app.Activity;
import android.app.ProgressDialog;

import java.io.IOException;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.kt.gigaiot_sdk.GigaIotOAuth;
import com.kt.gigaiot_sdk.PushApi;
import com.kt.gigaiot_sdk.data.GiGaIotOAuthResponse;
import com.kt.gigaiot_sdk.network.ApiConstants;
import com.kt.iot.mobile.GiGaIotApplication;
import com.kt.iot.mobile.android.R;
import com.kt.iot.mobile.utils.ApplicationPreference;
import com.kt.iot.mobile.utils.Blur;
import com.kt.iot.mobile.utils.BitmapUtil;
import com.kt.iot.mobile.utils.Common;


public class LoginActivity extends Activity implements View.OnClickListener {

    Button m_refButton;
    EditText user_id;
    EditText user_pw;
    private SharedPreferences appData;
    private boolean saveLoginData;

    private GoogleCloudMessaging mGcm;

    mGcm = GoogleCloudMessaging.getInstance(LoginActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_login);

        this.m_refButton =(Button)findViewById(R.id.fl_button01);
        this.user_id =(EditText)findViewById(R.id.user_id);
        this.user_pw =(EditText)findViewById(R.id.user_pw);
        user_id.setTypeface(GiGaIotApplication.getDefaultTypeFace());
        user_pw.setTypeface(GiGaIotApplication.getDefaultTypeFace());

        appData = getSharedPreferences("appData", MODE_PRIVATE);
        load();


        if (saveLoginData) {
            login();
        }
    }

//    public void fl_BtnClick(View v){
//        if(user_id.getText().toString().equals("test") && user_pw.getText().toString().equals("test") ) {
//            login();
//        }  else {
//            Toast.makeText(this, "아이디 또는 패스워드가 잘못되었습니다.", Toast.LENGTH_SHORT).show();
//        }
//    }
public void onClick(View v) {
    switch(v.getId()){

        case R.id.iv_login_bt:

            String id = mEtId.getText().toString();
            String pw = mEtPw.getText().toString();

            if(TextUtils.isEmpty(id)){
                Toast.makeText(LoginActivity.this, R.string.login_id_empty, Toast.LENGTH_SHORT).show();
                return;

            }else if(TextUtils.isEmpty(pw)){
                Toast.makeText(LoginActivity.this, R.string.login_pw_empty, Toast.LENGTH_SHORT).show();
                return;
            }

            new LoginTask().execute();

            break;

    }
}
    private void login() {
        save();

        Intent ref_Intent;
        ref_Intent = new Intent(this, MainActivity.class);
        startActivity(ref_Intent);

        LoginActivity.this.finish();

    }


    private void save() {
        SharedPreferences.Editor editor = appData.edit();

        editor.putBoolean("SAVE_LOGIN_DATA", true);
        editor.putString("user_id", "test");
        editor.putString("user_pw", "test");

        editor.apply();
    }

    private void load() {
        saveLoginData = appData.getBoolean("SAVE_LOGIN_DATA", false);
    }


    @Override
//    public void onClick(View v) {
//
//    }
//}
