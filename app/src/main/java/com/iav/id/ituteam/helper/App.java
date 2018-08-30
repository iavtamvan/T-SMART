package com.iav.id.ituteam.helper;

import android.app.Application;

import com.evernote.android.state.StateSaver;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        StateSaver.setEnabledForAllActivitiesAndSupportFragments(this, true);
    }

}
