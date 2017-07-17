package com.mdss.smeiling.mdss.controller.testcase;

import android.content.ComponentName;
import android.content.Context;
import android.media.AudioManager;
import android.view.KeyEvent;

import com.mdss.smeiling.mdss.common.base.TestingAct;
import com.mdss.smeiling.mdss.common.receiver.MediaButtonReceiver;

public class HeadsetKeyTestingAct extends TestingAct {

    private static String TAG = "SMMI";
    private AudioManager audioManager;
    private ComponentName componentName;

    @Override
    public void initTesting() {
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
    }

    @Override
    public void startTesting() {
        if (!checkHeadset()) {
            finishTesting(false);
        }
    }

    @Override
    public void finishTesting(Boolean testResult) {
        if (testResult == true) {
            setResult(RESULT_OK);
        } else {
            setResult(RESULT_CANCELED);
        }
        finish();
    }

    @Override
    public void saveResult() {

    }

    private boolean checkHeadset() {
        if (audioManager.isWiredHeadsetOn()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_HEADSETHOOK) {
            finishTesting(true);
        }
        return false;
    }

    private void headsetKeyTest() {
        componentName = new ComponentName(getPackageName(), MediaButtonReceiver.class.getName());
        audioManager.registerMediaButtonEventReceiver(componentName);
    }
}
