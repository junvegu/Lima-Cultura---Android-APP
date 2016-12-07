package com.bixspace.livesculture.presentation.presenters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bixspace.livesculture.data.ResponseQRModel;
import com.bixspace.livesculture.data.repository.local.SessionManager;
import com.bixspace.livesculture.data.repository.remote.ServiceFactory;
import com.bixspace.livesculture.data.repository.remote.request.EventRequest;
import com.bixspace.livesculture.presentation.contracts.ValidateQRContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;


public class ValidateQRPresenter implements ValidateQRContract.Presenter {

    private final ValidateQRContract.View mView;
    private SessionManager authLocalData;

    @Nullable
    private String mTaskId;

    public ValidateQRPresenter(Context context, @NonNull ValidateQRContract.View mView) {
        authLocalData= new SessionManager(context);
        this.mView = checkNotNull(mView, "mView cannot be null!");
        this.mView.setPresenter(this);
    }
    @Override
    public void start() {

    }


    @Override
    public void validateCode(@NonNull String shop_code, @NonNull String codeQR) {
        if (this.mView.isActive()) {
            mView.setLoadingIndicator(true);
            EventRequest request =
                    ServiceFactory.createService(EventRequest.class);
            Call<ResponseQRModel> call = request.qrRead("Token "+shop_code,codeQR);
            call.enqueue(new Callback<ResponseQRModel>() {
                @Override
                public void onResponse(Call<ResponseQRModel> call, Response<ResponseQRModel> response) {
                    mView.setLoadingIndicator(false);
                    if (mView.isActive()) {
                        if (response.isSuccessful()) {


                                mView.validateQRSuccess(response.body());


                        } else {
                            mView.showMessageError("No existen el cupon en Lima Cultura");
                        }
                    }

                }

                @Override
                public void onFailure(Call<ResponseQRModel> call, Throwable t) {
                    mView.setLoadingIndicator(false);
                    mView.showMessageError("Sucedió un error, intente nuevamente");
                }
            });
        }
    }
}
