package com.premom.www.premom3;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TabHost;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class DiaryActivity extends AppCompatActivity {
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");
    TextView mTextView;
    TextView mTextView2;

    RecyclerView recycler;
    MyAdapter2 mAdapter;
    ArrayList<MyItem2> item_views;
    Context mContext;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        setViewId();

        item_views = new ArrayList<>();
        mContext = this;

        getDiaryData();

        mTextView = (TextView) findViewById(R.id.TextView);
        mTextView.setText(getTime());/*
        mTextView2 = (TextView) findViewById(R.id.pic_is_pic);
        mTextView2.setText(getTime());*/
        TabHost tabHost1 = (TabHost) findViewById(R.id.tabHost1) ;
        tabHost1.setup() ;

        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"content1")
        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1") ;
        ts1.setContent(R.id.tab1) ;
        ts1.setIndicator("Calendar") ;
        tabHost1.addTab(ts1)  ;

        // 두 번째 Tab. (탭 표시 텍스트:"TAB 2"), (페이지 뷰:"content2")
        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2") ;
        ts2.setContent(R.id.tab2) ;
        ts2.setIndicator("Record") ;
        tabHost1.addTab(ts2) ;

        // 세 번째 Tab. (탭 표시 텍스트:"TAB 3"), (페이지 뷰:"content3")
        TabHost.TabSpec ts3 = tabHost1.newTabSpec("Tab Spec 3") ;
        ts3.setContent(R.id.tab3) ;
        ts3.setIndicator("Diary") ;
        tabHost1.addTab(ts3) ;
    }
    public void setViewId() {
        recycler = (RecyclerView) findViewById(R.id.pic_view);
    }

    public void getDiaryData() {
        for (int i = 0; i < 2; i++) {

            //JSONObject obj = data.getJSONObject(i);
            int is_pic = 0;//사진

            MyItem2 item = new MyItem2();
            item.setIs_pic(is_pic);

            item_views.add(item);
        }
        recycler.setLayoutManager(new LinearLayoutManager(mContext));

        // set Adapter
        mAdapter = new MyAdapter2(item_views);
        recycler.setAdapter(mAdapter);

    }

    private String getTime(){
        mDate = new Date();
        return mFormat.format(mDate);

    }
}
