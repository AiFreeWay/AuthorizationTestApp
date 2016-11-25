package aifree.com.authorizationtestapp.presentation.utils;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rey.material.app.Dialog;
import com.rey.material.app.SimpleDialog;

import aifree.com.authorizationtestapp.R;
import aifree.com.authorizationtestapp.presentation.screens.authorization.view_controllers.FmtCodeViewController;
import aifree.com.authorizationtestapp.presentation.screens.authorization.view_controllers.FmtInputWithPasswordViewController;
import aifree.com.authorizationtestapp.presentation.screens.commons.base_components.ViewController;

public class AuthorizationDialogManager {

    private Context mContext;
    private ViewController mViewController;

    private Dialog mRetryDialog;
    private Dialog mSendCodeDialog;

    private Dialog mInputDialog;
    private Dialog mInputErrorDialog;

    public AuthorizationDialogManager(Context context, ViewGroup parent, ViewController viewController) {
        mContext = context;
        mViewController = viewController;
        LayoutInflater inflater = LayoutInflater.from(context);
        mSendCodeDialog = new SimpleDialog(mContext)
                .backgroundColor(ContextCompat.getColor(mContext, R.color.send_code))
                .contentView(inflater.inflate(R.layout.v_load_code, parent, false))
                .cancelable(false);

        mInputDialog = new SimpleDialog(mContext)
                .backgroundColor(ContextCompat.getColor(mContext, R.color.send_code))
                .contentView(inflater.inflate(R.layout.v_input, parent, false))
                .cancelable(false);
    }

    public void showRetryDialog(String text) {
        mRetryDialog = new SimpleDialog(mContext)
                .message(text)
                .title(mContext.getString(R.string.error_send_code))
                .neutralActionClickListener(v -> {
                    mRetryDialog.dismiss(); mViewController.start();})
                .neutralActionTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark))
                .neutralAction(mContext.getString(R.string.retry))
                .cancelable(true);
        mRetryDialog.show();
    }

    public void showInputErrorDialogCode(String text) {
        mInputErrorDialog = new SimpleDialog(mContext)
                .message(text)
                .title(mContext.getString(R.string.error_input))
                .neutralActionClickListener(v -> neutralActionCode())
                .neutralActionTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark))
                .neutralAction(mContext.getString(R.string.retry))
                .cancelable(true);
        mInputErrorDialog.show();
    }

    public void showInputErrorDialogPassword(String text) {
        mInputErrorDialog = new SimpleDialog(mContext)
                .message(text)
                .title(mContext.getString(R.string.error_input))
                .positiveActionClickListener(v -> positiveActionPassword())
                .positiveActionTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark))
                .positiveAction(mContext.getString(R.string.retry))
                .negativeActionClickListener(v -> negativeActionPassword())
                .negativeActionTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark))
                .negativeAction(mContext.getString(R.string.input_without_password))
                .cancelable(true);
        mInputErrorDialog.show();
    }

    public void showSendCodeDialog() {
        mSendCodeDialog.show();
    }

    public void dismissSendCodeDialog() {
        mSendCodeDialog.dismiss();
    }

    public void showInputDialog() {
        mInputDialog.show();
    }

    public void dismissInputDialog() {
        mInputDialog.dismiss();
    }

    public void setViewController(ViewController mViewController) {
        this.mViewController = mViewController;
    }
    
    private void neutralActionCode() {
        mInputErrorDialog.dismiss(); 
        ((FmtCodeViewController) mViewController).tryAgain();
    }
    
    private void positiveActionPassword() {
        mInputErrorDialog.dismiss();
        ((FmtInputWithPasswordViewController) mViewController).tryAgain();
    }

    private void negativeActionPassword() {
        mInputErrorDialog.dismiss();
        ((FmtInputWithPasswordViewController) mViewController).showCodeFragment();
    }
}
