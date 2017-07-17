package com.mdss.smeiling.mdss.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mdss.smeiling.mdss.R;

/**
 * Created by songmeiling on 2016/1/23.
 */
public class CPUView extends LinearLayout {
    RelativeLayout csin, cdou, ctri, bsin, bdou, btri;
    TextView ct1, ct2, ct3, cb1, cb2, cb3,
            bt1, bt2, bt3, bb1, bb2, bb3;
    TextView cpu_mark, battery_mark;


    public CPUView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        showcpu(0);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.display_cpu, this);
        csin = (RelativeLayout) findViewById(R.id.csin);
        cdou = (RelativeLayout) findViewById(R.id.cdou);
        ctri = (RelativeLayout) findViewById(R.id.ctri);
        ct1 = (TextView) findViewById(R.id.ctop1);
        cb1 = (TextView) findViewById(R.id.cbottom1);
        ct2 = (TextView) findViewById(R.id.ctop2);
        cb2 = (TextView) findViewById(R.id.cbottom2);
        ct3 = (TextView) findViewById(R.id.ctop3);
        cb3 = (TextView) findViewById(R.id.cbottom3);
        bsin = (RelativeLayout) findViewById(R.id.bsin);
        bdou = (RelativeLayout) findViewById(R.id.bdou);
        btri = (RelativeLayout) findViewById(R.id.btri);
        bt1 = (TextView) findViewById(R.id.btop1);
        bb1 = (TextView) findViewById(R.id.bbottom1);
        bt2 = (TextView) findViewById(R.id.btop2);
        bb2 = (TextView) findViewById(R.id.bbottom2);
        bt3 = (TextView) findViewById(R.id.btop3);
        bb3 = (TextView) findViewById(R.id.bbottom3);
        cpu_mark = (TextView) findViewById(R.id.cpu_percent_mark);
        battery_mark = (TextView) findViewById(R.id.battery_percent_mark);
    }

    //电量处理
    public void showBattery(int percent) {
        String num = percent + "";
        char[] arr = num.toCharArray();
        if (percent > 20) {
            bt1.setTextColor(0x3350820F);
            bt2.setTextColor(0x3350820F);
            bt3.setTextColor(0x3350820F);
            bb1.setTextColor(0xff50820F);
            bb2.setTextColor(0xff50820F);
            bb3.setTextColor(0xff50820F);
            battery_mark.setTextColor(0xff50820F);
        } else {
            bt1.setTextColor(0x33ff0000);
            bt2.setTextColor(0x33ff0000);
            bt3.setTextColor(0x33ff0000);
            bb1.setTextColor(0xffff0000);
            bb2.setTextColor(0xffff0000);
            bb3.setTextColor(0xffff0000);
            battery_mark.setTextColor(0xffff0000);
        }
        drawNumber(arr.length, arr, percent, true);
    }

    //CPU处理
    public void showcpu(int mCpuRate) {
        String num = mCpuRate + "";
        char[] arr = num.toCharArray();
        if (mCpuRate > 50) {
            ct1.setTextColor(0x33ffa500);
            ct2.setTextColor(0x33ffa500);
            ct3.setTextColor(0x33ffa500);
            cb1.setTextColor(0xffffa500);
            cb2.setTextColor(0xffffa500);
            cb3.setTextColor(0xffffa500);
            cpu_mark.setTextColor(0xffffa500);
        } else {
            ct1.setTextColor(0x330DAAEB);
            ct2.setTextColor(0x330DAAEB);
            ct3.setTextColor(0x330DAAEB);
            cb1.setTextColor(0xff0DAAEB);
            cb2.setTextColor(0xff0DAAEB);
            cb3.setTextColor(0xff6699ff);
            cpu_mark.setTextColor(0xff6699ff);
        }
        drawNumber(arr.length, arr, mCpuRate, false);
    }

    //绘制数字
    public void drawNumber(int numberType, char[] arr, int height, boolean CORB) {
        if (CORB) {
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) bb1.getLayoutParams();
            switch (numberType) {
                case 1:
                    bsin.setVisibility(View.VISIBLE);
                    bdou.setVisibility(View.GONE);
                    btri.setVisibility(View.GONE);
                    bt1.setText(String.valueOf(arr[0]));
                    bb1.setText(String.valueOf(arr[0]));
                    lp.height = 47 + 133 * height / 100;
                    bb1.setLayoutParams(lp);

                    break;
                case 2:
                    bsin.setVisibility(View.VISIBLE);
                    bdou.setVisibility(View.VISIBLE);
                    btri.setVisibility(View.GONE);
                    bt1.setText(String.valueOf(arr[0]));
                    bb1.setText(String.valueOf(arr[0]));
                    bt2.setText(String.valueOf(arr[1]));
                    bb2.setText(String.valueOf(arr[1]));
                    lp.height = 47 + 133 * height / 100;
                    bb1.setLayoutParams(lp);
                    bb2.setLayoutParams(lp);
                    break;
                case 3:
                    bsin.setVisibility(View.VISIBLE);
                    bdou.setVisibility(View.VISIBLE);
                    btri.setVisibility(View.VISIBLE);
                    bt1.setText(String.valueOf(arr[0]));
                    bb1.setText(String.valueOf(arr[0]));
                    bt2.setText(String.valueOf(arr[1]));
                    bb2.setText(String.valueOf(arr[1]));
                    bt3.setText(String.valueOf(arr[2]));
                    bb3.setText(String.valueOf(arr[2]));
                    lp.height = 180;
                    bb1.setLayoutParams(lp);
                    bb2.setLayoutParams(lp);
                    bb3.setLayoutParams(lp);
                    break;
                default:
                    break;
            }
        } else {
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) cb1.getLayoutParams();
            switch (numberType) {
                case 1:
                    csin.setVisibility(View.VISIBLE);
                    cdou.setVisibility(View.GONE);
                    ctri.setVisibility(View.GONE);
                    ct1.setText(String.valueOf(arr[0]));
                    cb1.setText(String.valueOf(arr[0]));
                    lp.height = 47 + 133 * height / 100;
                    cb1.setLayoutParams(lp);
                    break;
                case 2:
                    csin.setVisibility(View.VISIBLE);
                    cdou.setVisibility(View.VISIBLE);
                    ctri.setVisibility(View.GONE);
                    ct1.setText(String.valueOf(arr[0]));
                    cb1.setText(String.valueOf(arr[0]));
                    ct2.setText(String.valueOf(arr[1]));
                    cb2.setText(String.valueOf(arr[1]));
                    lp.height = 47 + 133 * height / 100;
                    cb1.setLayoutParams(lp);
                    cb2.setLayoutParams(lp);
                    break;
                case 3:
                    csin.setVisibility(View.VISIBLE);
                    cdou.setVisibility(View.VISIBLE);
                    ctri.setVisibility(View.VISIBLE);
                    ct1.setText(String.valueOf(arr[0]));
                    cb1.setText(String.valueOf(arr[0]));
                    ct2.setText(String.valueOf(arr[1]));
                    cb2.setText(String.valueOf(arr[1]));
                    ct3.setText(String.valueOf(arr[2]));
                    cb3.setText(String.valueOf(arr[2]));
                    lp.height = 180;
                    cb1.setLayoutParams(lp);
                    cb2.setLayoutParams(lp);
                    cb3.setLayoutParams(lp);
                    break;
                default:
                    break;
            }
        }
    }

}
