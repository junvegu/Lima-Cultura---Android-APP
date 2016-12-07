package com.bixspace.livesculture.data.repository.local;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by junior on 13/10/16.
 */
public class PreferencesManager {
    private static final String PREF_NAME = "com.example.app.PREF_NAME";


    /**
     USUARIO DATA SESSION - JSON
     */
    public static final String USER_TOKEN = "user_code";
    public static final String USER_JSON = "user_json";
    public static final String IS_LOGIN = "user_login";
    private static PreferencesManager sInstance;
    private final SharedPreferences mPref;

    private PreferencesManager(Context context) {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized void initializeInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PreferencesManager(context);
        }
    }

    public static synchronized PreferencesManager getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException(PreferencesManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return sInstance;
    }

    public boolean isLogin()  {
        return mPref.getBoolean(IS_LOGIN, false);
    }


    public void openSession(String token) {
        mPref.edit().putBoolean(IS_LOGIN, true);
        mPref.edit().putString(USER_TOKEN, token);
        mPref.edit().commit();
    }


    public void closeSession() {
        mPref.edit().putBoolean(IS_LOGIN, false);
        mPref.edit().putString(USER_TOKEN, null);
        mPref.edit().commit();
    }


    public String getUserToken() {
        if (isLogin()) {
            return mPref.getString(USER_TOKEN, "");
        } else {
            return "";
        }
    }
}
