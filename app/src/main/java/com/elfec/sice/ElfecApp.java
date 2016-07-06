package com.elfec.sice;

import android.app.Application;

import com.elfec.sice.security.AppPreferences;

import net.danlew.android.joda.JodaTimeAndroid;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by drodriguez on 13/06/2016.
 * App for elfec
 */
public class ElfecApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/montserrat_light.otf").setFontAttrId(R.attr.fontPath)
                .build());
        JodaTimeAndroid.init(this);
        AppPreferences.init(this);
    }
}
