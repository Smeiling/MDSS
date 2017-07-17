package com.mdss.smeiling.mdss.common.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.mdss.smeiling.mdss.R;

/**
 * Created by songmeiling on 2016/1/19.
 */
public abstract class TestingAct extends Activity {

    private Animation animation = null;
    private ImageView loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initTesting();
        startTesting();
    }


    public abstract void initTesting();

    public abstract void startTesting();

    public abstract void finishTesting(Boolean testResult);

    public abstract void saveResult();

    private void initView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_testing);
        loading = (ImageView) findViewById(R.id.img_loading);
        startAnimation();
    }

    private void startAnimation() {
        animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
        loading.startAnimation(animation);
    }

}
