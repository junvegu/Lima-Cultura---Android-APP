package com.bixspace.livesculture.presentation.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bixspace.livesculture.data.User;
import com.bixspace.livesculture.data.repository.local.SessionManager;
import com.bixspace.livesculture.R;
import com.bixspace.livesculture.base.BaseActivity;
import com.bixspace.livesculture.presentation.fragments.EventFragment;
import com.bixspace.livesculture.presentation.utils.GlideUtils;
import com.bixspace.livesculture.util.ActivityUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.AccessToken;
import com.facebook.Profile;

public class MainActivity extends BaseActivity {

    DrawerLayout mDrawer;
    NavigationView navigationView;
    SessionManager mSessionManager;
    private Toolbar toolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public TextView tv_username;
    public ImageView profile_image;
    public TextView tv_state_gender;
    public User mUser;
    public Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         *Setup the DrawerLayout and NavigationView
         */
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.layout_content_frame);
        mDrawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigation);
        mSessionManager = new SessionManager(getApplicationContext());
        /**
         * Lets inflate the very first fragment
         */


        EventFragment guardifyFragment =
                (EventFragment) getSupportFragmentManager().findFragmentById(R.id.layout_content_frame);

        if (guardifyFragment == null) {
            guardifyFragment = EventFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), guardifyFragment, R.id.layout_content_frame);
        }


        /**
         * Setup click events on the Navigation View Items.
         */

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("");
        setupDrawerContent(navigationView);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                    /* host Activity */
                mDrawer,                    /* DrawerLayout object */
                toolbar,
                R.string.app_name,  /* "open drawer" description for accessibility */
                R.string.app_name  /* "close drawer" description for accessibility */
        );
        mDrawerToggle.syncState();
        mDrawer.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();
        View header = navigationView.getHeaderView(0);
        tv_username = (TextView) header.findViewById(R.id.tv_fullnanme);
        tv_state_gender = (TextView) header.findViewById(R.id.tv_state_gender);
        profile_image = (ImageView) header.findViewById(R.id.imageView);
        //  startService(new Intent(this, GeolocationService.class));
        initHeader();
    }


    public void initHeader() {

        mUser = mSessionManager.getUserEntity();
        if (mUser != null) {

            tv_username.setText(mUser.getFirst_name() + " " + mUser.getLast_name());


            /*if(mUser.getPicture()!=null){
                Glide.with(this).load(mUser.getPicture())
                        .bitmapTransform(new CircleTransform(this)).into(profile_image);
            }*/

            if (mUser.getGender() != null) {

                if (mUser.getGender().equals("m")) {
                    tv_state_gender.setText("Hombre");

                    GlideUtils.loadImageCircleTransform(profile_image, "http://mexicovivo.org/new/wp-content/uploads/2015/10/profile_picture_old_man_avatar-512.png", this);


                } else{
                    tv_state_gender.setText("Mujer");
                    GlideUtils.loadImageCircleTransform(profile_image, "http://serwer10463.lh.pl/wp-content/uploads/2015/11/Female-Side-comb-O-neck-512.png", this);

                }

            } else {
                tv_state_gender.setText("");

            }
        }


    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {


                        menuItem.setChecked(false);
                        menuItem.setCheckable(false);


                        switch (menuItem.getItemId()) {
                            case R.id.nav_connect:
                               /* Intent intent_connect = new Intent(getBaseContext(), ConnectActivity.class);
                                startActivity(intent_connect);*/
                                Intent intentOpenBluetoothSettings = new Intent();
                                intentOpenBluetoothSettings.setAction(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS);
                                startActivity(intentOpenBluetoothSettings);
                                break;
                            case R.id.nav_profile:
                                Intent intent3 = new Intent(getBaseContext(), CuponActivity.class);
                                startActivity(intent3);

                                // Intent intent_profile = new Intent(getBaseContext(), PerfilActivity.class);
                                //startActivityForResult(intent_profile, PerfilActivity.UPDATE_USER);
                                break;
                            case R.id.nav_contact:
                                Intent intent1 = new Intent(getBaseContext(), MyPlacesActivity.class);
                                startActivity(intent1);

                                break;
                            case R.id.places:
                                Intent intent2 = new Intent(getBaseContext(), PlacesActivity.class);
                                startActivity(intent2);
                                break;
                            case R.id.nav_signout:
                              /*  SharedPreferences settings = getApplicationContext().getSharedPreferences("Uluck", Context.MODE_PRIVATE);
                                settings.edit().clear().commit();*/
                                mSessionManager.closeSession();
                                // AccessToken.setCurrentAccessToken(null);
                                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);

                                // stopService(new Intent(PanelActivity.this, SocketService.class));

                                finish();
                                break;

                            default:

                                break;
                        }
                        // Close the navigation drawer when an item is selected.
                        menuItem.setChecked(false);
                        mDrawer.closeDrawers();
                        return true;
                    }

                });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        if (this.mDrawer.isDrawerOpen(GravityCompat.START)) {
            this.mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}
