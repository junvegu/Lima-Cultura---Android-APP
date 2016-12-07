package com.bixspace.livesculture.presentation.contracts;

import android.support.annotation.NonNull;

import com.bixspace.livesculture.base.BasePresenter;
import com.bixspace.livesculture.base.BaseView;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface LoginContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void loginSuccess();

        void showErroMessage(String msg);

        void showMessage(String msg);

        boolean isActive();
    }

    interface Presenter extends BasePresenter {


        void login(@NonNull String email, @NonNull String password);

    }
}