package com.mdss.smeiling.mdss.controller.testcase;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import com.mdss.smeiling.mdss.common.base.TestingAct;


public class ECompassTestingAct extends TestingAct implements SensorEventListener {

    private static String TAG = "SMMI";

    private SensorManager mSensorManager;

    @Override
    public void initTesting() {
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);
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
        if (Math.round(event.values[0]) != 0) {
            Log.d(TAG, "Getted ECompass data is " + String.valueOf(event.values[0]));
            finishTesting(true);
        } else {
            finishTesting(false);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
