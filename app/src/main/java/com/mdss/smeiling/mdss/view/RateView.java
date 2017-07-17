package com.mdss.smeiling.mdss.view;

import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdss.smeiling.mdss.R;
import com.mdss.smeiling.mdss.common.util.TextDrawable;


/**
 * Created by songmeiling on 2016/2/4.
 */
public class RateView extends LinearLayout {

    private ImageView cpu_b;
    private ImageView cpu_rate;
    private ImageView battery_b;
    private ImageView battery_rate;
    private TextDrawable textDrawable;
    private ClipDrawable clipDrawable;
    private TextView cpu;
    private TextView battery;
    private Context context;

    public RateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView(context);
        initData();
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.show_rate_layout, this);
        cpu_b = (ImageView) findViewById(R.id.cpu_bottom);
        cpu_rate = (ImageView) findViewById(R.id.cpu_rate);
        battery_b = (ImageView) findViewById(R.id.battery_bottom);
        battery_rate = (ImageView) findViewById(R.id.battery_rate);
        cpu = (TextView) findViewById(R.id.cpu_percent_mark);
        battery = (TextView) findViewById(R.id.battery_percent_mark);
    }

    private void initData() {

        textDrawable = new TextDrawable(context);
        textDrawable.setText("0");
        textDrawable.setTextColor(cpuBottomColor(0));
        textDrawable.setTextSize(90);
        cpu_b.setImageDrawable(textDrawable);

        textDrawable = new TextDrawable(context);
        textDrawable.setText("0");
        textDrawable.setTextColor(cpuRateColor(0));
        textDrawable.setTextSize(90);
        clipDrawable = new ClipDrawable(textDrawable, Gravity.BOTTOM, ClipDrawable.VERTICAL);
        cpu_rate.setImageDrawable(clipDrawable);
        cpu_rate.setImageLevel(0);

        cpu.setTextColor(cpuRateColor(0));

        textDrawable = new TextDrawable(context);
        textDrawable.setText("0");
        textDrawable.setTextColor(btrBottomColor(0));
        textDrawable.setTextSize(90);
        battery_b.setImageDrawable(textDrawable);

        textDrawable = new TextDrawable(context);
        textDrawable.setText("0");
        textDrawable.setTextColor(btrRateColor(0));
        textDrawable.setTextSize(90);
        clipDrawable = new ClipDrawable(textDrawable, Gravity.BOTTOM, ClipDrawable.VERTICAL);
        battery_rate.setImageDrawable(clipDrawable);
        battery_rate.setImageLevel(0);

        battery.setTextColor(btrRateColor(0));
    }

    private int parceLevel(int rate) {
        int level = 5000;
        if (rate > 50) {
            level = level + 32 * ((rate - 50) * 100 / 50);
        } else {
            level = level - 32 * ((50 - rate) * 100 / 50);
        }
        Log.d("RATE", String.valueOf(level));
        return level;
    }

    public void setBatteryData(int s) {

        String data = String.valueOf(s);

        textDrawable = new TextDrawable(context);
        textDrawable.setText(data);
        textDrawable.setTextColor(btrBottomColor(s));
        textDrawable.setTextSize(90);
        battery_b.setImageDrawable(textDrawable);

        textDrawable = new TextDrawable(context);

        textDrawable.setText(data);
        textDrawable.setTextColor(btrRateColor(s));
        textDrawable.setTextSize(90);
        clipDrawable = new ClipDrawable(textDrawable, Gravity.BOTTOM, ClipDrawable.VERTICAL);
        battery_rate.setImageDrawable(clipDrawable);
        battery_rate.setImageLevel(parceLevel(s));

        battery.setTextColor(btrRateColor(s));
    }

    public void setCpuData(int s) {
        String data = String.valueOf(s);
        textDrawable = new TextDrawable(context);
        textDrawable.setText(data);
        textDrawable.setTextColor(cpuBottomColor(s));
        textDrawable.setTextSize(90);
        cpu_b.setImageDrawable(textDrawable);

        textDrawable = new TextDrawable(context);
        textDrawable.setText(data);
        textDrawable.setTextColor(cpuRateColor(s));
        textDrawable.setTextSize(90);
        clipDrawable = new ClipDrawable(textDrawable, Gravity.BOTTOM, ClipDrawable.VERTICAL);
        cpu_rate.setImageDrawable(clipDrawable);
        cpu_rate.setImageLevel(parceLevel(s));

        cpu.setTextColor(cpuRateColor(s));
    }

    private int cpuBottomColor(int s) {
        if (s < 50) {
            return 0x330DAAEB;
        } else if (s < 90) {
            return 0x33512DA8;
        } else {
            return 0x33D32F2F;
        }
    }

    private int cpuRateColor(int s) {
        if (s < 50) {
            return 0xff0DAAEB;
        } else if (s < 90) {
            return 0xff512DA8;
        } else {
            return 0xffD32F2F;
        }
    }

    private int btrBottomColor(int s) {
        if (s < 50) {
            return 0x33d32f2f;
        } else if (s < 90) {
            return 0x33FFC107;
        } else {
            return 0x338BC34A;
        }
    }

    private int btrRateColor(int s) {
        if (s < 50) {
            return 0xFFd32f2f;
        } else if (s < 90) {
            return 0xFFFFC107;
        } else {
            return 0xFF8BC34A;
        }
    }
}

