package aifree.com.authorizationtestapp.applicication.di.modules;

import android.content.Context;

import aifree.com.authorizationtestapp.applicication.AuthorizationTestApplication;
import aifree.com.authorizationtestapp.domain.executors.AuthorizationInteractorImpl;
import aifree.com.authorizationtestapp.domain.interactors.AuthorizationInteractor;
import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {

    private AuthorizationTestApplication mApplication;

    public ApplicationModule(AuthorizationTestApplication application) {
        mApplication = application;
    }

    @Provides
    public Context provideContext() {
        return mApplication;
    }

    @Provides
    public AuthorizationInteractor provideAuthorizationInteractor(AuthorizationInteractorImpl authorizationInteractor) {
        return authorizationInteractor;
    }
}
