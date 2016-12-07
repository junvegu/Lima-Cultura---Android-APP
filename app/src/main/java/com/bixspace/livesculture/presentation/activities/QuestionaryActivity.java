package com.bixspace.livesculture.presentation.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.bixspace.livesculture.R;
import com.bixspace.livesculture.base.BaseActivity;
import com.bixspace.livesculture.data.ResponseQRModel;
import com.bixspace.livesculture.presentation.fragments.PlacesFragment;
import com.bixspace.livesculture.presentation.fragments.QuestionaryFragment;
import com.bixspace.livesculture.presentation.presenters.PlacesPresenter;
import com.bixspace.livesculture.presentation.presenters.QuestionaryPresenter;
import com.bixspace.livesculture.util.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionaryActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);
        ButterKnife.bind(this);
        toolbar.setTitle("Trivia");

        Bundle bundle = getIntent().getExtras();
        ResponseQRModel responseQRModel = (ResponseQRModel) bundle.getSerializable("qr");
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        QuestionaryFragment fragment = (QuestionaryFragment) getSupportFragmentManager()
                .findFragmentById(R.id.body);

        if (fragment == null) {
            fragment = QuestionaryFragment.newInstance(responseQRModel);

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.body);
        }

        // Create the presenter
        new QuestionaryPresenter(this,
                fragment);
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
