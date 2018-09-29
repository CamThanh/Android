package com.camthanh.vn.camthanhbook.rest;

import android.content.Context;

import com.camthanh.vn.camthanhbook.signinup.SignInResponse;
import com.camthanh.vn.camthanhbook.utils.ProgressLoading;

import retrofit2.Callback;
import retrofit2.Response;


public class Call<T> {

    private ProgressLoading loading;
    private retrofit2.Call<SignInResponse> call;
    private Delegate delegate;
    private String city;

    public Call(Context context, String city, Delegate delegate) {
        this.delegate       = delegate;
        this.city           = city;
        this.loading        = new ProgressLoading(context);
    }

    public void execute() {
        loading.onShow();
        CallInterface weatherCallInterface = Api.getClient().create(CallInterface.class);
        call = weatherCallInterface.getWeatherDays(city, Config.DAYS, Config.UNIT_TYPE, Config.APP_KEY);
        call.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(retrofit2.Call<SignInResponse> call, Response<SignInResponse> response) {
                int statusCode = response.code();
                if(statusCode == Config.RESP_OK) {
                    delegate.onSuccess(response.body());
                } else {
                    delegate.onFailure("On Failure - " + statusCode);
                }
                loading.dismiss();
            }
            @Override
            public void onFailure(retrofit2.Call<SignInResponse> call, Throwable t) {
                delegate.onFailure(t.getMessage());
                loading.dismiss();
            }
        });
    }

    public interface Delegate {
        void onSuccess(SignInResponse weatherResponse);
        void onFailure(Object t);
    }

}
