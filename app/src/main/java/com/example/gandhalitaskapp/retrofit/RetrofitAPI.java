package com.example.gandhalitaskapp.retrofit;

import com.example.gandhalitaskapp.model.CommonRepo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPI {


    String BASE_URL = "https://my-json-server.typicode.com/";


    @GET("iranjith4/ad-assignment/db")
    Call<CommonRepo> getsuperHeroes();

}
