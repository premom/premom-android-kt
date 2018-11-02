package com.premom.www.premom3;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class DiaryActivity extends AppCompatActivity {
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");
    TextView mTextView;
    TextView mTextView2;
    TabHost tabHost1;

    RecyclerView recycler;
    MyAdapter2 mAdapter;
    ArrayList<MyItem2> item_views;
    Context mContext;

    ImageView pic_cho2;
    ImageButton diary_img_select;
    ImageButton write_btn;

    private final int GALLERY_CODE=1112;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        setViewId();

        item_views = new ArrayList<>();
        mContext = this;

        mTextView.setText(getTime());

        setTab();
        getDiaryData();
        clickEvent();
    }

    public void setTab(){
        tabHost1.setup() ;

        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"content1")
        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1") ;
        ts1.setContent(R.id.tab1) ;
        ts1.setIndicator("Calendar") ;
        tabHost1.addTab(ts1)  ;

        // 두 번째 Tab. (탭 표시 텍스트:"TAB 2"), (페이지 뷰:"content2")
        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2") ;
        ts2.setContent(R.id.tab2) ;
        ts2.setIndicator("TAB 2") ;
        tabHost1.addTab(ts2) ;

        // 세 번째 Tab. (탭 표시 텍스트:"TAB 3"), (페이지 뷰:"content3")
        TabHost.TabSpec ts3 = tabHost1.newTabSpec("Tab Spec 3") ;
        ts3.setContent(R.id.tab3) ;
        ts3.setIndicator("Diary") ;
        tabHost1.addTab(ts3) ;
    }

    public void clickEvent(){
        diary_img_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectGallery();
            }
        });

        write_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "test", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setViewId() {
        tabHost1 = (TabHost) findViewById(R.id.tabHost1) ;
        mTextView = (TextView) findViewById(R.id.TextView);
        pic_cho2 = (ImageView) findViewById(R.id.pic_cho2);
        diary_img_select = (ImageButton) findViewById(R.id.diary_img_select);
        recycler = (RecyclerView) findViewById(R.id.pic_view);
        write_btn = (ImageButton) findViewById(R.id.write_btn);
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

    private void selectGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case GALLERY_CODE:
                    sendPicture(data); //갤러리에서 가져오기
                    break;
                default:
                    break;
            }

        }
    }

    private void sendPicture(Intent data) {

        if( data != null )
        {
            Uri thumbnail = data.getData();
            if( thumbnail != null )
            {
                pic_cho2.setImageURI(thumbnail);
                diary_img_select.setImageBitmap(null);
            }
        }

    }

}
