package aifree.com.authorizationtestapp.applicication.di.modules;

import aifree.com.authorizationtestapp.presentation.screens.commons.base_components.BaseActivity;
import aifree.com.authorizationtestapp.presentation.utils.ScreensRouter;
import dagger.Module;
import dagger.Provides;


@Module
public class ActivityBaseModule {

    private BaseActivity mBaseActivity;

    public ActivityBaseModule(BaseActivity activity) {
        mBaseActivity = activity;
    }

    @Provides
    public ScreensRouter provideScreensRouter(ScreensRouter router) {
        return router;
    }
}
