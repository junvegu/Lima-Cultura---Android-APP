package com.bixspace.livesculture.data.repository.remote.request;

import com.bixspace.livesculture.data.EventModel;
import com.bixspace.livesculture.data.ResponseQRModel;
import com.bixspace.livesculture.data.repository.remote.ApiConstants;
import com.bixspace.livesculture.data.repository.remote.TrackCuponModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by junior on 27/11/16.
 */

public interface EventRequest {


    @GET(ApiConstants.EVENTS)
    Call<ArrayList<EventModel>> getEvents(@Header("Authorization") String token);


    @GET("cupons/{code}/")
    Call<ResponseQRModel> qrRead(@Header("Authorization") String token, @Path("code") String code);

    @FormUrlEncoded
    @POST("cupons/{id}/qualify/")
    Call<Void> qualify(@Header("Authorization") String token,
                       @Field("approved") int aprobed,@Path("id") String code);

    @FormUrlEncoded
    @POST("me/places/add/")
    Call<Void> like(@Header("Authorization") String token,@Field("id_place") String id);

    @FormUrlEncoded
    @POST("me/places/remove/")
    Call<Void> dislike(@Header("Authorization") String token,@Field("id_place") String id);


    @GET("me/cupons/")
    Call<TrackCuponModel> getcupons(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST(ApiConstants.USER_REGISTER)
    Call<com.bixspace.livesculture.data.AccessToken> registerUser(@Field("email") String email,
                                                                  @Field("first_name") String first_name,
                                                                  @Field("last_name") String last_name,
                                                                  @Field("password") String password,
                                                                  @Field("gender") String gender);
}
