package aifree.com.authorizationtestapp.presentation.screens.authorization.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import aifree.com.authorizationtestapp.R;
import aifree.com.authorizationtestapp.applicication.di.components.AuthorizationComponent;
import aifree.com.authorizationtestapp.applicication.di.components.DaggerAuthorizationComponent;
import aifree.com.authorizationtestapp.applicication.di.modules.AuthorizationModule;
import aifree.com.authorizationtestapp.presentation.screens.authorization.view_controllers.AcAuthorizationViewController;
import aifree.com.authorizationtestapp.presentation.screens.commons.base_components.BaseActivity;


public class AuthorizationActivity extends BaseActivity<AcAuthorizationViewController> {

    private Toolbar mToolbar;
    private FrameLayout mFlBody;
    private AuthorizationComponent mAuthorizationComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_authorization);
        bindViews();
        initActionBar();
        mAuthorizationComponent = DaggerAuthorizationComponent.builder()
                .activityBaseComponent(getActivityComponent())
                .authorizationModule(new AuthorizationModule(this))
                .build();

        mViewController = new AcAuthorizationViewController(this);
        mViewController.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            mViewController.popBack();
        return true;
    }

    public AuthorizationComponent getAuthorizationComponent() {
        return mAuthorizationComponent;
    }

    public int getFragmentBodyId() {
        return R.id.ac_authorization_fl_body;
    }

    private void bindViews() {
        mToolbar = (Toolbar) findViewById(R.id.ac_authorization_toolbar);
        mFlBody = (FrameLayout) findViewById(R.id.ac_authorization_fl_body);
    }

    private void initActionBar() {
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.app_name);
    }
}
