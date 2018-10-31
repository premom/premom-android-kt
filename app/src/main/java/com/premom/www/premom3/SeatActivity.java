package com.premom.www.premom3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SeatActivity extends AppCompatActivity {
    Retrofit retrofit;
    ApiService apiService;
    TextView text;
    TextView text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofit=new Retrofit.Builder().baseUrl(ApiService.API_URL).build();
        //서버주소빌드해옴(?)
        apiService = retrofit.create(ApiService.class);
        //apiService클래스 생성
        text = (TextView) findViewById(R.id.text1);         //text1창을 text로
        text1 = (TextView) findViewById(R.id.text2);         //text1창을 text로


        Call<ResponseBody> comment = apiService.getComment();
        //getComment우리가 만든 함수 괄호 안은 변수명
        comment.enqueue(new Callback<ResponseBody>(){
            //데이터가 성공적으로 받아지면 onResponse, 실패하면 onFailure
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response){
                try {
                    String result = response.body().string();
                    Log.v("Test", result);
                    try {
                        JSONObject json = new JSONObject(result);
                        JSONArray data = json.getJSONArray("data");
/*
                        for(int i = 0; i < data.length(); i++) {

                            JSONObject obj = data.getJSONObject(i);
                            int is_seat = obj.getInt("is_seat");
                            int idx = obj.getInt("idx");


                            Log.v("Test2", Integer.toString(is_seat));
                            text.setText(Integer.toString(is_seat));
                            text1.setText(Integer.toString(is_seat));
                        }*/
                        JSONObject obj = data.getJSONObject(0);
                        int is_seat = obj.getInt("is_seat");
                        JSONObject obj2 = data.getJSONObject(1);
                        int is_seat2 = obj2.getInt("is_seat");

                        Log.v("Test1", Integer.toString(is_seat));
                        text.setText(Integer.toString(is_seat));
                        text1.setText(Integer.toString(is_seat2));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

//                    try {
//                        JSONArray jsonArray = new JSONArray(result);
                        /*
                        int post=0;
                        int id;
                        String name;
                        String mail;
                        String body;
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            post = jsonObject.getInt("is_seat");//postId변수명을 바꿔보았댱
                            id = jsonObject.getInt("idx");
                            Log.v("Test", jsonObject.toString());
                        }
                        //System.out.println(post);
                        TextView text;
                        text = (TextView) findViewById(R.id.text1);
                        text.setText(post);
                        */
//                    }catch(JSONException e){
//                            e.printStackTrace();
//                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t){

            }

        });
    }
}
