package com.mdss.smeiling.mdss.controller.testcase;

import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.mdss.smeiling.mdss.R;
import com.mdss.smeiling.mdss.common.base.TestingAct;


public class VibratorTestingAct extends TestingAct implements View.OnClickListener {

    private Switch vSwitch;
    private Vibrator vibrator;
    private long[] pattern;
    private Button passBtn;
    private Button failBtn;

    @Override
    public void initTesting() {
        setContentView(R.layout.vibrator_test_act);
        passBtn = (Button) findViewById(R.id.testPassBtn);
        failBtn = (Button) findViewById(R.id.testFailBtn);
        vSwitch = (Switch) findViewById(R.id.switch1);
        vSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    vibrator.vibrate(pattern, 0);
                } else {
                    vibrator.cancel();
                }
            }
        });
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        pattern = new long[]{100, 400, 100, 400};
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.testPassBtn:
                finishTesting(true);
                break;
            case R.id.testFailBtn:
                finishTesting(false);
                break;
        }
    }
}
