package com.camthanh.vn.camthanhbook.rest;

import com.camthanh.vn.camthanhbook.signinup.SignInResponse;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by gulgulu on 14-02-2018.
 */

public interface CallInterface {

    @GET("daily?")
        //http://api.openweathermap.org/data/2.5/forecast/daily?q=Palermo,it&cnt=8&APPID=b241c825823611967aaaa82711951620
    retrofit2.Call<SignInResponse> getWeatherDays(@Query("q") String city, @Query("cnt") String countDays, @Query("units") String unitType, @Query("APPID") String appID);

    @FormUrlEncoded
    @POST("/camthanh-oauth2/oauth/token")
    retrofit2.Call<SignInResponse> register(@Field("grant_type") String grant_type, @Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("/camthanh-oauth2/oauth/token")
    retrofit2.Call<SignInResponse> getRegister(@Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Field("grant_type") String grant_type, @Field("username") String username, @Field("password") String password);

    @POST("/camthanh-oauth2/oauth/token")
    @FormUrlEncoded
    Call<TokenResponse> getToken(@Field("client_id") String client_id, @Field("client_secret") String client_secret, @Field("scope") String scope, @Field("grant_type") String grant_type);
}

