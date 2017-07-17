package com.mdss.smeiling.mdss.common.thread;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by songmeiling on 2016/1/21.
 */
public class MemoryThread implements Runnable {

    private Context activity;
    private Handler mHandler;

    public MemoryThread(Context context,Handler handler) {
        activity = context;
        mHandler = handler;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            int memory = (int) getmem_UNUSED(activity);
            Message message = new Message();
            message.what = 0x1000;
            message.arg1 = memory;
            mHandler.sendMessage(message);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static long getmem_UNUSED(Context mContext) {
        long MEM_UNUSED;
        // 得到ActivityManager
        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        // 创建ActivityManager.MemoryInfo对象
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        // 取得剩余的内存空间
        MEM_UNUSED = mi.availMem / 1024;
        long mTotal;
        // /proc/meminfo读出的内核信息进行解释
        String path = "/proc/meminfo";
        String content = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path), 8);
            String line;
            if ((line = br.readLine()) != null) {
                content = line;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // beginIndex
        int begin = content.indexOf(':');
        // endIndex
        int end = content.indexOf('k');
        // 截取字符串信息
        content = content.substring(begin + 1, end).trim();
        mTotal = Integer.parseInt(content);
        return (mTotal - MEM_UNUSED) * 100 / mTotal;
    }
}
