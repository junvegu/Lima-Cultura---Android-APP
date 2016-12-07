package com.bixspace.livesculture.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bixspace.livesculture.data.PlaceModel;
import com.bixspace.livesculture.data.User;
import com.bixspace.livesculture.data.repository.local.SessionManager;
import com.bixspace.livesculture.R;
import com.bixspace.livesculture.base.BaseActivity;
import com.bixspace.livesculture.presentation.fragments.DetailEventFragment;
import com.bixspace.livesculture.presentation.fragments.DetailPlaceFragment;
import com.bixspace.livesculture.presentation.fragments.EventFragment;
import com.bixspace.livesculture.presentation.fragments.RegisterFragment;
import com.bixspace.livesculture.presentation.presenters.DetailPlacePresenter;
import com.bixspace.livesculture.presentation.presenters.PlacesPresenter;
import com.bixspace.livesculture.presentation.presenters.RegisterPresenter;
import com.bixspace.livesculture.presentation.utils.GlideUtils;
import com.bixspace.livesculture.util.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailPlaceActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        Bundle bundle = getIntent().getExtras();
        PlaceModel placeModel = (PlaceModel) bundle.getSerializable("place");
        DetailPlaceFragment fragment = (DetailPlaceFragment) getSupportFragmentManager()
                .findFragmentById(R.id.body);

        if (fragment == null) {
            fragment = DetailPlaceFragment.newInstance(placeModel);

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.body);
        }


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
