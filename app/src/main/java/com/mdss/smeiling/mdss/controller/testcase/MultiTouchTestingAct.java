package com.mdss.smeiling.mdss.controller.testcase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.mdss.smeiling.mdss.common.base.TestingAct;
import com.mdss.smeiling.mdss.view.MultiTouchView;

public class MultiTouchTestingAct extends TestingAct {
    private static String TAG = "SMMI";
    private IntentFilter intentFilter;

    @Override
    public void initTesting() {
        setContentView(new MultiTouchView(this));
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.multitouch.receiver");
        registerReceiver(multiTouchBroadcastReceiver, intentFilter);
    }

    @Override
    public void startTesting() {

    }

    @Override
    public void finishTesting(Boolean testResult) {
        if (testResult == true) {
            setResult(RESULT_OK);
        } else {
            setResult(RESULT_CANCELED);
        }
        unregisterReceiver(multiTouchBroadcastReceiver);
        finish();
    }

    @Override
    public void saveResult() {

    }

    BroadcastReceiver multiTouchBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "multiTouch successd");
            finishTesting(true);
        }
    };
}
