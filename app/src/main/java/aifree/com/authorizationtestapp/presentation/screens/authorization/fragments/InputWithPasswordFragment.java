package aifree.com.authorizationtestapp.presentation.screens.authorization.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import aifree.com.authorizationtestapp.R;
import aifree.com.authorizationtestapp.presentation.models.Account;
import aifree.com.authorizationtestapp.presentation.screens.authorization.view_controllers.FmtInputWithPasswordViewController;
import aifree.com.authorizationtestapp.presentation.screens.commons.base_components.BaseFragment;
import aifree.com.authorizationtestapp.presentation.utils.AuthorizationDialogManager;


public class InputWithPasswordFragment extends BaseFragment<FmtInputWithPasswordViewController> {

    private EditText mEtMail;
    private EditText mEtPassword;
    private Button mBtnInput;
    private Button mBtnInputWithoutCode;
    private AuthorizationDialogManager mDilogManger;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDilogManger = new AuthorizationDialogManager(getContext(), container, mViewController);
        return inflater.inflate(R.layout.fmt_input_with_password, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindViews();
        mViewController = new FmtInputWithPasswordViewController(this);
        mBtnInputWithoutCode.setOnClickListener(v -> mViewController.showCodeFragment());
        mBtnInput.setOnClickListener(v -> {
            if (isValidLoginPassword())
                mViewController.input();
            else
                showToast("Заполните поля");});
        mDilogManger.setViewController(mViewController);
        mViewController.start();
    }

    public void showInputDialog() {
        mDilogManger.showInputDialog();
    }

    public void dismissInputDialog() {
        mDilogManger.dismissInputDialog();
    }

    public void showInputErrorDialog(String text) {
        mDilogManger.showInputErrorDialogPassword(text);
    }

    public Account getAccount() {
        return new Account(mEtMail.getText().toString(), mEtPassword.getText().toString());
    }

    private void bindViews() {
        mEtMail = (EditText) getActivity().findViewById(R.id.fmt_input_with_password_et_mail);
        mEtPassword = (EditText) getActivity().findViewById(R.id.fmt_input_with_password_et_password);
        mBtnInput = (Button) getActivity().findViewById(R.id.fmt_input_with_password_btn_input);
        mBtnInputWithoutCode = (Button) getActivity().findViewById(R.id.fmt_input_with_password_btn_input_with_code);
    }

    private boolean isValidLoginPassword() {
        return mEtMail.getText().length()>0 && mEtPassword.getText().length()>0;
    }

    private void showToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }
}
