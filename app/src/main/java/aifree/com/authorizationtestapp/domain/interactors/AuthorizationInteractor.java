package aifree.com.authorizationtestapp.domain.interactors;

import aifree.com.authorizationtestapp.domain.models.Phone;
import aifree.com.authorizationtestapp.presentation.models.Account;
import rx.Observable;

public interface AuthorizationInteractor {

    Observable<Phone> checkPhoneBind(String email);
    Observable<Void> sendCodeOnMail();
    Observable<Void> sendCodeOnPhone();
    Observable<Boolean> loginCode(String code);
    Observable<Boolean> loginAccount(Account account);
}
