package aifree.com.authorizationtestapp.presentation.screens.commons.base_components;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import aifree.com.authorizationtestapp.applicication.AuthorizationTestApplication;
import aifree.com.authorizationtestapp.applicication.di.components.ActivityBaseComponent;
import aifree.com.authorizationtestapp.applicication.di.components.DaggerActivityBaseComponent;
import aifree.com.authorizationtestapp.applicication.di.modules.ActivityBaseModule;


public abstract class BaseActivity<VC extends ViewController> extends AppCompatActivity {

    protected VC mViewController;
    protected ActivityBaseComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityBaseComponent.builder()
                .applicationComponent(getAppInstanse().getAppComponent())
                .activityBaseModule(new ActivityBaseModule(this))
                .build();
    }

    @Nullable
    public ActivityBaseComponent getActivityComponent() {
        return mActivityComponent;
    }

    public AuthorizationTestApplication getAppInstanse() {
        return (AuthorizationTestApplication) getApplication();
    }

    @Nullable
    public VC getViewController() {
        return mViewController;
    }
}
