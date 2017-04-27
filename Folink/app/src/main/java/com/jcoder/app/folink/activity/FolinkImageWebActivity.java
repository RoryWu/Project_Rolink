package com.jcoder.app.folink.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcoder.app.folink.R;
import com.jcoder.app.folink.model.AbNewsItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rory on 2016/10/14.
 */

public class FolinkImageWebActivity extends BaseActivity {

    public static String SERIALIZABLE_TAG = "F_IMAGE_TAG";

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
        AbNewsItem fNews = (AbNewsItem) getIntent().getSerializableExtra(SERIALIZABLE_TAG);
        if (fNews != null) {

            showNewsDetail(fNews);
        }
    }

    public void showNewsDetail(AbNewsItem newsDetail) {
        String htmlData = "<link rel=\"stylesheet\" type=\"text/css\" href=\"zhihu.css\" />" + newsDetail.getContent();
        wbNewsContent.loadDataWithBaseURL("file:///android_asset/", htmlData, "text/html", "utf-8", null);

        tvHeaderTitle.setText(newsDetail.getTitle());
        tvImgSource.setText(newsDetail.getContent());
//        Glide.with(this).load(newsDetail.getImgsrc()).diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .centerCrop().into(ivHeaderImg);
    }

}
