package aifree.com.authorizationtestapp.presentation.screens.authorization.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import aifree.com.authorizationtestapp.R;
import aifree.com.authorizationtestapp.presentation.screens.authorization.activities.AuthorizationActivity;
import aifree.com.authorizationtestapp.presentation.screens.authorization.view_controllers.FmtMailViewController;
import aifree.com.authorizationtestapp.presentation.screens.commons.base_components.BaseFragment;


public class MailFragment extends BaseFragment<FmtMailViewController> {

    private Button mBtnGetCode;
    private Button mBtnInputWithPassword;
    private EditText mEtMail;

    private TextWatcher mMailTextChangeListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            changeBtnGetCodeEnabled(charSequence.length()>0);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fmt_mail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindViews();
        mViewController = new FmtMailViewController(this);
        mBtnGetCode.setOnClickListener(v -> mViewController.showGetCodeScreen());
        mBtnInputWithPassword.setOnClickListener(v -> mViewController.showInputWithPasswordScreen());
        mEtMail.addTextChangedListener(mMailTextChangeListener);
        mViewController.start();
    }

    public String getEtMail() {
        return mEtMail.getText().toString();
    }

    public void changeBtnGetCodeEnabled(boolean enabled) {
        mBtnGetCode.setEnabled(enabled);
    }

    public int getFragmentBodyId() {
        return ((AuthorizationActivity) getActivity()).getFragmentBodyId();
    }

    private void bindViews() {
        mBtnGetCode = (Button) getActivity().findViewById(R.id.fmt_mail_btn_get_code);
        mBtnInputWithPassword = (Button) getActivity().findViewById(R.id.fmt_mail_btn_input_with_password);
        mEtMail = (EditText) getActivity().findViewById(R.id.fmt_mail_et_mail);
    }
}
