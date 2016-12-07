package com.bixspace.livesculture.presentation.contracts;

import com.bixspace.livesculture.base.BasePresenter;
import com.bixspace.livesculture.base.BaseView;
import com.bixspace.livesculture.data.EventModel;
import com.bixspace.livesculture.data.PlaceModel;

import java.util.ArrayList;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface MyPlacesContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        boolean isActive();

        void showEvents(ArrayList<PlaceModel> eventModels);

        void showDetailEvent(PlaceModel eventModel);

        void showErroMessage(String msg);

        void showMessage(String msg);
    }

    interface Presenter extends BasePresenter {


        void loadEvents();

        void openEventDetail(EventModel eventModel);
    }
}