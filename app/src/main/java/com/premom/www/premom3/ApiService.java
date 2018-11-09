//API인터페이스
package com.premom.www.premom3;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    public static final String API_URL = "http://52.79.238.48:8080/";
    //서버주소를 담고 있는 상수선언

    @GET("api/seat/1") //API주소=서버주소
    Call<ResponseBody>getComment();
    //Call<ResponseBody>함수이름(@Query("변수 이름"), 안드로이드에서 보낼 변수)
}

