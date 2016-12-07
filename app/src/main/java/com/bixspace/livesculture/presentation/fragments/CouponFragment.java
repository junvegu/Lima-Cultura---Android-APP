package com.bixspace.livesculture.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.bixspace.livesculture.R;
import com.bixspace.livesculture.base.BaseActivity;
import com.bixspace.livesculture.base.BaseFragment;
import com.bixspace.livesculture.presentation.activities.MainActivity;
import com.bixspace.livesculture.presentation.contracts.RegisterContract;
import com.bixspace.livesculture.presentation.utils.ProgressDialogCustom;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by junior on 27/08/16.
 */
public class CouponFragment extends BaseFragment implements RegisterContract.View
        , Validator.ValidationListener {

    @NotEmpty(message = "El campo es obligatorio")
    @Email(message = "Introducir un mail válido")
    @BindView(R.id.et_email)
    EditText etEmail;
    @NotEmpty(message = "El campo es obligatorio")
    @BindView(R.id.et_first_name)
    EditText etFirstName;
    @NotEmpty(message = "El campo es obligatorio")
    @BindView(R.id.et_last_name)
    EditText etLastName;
    @NotEmpty(message = "El campo es obligatorio")
    @Password(min = 6, message = "La contraseña debe contener al menos 6 dígitos")
    @BindView(R.id.et_password)
    EditText etPassword;
    @NotEmpty(message = "El campo es obligatorio")
    @ConfirmPassword(message = "Las contraseñas no coinciden")
    @BindView(R.id.et_repeat_password)
    EditText etRepeatPassword;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.rbGroup)
    RadioGroup rbGroup;
    private RegisterContract.Presenter mPresenter;
    private Unbinder unbinder;
    private ProgressDialogCustom mProgressDialogCustom;
    private Validator validator;
    private String gender;

    public CouponFragment() {
        // Requires empty public constructor
    }

    public static CouponFragment newInstance() {
        return new CouponFragment();
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
    public void setPresenter(@NonNull RegisterContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_layout_register, container, false);
        unbinder = ButterKnife.bind(this, root);
        setHasOptionsMenu(true);
        return root;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProgressDialogCustom = new ProgressDialogCustom(getContext(), "Registrando...");
        validator = new Validator(this);
        validator.setValidationListener(this);
        rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_male) {
                    gender = "m";
                } else {
                    gender = "f";
                }

            }
        });
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
    public void registerSuccess() {
        newActivityClearPreview(getActivity(), null, MainActivity.class);
    }

    @Override
    public void showErroMessage(String msg) {
        showRedMessage(getActivity(),msg);

    }

    @Override
    public void showMessage(String msg) {
        ((BaseActivity)getActivity()).showMessage(getView(),msg);
    }


    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_register)
    public void onClick() {
        validator.validate();

    }

    @Override
    public void onValidationSucceeded() {
        String email = etEmail.getText().toString();
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String password = etPassword.getText().toString();
        // String gender = etLoginPassword.getText().toString();

        mPresenter.registerUser(email, firstName, lastName, password,gender);

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
