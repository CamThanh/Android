package com.camthanh.vn.camthanhbook.rest;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static Retrofit retrofit = null;

    public static Retrofit getClientForRegister(final String token, final String contentType) {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "Basic " + token).addHeader("Content-Type", contentType)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().client(client)
                    .baseUrl(Config.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getClient() {


        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Config.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
