package com.example.chtlei.studydemo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.example.chtlei.studydemo.utils.NetUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by chtlei on 19-3-25.
 */

public class StudyDemoApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> mAndroidInjector;

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mAndroidInjector;
    }

    private static StudyDemoApplication mApplication;
    private static Context context;
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        context = getApplicationContext();

        init();
    }

    private void init() {
        NetUtils.init(this);

        refWatcher = setupLeakCanary();
    }

    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        StudyDemoApplication application = (StudyDemoApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    public static Context getContext() {
        return context;
    }
}
