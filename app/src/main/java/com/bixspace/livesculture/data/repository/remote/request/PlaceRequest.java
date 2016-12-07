package com.bixspace.livesculture.data.repository.remote.request;

import com.bixspace.livesculture.data.EventModel;
import com.bixspace.livesculture.data.repository.remote.ApiConstants;
import com.bixspace.livesculture.data.repository.remote.TrackPlaceModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by junior on 27/11/16.
 */

public interface PlaceRequest {


    @GET(ApiConstants.PLACES)
    Call<TrackPlaceModel> getPlaces(@Header("Authorization") String token);

    @GET("me/places/")
    Call<TrackPlaceModel> getPlacesFavorit(@Header("Authorization") String token);


    @FormUrlEncoded
    @POST(ApiConstants.USER_REGISTER)
    Call<com.bixspace.livesculture.data.AccessToken> registerUser(@Field("email") String email,
                                                                  @Field("first_name") String first_name,
                                                                  @Field("last_name") String last_name,
                                                                  @Field("password") String password,
                                                                  @Field("gender") String gender);
}
