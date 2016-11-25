package aifree.com.authorizationtestapp.applicication;


import android.app.Application;

import aifree.com.authorizationtestapp.applicication.di.components.ApplicationComponent;
import aifree.com.authorizationtestapp.applicication.di.components.DaggerApplicationComponent;
import aifree.com.authorizationtestapp.applicication.di.modules.ApplicationModule;


public class AuthorizationTestApplication extends Application {

    private ApplicationComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getAppComponent() {
        return mAppComponent;
    }
}
