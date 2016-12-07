package com.bixspace.livesculture.presentation.presenters;


import android.content.Context;
import android.support.annotation.NonNull;

import com.bixspace.livesculture.data.User;
import com.bixspace.livesculture.data.repository.local.SessionManager;
import com.bixspace.livesculture.data.repository.remote.ServiceFactory;
import com.bixspace.livesculture.data.repository.remote.request.UserRequest;
import com.bixspace.livesculture.presentation.contracts.MainContract;
import com.bixspace.livesculture.presentation.contracts.RegisterContract;
import com.bixspace.livesculture.presentation.fragments.MainFragment;
import com.facebook.AccessToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Listens to user actions from the UI ({@link MainFragment}),
 * retrieves the data and updates the UI as required.
 */
public class RegisterPresenter implements RegisterContract.Presenter {

    private final RegisterContract.View mView;
    private final Context mContext;
    private SessionManager sessionManager;


    public RegisterPresenter(@NonNull Context mContext,
                             @NonNull RegisterContract.View mView) {
        this.mContext=mContext;
        this.mView = checkNotNull(mView, "mView cannot be null!");
        this.mView.setPresenter(this);
        sessionManager= new SessionManager(mContext);
    }
    @Override
    public void start() {

    }


    @Override
    public void registerUser(@NonNull String email, @NonNull String first_name, @NonNull String last_name, @NonNull String password, @NonNull String gender) {
        if (this.mView.isActive()) {
            mView.setLoadingIndicator(true);
            UserRequest request =
                    ServiceFactory.createService(UserRequest.class);
            Call<com.bixspace.livesculture.data.AccessToken> call = request.registerUser(
                    email,first_name,last_name,password,gender);
            call.enqueue(new Callback<com.bixspace.livesculture.data.AccessToken>() {
                @Override
                public void onResponse(Call<com.bixspace.livesculture.data.AccessToken> call, Response<com.bixspace.livesculture.data.AccessToken> response) {
                    mView.setLoadingIndicator(false);
                    if (mView.isActive()) {
                        if (response.isSuccessful()) {

                            getUser(response.body());
                        } else {
                            mView.showErroMessage("Ocurrió un error al tratar de traer su información, inténtelo nuevamente");
                        }
                    }

                }

                @Override
                public void onFailure(Call<com.bixspace.livesculture.data.AccessToken> call, Throwable t) {
                    mView.setLoadingIndicator(false);
                    mView.showErroMessage("Sucedió un error al tratar de ingresar, compruebe su conexión al internet");
                }
            });
        }
    }



    public void getUser(final com.bixspace.livesculture.data.AccessToken accessToken) {
        if (this.mView.isActive()) {
            mView.setLoadingIndicator(true);
            UserRequest request =
                    ServiceFactory.createService(UserRequest.class);
            Call<User> call = request.getUser("Token "+accessToken.getToken());
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {

                    if (mView.isActive()) {
                        if (response.isSuccessful()) {
                            sessionManager.openSession(accessToken);
                            sessionManager.setUser(response.body());
                            mView.registerSuccess();
                        } else {
                            mView.setLoadingIndicator(false);
                            mView.showErroMessage("Ocurrió un error al tratar de traer su información," +
                                    "inténtelo nuevamente");
                        }
                    }

                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    mView.setLoadingIndicator(false);
                    mView.showErroMessage("Sucedió un error al tratar de ingresar, " +
                            "compruebe su conexión al internet");
                }
            });
        }
    }
}
