package com.bixspace.livesculture.presentation.presenters;


import android.content.Context;
import android.support.annotation.NonNull;

import com.bixspace.livesculture.data.CuponModel;
import com.bixspace.livesculture.data.EventModel;
import com.bixspace.livesculture.data.repository.local.SessionManager;
import com.bixspace.livesculture.data.repository.remote.ServiceFactory;
import com.bixspace.livesculture.data.repository.remote.TrackCuponModel;
import com.bixspace.livesculture.data.repository.remote.request.EventRequest;
import com.bixspace.livesculture.presentation.contracts.CuponContract;
import com.bixspace.livesculture.presentation.contracts.EventContract;
import com.bixspace.livesculture.presentation.fragments.MainFragment;
import com.bixspace.livesculture.presentation.presenters.commons.CommunicateCuponPresenter;
import com.bixspace.livesculture.presentation.presenters.commons.CommunicateEventsPresenter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Listens to user actions from the UI ({@link MainFragment}),
 * retrieves the data and updates the UI as required.
 */
public class CuponPresenter implements CuponContract.Presenter, CommunicateCuponPresenter {

    private final CuponContract.View mView;
    private final Context mContext;
    private SessionManager sessionManager;
    private boolean mFirstLoad = true;

    public CuponPresenter(@NonNull Context mContext,
                          @NonNull CuponContract.View mView) {
        this.mContext = mContext;
        this.mView = checkNotNull(mView, "mView cannot be null!");
        this.mView.setPresenter(this);
        sessionManager = new SessionManager(mContext);
    }

    @Override
    public void start() {
        String token = sessionManager.getUserToken();
        if (mFirstLoad) {
            loadEvents(token);
            mFirstLoad = false;
        }
    }


    @Override
    public void loadEvents() {

        loadEvents(sessionManager.getUserToken());
    }


    public void loadEvents(String token) {
        mView.setLoadingIndicator(true);
        final EventRequest couponRequest =
                ServiceFactory.createService(EventRequest.class);

        Call<TrackCuponModel> call = couponRequest.getcupons("Token " +
                token);

        call.enqueue(new Callback<TrackCuponModel>() {
            @Override
            public void onResponse(Call<TrackCuponModel> call, Response<TrackCuponModel> response) {
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
            public void onFailure(Call<TrackCuponModel> call, Throwable t) {
                if (!mView.isActive()) {
                    return;
                }

                mView.setLoadingIndicator(false);
                mView.showErroMessage("No se ha podido conectar al servidor, inténtelo nuevamente");
            }

        });
    }

    public void Likeevent(String token) {
        mView.setLoadingIndicator(true);
        final EventRequest couponRequest =
                ServiceFactory.createService(EventRequest.class);

        Call<ArrayList<EventModel>> call = couponRequest.getEvents("Token " +
                token);

        call.enqueue(new Callback<ArrayList<EventModel>>() {
            @Override
            public void onResponse(Call<ArrayList<EventModel>> call, Response<ArrayList<EventModel>> response) {
                mView.setLoadingIndicator(false);
                if (response.isSuccessful()) {


                    if (!mView.isActive()) {
                        return;
                    }

                    //mView.showEvents(response.body());

                } else {

                    if (!mView.isActive()) {
                        return;
                    }

                    mView.showErroMessage("No se pudo eliminar el cupón, inténtelo nuevamente");

                }
            }

            @Override
            public void onFailure(Call<ArrayList<EventModel>> call, Throwable t) {
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

    @Override
    public void clickItem(CuponModel eventModel) {

    }
}
