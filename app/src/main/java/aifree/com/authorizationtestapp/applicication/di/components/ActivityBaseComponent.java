package aifree.com.authorizationtestapp.applicication.di.components;

import android.content.Context;

import aifree.com.authorizationtestapp.applicication.di.modules.ActivityBaseModule;
import aifree.com.authorizationtestapp.applicication.di.scopes.PerActivity;
import dagger.Component;


@PerActivity
@Component(modules = ActivityBaseModule.class, dependencies = ApplicationComponent.class)
public interface ActivityBaseComponent {

    Context provideContext();
}
