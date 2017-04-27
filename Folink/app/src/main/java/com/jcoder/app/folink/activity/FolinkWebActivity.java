package com.jcoder.app.folink.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jcoder.app.folink.R;
import com.jcoder.app.folink.model.FNews;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rory on 2016/10/14.
 */

public class FolinkWebActivity extends BaseActivity {

    public static String SERIALIZABLE_TAG = "F_NEWS_TAG";

    @BindView(R.id.iv_header_img)
    ImageView ivHeaderImg;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.tv_img_source)
    TextView tvImgSource;
    @BindView(R.id.wb_story_content)
    WebView wbNewsContent;
//    @InjectView(R.id.fab)
//    FloatingActionButton fab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_folink_web);
        ButterKnife.bind(this);
        FNews fNews = (FNews) getIntent().getSerializableExtra(SERIALIZABLE_TAG);
        if (fNews != null) {

            showNewsDetail(fNews);
        }
    }

    public void showNewsDetail(FNews newsDetail) {
        String htmlData = "<link rel=\"stylesheet\" type=\"text/css\" href=\"zhihu.css\" />" + newsDetail.getSource();
        wbNewsContent.loadDataWithBaseURL("file:///android_asset/", htmlData, "text/html", "utf-8", null);

        tvHeaderTitle.setText(newsDetail.getTitle());
        tvImgSource.setText(newsDetail.getSource());
        Glide.with(this).load(newsDetail.getImgsrc()).diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop().into(ivHeaderImg);
    }

}
