package com.bixspace.livesculture.presentation.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.bixspace.livesculture.R;
import com.bixspace.livesculture.base.BaseActivity;
import com.bixspace.livesculture.presentation.fragments.LoginFragment;
import com.bixspace.livesculture.presentation.fragments.MainFragment;
import com.bixspace.livesculture.presentation.presenters.LoginPresenter;
import com.bixspace.livesculture.presentation.presenters.MainPresenter;
import com.bixspace.livesculture.util.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear);

        LoginFragment fragment = (LoginFragment) getSupportFragmentManager()
                .findFragmentById(R.id.body);

        if (fragment == null) {
            fragment = LoginFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.body);
        }

        // Create the presenter
        new LoginPresenter(this,
                fragment);
    }
}
