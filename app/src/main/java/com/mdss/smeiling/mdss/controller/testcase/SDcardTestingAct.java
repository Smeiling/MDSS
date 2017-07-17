package com.mdss.smeiling.mdss.controller.testcase;

import android.util.Log;

import com.mdss.smeiling.mdss.common.base.TestingAct;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SDcardTestingAct extends TestingAct {

    @Override
    public void initTesting() {

    }

    @Override
    public void startTesting() {
        getSDInfo();
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

    private void getSDInfo() {
        List<String> extPaths = getExtSDCardPath();
        if (!extPaths.isEmpty()) {
            for (String path : extPaths) {
                Log.d("MainActivity", "外置SD卡路径：" + path + "\r\n");
            }
            finishTesting(true);
        } else {
            Log.d("MainActivity", "外置SD卡为空");
            finishTesting(false);
        }
    }

    public List<String> getExtSDCardPath() {
        List<String> lResult = new ArrayList<String>();
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec("mount");
            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("extSdCard")) {
                    String[] arr = line.split(" ");
                    String path = arr[1];
                    File file = new File(path);
                    if (file.isDirectory()) {
                        lResult.add(path);
                    }
                }
            }
            isr.close();
        } catch (Exception e) {
        }
        return lResult;
    }

}
