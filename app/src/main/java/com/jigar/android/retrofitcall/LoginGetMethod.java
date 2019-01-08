package com.jigar.android.retrofitcall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jigar.android.retrofitcall.Interfaces.Api;
import com.jigar.android.retrofitcall.Response.GetCandidateResponse;
import com.jigar.android.retrofitcall.Response.LoginGetResponse;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by COMP11 on 05-Dec-18.
 */

public class LoginGetMethod extends AppCompatActivity {
    EditText ed_user_nm;
    EditText ed_pass;
    Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed_user_nm = findViewById(R.id.ed_user_nm);
        ed_pass=findViewById(R.id.ed_pass);
        btn_login=findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str_user_nm = ed_user_nm.getText().toString().trim();
                String str_user_pass = ed_pass.getText().toString().trim();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://35.162.89.140:82/GotHireServices.svc/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Api api = retrofit.create(Api.class);

                Call<LoginGetResponse> call = api.useLogin_Get(str_user_nm,str_user_pass,"123456","1");

                call.enqueue(new Callback<LoginGetResponse>() {
                    @Override
                    public void onResponse(Call<LoginGetResponse> call, Response<LoginGetResponse> response) {
                        String res = response.body().getGetbyEmailPasswordResult();
                        if(res.isEmpty()){
                            Toast.makeText(LoginGetMethod.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Intent intent = new Intent(LoginGetMethod.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("Candidante_id",res);
                            startActivity(intent);


                        }
                    }

                    @Override
                    public void onFailure(Call<LoginGetResponse> call, Throwable t) {

                    }
                });
            }
        });


    }
}
