package aifree.com.authorizationtestapp.presentation.utils;


import android.content.Context;
import android.content.Intent;

import java.io.Serializable;

import javax.inject.Inject;

public class ScreensRouter {

    private Context mContext;

    @Inject
    public ScreensRouter(Context context) {
        mContext = context;
    }

    public void showActivityClearTask(Class activityClass) {
        Intent intent = new Intent(mContext, activityClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    public void showActivity(Class activityClass, String dataTag, Serializable data) {
        Intent intent = new Intent(mContext, activityClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(dataTag, data);
        mContext.startActivity(intent);
    }
}
