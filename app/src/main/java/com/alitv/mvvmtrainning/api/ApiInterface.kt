package com.alitv.mvvmtrainning.api

import com.alitv.mvvmtrainning.model.LoginResponseModel
import com.alitv.mvvmtrainning.model.RegisterResponseModel
import io.reactivex.Single
import retrofit2.http.*


interface  ApiInterface{
    @GET("user/login")
    fun login(@Query("email") email: String, @Query("password") password: String): Single<LoginResponseModel>

    @FormUrlEncoded
    @POST("user/register")
    fun register(
        @Field("email") email: String?, @Field("name") name: String?,
        @Field("address") address: String?, @Field("dob") dob: String?,
        @Field("password") password: String?
    ): Single<RegisterResponseModel>
}