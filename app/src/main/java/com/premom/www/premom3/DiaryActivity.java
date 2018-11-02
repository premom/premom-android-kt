package com.premom.www.premom3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DiaryActivity extends AppCompatActivity {
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");
    TabHost tabHost1;

    RecyclerView recycler;
    DiaryAdapter mAdapter;
    ArrayList<DiaryItem> item_views;
    Context mContext;

    ImageView pic_cho2;
    ImageButton diary_img_select;
    ImageButton write_btn;

    private final int GALLERY_CODE=1112;

    private DBHelper dbHelper;

    EditText view_title;
    TextView view_date;
    EditText view_content;


    byte[] data_img;
    String data_title;
    String data_content;
    String data_date;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        setDatabase();

        setViewId();

        item_views = new ArrayList<>();
        mContext = this;

        view_date.setText(getTime());

        setTab();
        getDiaryData();
        clickEvent();
    }

    private void setDatabase() {
        if(dbHelper == null) {
            dbHelper = new DBHelper(DiaryActivity.this, "diary", null, 1);
        }
    }

    public void save() {
        DiaryItem data = new DiaryItem();
        data.setIs_pic(data_img);
        data.setContent(data_content);
        data.setDate(data_date);
        data.setTitle(data_title);

        dbHelper.addDiary(data);


    }

    public boolean checkWrite() {
        if(view_content.getText().toString().equals("") || view_content.getText().toString() == null) {
            return false;
        } else if(view_title.getText().toString().equals("") || view_title.getText().toString() == null) {
            return false;
        } else if(pic_cho2.getDrawable() == null) {
            return false;
        } else {
            data_content = view_content.getText().toString();
            data_title = view_title.getText().toString();
            data_date = view_date.getText().toString();
            data_img = getByteArrayFromDrawable(pic_cho2.getDrawable());
            return true;
        }
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
        ts2.setIndicator("Record") ;
        tabHost1.addTab(ts2) ;

        // 세 번째 Tab. (탭 표시 텍스트:"TAB 3"), (페이지 뷰:"content3")
        TabHost.TabSpec ts3 = tabHost1.newTabSpec("Tab Spec 3") ;
        ts3.setContent(R.id.tab3) ;
        ts3.setIndicator("Diary") ;
        tabHost1.addTab(ts3) ;

        tabHost1.setCurrentTab(1);
    }

    public void clearWriteView() {
        view_title.setText("");
        view_content.setText("");
        pic_cho2.setImageBitmap(null);
        diary_img_select.setImageResource(R.drawable.write_pic);
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view_title.getWindowToken(), 0);
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
                if(checkWrite()) {
                    save();
                    clearWriteView();
                    getDiaryData();
                    Toast.makeText(mContext, "글이 저장되었습니다.", Toast.LENGTH_SHORT).show();
                    tabHost1.setCurrentTab(1);
                } else {
                    Toast.makeText(mContext, "모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void setViewId() {
        tabHost1 = (TabHost) findViewById(R.id.tabHost1) ;
        pic_cho2 = (ImageView) findViewById(R.id.pic_cho2);
        diary_img_select = (ImageButton) findViewById(R.id.diary_img_select);
        recycler = (RecyclerView) findViewById(R.id.pic_view);
        write_btn = (ImageButton) findViewById(R.id.write_btn);

        view_date = (TextView) findViewById(R.id.data_date);
        view_content = (EditText) findViewById(R.id.data_content);
        view_title = (EditText) findViewById(R.id.data_title);

    }



    public byte[] getByteArrayFromDrawable(Drawable d) {
        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

        byte[] data = stream.toByteArray();

        return stream.toByteArray();
    }

    public void getDiaryData() {
        item_views = dbHelper.getAllDiaryData();
Log.d("test", Integer.toString(item_views.size()));
        recycler.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new DiaryAdapter(item_views);
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
