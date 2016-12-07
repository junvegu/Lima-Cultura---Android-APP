package com.bixspace.livesculture.presentation.presenters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bixspace.livesculture.data.repository.MainModelRepository;
import com.bixspace.livesculture.data.repository.local.SessionManager;
import com.bixspace.livesculture.presentation.contracts.MainContract;
import com.bixspace.livesculture.presentation.fragments.MainFragment;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Listens to user actions from the UI ({@link MainFragment}),
 * retrieves the data and updates the UI as required.
 */
public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View mView;
    private final Context mContext;
    private SessionManager sessionManager;


    public MainPresenter(@NonNull Context mContext,
                               @NonNull MainContract.View mView) {
        this.mContext=mContext;
        this.mView = checkNotNull(mView, "mView cannot be null!");
        this.mView.setPresenter(this);
        sessionManager= new SessionManager(mContext);
    }
    @Override
    public void start() {

    }




}
