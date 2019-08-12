package com.example.crudbiodataagustus.network;

import com.example.crudbiodataagustus.model.ResponseGetBiodata;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("Server/getBiodata")
    Call<ResponseGetBiodata> actionGetBiodata();

    @FormUrlEncoded
    @POST("Server/insertBiodata")
    Call<ResponseGetBiodata> actionInsert(@Field("nama") String nama,
                                          @Field("jekel") String jekel,
                                          @Field("hobi") String hobi,
                                          @Field("alamat") String alamat);


    @FormUrlEncoded
    @POST("Server/updateBiodata")
    Call<ResponseGetBiodata> actionUpdate (@Field("id") String id,
                                     @Field("nama") String nama,
                                     @Field("jekel") String jekel,
                                     @Field("hobi") String hobi,
                                     @Field("alamat") String alamat);

    @FormUrlEncoded
    @POST("Server/deleteBiodata")
    Call<ResponseGetBiodata> actionDelete (@Field("id") String id);
}
