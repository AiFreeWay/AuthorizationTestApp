package aifree.com.authorizationtestapp.presentation.screens.authorization.view_controllers;


import javax.inject.Inject;

import aifree.com.authorizationtestapp.domain.interactors.AuthorizationInteractor;
import aifree.com.authorizationtestapp.presentation.factories.AuthorizationFragmentsFactory;
import aifree.com.authorizationtestapp.presentation.screens.authorization.activities.AuthorizationActivity;
import aifree.com.authorizationtestapp.presentation.screens.authorization.fragments.InputWithPasswordFragment;
import aifree.com.authorizationtestapp.presentation.screens.commons.base_components.ViewController;
import aifree.com.authorizationtestapp.presentation.screens.main.activities.MainActivity;
import aifree.com.authorizationtestapp.presentation.utils.FragmentRouter;
import aifree.com.authorizationtestapp.presentation.utils.Logger;
import aifree.com.authorizationtestapp.presentation.utils.ScreensRouter;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FmtInputWithPasswordViewController extends ViewController<InputWithPasswordFragment> {

    @Inject
    FragmentRouter mFragmentRouter;
    @Inject
    AuthorizationFragmentsFactory mFragmentFactory;
    @Inject
    AuthorizationInteractor mAuthorizationInteractor;
    @Inject
    ScreensRouter mScreensRouter;

    public FmtInputWithPasswordViewController(InputWithPasswordFragment view) {
        super(view);
        ((AuthorizationActivity) mView.getActivity()).getAuthorizationComponent()
                .inject(this);
    }

    public void tryAgain() {
        input();
    }

    public void showCodeFragment() {
        mFragmentRouter.back();
    }

    public void input() {
        mAuthorizationInteractor.loginAccount(mView.getAccount())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(() -> mView.showInputDialog())
                .subscribe(isSuccess -> {
                            mView.dismissInputDialog();
                            if (isSuccess)
                                mScreensRouter.showActivityClearTask(MainActivity.class);
                            else
                                mView.showInputErrorDialog("Не удалось войти");},
                        e-> {
                            mView.showInputErrorDialog(e.getMessage());
                            Logger.logError(e);});
    }
}
