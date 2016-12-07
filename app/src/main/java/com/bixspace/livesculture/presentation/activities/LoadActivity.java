package com.bixspace.livesculture.presentation.activities;

import android.os.Bundle;

import com.bixspace.livesculture.R;
import com.bixspace.livesculture.base.BaseActivity;
import com.bixspace.livesculture.data.repository.local.SessionManager;

public class LoadActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Splash);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear);
        if (savedInstanceState == null)
            initialProcess();
    }


    private void initialProcess() {
        SessionManager mSessionManager = new SessionManager(getApplicationContext());
        if(mSessionManager.isLogin()){
            //next(this,null, CatalogActivity.class, true);
            nextActivity(this,null, MainActivity.class, true);
        }else{
            nextActivity(this,null, LoginActivity.class, true);
        }
    }
}
