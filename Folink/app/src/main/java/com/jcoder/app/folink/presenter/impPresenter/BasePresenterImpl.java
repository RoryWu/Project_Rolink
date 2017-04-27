package com.jcoder.app.folink.presenter.impPresenter;

import com.jcoder.app.folink.presenter.BasePresenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Rory on 2016/10/8.
 */

public class BasePresenterImpl implements BasePresenter{

    private CompositeSubscription mCompositeSubscription;

    public void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void unsubcrible() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
