package com.jcoder.app.folink.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.jcoder.app.folink.R;
import com.jcoder.app.folink.widget.splash.ParticleView;

/**
 * Created by Rory on 16/9/30.
 */

public class FolinkGuide extends BaseActivity {

    ParticleView mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_folink_splash);

        mView = (ParticleView) findViewById(R.id.splash_anim);

        mView.startAnim();

        mView.setOnParticleAnimListener(new ParticleView.ParticleAnimListener() {
            @Override
            public void onAnimationEnd() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(FolinkHome.class);
                        FolinkGuide.this.finish();
                    }
                }, 3000);

            }
        });
    }

    @Override
    public void onBackPressed() {
        return ;
    }
}
