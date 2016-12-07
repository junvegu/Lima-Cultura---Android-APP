package com.bixspace.livesculture.presentation.presenters;


import android.content.Context;
import android.support.annotation.NonNull;

import com.bixspace.livesculture.data.repository.local.SessionManager;
import com.bixspace.livesculture.presentation.contracts.DetailPlaceContract;
import com.bixspace.livesculture.presentation.contracts.MainContract;
import com.bixspace.livesculture.presentation.fragments.MainFragment;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Listens to user actions from the UI ({@link MainFragment}),
 * retrieves the data and updates the UI as required.
 */
public class DetailPlacePresenter implements DetailPlaceContract.Presenter {

    private final DetailPlaceContract.View mView;
    private final Context mContext;
    private SessionManager sessionManager;


    public DetailPlacePresenter(@NonNull Context mContext,
                                @NonNull DetailPlaceContract.View mView) {
        this.mContext=mContext;
        this.mView = checkNotNull(mView, "mView cannot be null!");
        this.mView.setPresenter(this);
        sessionManager= new SessionManager(mContext);
    }
    @Override
    public void start() {


    }




}
