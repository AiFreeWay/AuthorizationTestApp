package aifree.com.authorizationtestapp.presentation.screens.authorization.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import aifree.com.authorizationtestapp.R;
import aifree.com.authorizationtestapp.presentation.screens.authorization.view_controllers.FmtCodeViewController;
import aifree.com.authorizationtestapp.presentation.screens.commons.base_components.BaseFragment;
import aifree.com.authorizationtestapp.presentation.utils.AuthorizationDialogManager;


public class CodeFragment extends BaseFragment<FmtCodeViewController> {

    public static final String EMAIL_TAG = "email";

    private String mEmail;
    private TextView mTvTitle;
    private EditText mEtCode;
    private Button mBtnInput;
    private Button mBtnRetry;
    private AuthorizationDialogManager mDialogManger;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDialogManger = new AuthorizationDialogManager(getContext(), container, mViewController);
        return inflater.inflate(R.layout.fmt_code, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mEmail = getArguments().getString(EMAIL_TAG);
        bindViews();
        mViewController = new FmtCodeViewController(this);
        mDialogManger.setViewController(mViewController);
        mBtnInput.setOnClickListener(v -> {
            if (validCode())
                mViewController.checkInputCode();
            else
                showToast("Введите код");});
        mViewController.start();
    }

    public String getEmail() {
        return mEmail;
    }

    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    public String getCode() {
        return mEtCode.getText().toString();
    }

    public void setTitleRetryButton(String title) {
        mBtnRetry.setText(title);
    }

    public void setRetryButtonListener(View.OnClickListener listener) {
        mBtnRetry.setOnClickListener(listener);
    }

    public void changeBtnRetryEnabled(boolean enabled) {
        mBtnRetry.setEnabled(enabled);
    }

    public void showToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    public void showRetryDialog(String text) {
        mDialogManger.showRetryDialog(text);
    }

    public void showSendCodeDialog() {
        mDialogManger.showSendCodeDialog();
    }

    public void dismissSendCodeDialog() {
        mDialogManger.dismissSendCodeDialog();
    }

    public void showInputDialog() {
        mDialogManger.showInputDialog();
    }

    public void dismissInputDialog() {
        mDialogManger.dismissInputDialog();
    }

    public void showInputErrorDialog(String text) {
        mDialogManger.showInputErrorDialogCode(text);
    }

    public void enableInputCodeFunctionaly() {
        mEtCode.setEnabled(true);
        mBtnInput.setEnabled(true);
        mBtnRetry.setEnabled(true);
    }

    public void disableInputCodeFunctionaly() {
        mEtCode.setEnabled(false);
        mBtnInput.setEnabled(false);
        mBtnRetry.setEnabled(false);
    }

    private void bindViews() {
        mTvTitle = (TextView) getActivity().findViewById(R.id.fmt_code_tv_title);
        mEtCode = (EditText) getActivity().findViewById(R.id.fmt_code_et_code);
        mBtnInput = (Button) getActivity().findViewById(R.id.fmt_code_btn_input);
        mBtnRetry = (Button) getActivity().findViewById(R.id.fmt_code_btn_retry);
    }

    private boolean validCode() {
        return mEtCode.length()>0;
    }
}
