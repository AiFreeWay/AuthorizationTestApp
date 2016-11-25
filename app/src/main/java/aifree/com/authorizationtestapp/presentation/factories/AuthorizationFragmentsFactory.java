package aifree.com.authorizationtestapp.presentation.factories;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import aifree.com.authorizationtestapp.presentation.screens.authorization.fragments.CodeFragment;
import aifree.com.authorizationtestapp.presentation.screens.authorization.fragments.InputWithPasswordFragment;
import aifree.com.authorizationtestapp.presentation.screens.authorization.fragments.MailFragment;
import aifree.com.authorizationtestapp.presentation.screens.commons.base_components.BaseFragment;


public class AuthorizationFragmentsFactory {

    private SortedMap<Integer, BaseFragment> mFragments;

    public AuthorizationFragmentsFactory() {
        generateFragments();
    }

    public BaseFragment getFragmentById(Fragments fragments) {
        return mFragments.get(fragments.id);
    }

    public List<BaseFragment> getFragments() {
        return new ArrayList<>(mFragments.values());
    }

    private void generateFragments() {
        mFragments = new TreeMap<>();
        mFragments.put(Fragments.MAIL.id, new MailFragment());
        mFragments.put(Fragments.INPUT_WITH_PASSWORD.id, new InputWithPasswordFragment());
        mFragments.put(Fragments.CODE.id, addBundleToFragment(new CodeFragment()));
    }

    private BaseFragment addBundleToFragment(BaseFragment fragment) {
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public enum Fragments {
        MAIL(0),
        INPUT_WITH_PASSWORD(1),
        CODE(2);

        public int id;

        Fragments(int id) {
            this.id = id;
        }
    }
}
