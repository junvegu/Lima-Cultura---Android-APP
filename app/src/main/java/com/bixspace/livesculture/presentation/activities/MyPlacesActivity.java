package com.bixspace.livesculture.presentation.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.bixspace.livesculture.R;
import com.bixspace.livesculture.base.BaseActivity;
import com.bixspace.livesculture.presentation.fragments.MyPlacesFragment;
import com.bixspace.livesculture.presentation.fragments.PlacesFragment;
import com.bixspace.livesculture.presentation.presenters.MyPlacesPresenter;
import com.bixspace.livesculture.presentation.presenters.PlacesPresenter;
import com.bixspace.livesculture.util.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyPlacesActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);
        ButterKnife.bind(this);
        toolbar.setTitle("Lugares Favoritos");

        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        MyPlacesFragment fragment = (MyPlacesFragment) getSupportFragmentManager()
                .findFragmentById(R.id.body);

        if (fragment == null) {
            fragment = MyPlacesFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.body);
        }

        // Create the presenter
        new MyPlacesPresenter(this,
                fragment);
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
