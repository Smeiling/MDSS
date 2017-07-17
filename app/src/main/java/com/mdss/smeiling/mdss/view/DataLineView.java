package com.mdss.smeiling.mdss.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songmeiling on 2016/1/20.
 */
public class DataLineView extends View {
    private int XPoint = 60;  //原点坐标
    private int YPoint = 220;
    private int XScale = 10;  //显示精度
    private int YScale = 2;
    private int XLength = 600;//轴线长度
    private int YLength = 200;
    private int drawData = 0; //数据缓存

    private int MaxDataSize = XLength / XScale;//最多显示数据量

    private List<Integer> data = new ArrayList<Integer>();


    private String[] YLabel = new String[3];
    private String[] XLabel = new String[4];

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0x1234) {
                DataLineView.this.invalidate();//重新绘制
            }
        }

        ;
    };

    public DataLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //填充Y轴文字
        for (int i = 0; i < YLabel.length; i++) {
            YLabel[i] = (i * 50) + "%";
        }
        //填充X轴文字
        for (int i = 0; i < XLabel.length; i++) {
            XLabel[i] = (i * 20) + "s";
        }
        //每1S添加一个数字
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (data.size() > MaxDataSize) {
                        data.remove(0);
                    }
                    data.add(drawData);
                    handler.sendEmptyMessage(0x1234);
                }
            }
        }).start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true); //去锯齿
        paint.setColor(0xcccccccc);
        paint.setTextSize(20);

        //画Y轴
        canvas.drawLine(XPoint, YPoint - YLength, XPoint, YPoint, paint);
        //添加Y刻度和文字
        for (int i = 0; i * 50 * YScale <= YLength; i++) {
            canvas.drawLine(XPoint, YPoint - i * 50 * YScale, XPoint + XLength, YPoint - i * 50 * YScale, paint);  //Y刻度
            canvas.drawText(YLabel[i], XPoint - 50, YPoint - i * 50 * YScale + 5, paint);//Y文字
        }

        //画X轴
        canvas.drawLine(XPoint, YPoint, XPoint + XLength, YPoint, paint);
        //添加X刻度和文件
        for (int i = 0; i * 20 * XScale <= XLength; i++) {
            canvas.drawLine(XPoint + i * 20 * XScale, YPoint, XPoint + i * 20 * XScale, YPoint - YLength, paint);  //X刻度
            canvas.drawText(XLabel[i], XPoint + i * 20 * XScale - 10, YPoint + 20, paint);//X文字
        }

        paint.setStrokeWidth(3);
        paint.setColor(0xff0DAAEB);
        //画折线
        if (data.size() > 1) {
            for (int i = 1; i < data.size(); i++) {
                canvas.drawLine(XPoint + (i - 1) * XScale, YPoint - data.get(data.size() - i) * YScale,
                        XPoint + i * XScale, YPoint - data.get(data.size() - 1 - i) * YScale, paint);
            }
        }
    }

    public void getDate(int data) {
        drawData = data;
    }
}
