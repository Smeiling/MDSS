package com.mdss.smeiling.mdss.controller.testcase;

import android.graphics.Color;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mdss.smeiling.mdss.R;
import com.mdss.smeiling.mdss.common.base.TestingAct;


public class KeypadTestingAct extends TestingAct {
    private TextView hint;
    private TextView up;
    private TextView down;
    private ImageView pass_up;
    private ImageView Pass_down;
    private boolean up_passed = false;

    @Override
    public void initTesting() {
        setContentView(R.layout.keypad_test_act);
        up = (TextView) findViewById(R.id.but_up);
        down = (TextView) findViewById(R.id.but_down);
        hint = (TextView) findViewById(R.id.hint);
        pass_up = (ImageView) findViewById(R.id.pass_up);
        Pass_down = (ImageView) findViewById(R.id.pass_down);
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
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            up.setTextColor(Color.WHITE);
            pass_up.setVisibility(View.VISIBLE);
            hint.setText("Please press Volume Down Button.");
            up_passed = true;
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN | up_passed) {
            down.setTextColor(Color.WHITE);
            Pass_down.setVisibility(View.VISIBLE);
            finishTesting(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
