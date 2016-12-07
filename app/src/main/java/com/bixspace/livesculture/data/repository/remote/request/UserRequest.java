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

public interface UserRequest {


    @GET(ApiConstants.USER_RETRIEVE)
    Call<User> getUser(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST(ApiConstants.USER_REGISTER)
    Call<com.bixspace.livesculture.data.AccessToken> registerUser(@Field("email") String email,
                            @Field("first_name") String first_name,
                            @Field("last_name") String last_name,
                            @Field("password") String password,
                            @Field("gender") String gender);
}
