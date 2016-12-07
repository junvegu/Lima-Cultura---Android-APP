package com.bixspace.livesculture.presentation.presenters;


import android.content.Context;
import android.support.annotation.NonNull;

import com.bixspace.livesculture.data.EventModel;
import com.bixspace.livesculture.data.repository.local.SessionManager;
import com.bixspace.livesculture.data.repository.remote.ServiceFactory;
import com.bixspace.livesculture.data.repository.remote.request.EventRequest;
import com.bixspace.livesculture.presentation.contracts.EventContract;
import com.bixspace.livesculture.presentation.contracts.QuestonaryContract;
import com.bixspace.livesculture.presentation.fragments.MainFragment;
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
public class QuestionaryPresenter implements QuestonaryContract.Presenter {

    private final QuestonaryContract.View mView;
    private final Context mContext;
    private SessionManager sessionManager;
    private boolean mFirstLoad = true;

    public QuestionaryPresenter(@NonNull Context mContext,
                                @NonNull QuestonaryContract.View mView) {
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





    public void loadEvents(String token) {
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

                   // mView.showErroMessage("No se pudo eliminar el cupón, inténtelo nuevamente");

                }
            }

            @Override
            public void onFailure(Call<ArrayList<EventModel>> call, Throwable t) {
                if (!mView.isActive()) {
                    return;
                }

                mView.setLoadingIndicator(false);
              ///  mView.showErroMessage("No se ha podido conectar al servidor, inténtelo nuevamente");
            }

        });
    }


    @Override
    public void califyQuestionary(String id) {
        mView.setLoadingIndicator(true);
        final EventRequest couponRequest =
                ServiceFactory.createService(EventRequest.class);

        Call<Void> call = couponRequest.qualify("Token " +
                sessionManager.getUserToken(),1,id);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                mView.setLoadingIndicator(false);
                if (response.isSuccessful()) {


                    if (!mView.isActive()) {
                        return;
                    }

                    mView.showmessage();

                } else {

                    if (!mView.isActive()) {
                        return;
                    }

                     mView.showerrormessage("Usted ya ganó este cupón");

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                if (!mView.isActive()) {
                    return;
                }

                mView.setLoadingIndicator(false);
                mView.showerrormessage("No se ha podido conectar al servidor, inténtelo nuevamente");
            }

        });
    }
}
