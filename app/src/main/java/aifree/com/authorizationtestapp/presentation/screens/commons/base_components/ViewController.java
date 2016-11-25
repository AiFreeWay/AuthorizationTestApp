package aifree.com.authorizationtestapp.presentation.screens.commons.base_components;


public abstract class ViewController<V> {

    protected V mView;


    public ViewController(V view) {
        mView = view;
        inject();
    }

    public void inject() {

    }

    public void start() {

    }

    public void restart() {

    }

    public void stop() {

    }

    public V getView() {
        return mView;
    }
}
