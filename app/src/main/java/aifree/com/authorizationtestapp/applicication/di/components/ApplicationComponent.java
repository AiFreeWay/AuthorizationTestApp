package aifree.com.authorizationtestapp.applicication.di.components;

import android.content.Context;

import javax.inject.Singleton;

import aifree.com.authorizationtestapp.applicication.di.modules.ApplicationModule;
import dagger.Component;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context provideContext();
}
