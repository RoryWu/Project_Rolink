package com.jcoder.app.folink.presenter.impPresenter;

import android.util.Log;

import com.jcoder.app.folink.api.FolinkApiManager;
import com.jcoder.app.folink.model.ABList;
import com.jcoder.app.folink.model.AbNewsItem;
import com.jcoder.app.folink.presenter.IFolinkNewsPresenter;
import com.jcoder.app.folink.presenter.impViews.IImageFragment;
import com.jcoder.app.folink.util.Jloger;

import java.util.ArrayList;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Rory on 2016/10/8.
 */

public class FImagePresenterImpl extends BasePresenterImpl implements IFolinkNewsPresenter {

    IImageFragment mNewsFragment;

    public FImagePresenterImpl(IImageFragment iNewsFragment) {
        if (iNewsFragment == null) {
            throw new IllegalArgumentException("the Fragment is null");
        }
        this.mNewsFragment = iNewsFragment;
    }

    @Override
    public void getFolinkNews(String id) {
        mNewsFragment.showProgress();

        Subscription subscription = FolinkApiManager.getmApiManager().getFolinkImageService().getAbNewsItem()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ABList>() {
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
                    public void onNext(ABList list) {

                        Jloger.e("============"+list.toString());
                        ArrayList<AbNewsItem> ngs = list.getAbNewsList();
                        for (AbNewsItem ng : ngs) {

                            Jloger.e("list "+ ng.getTitle());
                            Jloger.e("============");
                        }
                        mNewsFragment.hideProgress();
                        mNewsFragment.updateImageList(list);
                    }
                });



        addSubscription(subscription);
    }

}
