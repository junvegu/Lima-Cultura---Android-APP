package com.bixspace.livesculture.data.repository.remote.request;

import com.bixspace.livesculture.data.User;
import com.bixspace.livesculture.data.repository.remote.ApiConstants;
import com.facebook.AccessToken;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by junior on 27/11/16.
 */

public interface LoginRequest {


    @FormUrlEncoded
    @POST(ApiConstants.USER_LOGIN)
    Call<com.bixspace.livesculture.data.AccessToken> basicLogin(@Field("email") String email, @Field("password") String password);

    @GET(ApiConstants.USER_RETRIEVE)
    Call<User> basicLogin(@Header("Authorization") String token);
}
