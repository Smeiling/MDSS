package com.mdss.smeiling.mdss.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by songmeiling on 2016/1/14.
 */
public class MultiTouchView extends SurfaceView implements SurfaceHolder.Callback {

    private static final int MAX_TOUCHPOINTS = 5;
    private Paint textPaint = new Paint();
    private Paint touchPaints[] = new Paint[MAX_TOUCHPOINTS];
    private int colors[] = new int[MAX_TOUCHPOINTS];
    private int width, height;
    private Context context;

    public MultiTouchView(Context context) {
        super(context);
        this.context = context;
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setFocusable(true); // 确保我们的View能获得输入焦点
        setFocusableInTouchMode(true); // 确保能接收到触屏事件
        init();
    }

    private void init() {
        textPaint.setColor(Color.WHITE);
        colors[0] = Color.BLUE;
        colors[1] = Color.RED;
        colors[2] = Color.GREEN;
        colors[3] = Color.YELLOW;
        colors[4] = Color.CYAN;
        for (int i = 0; i < MAX_TOUCHPOINTS; i++) {
            touchPaints[i] = new Paint();
            touchPaints[i].setColor(colors[i]);
        }
    }

    /*
     * 处理触屏事件
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointerCount = event.getPointerCount();
        if (pointerCount == MAX_TOUCHPOINTS) {
            Intent intent = new Intent();
            intent.setAction("com.example.multitouch.receiver");
            context.sendBroadcast(intent);
        }
        Canvas c = getHolder().lockCanvas();
        if (c != null) {
            c.drawColor(Color.BLACK);
            if (event.getAction() == MotionEvent.ACTION_UP) {
            } else {
                for (int i = 0; i < pointerCount; i++) {
                    int id = event.getPointerId(i);
                    int x = (int) event.getX(i);
                    int y = (int) event.getY(i);
                    drawCircle(x, y, touchPaints[id], c);
                }
            }
            getHolder().unlockCanvasAndPost(c);
        }
        return true;
    }

    /**
     * 画圆
     */
    private void drawCircle(int x, int y, Paint paint, Canvas c) {
        paint.setStyle(Paint.Style.STROKE);//空心
        paint.setStrokeWidth(10);
        c.drawCircle(x, y, 60, paint);//60-半径
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        this.width = width;
        this.height = height;
        textPaint.setTextSize(14);
        Canvas c = getHolder().lockCanvas();
        if (c != null) {
            c.drawColor(Color.BLACK);
            getHolder().unlockCanvasAndPost(c);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
