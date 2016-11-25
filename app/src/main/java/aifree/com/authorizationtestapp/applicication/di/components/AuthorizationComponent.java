package aifree.com.authorizationtestapp.applicication.di.components;

import aifree.com.authorizationtestapp.applicication.di.modules.AuthorizationModule;
import aifree.com.authorizationtestapp.applicication.di.scopes.PerAuthorization;
import aifree.com.authorizationtestapp.presentation.screens.authorization.view_controllers.AcAuthorizationViewController;
import aifree.com.authorizationtestapp.presentation.screens.authorization.view_controllers.FmtCodeViewController;
import aifree.com.authorizationtestapp.presentation.screens.authorization.view_controllers.FmtInputWithPasswordViewController;
import aifree.com.authorizationtestapp.presentation.screens.authorization.view_controllers.FmtMailViewController;
import dagger.Component;


@PerAuthorization
@Component(modules = AuthorizationModule.class, dependencies = ActivityBaseComponent.class)
public interface AuthorizationComponent {

    void inject(AcAuthorizationViewController controller);
    void inject(FmtMailViewController controller);
    void inject(FmtCodeViewController controller);
    void inject(FmtInputWithPasswordViewController controller);
}
