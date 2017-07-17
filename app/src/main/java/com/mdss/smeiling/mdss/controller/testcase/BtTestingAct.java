package com.mdss.smeiling.mdss.controller.testcase;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.mdss.smeiling.mdss.common.base.TestingAct;

public class BtTestingAct extends TestingAct {

    private static String TAG = "SMMI";

    private BluetoothAdapter bluetoothAdapter;
    private IntentFilter intentFilter;

    @Override
    public void initTesting() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter(); //得到bluetoothAdapter对象
        intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        registerReceiver(btBroadcastReceiver, intentFilter);
    }

    @Override
    public void startTesting() {
        //若bluetoothAdapter为空，表明当前设备没有蓝牙设备
        if (bluetoothAdapter != null) {
            Log.d("MainActivity", "There's bluetooth in the device.");
            //判断蓝牙是否可用
            if (!bluetoothAdapter.isEnabled()) {
                bluetoothAdapter.enable(); //打开蓝牙
            } else {
                bluetoothAdapter.disable();
            }
        }
    }

    @Override
    public void finishTesting(Boolean testResult) {
        if (testResult == true) {
            setResult(RESULT_OK);
        } else {
            setResult(RESULT_CANCELED);
        }
        unregisterReceiver(btBroadcastReceiver);
        bluetoothAdapter.disable();
        bluetoothAdapter = null;
        finish();
    }

    @Override
    public void saveResult() {

    }

    BroadcastReceiver btBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                switch (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.STATE_OFF)) {
                    case BluetoothAdapter.STATE_ON:
                        Log.d(TAG, "bluetooth state on");
                        finishTesting(true);
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Log.d(TAG, "bluetooth turning on");
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Log.d(TAG, "bluetooth turning off");
                        break;
                    case BluetoothAdapter.STATE_OFF:
                        Log.d(TAG, "bluetooth state off");
                        bluetoothAdapter.enable();
                        break;
                    default:
                        break;
                }
            }
        }
    };

}
