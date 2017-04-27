package com.jcoder.app.folink.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.jcoder.app.folink.R;
import com.jcoder.app.folink.adapter.FolinkNewsAdapter;
import com.jcoder.app.folink.model.NewsList;
import com.jcoder.app.folink.presenter.impPresenter.FNewsPresenterImpl;
import com.jcoder.app.folink.presenter.impViews.INewsFragment;
import com.jcoder.app.folink.widget.GridItemDividerDecoration;
import com.jcoder.app.folink.widget.WrapContentLinearLayoutManager;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rory on 2016/10/8.
 */

public class FolinkVideoFragment extends BaseFragment implements INewsFragment{

    public static final String TAG = "jian_news";
    public static final String EXTRA_DATE = "fragment_extra_date";

    FNewsPresenterImpl mFNewsPresenter;
    FolinkNewsAdapter mFolinkNewsAdapter;

    LinearLayoutManager mLinearLayoutManager;

    @BindView(R.id.recycle_topnews)
    RecyclerView mRecyclerView;

    @BindView(R.id.prograss)
    ProgressBar mProgressBar;

    public static FolinkVideoFragment newInstance() {
//        Log.d(TAG, "newInstance(" + formatDate(date) + ")");
        Date date = new Date();
        Bundle args = new Bundle();
        args.putLong(EXTRA_DATE, date.getTime());

        FolinkVideoFragment fragment = new FolinkVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_folink_news_layout, container, false);
        ButterKnife.bind(this, v);
        initialViews();
        return v;

    }

    @Override
    protected int getLayoutResource() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialData();

    }

    private void initialViews() {
        mLinearLayoutManager = new WrapContentLinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new GridItemDividerDecoration(getContext(), R.dimen.divider_height, R.color.divider));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mFolinkNewsAdapter);
//        mRecyclerView.addOnScrollListener(loadingMoreListener);
    }

    private void initialData() {
        mFNewsPresenter = new FNewsPresenterImpl(this);
        mFolinkNewsAdapter = new FolinkNewsAdapter(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        mFNewsPresenter.getFolinkNews("1");
    }

    @Override
    public void updateNewsData(NewsList list) {

        Log.e("jjj", "11112");
        mProgressBar.setVisibility(View.INVISIBLE);
        mFolinkNewsAdapter.addNewsData(list.getNewsList());
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError() {

    }
}
