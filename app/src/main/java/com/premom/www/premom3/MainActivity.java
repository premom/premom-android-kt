package com.premom.www.premom3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    Button go_seat_txt;
    ImageButton go_seat_img;
    Button go_diary_txt;
    ImageButton go_diary_img;
    Button go_setting_txt;
    ImageButton go_setting_img;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setContentView(R.layout.activity_main);

        setViewId();

        go_seat_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goSeatActivity();
            }
        });

        go_seat_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goSeatActivity();
            }
        });

        go_diary_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goDiaryActivity();
            }
        });

        go_diary_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goDiaryActivity();
            }
        });

        go_setting_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goSettingActivity();
            }
        });

        go_setting_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goSettingActivity();
            }
        });
    }


    public void setViewId() {
        go_seat_txt = (Button) findViewById(R.id.menu_seat_txt);
        go_seat_img = (ImageButton) findViewById(R.id.menu_seat_img);
        go_diary_txt = (Button) findViewById(R.id.menu_diary_txt);
        go_diary_img = (ImageButton) findViewById(R.id.menu_diary_img);
        go_setting_txt = (Button) findViewById(R.id.menu_setting_txt);
        go_setting_img = (ImageButton) findViewById(R.id.menu_setting_img);
    }

    public void goSeatActivity(){
        Intent m1_Intent;
        m1_Intent = new Intent(this, SeatActivity.class);
        startActivity(m1_Intent);

//        finish();

    }

    public void goDiaryActivity(){
        Intent m2_Intent;
        m2_Intent = new Intent(this, DiaryActivity.class);
        startActivity(m2_Intent);

//        finish();

    }

    public void goSettingActivity(){
        Intent m3_Intent;
        m3_Intent = new Intent(this, SettingActivity.class);
        startActivity(m3_Intent);

//        finish();

    }

}