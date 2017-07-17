package com.mdss.smeiling.mdss.controller.testcase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Environment;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.mdss.smeiling.mdss.R;
import com.mdss.smeiling.mdss.common.base.TestingAct;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainCamTestingAct extends TestingAct implements View.OnClickListener {

    private View layout;
    private Camera camera;
    private Camera.Parameters parameters = null;
    private WindowManager mWindowManager;
    private String savePath = "/cam_test/";
    private Button capture;
    private ImageView iv_showPhoto;
    private SurfaceView surfaceView;

    @Override
    public void initTesting() {
        setContentView(R.layout.camera_test_act);
        capture = (Button) findViewById(R.id.takepicture);
        capture.setOnClickListener(this);
        iv_showPhoto = (ImageView) findViewById(R.id.imageView);
        layout = findViewById(R.id.buttonLayout);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceView.getHolder().setFixedSize(240, 220);//设置surface分辨率
        surfaceView.getHolder().setKeepScreenOn(true);//屏幕常量
        surfaceView.getHolder().addCallback(new SurfaceCallback());//为SurfaceView添加一个回调函数
    }

    @Override
    public void startTesting() {

    }

    @Override
    public void finishTesting(Boolean testResult) {

    }

    @Override
    public void saveResult() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.takepicture) {
            camera.takePicture(null, null, new MyPictureCallback());
        }
    }

    private class SurfaceCallback implements SurfaceHolder.Callback {

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {
            parameters = camera.getParameters(); // 获取各项参数
            parameters.setPictureFormat(PixelFormat.JPEG); // 设置图片格式
            parameters.setPreviewSize(width, height); // 设置预览大小
            parameters.setPreviewFrameRate(5);    //设置每秒显示4帧
            parameters.setPictureSize(width, height); // 设置保存的图片尺寸
            parameters.setJpegQuality(80); // 设置照片质量
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                //camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT); // 打开前置摄像头
                camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK); // 打开后置摄像头
                camera.setPreviewDisplay(holder); // 设置用于显示拍照影像的SurfaceHolder对象
                camera.setDisplayOrientation(getPreviewDegree(MainCamTestingAct.this));
                camera.startPreview(); // 开始预览
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if (camera != null) {
                camera.release(); // 释放照相机
                camera = null;
            }
        }
    }

    private class MyPictureCallback implements Camera.PictureCallback {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            try {
                saveToSDCard(data);//保存到SD卡中
                camera.release();

                surfaceView.setVisibility(View.GONE);
                iv_showPhoto.setImageBitmap(byteToBitmap(data));
                layout.setVisibility(View.GONE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveToSDCard(byte[] data) throws IOException {
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Bitmap bitmap = byteToBitmap(data);
        String filename = "mainCamTest.jpg";
        File fileFolder = new File(Environment.getExternalStorageDirectory() + savePath);
        if (!fileFolder.exists()) {
            fileFolder.mkdir();
        }

        File jpgFile = new File(fileFolder, filename);
        FileOutputStream outputStream = new FileOutputStream(jpgFile);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        outputStream.flush();
        outputStream.close();
        Intent intent = new Intent();
        intent.putExtra("path", Environment.getExternalStorageDirectory() + savePath);
        setResult(1, intent);
    }

    private Bitmap byteToBitmap(byte[] data) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap b;
        int i = 0;
        while (true) {
            if ((options.outWidth >> i <= 1000) && (options.outHeight >> i <= 1000)) {
                options.inSampleSize = (int) Math.pow(2.0d, i);
                options.inJustDecodeBounds = false;
                b = BitmapFactory.decodeByteArray(data, 0, data.length, options);
                break;
            }
            i += 1;
        }
        return b;
    }

    // 提供一个静态方法，用于根据手机方向获得相机预览画面旋转的角度
    public static int getPreviewDegree(Activity activity) {
        // 获得手机的方向
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        int degree = 0;
        // 根据手机的方向计算相机预览画面应该选择的角度
        switch (rotation) {
            case Surface.ROTATION_0:
                degree = 90;
                break;
            case Surface.ROTATION_90:
                degree = 0;
                break;
            case Surface.ROTATION_180:
                degree = 270;
                break;
            case Surface.ROTATION_270:
                degree = 180;
                break;
        }
        return degree;
    }

}
