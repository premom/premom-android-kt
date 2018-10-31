package com.premom.www.premom3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button m_refButton;

    public void sl_BtnClick(View v){
        Intent ref_Intent;
        ref_Intent = new Intent(this, LoginActivity.class);
        startActivity(ref_Intent);

        finish();

    }
    protected void onCreate(Bundle savedInstanceState) {
        // hyez test
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        m_refButton=(Button)findViewById(R.id.sl_button);
    }
}
