package com.jigar.android.retrofitcall.Interfaces;

import com.jigar.android.retrofitcall.LoginActivity;
import com.jigar.android.retrofitcall.LoginGetMethod;
import com.jigar.android.retrofitcall.Response.GetCandidateResponse;
import com.jigar.android.retrofitcall.Response.LoginGetResponse;
import com.jigar.android.retrofitcall.Response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by COMP11 on 05-Dec-18.
 */

public interface Api {

    @FormUrlEncoded
    @POST("login.php")//Post methoud webservices
    Call<LoginResponse> userLogin(@Field("user_nm") String user_nm,
                                  @Field("pass") String pass,
                                  @Field("login_type") String login_type);

    @GET("GetbyEmailPassword/{user_nm}/{pass}/{token_id}/{mobile_id}")
    Call<LoginGetResponse> useLogin_Get(@Path("user_nm") String user_nm,
                                        @Path("pass") String pass,
                                        @Path("token_id") String token_id,
                                        @Path("mobile_id") String mobile_id
    );
    @GET("GetCandidate/{candidate_id}")//GetMethod webservices
    Call<GetCandidateResponse> Candidte_paramer(@Path("candidate_id") String candidate_id

    );


//    @FormUrlEncoded
//    @POST("login.php")
//    Call<LoginActivity> userLogin(@Field("user_id") String candidate_id,
//                                  @Field("pass") String pass,
//                                  @Field("type") String type);
//
//    @GET("GetbyEmailPassword/{user_nm}/{pass},{token_id},{mobile_key}")
//    Call<LoginGetResponse> useLogin_Get(@Path("user_nm") String user_nm,
//                                            @Path("pass")String pass,
//                                            @Path("token_id")String token_id,
//                                            @Path("mobile_key") String mobile_key);
//
//    @GET("GetCandidate")
//    Call<GetCandidateResponse> Candidte_paramer(@Path("Candidat_id") String candidate_id);






}
