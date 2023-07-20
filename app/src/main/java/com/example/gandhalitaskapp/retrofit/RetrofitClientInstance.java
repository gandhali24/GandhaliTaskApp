package com.example.gandhalitaskapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static   RetrofitClientInstance instance=null;
    private  RetrofitAPI retrofitAPI;

    private RetrofitClientInstance()
    {

        Retrofit retrofit=new Retrofit.Builder().baseUrl(RetrofitAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitAPI=retrofit.create(RetrofitAPI.class);


    }

    public  static synchronized RetrofitClientInstance getInstance()
    {
        if(instance==null)
        {
            instance=new RetrofitClientInstance();
        }
        return  instance;
    }


    public RetrofitAPI getRetrofitAPI()
    {

        return retrofitAPI;
    }
}
