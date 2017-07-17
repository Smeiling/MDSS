package com.mdss.smeiling.mdss.common.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

/**
 * Created by songmeiling on 2016/1/21.
 */
public class BatteryReceiver extends BroadcastReceiver {
    private Handler mHandler;

    public BatteryReceiver(Handler handler) {
        mHandler = handler;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int status = intent.getExtras().getInt("status");
        int current = intent.getExtras().getInt("level");//获得当前电量
        int total = intent.getExtras().getInt("scale");//获得总电量
        int percent = current * 100 / total;
        Message message = new Message();
        message.what = 0x0010;
        message.arg1 = percent;
        message.arg2 = status;
        mHandler.sendMessage(message);
    }
}
