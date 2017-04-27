package com.jcoder.app.folink.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.jcoder.app.folink.R;

/**
 * Created by Rory on 16/9/30.
 */

public class BaseActivity extends AppCompatActivity {


    public void startActivity(Class clz) {
        Intent intent = new Intent(this , clz);
        startActivity(intent);

    }

    public void startActivityWithAnim(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.start_anim,0);

    }

    public void startActivityWithAnim(Class clz) {
        Intent intent = new Intent(this , clz);
        startActivity(intent);
        overridePendingTransition(R.anim.start_anim,0);

    }

    public void finishSelf() {
        super.finish();
        overridePendingTransition(R.anim.finish_anim , 0 );
    }
}
