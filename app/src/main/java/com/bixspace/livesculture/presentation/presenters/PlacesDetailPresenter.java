package com.bixspace.livesculture.presentation.presenters;


import android.content.Context;
import android.support.annotation.NonNull;

import com.bixspace.livesculture.data.EventModel;
import com.bixspace.livesculture.data.PlaceModel;
import com.bixspace.livesculture.data.repository.local.SessionManager;
import com.bixspace.livesculture.data.repository.remote.ServiceFactory;
import com.bixspace.livesculture.data.repository.remote.TrackPlaceModel;
import com.bixspace.livesculture.data.repository.remote.request.PlaceRequest;
import com.bixspace.livesculture.presentation.contracts.PlacesContract;
import com.bixspace.livesculture.presentation.fragments.MainFragment;
import com.bixspace.livesculture.presentation.presenters.commons.CommunicateContactsPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Listens to user actions from the UI ({@link MainFragment}),
 * retrieves the data and updates the UI as required.
 */
public class PlacesDetailPresenter implements PlacesContract.Presenter, CommunicateContactsPresenter {

    private final PlacesContract.View mView;
    private final Context mContext;
    private SessionManager sessionManager;
    private boolean mFirstLoad = true;

    public PlacesDetailPresenter(@NonNull Context mContext,
                                 @NonNull PlacesContract.View mView) {
        this.mContext = mContext;
        this.mView = checkNotNull(mView, "mView cannot be null!");
        this.mView.setPresenter(this);
        sessionManager = new SessionManager(mContext);
    }

    @Override
    public void start() {
        String token = sessionManager.getUserToken();
        if (mFirstLoad) {
            loadPlace(token);
            mFirstLoad = false;
        }
    }


    @Override
    public void clickItem(PlaceModel eventModel) {
        mView.showDetailEvent(eventModel);
    }

    @Override
    public void loadEvents() {
        loadPlace(sessionManager.getUserToken());

    }


    public void loadPlace(String token) {
        mView.setLoadingIndicator(true);
        final PlaceRequest couponRequest =
                ServiceFactory.createService(PlaceRequest.class);

        Call<TrackPlaceModel> call = couponRequest.getPlaces("Token " +
                token);

        call.enqueue(new Callback<TrackPlaceModel>() {
            @Override
            public void onResponse(Call<TrackPlaceModel> call, Response<TrackPlaceModel> response) {
                mView.setLoadingIndicator(false);
                if (response.isSuccessful()) {


                    if (!mView.isActive()) {
                        return;
                    }

                    mView.showEvents(response.body().getResults());

                } else {

                    if (!mView.isActive()) {
                        return;
                    }

                    mView.showErroMessage("No se pudo eliminar el cupón, inténtelo nuevamente");

                }
            }

            @Override
            public void onFailure(Call<TrackPlaceModel> call, Throwable t) {
                if (!mView.isActive()) {
                    return;
                }

                mView.setLoadingIndicator(false);
                mView.showErroMessage("No se ha podido conectar al servidor, inténtelo nuevamente");
            }

        });
    }


    @Override
    public void openEventDetail(EventModel eventModel) {

    }
}
