package com.mdss.smeiling.mdss.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by songmeiling on 2016/1/13.
 */
public class GsensorView extends View {
    private boolean xPos;
    private boolean xNeg;
    private boolean yPos;
    private boolean yNeg;
    private boolean zPos;
    private boolean zNeg;
    private boolean pass;

    public GsensorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int heigh = getMeasuredHeight();
        int width = getMeasuredWidth();

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true); //去锯齿
        paint.setTextSize(40);

        //画坐标和文字
        paint.setColor(Color.RED);
        canvas.drawLine(100, heigh / 2, width - 100, heigh / 2, paint);
        canvas.drawText("X-", 70, heigh / 2 - 30, paint);
        canvas.drawText("X+", width - 90, heigh / 2 - 30, paint);

        paint.setColor(Color.YELLOW);
        canvas.drawLine(width / 2, heigh - 100, width / 2, 100, paint);
        canvas.drawText("Y-", width / 2 + 30, heigh - 90, paint);
        canvas.drawText("Y+", width / 2 + 30, 90, paint);

        paint.setColor(Color.BLUE);
        canvas.drawLine(width / 2, heigh / 2, 150, heigh - 150, paint);
        PathEffect effect = new DashPathEffect(new float[]{10, 10, 10, 10}, 1);
        paint.setPathEffect(effect);
        Path path = new Path();
        path.moveTo(width / 2, heigh / 2);
        path.lineTo(width - 150, 150);
        canvas.drawPath(path, paint);
        canvas.drawText("Z+", 200, heigh - 180, paint);
        canvas.drawText("Z-", width - 160, 220, paint);


        //画6个球
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(75, heigh / 2, 25, paint);
        canvas.drawCircle(width - 75, heigh / 2, 25, paint);
        canvas.drawCircle(width / 2, 75, 25, paint);
        canvas.drawCircle(width / 2, heigh - 75, 25, paint);
        canvas.drawCircle(150, heigh - 150, 25, paint);
        canvas.drawCircle(width - 150, 150, 25, paint);


        if (xPos) {
            paint.setColor(Color.RED);
            canvas.drawCircle(width - 75, heigh / 2, 25, paint);
        }
        if (xNeg) {
            paint.setColor(Color.RED);
            canvas.drawCircle(75, heigh / 2, 25, paint);
        }
        if (yPos) {
            paint.setColor(Color.YELLOW);
            canvas.drawCircle(width / 2, 75, 25, paint);
        }
        if (yNeg) {
            paint.setColor(Color.YELLOW);
            canvas.drawCircle(width / 2, heigh - 75, 25, paint);
        }
        if (zPos) {
            paint.setColor(Color.BLUE);
            canvas.drawCircle(150, heigh - 150, 25, paint);
        }
        if (zNeg) {
            paint.setColor(Color.BLUE);
            canvas.drawCircle(width - 150, 150, 25, paint);
        }
        if (xPos && xNeg && yPos && yNeg && zPos && zNeg) {
            pass = true;
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //view 需要的大小
        int width = 0, height = 0;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = 600;
            if (widthMode == MeasureSpec.AT_MOST) {
                width = Math.min(width, widthSize);
            }
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = 600;
            if (heightMode == MeasureSpec.AT_MOST) {
                height = Math.min(width, heightSize);
            }
        }
        setMeasuredDimension(width, height);
    }

    public void setxPos(boolean xPos) {
        this.xPos = xPos;
    }

    public void setxNeg(boolean xNeg) {
        this.xNeg = xNeg;
    }

    public void setyPos(boolean yPos) {
        this.yPos = yPos;
    }

    public void setyNeg(boolean yNeg) {
        this.yNeg = yNeg;
    }

    public void setzPos(boolean zPos) {
        this.zPos = zPos;
    }

    public void setzNeg(boolean zNeg) {
        this.zNeg = zNeg;
    }

    public boolean isPass() {
        return pass;
    }
}
