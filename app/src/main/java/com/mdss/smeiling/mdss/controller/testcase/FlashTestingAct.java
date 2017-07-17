package com.mdss.smeiling.mdss.controller.testcase;

import android.hardware.Camera;
import android.view.View;
import android.widget.Button;

import com.mdss.smeiling.mdss.R;
import com.mdss.smeiling.mdss.common.base.TestingAct;

public class FlashTestingAct extends TestingAct implements View.OnClickListener {

    private Camera camera = null;

    private Button passBtn;
    private Button failBtn;

    @Override
    public void initTesting() {
        setContentView(R.layout.flash_test_act);
        passBtn = (Button) findViewById(R.id.testPassBtn);
        failBtn = (Button) findViewById(R.id.testFailBtn);
        passBtn.setOnClickListener(this);
        failBtn.setOnClickListener(this);
        camera = Camera.open();
        Camera.Parameters localParameters = camera.getParameters();
        localParameters.setFlashMode("torch");
        camera.setParameters(localParameters);
    }

    @Override
    public void startTesting() {
        camera.startPreview();
    }

    @Override
    public void finishTesting(Boolean testResult) {
        if (testResult == true) {
            setResult(RESULT_OK);
        } else {
            setResult(RESULT_CANCELED);
        }
        if (camera != null) {
            Camera.Parameters localParameters = this.camera.getParameters();
            localParameters.setFlashMode("off");
            this.camera.setParameters(localParameters);
            this.camera.stopPreview();
            this.camera.release();
            this.camera = null;
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
            default:
                break;
        }
    }
}
