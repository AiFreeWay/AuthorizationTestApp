package aifree.com.authorizationtestapp.presentation.screens.authorization.view_controllers;

import javax.inject.Inject;

import aifree.com.authorizationtestapp.presentation.factories.AuthorizationFragmentsFactory;
import aifree.com.authorizationtestapp.presentation.screens.authorization.activities.AuthorizationActivity;
import aifree.com.authorizationtestapp.presentation.screens.authorization.fragments.CodeFragment;
import aifree.com.authorizationtestapp.presentation.screens.authorization.fragments.MailFragment;
import aifree.com.authorizationtestapp.presentation.screens.commons.base_components.BaseFragment;
import aifree.com.authorizationtestapp.presentation.screens.commons.base_components.ViewController;
import aifree.com.authorizationtestapp.presentation.utils.FragmentRouter;


public class FmtMailViewController extends ViewController<MailFragment> {

    @Inject
    FragmentRouter mFragmentRouter;
    @Inject
    AuthorizationFragmentsFactory mFragmentFactory;

    public FmtMailViewController(MailFragment view) {
        super(view);
        ((AuthorizationActivity) mView.getActivity()).getAuthorizationComponent()
                .inject(this);
    }

    public void showGetCodeScreen() {
        BaseFragment fragment = mFragmentFactory.getFragmentById(AuthorizationFragmentsFactory.Fragments.CODE);
        fragment.getArguments().putString(CodeFragment.EMAIL_TAG, mView.getEtMail());
        mFragmentRouter.showWithAddBackStack(fragment, mView.getFragmentBodyId());
    }

    public void showInputWithPasswordScreen() {
        BaseFragment fragment = mFragmentFactory.getFragmentById(AuthorizationFragmentsFactory.Fragments.INPUT_WITH_PASSWORD);
        mFragmentRouter.showWithAddBackStack(fragment, mView.getFragmentBodyId());
    }
}
