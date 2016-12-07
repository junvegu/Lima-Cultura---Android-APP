package com.bixspace.livesculture.presentation.presenters;


import android.content.Context;
import android.support.annotation.NonNull;

import com.bixspace.livesculture.data.repository.local.SessionManager;
import com.bixspace.livesculture.presentation.contracts.CalendarContract;
import com.bixspace.livesculture.presentation.contracts.MainContract;
import com.bixspace.livesculture.presentation.fragments.MainFragment;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Listens to user actions from the UI ({@link MainFragment}),
 * retrieves the data and updates the UI as required.
 */
public class CalendarPresenter implements CalendarContract.Presenter {

    private final CalendarContract.View mView;
    private final Context mContext;
    private SessionManager sessionManager;


    public CalendarPresenter(@NonNull Context mContext,
                             @NonNull CalendarContract.View mView) {
        this.mContext=mContext;
        this.mView = checkNotNull(mView, "mView cannot be null!");
        this.mView.setPresenter(this);
        sessionManager= new SessionManager(mContext);
    }
    @Override
    public void start() {

    }




}
