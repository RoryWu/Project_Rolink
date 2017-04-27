package com.jcoder.app.folink.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.jcoder.app.folink.R;
import com.jcoder.app.folink.activity.FolinkAboutActivity;
import com.jcoder.app.folink.activity.FolinkSettings;

import butterknife.BindView;

/**
 * Created by Rory on 2016/10/8.
 */

public class FolinkProfileFragment extends BaseFragment implements View.OnClickListener {

    private static FolinkProfileFragment mFragment;

    public static FolinkProfileFragment newInstance() {

        if (mFragment == null) {

            mFragment = new FolinkProfileFragment();
        }

        return mFragment;
    }


    @BindView(R.id.profile_settings_layout)
    LinearLayout mSettings;

    @BindView(R.id.profile_about_layout)
    LinearLayout mAbout;

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        View v = inflater.inflate(R.layout.fragment_folink_profile_layout, container, false);
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

        mSettings.setClickable(true);
        mAbout.setClickable(true);

        mSettings.setOnClickListener(this);
        mAbout.setOnClickListener(this);


    }

    private void initialData() {
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onClick(View view) {
        if (view == mSettings) {
            startActivity(FolinkSettings.class);
        } else if (view == mAbout) {
            startActivity(FolinkAboutActivity.class);
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_folink_profile_layout;
    }

    @Override
    protected void initView() {
        initialViews();
    }
}
