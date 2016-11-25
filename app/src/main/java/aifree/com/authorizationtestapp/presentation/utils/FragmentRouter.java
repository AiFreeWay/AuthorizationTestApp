package aifree.com.authorizationtestapp.presentation.utils;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import aifree.com.authorizationtestapp.presentation.screens.commons.base_components.BaseFragment;


public class FragmentRouter {

    private FragmentManager mFragmentManager;

    public FragmentRouter(FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
    }

    public void show(BaseFragment fragment, int containerId) {
        try {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.replace(containerId, fragment);
            transaction.commit();
        } catch (Exception e) {
            Logger.logError(e);
        }
    }

    public void showWithAddBackStack(BaseFragment fragment, int containerId) {
        try {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.replace(containerId, fragment);
            transaction.addToBackStack(generateFragmentTag(containerId));
            transaction.commitAllowingStateLoss();
        } catch (Exception e) {
            Logger.logError(e);
        }
    }

    public boolean back() {
        boolean isCanBack = mFragmentManager.getBackStackEntryCount() > 1;
        if (isCanBack)
            mFragmentManager.popBackStack();
        return isCanBack;
    }

    private String generateFragmentTag(int viewId) {
        return viewId+"";
    }
}
