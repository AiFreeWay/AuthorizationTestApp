package aifree.com.authorizationtestapp.presentation.screens.authorization.view_controllers;

import javax.inject.Inject;

import aifree.com.authorizationtestapp.presentation.factories.AuthorizationFragmentsFactory;
import aifree.com.authorizationtestapp.presentation.screens.authorization.activities.AuthorizationActivity;
import aifree.com.authorizationtestapp.presentation.screens.commons.base_components.ViewController;
import aifree.com.authorizationtestapp.presentation.utils.FragmentRouter;


public class AcAuthorizationViewController extends ViewController<AuthorizationActivity> {

    @Inject
    FragmentRouter mFragmentRouter;
    @Inject
    AuthorizationFragmentsFactory mFragmentFactory;

    public AcAuthorizationViewController(AuthorizationActivity view) {
        super(view);
        mView.getAuthorizationComponent()
                .inject(this);
    }

    public void start() {
        mFragmentRouter.showWithAddBackStack(mFragmentFactory.getFragmentById(AuthorizationFragmentsFactory.Fragments.MAIL), mView.getFragmentBodyId());
    }

    public void popBack() {
        if (!mFragmentRouter.back())
            mView.finish();
    }
}
