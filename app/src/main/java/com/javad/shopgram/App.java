package com.javad.shopgram;

import android.app.Application;
import android.content.Context;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by AMIR on 8/16/2018.
 */

public class App extends Application {

    Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/IRANSans_FaNum.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }
}
