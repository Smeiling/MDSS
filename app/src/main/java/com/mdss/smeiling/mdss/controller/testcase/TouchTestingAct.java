package com.mdss.smeiling.mdss.controller.testcase;

import android.view.KeyEvent;

import com.mdss.smeiling.mdss.common.base.TestingAct;
import com.mdss.smeiling.mdss.view.TouchView;


public class TouchTestingAct extends TestingAct {

    @Override
    public void initTesting() {
        setContentView(new TouchView(this));
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
        finish();
    }

    @Override
    public void saveResult() {

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finishTesting(true);
        }
        return false;
    }

}
