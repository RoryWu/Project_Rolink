package com.jcoder.app.folink.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.jcoder.app.folink.R;
import com.jcoder.app.folink.adapter.FolinkImageAdapter;
import com.jcoder.app.folink.model.ABList;
import com.jcoder.app.folink.presenter.impPresenter.FImagePresenterImpl;
import com.jcoder.app.folink.presenter.impViews.IImageFragment;
import com.jcoder.app.folink.widget.GridItemDividerDecoration;
import com.jcoder.app.folink.widget.WrapContentLinearLayoutManager;

import butterknife.BindView;

/**
 * Created by Rory on 2016/10/8.
 */

public class FolinkImagesFragment extends BaseFragment implements IImageFragment {

    FImagePresenterImpl mFImagePresenter;
    FolinkImageAdapter mFolinkImageAdapter;

    LinearLayoutManager mLinearLayoutManager;

    @BindView(R.id.recycle_image)
    RecyclerView mRecyclerView;

    @BindView(R.id.prograss)
    ProgressBar mProgressBar;

    private static FolinkImagesFragment fragment;

    public static FolinkImagesFragment newInstance() {

        if (fragment == null)
            fragment = new FolinkImagesFragment();
        return fragment;
    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        View v = inflater.inflate(R.layout.fragment_folink_image_layout, container, false);
//        ButterKnife.inject(this, v);
//        initialViews();
//        return v;
//
//    }

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
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext() , DividerItemDecoration.HORIZONTAL_LIST));

//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mFolinkImageAdapter);
//        mRecyclerView.addOnScrollListener(loadingMoreListener);
    }

    private void initialData() {
        mFImagePresenter = new FImagePresenterImpl(this);
        mFolinkImageAdapter = new FolinkImageAdapter(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void updateImageList(ABList list) {

        mProgressBar.setVisibility(View.INVISIBLE);
        mFolinkImageAdapter.addNewsData(list.getAbNewsList());
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

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_folink_image_layout;
    }

    @Override
    protected void initView() {
        initialViews();
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        Log.e("abc", "======FolinkImage===="+isVisible);
//        if (isVisible) {
//            mFNewsPresenter.getFolinkNews("1");
//        }
    }
}
