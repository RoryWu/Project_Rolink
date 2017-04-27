package com.jcoder.app.folink.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gigamole.navigationtabbar.ntb.NavigationTabBar;
import com.jcoder.app.folink.R;
import com.jcoder.app.folink.adapter.MainFragmentAdapter;
import com.jcoder.app.folink.api.FolinkApiManager;
import com.jcoder.app.folink.fragment.FolinkNewsFragment;
import com.jcoder.app.folink.model.NewsList;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class FolinkHome extends AppCompatActivity {

    private FragmentManager mFragmentManager;
    private Fragment currentFragment;

    @BindView(R.id.vp_horizontal_ntb)
    ViewPager mViewPager;

    @BindView(R.id.ntb_horizontal)
    NavigationTabBar navigationTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_folink_home);
        ButterKnife.bind(this);

        mFragmentManager = getSupportFragmentManager();

        initViews();
    }

    private void initViews() {
        initToolsBar();
        if (mViewPager == null) {
            Log.e("Runtime", "--------viewPager is null--------");
        }else {
            mViewPager.setAdapter(new MainFragmentAdapter(mFragmentManager));
            initNavigationBar();
        }
    }

    private void initToolsBar() {

    }

    private void initNavigationBar() {

        final String[] colors = getResources().getStringArray(R.array.default_preview);

        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_first),
                        Color.parseColor(colors[0]))
                        .title("News")
                        .badgeTitle("NTB")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_second),
                        Color.parseColor(colors[1]))
                        .title("Image")
                        .badgeTitle("with")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_third),
                        Color.parseColor(colors[2]))
                        .title("Profile")
                        .badgeTitle("state")
                        .build()
        );

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(mViewPager, 0);
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                navigationTabBar.getModels().get(position).hideBadge();

            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

//        navigationTabBar.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
//                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
//                    navigationTabBar.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            model.showBadge();
//                        }
//                    }, i * 100);
//                }
//            }
//        }, 500);

    }

    private void addFragment() {
        Fragment fragment = new FolinkNewsFragment();
        switchFragment(fragment, "");

    }

    private void switchFragment(Fragment fragment, String title) {

//        if (currentFragment == null || !currentFragment.getClass().getName().equals(fragment.getClass().getName()))
//            mFragmentManager.beginTransaction().replace(R.id.fragment_content_layout, fragment)
//                    .commit();
//        currentFragment = fragment;

    }

    private void getFNewsData() {
        Subscription subscription = FolinkApiManager.getmApiManager().getFolinkNewsService().getNews(0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("jjj", "22222");
                    }

                    @Override
                    public void onNext(NewsList list) {

                        Log.e("jjj", "111111" + list.getNewsList().get(0).getTitle());
                    }
                });
        CompositeSubscription compositeSubscription = new CompositeSubscription();
        compositeSubscription.add(subscription);
    }
}
