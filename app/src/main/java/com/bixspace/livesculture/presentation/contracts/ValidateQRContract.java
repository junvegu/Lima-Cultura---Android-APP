package com.bixspace.livesculture.presentation.contracts;

import android.support.annotation.NonNull;

import com.bixspace.livesculture.base.BasePresenter;
import com.bixspace.livesculture.base.BaseView;
import com.bixspace.livesculture.data.ResponseQRModel;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface ValidateQRContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);


        void showMessageError(String msg);

        void validateQRSuccess(ResponseQRModel couponDetail);


        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void validateCode(@NonNull String shop_code, @NonNull String codeQR);
    }
}