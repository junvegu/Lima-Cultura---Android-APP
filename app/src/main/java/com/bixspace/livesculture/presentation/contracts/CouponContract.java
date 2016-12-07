package com.bixspace.livesculture.presentation.contracts;

import com.bixspace.livesculture.base.BasePresenter;
import com.bixspace.livesculture.base.BaseView;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface CouponContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        boolean isActive();
    }

    interface Presenter extends BasePresenter {


    }
}