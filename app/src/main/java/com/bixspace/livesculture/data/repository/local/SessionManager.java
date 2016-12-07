package com.bixspace.livesculture.data.repository.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.bixspace.livesculture.data.User;
import com.facebook.AccessToken;
import com.google.gson.Gson;


/**
 * Session data in-memory
 */
public class SessionManager {




    public static final String PREFERENCE_NAME = "NamePreference";
    public static int PRIVATE_MODE = 0;

    /**
     USUARIO DATA SESSION - JSON
     */
    public static final String USER_TOKEN = "user_code";
    public static final String USER_JSON = "user_json";
    public static final String IS_LOGIN = "user_login";




    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        preferences = this.context.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public boolean isLogin()  {
        return preferences.getBoolean(IS_LOGIN, false);
    }


    public void openSession(com.bixspace.livesculture.data.AccessToken token) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(USER_TOKEN, token.getToken());
        editor.commit();
    }


    public void closeSession() {
        editor.putBoolean(IS_LOGIN, false);
        editor.putString(USER_TOKEN, null);
        editor.putString(USER_JSON, null);
        editor.commit();
    }


    public String getUserToken() {
        if (isLogin()) {
            return preferences.getString(USER_TOKEN, "");
        } else {
            return "";
        }
    }

    public void setUser(User userEntity) {
        if (userEntity != null) {
            Gson gson = new Gson();
            String user = gson.toJson(userEntity);
            editor.putString(USER_JSON, user);
        }
        editor.commit();
    }

    public User getUserEntity() {
        String userData = preferences.getString(USER_JSON, null);
        return new Gson().fromJson(userData, User.class);
    }

}
