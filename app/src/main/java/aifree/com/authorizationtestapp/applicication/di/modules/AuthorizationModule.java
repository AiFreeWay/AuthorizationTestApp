package aifree.com.authorizationtestapp.applicication.di.modules;

import aifree.com.authorizationtestapp.domain.executors.AuthorizationInteractorImpl;
import aifree.com.authorizationtestapp.domain.interactors.AuthorizationInteractor;
import aifree.com.authorizationtestapp.presentation.factories.AuthorizationFragmentsFactory;
import aifree.com.authorizationtestapp.presentation.screens.authorization.activities.AuthorizationActivity;
import aifree.com.authorizationtestapp.presentation.utils.FragmentRouter;
import aifree.com.authorizationtestapp.presentation.utils.IntervalKeeper;
import dagger.Module;
import dagger.Provides;


@Module
public class AuthorizationModule {

    private AuthorizationFragmentsFactory mAuthorizationFragmentsFactory;
    private FragmentRouter mFragmentRouter;
    private IntervalKeeper mIntervalKeeper;

    public AuthorizationModule(AuthorizationActivity authorizationActivity) {
        mAuthorizationFragmentsFactory = new AuthorizationFragmentsFactory();
        mFragmentRouter = new FragmentRouter(authorizationActivity.getSupportFragmentManager());
        mIntervalKeeper = new IntervalKeeper();
    }

    @Provides
    public AuthorizationInteractor provideCityInteractor(AuthorizationInteractorImpl authorizationInteractor) {
        return authorizationInteractor;
    }

    @Provides
    public AuthorizationFragmentsFactory provideAuthorizationFragmentsFactory() {
        return mAuthorizationFragmentsFactory;
    }

    @Provides
    public FragmentRouter provideFragmentsRouter() {
        return mFragmentRouter;
    }

    @Provides
    public IntervalKeeper provideIntervalKeeper() {
        return mIntervalKeeper;
    }
}
