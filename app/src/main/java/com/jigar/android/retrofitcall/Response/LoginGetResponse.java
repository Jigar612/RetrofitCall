package com.jigar.android.retrofitcall.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by COMP11 on 05-Dec-18.
 */

public class LoginGetResponse {

    public LoginGetResponse(String getbyEmailPasswordResult) {
        GetbyEmailPasswordResult = getbyEmailPasswordResult;
    }

    public String getGetbyEmailPasswordResult() {
        return GetbyEmailPasswordResult;
    }

    public void setGetbyEmailPasswordResult(String getbyEmailPasswordResult) {
        GetbyEmailPasswordResult = getbyEmailPasswordResult;
    }

    @SerializedName("GetbyEmailPasswordResult")
    private String GetbyEmailPasswordResult;

}
