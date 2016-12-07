package com.bixspace.livesculture;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class CultureLivesApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //AppEventsLogger.activateApp(this);
        //printKeyHash();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/proximanovaregular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );


    }


    public void printKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.kodevian.glup",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("Hash Key", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

    }

}
