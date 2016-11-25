package aifree.com.authorizationtestapp.presentation.utils;


import rx.Subscription;

public class IntervalKeeper {

    private Subscription mInterval;

    public IntervalKeeper() {
    }

    public void setInterval(Subscription interval) {
        mInterval = interval;
    }

    public boolean isFree() {
        return mInterval == null || mInterval.isUnsubscribed();
    }
}
