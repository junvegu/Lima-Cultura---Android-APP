package com.bixspace.livesculture.presentation.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bixspace.livesculture.R;
import com.bixspace.livesculture.base.BaseActivity;
import com.bixspace.livesculture.base.BaseFragment;
import com.bixspace.livesculture.presentation.activities.MainActivity;
import com.bixspace.livesculture.presentation.activities.PanelActivity;
import com.bixspace.livesculture.presentation.activities.RegisterActivity;
import com.bixspace.livesculture.presentation.contracts.LoginContract;
import com.bixspace.livesculture.presentation.utils.ProgressDialogCustom;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by junior on 27/08/16.
 */
public class LoginFragment extends BaseFragment implements LoginContract.View, Validator.ValidationListener {
    @NotEmpty(message = "El campo es obligatorio")
    @Email(message = "Introducir un mail válido")
    @BindView(R.id.et_email)
    EditText etEmail;
    @NotEmpty(message = "El campo es obligatorio")
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_login_facebook)
    Button btnLoginFacebook;
    @BindView(R.id.tv_create_account)
    TextView tvCreateAccount;
    private LoginContract.Presenter mPresenter;
    private Unbinder unbinder;
    private ProgressDialogCustom mProgressDialogCustom;
    private Validator validator;

    public LoginFragment() {
        // Requires empty public constructor
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull LoginContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_layout_login, container, false);

        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProgressDialogCustom = new ProgressDialogCustom(getContext(), "Ingresando...");
        validator = new Validator(this);
        validator.setValidationListener(this);


    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }
        if (active) {
            mProgressDialogCustom.show();
        } else {
            if (mProgressDialogCustom.isShowing()) {
                mProgressDialogCustom.dismiss();
            }
        }
    }

    @Override
    public void loginSuccess() {
        nextActivity(getActivity(), null, MainActivity.class, true);
    }

    @Override
    public void showErroMessage(String msg) {
       showRedMessage(getActivity(),msg);
    }

    @Override
    public void showMessage(String msg) {
        ((BaseActivity) getActivity()).showMessage(getView(), msg);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @OnClick({R.id.btn_login, R.id.btn_login_facebook, R.id.tv_create_account})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                validator.validate();
                break;
            case R.id.btn_login_facebook:
                break;
            case R.id.tv_create_account:
                nextActivity(getActivity(), null, RegisterActivity.class, false);
                break;
        }
    }


    @Override
    public void onValidationSucceeded() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        // String gender = etLoginPassword.getText().toString();

        mPresenter.login(email, password);

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getContext());
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                ((BaseActivity) getActivity()).showMessageError(message);
            }
        }
    }
}
