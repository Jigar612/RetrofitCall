package com.jigar.android.retrofitcall.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by COMP11 on 05-Dec-18.
 */

public class LoginResponse {

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @SerializedName("message")
    private String message;

    public LoginResponse(String message) {
        this.message = message;
    }
}
