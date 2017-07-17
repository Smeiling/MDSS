package com.mdss.smeiling.mdss.controller.testcase;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.mdss.smeiling.mdss.common.base.TestingAct;

public class SIMcardTestingAct extends TestingAct {

    private TelephonyManager tm;

    @Override
    public void initTesting() {
        tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
    }

    @Override
    public void startTesting() {
        if (TelephonyManager.SIM_STATE_ABSENT == tm.getSimState()) {
            Toast.makeText(this, "NO USIM CARD", Toast.LENGTH_SHORT).show();
            finishTesting(false);
        } else {
            Bundle bundle = getSIMInfo();
            Log.d("MainActivity", bundle.getString("OpName"));
            finishTesting(true);
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

    private Bundle getSIMInfo() {
        Bundle bundle = new Bundle();
        String operator;
        operator = tm.getSimOperator();
        if (operator != null) {
            if (operator.equals("46000") || operator.equals("46002") || operator.equals("46007")) {//中国移动
                bundle.putString("OpName", "中国移动");
            } else if (operator.equals("46001")) {//中国联通
                bundle.putString("OpName", "中国联通");
            } else if (operator.equals("46003")) {//中国电信
                bundle.putString("OpName", "中国电信");
            }
        }
        return bundle;
    }
}
