package com.mdss.smeiling.mdss.controller.testcase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.mdss.smeiling.mdss.common.base.TestingAct;

/**
 * Created by songmeiling on 2016/1/20.
 */
public class WifiTestingAct extends TestingAct {

    private static String TAG = "SMMI";

    private WifiManager wifiManager;
    private IntentFilter intentFilter;
    private Boolean wifiSwitch;
    private Boolean searchResult;

    @Override
    public void initTesting() {
        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(wifiBroadcastReceiver, intentFilter);
    }

    @Override
    public void startTesting() {
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        } else {
            wifiManager.setWifiEnabled(false);
        }
    }

    @Override
    public void finishTesting(Boolean testResult) {
        wifiClose();
        if (testResult == true) {
            setResult(RESULT_OK);
        } else {
            setResult(RESULT_CANCELED);
        }
        unregisterReceiver(wifiBroadcastReceiver);
        wifiManager.setWifiEnabled(false);
        wifiManager = null;
        finish();
    }

    @Override
    public void saveResult() {

    }


    private void wifiClose() {
        if (wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(false);
        }
    }

    BroadcastReceiver wifiBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            final String action = intent.getAction();
            if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(action)) {
                int st = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);
                switch (st) {
                    case WifiManager.WIFI_STATE_ENABLED:
                        Log.d(TAG, "wifi enabled");
                        finishTesting(true);
                        break;
                    case WifiManager.WIFI_STATE_ENABLING:
                        Log.d(TAG, "wifi enabling");
                        break;
                    case WifiManager.WIFI_STATE_DISABLED:
                        Log.d(TAG, "wifi disabled");
                        wifiManager.setWifiEnabled(true);
                        break;
                    case WifiManager.WIFI_STATE_DISABLING:
                        Log.d(TAG, "wifi disabling");
                        break;
                    default:
                        break;
                }
            }
//                if (st == WifiManager.WIFI_STATE_ENABLED) {
//                    wifiSwitch = true;
//                    Log.d("MainActivity", "wifiSwitch=true");
//                }
//            } else if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(action)) {
//                List<ScanResult> results = wifiManager.getScanResults();
//                if (results.size() > 0 && wifiSwitch == true) {
//                    searchResult = true;
//                    Log.d("MainActivity", "得到扫描结果" + wifiManager.getScanResults().size());
//                    Log.d("MainActivity", "searchResult=true");
//                    finishTesting(true);
//                }
//            }
        }

    };
}
