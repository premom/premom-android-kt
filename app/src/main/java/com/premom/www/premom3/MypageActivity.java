package com.premom.www.premom3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class MypageActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        btn1 = (Button) findViewById(R.id.pg_menu1);
        btn2 = (Button) findViewById(R.id.pg_menu2);
        btn3 = (Button) findViewById(R.id.pg_menu3);
        btn4 = (Button) findViewById(R.id.pg_menu4);
        btn5 = (Button) findViewById(R.id.pg_menu5);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(MypageActivity.this, "준비중입니다~^^*", Toast.LENGTH_SHORT).show();
    }

}
