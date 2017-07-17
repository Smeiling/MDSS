package com.mdss.smeiling.mdss.common.base;

import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        setContentView();
        findViewById();
        initView();
    }

    public abstract void setContentView();

    public abstract void initData();

    public abstract void findViewById();

    public abstract void initView();

}
