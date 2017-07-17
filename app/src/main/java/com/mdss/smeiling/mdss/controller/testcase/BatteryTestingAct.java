package com.mdss.smeiling.mdss.controller.testcase;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.mdss.smeiling.mdss.common.base.TestingAct;
import com.mdss.smeiling.mdss.common.receiver.BatteryReceiver;

public class BatteryTestingAct extends TestingAct {

    private static String TAG = "SMMI";

    private BatteryReceiver batteryReceiver;


    @Override
    public void initTesting() {
        batteryReceiver = new BatteryReceiver(myHandler);
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryReceiver, filter);
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
        unregisterReceiver(batteryReceiver);
        batteryReceiver = null;
        finish();
    }

    @Override
    public void saveResult() {

    }

    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x0010) {
                switch (msg.arg2) {
                    case BatteryManager.BATTERY_STATUS_UNKNOWN:
                        finishTesting(false);
                        break;
                    default:
                        if (msg.arg1 >= 0) {
                            Log.d(TAG, "Current battery level is " + String.valueOf(msg.arg1));
                            finishTesting(true);
                        }
                        break;
                }

            }
        }
    };
}
