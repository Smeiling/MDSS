package com.mdss.smeiling.mdss.controller.testcase;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.mdss.smeiling.mdss.R;
import com.mdss.smeiling.mdss.common.base.TestingAct;
import com.mdss.smeiling.mdss.view.GsensorView;


public class GSensorTestingAct extends TestingAct implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private GsensorView gsensorView;

    @Override
    public void initTesting() {
        setContentView(R.layout.gsensor_test_act);
        gsensorView = (GsensorView) findViewById(R.id.GsensorView);

        mSensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener((SensorEventListener) this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
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
        mSensorManager.unregisterListener(this);
        mSensorManager = null;
        finish();
    }

    @Override
    public void saveResult() {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == null) {
            return;
        }

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            int x = (int) event.values[0];
            int y = (int) event.values[1];
            int z = (int) event.values[2];
//            if (BuildConfig.DEBUG) Log.d("MainActivity", "x:" + x);
//            if (BuildConfig.DEBUG) Log.d("MainActivity", "y:" + y);
//            if (BuildConfig.DEBUG) Log.d("MainActivity", "z:" + z);
            if (x >= 9) {
                gsensorView.setxPos(true);
                gsensorView.invalidate();
            }
            if (x <= -9) {
                gsensorView.setxNeg(true);
                gsensorView.invalidate();
            }
            if (y >= 9) {
                gsensorView.setyPos(true);
                gsensorView.invalidate();
            }
            if (y <= -9) {
                gsensorView.setyNeg(true);
                gsensorView.invalidate();
            }
            if (z >= 9) {
                gsensorView.setzPos(true);
                gsensorView.invalidate();
            }
            if (z <= -9) {
                gsensorView.setzNeg(true);
                gsensorView.invalidate();
            }
            if (gsensorView.isPass()) {
                finishTesting(true);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
