package com.bixspace.livesculture.presentation.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bixspace.livesculture.R;
import com.bumptech.glide.Glide;

/**
 * Created by junior on 06/12/16.
 */

public class ActivityTestGlide extends AppCompatActivity {

    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.activity_test);
        imageView = (ImageView) findViewById(R.id.image);


        Glide.with(this).load("http://www.infoanimales.com/wp-content/uploads/2014/05/leon-3-550x413.jpeg").into(imageView);

    }
}
