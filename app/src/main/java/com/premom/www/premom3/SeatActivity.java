package com.premom.www.premom3;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SeatActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    Retrofit retrofit;
    ApiService apiService;
    TextView text;
    TextView seat_today;
    RecyclerView recycler;
    SeatAdapter mAdapter;
    ArrayList<SeatItem> item_views;
    Context mContext;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);

        setViewId();

        item_views = new ArrayList<>();
        mContext = this;
        mSwipeRefreshLayout.setOnRefreshListener(this);

        getSeatData();


    }

    public void setViewId() {
        retrofit = new Retrofit.Builder().baseUrl(ApiService.API_URL).build();
        apiService = retrofit.create(ApiService.class);
        recycler = (RecyclerView) findViewById(R.id.seat_view);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        seat_today = (TextView) findViewById(R.id.seat_today);
    }

    public void setDate() {
        SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String time = date.format(new Date(System.currentTimeMillis()));

        seat_today.setText(time);

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            setDate();
        }
    };


    @Override
    protected void onStart() {
        super.onStart();
        Thread myThread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        handler.sendMessage(handler.obtainMessage());
                        Thread.sleep(1000);
                    } catch (Throwable t) {
                    }
                }
            }
        });

        myThread.start();
    }



    public void getSeatData() {
        Call<ResponseBody> comment = apiService.getComment();


        comment.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    Log.v("Test", result);
                    try {
                        JSONObject json = new JSONObject(result);
                        JSONArray data = json.getJSONArray("data");

                        item_views.clear();

                        for (int i = 0; i < data.length(); i++) {

                            JSONObject obj = data.getJSONObject(i);

                            int is_seat = obj.getInt("is_seat");
                            int idx = obj.getInt("idx");
                            int car = obj.getInt("car");
                            int sub_car = obj.getInt("sub_car");
                            int fast_transfer = obj.getInt("fast_transfer");

                            SeatItem item = new SeatItem();
                            item.setIdx(idx);
                            item.setIs_seat(is_seat);
                            item.setCar(car);
                            item.setSub_car(sub_car);
                            item.setFast_transfer(fast_transfer);
                            item_views.add(item);
                        }



                        // set layoutManager
                        recycler.setLayoutManager(new LinearLayoutManager(mContext));

                        // set Adapter
                        mAdapter = new SeatAdapter(item_views);
                        recycler.setAdapter(mAdapter);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }

        });

    }


    @Override
    public void onRefresh() {
        // 새로고침 코드
        getSeatData();

        mSwipeRefreshLayout.setRefreshing(false);
    }
}
