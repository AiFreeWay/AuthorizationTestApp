package aifree.com.authorizationtestapp.presentation.screens.authorization.view_controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import aifree.com.authorizationtestapp.domain.interactors.AuthorizationInteractor;
import aifree.com.authorizationtestapp.domain.models.Phone;
import aifree.com.authorizationtestapp.presentation.screens.authorization.activities.AuthorizationActivity;
import aifree.com.authorizationtestapp.presentation.screens.authorization.fragments.CodeFragment;
import aifree.com.authorizationtestapp.presentation.screens.commons.base_components.ViewController;
import aifree.com.authorizationtestapp.presentation.screens.main.activities.MainActivity;
import aifree.com.authorizationtestapp.presentation.utils.IntervalKeeper;
import aifree.com.authorizationtestapp.presentation.utils.Logger;
import aifree.com.authorizationtestapp.presentation.utils.ScreensRouter;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class FmtCodeViewController extends ViewController<CodeFragment> {

    public static final int PHONE_LENGTH_WITHOUT_PLUS = 11;
    public static final int HIDED_SIMBOLS_COUNT = 5;

    private final int INTERVAL_LENGTH = 45;
    private final Calendar CALENDAR = new GregorianCalendar();

    @Inject
    ScreensRouter mScreensRouter;
    @Inject
    AuthorizationInteractor mAuthorizationInteractor;
    @Inject
    IntervalKeeper mIntervalKeeper;

    private SimpleDateFormat mTimeFormatter = new SimpleDateFormat("mm:ss");
    private Subscription mTimerSubscription;
    private Observable<Long> mTimer = Observable.interval(1, TimeUnit.SECONDS);

    public FmtCodeViewController(CodeFragment view) {
        super(view);
        ((AuthorizationActivity) mView.getActivity()).getAuthorizationComponent()
                .inject(this);
    }

    @Override
    public void start() {
        super.start();
        mAuthorizationInteractor.checkPhoneBind(mView.getEmail())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::doOnCheckPhoneBind, e -> {
                    mView.setTitle(e.getMessage());
                    mView.showRetryDialog(e.getMessage());
                    mView.disableInputCodeFunctionaly();
                    Logger.logError(e);});
    }

    public void tryAgain() {
        checkInputCode();
    }

    public void checkInputCode() {
        mAuthorizationInteractor.loginCode(mView.getCode())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(() -> mView.showInputDialog())
                .subscribe(isSuccess -> {
                    mView.dismissInputDialog();
                    if (isSuccess)
                        mScreensRouter.showActivityClearTask(MainActivity.class);
                    else
                        mView.showInputErrorDialog("Не удалось войти");},
                        e-> {
                    mView.showInputErrorDialog(e.getMessage());
                    Logger.logError(e);});
    }

    private void doOnCheckPhoneBind(Phone phone) {
        mView.enableInputCodeFunctionaly();
        if (phone.isValid()) {
            mView.setRetryButtonListener(v -> sendCodeOnPhone());
            mView.setTitle("Мы выслали код (отправлен на телефон "+parsePhoneNumber(phone.getPhone())+")");
            sendCodeOnPhone();
        } else {
            mView.setRetryButtonListener(v -> sendCodeOnMail());
            mView.setTitle("Мы выслали код (отправлен на почту "+mView.getEmail()+")");
            sendCodeOnMail();
        }
    }

    private void sendCodeOnPhone() {
        if (mIntervalKeeper.isFree())
            mAuthorizationInteractor.sendCodeOnPhone()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(() -> mView.showSendCodeDialog())
                    .subscribe(aVoid -> {
                        mView.dismissSendCodeDialog();
                        mView.changeBtnRetryEnabled(false);
                        startCodeLimitInterval();
                    }, e -> { mView.showRetryDialog(e.getMessage()); Logger.logError(e);});
    }

    private void sendCodeOnMail() {
        mAuthorizationInteractor.sendCodeOnMail()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(() -> mView.showSendCodeDialog())
                .subscribe(aVoid -> {
                            mView.dismissSendCodeDialog();
                }, e -> { mView.showRetryDialog(e.getMessage()); Logger.logError(e);});
    }

    private String parsePhoneNumber(String phone) {
        char[] phoneWithHidedSimbols = phone.toCharArray();
        int startSimbol;
        int iterationCount;

        if (phone.length() > PHONE_LENGTH_WITHOUT_PLUS)
            startSimbol = 5;
        else
            startSimbol = 4;
        iterationCount = HIDED_SIMBOLS_COUNT+startSimbol;

        for (int i=startSimbol;i<iterationCount; i++) {
            phoneWithHidedSimbols[i] = '*';
        }

        return new String(phoneWithHidedSimbols);
    }

    private void startCodeLimitInterval() {
        mTimerSubscription = mTimer
                .doOnUnsubscribe(() -> {
                    mView.changeBtnRetryEnabled(true);
                    mView.setTitleRetryButton("Выслать код повторно");})
                .doOnSubscribe(() -> mIntervalKeeper.setInterval(mTimerSubscription))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onChangeInterval, Logger::logError);
    }

    private void onChangeInterval(Long time) {
        try {
            if (time == INTERVAL_LENGTH)
                mTimerSubscription.unsubscribe();
            else {
                int remainingTime = INTERVAL_LENGTH - time.intValue();
                String timeText = intToMinuteAndSeconds(remainingTime);
                mView.setTitleRetryButton("Выслать код повторно ("+timeText+")");
            }
        } catch (Exception e) {

        }
    }

    private String intToMinuteAndSeconds(int time) {
        int minutes = time/60;
        int seconds = time-(minutes*60);
        CALENDAR.set(Calendar.MINUTE, minutes);
        CALENDAR.set(Calendar.SECOND, seconds);
        return mTimeFormatter.format(CALENDAR.getTimeInMillis());
    }

}
