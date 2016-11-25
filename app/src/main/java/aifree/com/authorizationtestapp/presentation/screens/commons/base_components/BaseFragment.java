package aifree.com.authorizationtestapp.presentation.screens.commons.base_components;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public abstract class BaseFragment<VC extends ViewController> extends Fragment {

    protected VC mViewController;

    public void refresh() {

    }

    @Nullable
    public VC getViewController() {
        return mViewController;
    }
}
