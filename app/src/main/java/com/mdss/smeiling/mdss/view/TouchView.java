package com.mdss.smeiling.mdss.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by songmeiling on 2016/1/14.
 */
public class TouchView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mPaint;

    private float mX;
    private float mY;
    private boolean mIsDrawing;

    public TouchView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        mPath = new Path();
        mPath.moveTo(0, 0);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);
        mPaint.setAntiAlias(false);
        mPaint.setColor(Color.WHITE);
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
    }

    public void bgDraw() {
        try {
            mCanvas = mHolder.lockCanvas();
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setStrokeWidth(3);
            paint.setTextSize(80);
            String testString = "测试：ijkJQKA:1234";
            paint.setColor(Color.CYAN);
            mCanvas.drawText(testString, 50, 50, paint);
        } catch (Exception e) {
        } finally {
            if (mCanvas != null)
                mHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        bgDraw();
        while (mIsDrawing) {
            draw();
        }
        long end = System.currentTimeMillis();
        if (end - start < 100) {
            try {
                Thread.sleep(100 - (end - start));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            mCanvas.drawColor(Color.BLACK);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setStrokeWidth(3);
            paint.setTextSize(25);
            String testString = "Please move your finger on the screen.";
            paint.setColor(Color.WHITE);
            mCanvas.drawText(testString, 150, 550, paint);
            testString = "Press back or menu to choose pass fail.";
            mCanvas.drawText(testString, 148, 600, paint);
            mCanvas.drawPath(mPath, mPaint);
        } catch (Exception e) {
        } finally {
            if (mCanvas != null)
                mHolder.unlockCanvasAndPost(mCanvas);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        int x = (int) event.getX();
//        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //mPath.moveTo(x, y);
                touchDown(event);
                break;
            case MotionEvent.ACTION_MOVE:
                //mPath.lineTo(x, y);
                touchMove(event);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    private void touchDown(MotionEvent event) {
        //mPath.reset();
        float x = event.getX();
        float y = event.getY();
        mX = x;
        mY = y;

        mPath.moveTo(x, y);
    }

    private void touchMove(MotionEvent event) {
        final float x = event.getX();
        final float y = event.getY();

        final float previousX = mX;
        final float previousY = mY;

        final float dx = Math.abs(x - previousX);
        final float dy = Math.abs(y - previousY);

        if (dx >= 3 || dy >= 3) {
            float cX = (x + previousX) / 2;
            float cY = (y + previousY) / 2;

            mPath.quadTo(previousX, previousY, cX, cY);
            mX = x;
            mY = y;
        }
    }
}
