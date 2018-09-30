package com.camthanh.vn.camthanhbook.rest;

import com.camthanh.vn.camthanhbook.signinup.SignInResponse;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static retrofit2.converter.gson.GsonConverterFactory.*;

public class WebService {
    private static WebService instance;
    private CallInterface api;
    private retrofit2.Call<JSONObject> callRegister;
    String CLIENT_ID = "spring-security-oauth2-read-write-client";
    String CLIENT_SECRET = "spring-security-oauth2-read-write-client-password1234";
    String grant_type = "password";

    public WebService() {

        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder().client(client)
                .addConverterFactory(create())
                .baseUrl(Urls.MAIN_URL)
                .build();

        api = retrofit.create(CallInterface.class);
    }

    public static WebService getInstance() {
        if (instance == null) {
            instance = new WebService();
        }
        return instance;
    }

    public CallInterface getApi() {
        return api;
    }

    public void loginUser(String username, String password, final ServiceCallBack serviceCallBack) {
        callRegister = api.loginAccount(grant_type, CLIENT_ID, CLIENT_SECRET, username, password);
        callRegister.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                JSONObject object = response.body();
                serviceCallBack.onSuccess(object);
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                serviceCallBack.onFailure("Fail");
            }
        });
    }

    public void registerUser(String username, String password, String email, String phone, String firstName, String lastName, String gentle, int age, final ServiceCallBack serviceCallBack) {
        JSONObject jsonObject = null;
        try {
            String jsonBody = getRequestBodyRegister(username, password, email, phone, firstName, lastName, gentle, age);
            jsonObject = new JSONObject(jsonBody);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        callRegister = api.registerAccount(jsonObject);
        callRegister.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                JSONObject object = response.body();
                serviceCallBack.onSuccess(object);
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                serviceCallBack.onFailure("Fail");
            }
        });
    }

    public interface ServiceCallBack {
        void onSuccess(Object object);

        void onFailure(Object t);
    }

    String getRequestBodyRegister(String username, String password, String email, String phone, String firstName, String lastName, String gentle, int age) {
        String requestString = "{\"uuid\":\"\",\"username\":\"%s\",\"password\":\"%s\",\"accountExpired\":false,\"accountLocked\":false,\"credentialsExpired\":false," +
                "\"enabled\":true,\"accountNonExpired\":false,\"accountNonLocked\":false,\"credentialsNonExpired\":false," +
                "\"userDetail\":{\"uuid\":\"\",\"email\":\"%s\",\"firstname\":\"%s\",\"lastname\":\"%s\",\"gentle\":\"%s\",\"age\":%d,\"phone\":\"%s\",\"avatarUri\":\"\"}," +
                "\"authorities\": [{\"uuid\":null,\"name\":\"COMPANY_CREATE\",\"authority\":\"COMPANY_CREATE\"},{\"uuid\":null,\"name\":\"COMPANY_READ\",\"authority\":\"COMPANY_READ\"}," +
                "{\"uuid\":null,\"name\":\"COMPANY_UPDATE\",\"authority\":\"COMPANY_UPDATE\"},{\"uuid\":null,\"name\":\"COMPANY_DELETE\",\"authority\":\"COMPANY_DELETE\"}]}";
        return String.format(requestString, username, password, email, firstName, lastName, gentle, age, phone);
    }

}
