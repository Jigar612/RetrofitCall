package com.jigar.android.retrofitcall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.jigar.android.retrofitcall.Interfaces.Api;
import com.jigar.android.retrofitcall.Response.LoginResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    EditText ed_user_nm;
    EditText ed_pass;
    Spinner login_with;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed_user_nm = findViewById(R.id.ed_user_nm);
        ed_pass=findViewById(R.id.ed_pass);
        login_with = findViewById(R.id.spinner_login_with);
        btn_login=findViewById(R.id.btn_login);

        List<String> list_type = new ArrayList<>();
        list_type.add("admin");
        list_type.add("waiter");

        ArrayAdapter adapter_spinner = new ArrayAdapter(LoginActivity.this,R.layout.support_simple_spinner_dropdown_item,list_type);
        login_with.setAdapter(adapter_spinner);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_user_nm = ed_user_nm.getText().toString().trim();
                String str_user_pass = ed_pass.getText().toString().trim();
                String user_type = login_with.getSelectedItem().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.185.129.71/~webservices1/resto/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Api api= retrofit.create(Api.class);
                Call<LoginResponse> call = api.userLogin(str_user_nm,str_user_pass,user_type);

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        String res = response.body().getMessage();
                        if (res.equals("admin"))
                        {
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                    }
                });
            }
        });
    }
}
