package com.mdss.smeiling.mdss.common.thread;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by songmeiling on 2016/1/21.
 */
public class CPUThread implements Runnable {
    private Handler mHandler;

    public CPUThread(Handler handler) {
        mHandler = handler;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = new Message();
            message.what = 0x0100;
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            message.arg1 = (int) getProcessCpuRate();
            mHandler.sendMessage(message);

        }
    }

    //计算CPU的使用率
    private float getProcessCpuRate() {
        float totalCpuTime1 = getTotalCpuRate();
        float processCpuTime1 = getUseCpuRate();
        try {
            Thread.sleep(360);
        } catch (Exception ignored) {
        }
        float totalCpuTime2 = getTotalCpuRate();
        float processCpuTime2 = getUseCpuRate();

        float cpuRate = 100 * (processCpuTime2 - processCpuTime1)
                / (totalCpuTime2 - totalCpuTime1);

        return Math.abs(cpuRate);
    }

    // 获取系统总CPU使用时间
    public static long getTotalCpuRate() {
        String[] cpuInfos = null;
        long cpuRate = 0;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream("/proc/stat")), 1000);
            String load = reader.readLine();
            reader.close();
            cpuInfos = load.split(" ");
            long totalCpu = Long.parseLong(cpuInfos[2]) +
                    Long.parseLong(cpuInfos[3]) + Long.parseLong(cpuInfos[4]) +
                    Long.parseLong(cpuInfos[5]) + Long.parseLong(cpuInfos[6]) +
                    Long.parseLong(cpuInfos[7]) + Long.parseLong(cpuInfos[8]);
            return totalCpu;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return cpuRate;
    }

    //获取CPU用时
    public static long getUseCpuRate() {
        String[] cpuInfos = null;
        long cpuRate = 0;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream("/proc/stat")), 1000);
            String load = reader.readLine();
            reader.close();
            cpuInfos = load.split(" ");
            long useCpu = Long.parseLong(cpuInfos[2]) +
                    Long.parseLong(cpuInfos[3]) + Long.parseLong(cpuInfos[4]) +
                    Long.parseLong(cpuInfos[6]) + Long.parseLong(cpuInfos[7]) +
                    Long.parseLong(cpuInfos[8]);
            return useCpu;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return cpuRate;
    }
}
