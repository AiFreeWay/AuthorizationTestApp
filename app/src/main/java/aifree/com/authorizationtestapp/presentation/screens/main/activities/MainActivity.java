package aifree.com.authorizationtestapp.presentation.screens.main.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import aifree.com.authorizationtestapp.R;
import aifree.com.authorizationtestapp.presentation.screens.commons.base_components.BaseActivity;


public class MainActivity extends BaseActivity {

    private Toolbar mToolbar;
    private Button mBtnOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        bindViews();
        initActionBar();
        mBtnOutput.setOnClickListener(v -> mBtnOutput.setText("Не так быстро хитрюга"));
    }

    private void bindViews() {
        mToolbar = (Toolbar) findViewById(R.id.ac_main_toolbar);
        mBtnOutput = (Button) findViewById(R.id.ac_main_btn_output);
    }

    private void initActionBar() {
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.app_name);
    }
}
