package aifree.com.authorizationtestapp.domain.executors;

import android.util.Log;

import javax.inject.Inject;

import aifree.com.authorizationtestapp.domain.interactors.AuthorizationInteractor;
import aifree.com.authorizationtestapp.domain.models.Phone;
import aifree.com.authorizationtestapp.presentation.models.Account;
import rx.Observable;


public class AuthorizationInteractorImpl implements AuthorizationInteractor {

    @Inject
    public AuthorizationInteractorImpl() {
    }

    @Override
    public Observable<Phone> checkPhoneBind(String email) {
        Observable.OnSubscribe<Phone> onSubscribe = subscriber -> {
            try {
                if (!(email.equals("user@mail.ru") || email.equals("user2@mail.ru")))
                    subscriber.onError(new Exception("Пользователь с такой почтой не зарегестрирован"));
                if (email.equals("user2@mail.ru"))
                    subscriber.onNext(new Phone("89347517230"));
                else
                    subscriber.onNext(new Phone());
            } catch (Exception e) {
                subscriber.onError(new Exception("Произошла ошибка", e));
            }
        };
        return Observable.create(onSubscribe);
    }

    @Override
    public Observable<Void> sendCodeOnMail() {
        return Observable.just(null);
    }

    @Override
    public Observable<Void> sendCodeOnPhone() {
        return Observable.just(null);
    }

    @Override
    public Observable<Boolean> loginCode(String code) {
        Observable.OnSubscribe<Boolean> onSubscribe = subscriber -> {
            try {
                subscriber.onNext(code.equals("1234"));
            } catch (Exception e) {
                subscriber.onError(new Exception("Произошла ошибка", e));
            }
        };
        return Observable.create(onSubscribe);
    }

    @Override
    public Observable<Boolean> loginAccount(Account account) {
        Observable.OnSubscribe<Boolean> onSubscribe = subscriber -> {
            try {
                subscriber.onNext(isAccountValid(account));
            } catch (Exception e) {
                subscriber.onError(new Exception("Произошла ошибка", e));
            }
        };
        return Observable.create(onSubscribe);
    }

    private boolean isAccountValid(Account account) {
        return account.getLogin().equals("user") && account.getPassword().equals("0");
    }
}
