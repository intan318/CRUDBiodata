package com.example.crudbiodataagustus.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.95.249/biodata_diri/index.php/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    public static ApiInterface service = retrofit.create(ApiInterface.class);
}
