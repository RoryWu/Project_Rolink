package com.jcoder.app.folink.activity;

import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.jcoder.app.folink.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rory on 2016/10/14.
 */

public class FolinkSettings extends BaseActivity {

    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_folink_settings);

        ButterKnife.bind(this);

        initialViews();

    }

    private void initialViews() {

        mToolbarTitle.setText("Settings");


    }


}
