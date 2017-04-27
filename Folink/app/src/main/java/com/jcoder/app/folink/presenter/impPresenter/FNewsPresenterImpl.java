package com.jcoder.app.folink.presenter.impPresenter;

import android.util.Log;

import com.jcoder.app.folink.api.FolinkApiManager;
import com.jcoder.app.folink.model.FNews;
import com.jcoder.app.folink.model.NewsList;
import com.jcoder.app.folink.presenter.IFolinkNewsPresenter;
import com.jcoder.app.folink.presenter.impViews.INewsFragment;
import com.jcoder.app.folink.util.Jloger;

import java.util.ArrayList;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Rory on 2016/10/8.
 */

public class FNewsPresenterImpl extends BasePresenterImpl implements IFolinkNewsPresenter {

    INewsFragment mNewsFragment;

    public FNewsPresenterImpl(INewsFragment iNewsFragment) {
        if (iNewsFragment == null) {
            throw new IllegalArgumentException("the Fragment is null");
        }
        this.mNewsFragment = iNewsFragment;
    }

    @Override
    public void getFolinkNews(String id) {
        mNewsFragment.showProgress();

        Subscription subscription = FolinkApiManager.getmApiManager().getFolinkNewsService().getNews(0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("jjj", "exception : "+e.toString());
                        mNewsFragment.hideProgress();
                        mNewsFragment.showError();
                    }

                    @Override
                    public void onNext(NewsList list) {

                        Jloger.e("============"+list.toString());
                        ArrayList<FNews> ngs = list.getNewsList();
                        mNewsFragment.hideProgress();
                        mNewsFragment.updateNewsData(list);
                    }
                });



        addSubscription(subscription);
    }

}
