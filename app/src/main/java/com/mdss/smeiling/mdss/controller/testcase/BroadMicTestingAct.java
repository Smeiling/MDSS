package com.mdss.smeiling.mdss.controller.testcase;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import com.mdss.smeiling.mdss.common.base.TestingAct;

import java.io.IOException;

public class BroadMicTestingAct extends TestingAct implements MediaPlayer.OnCompletionListener {
    private static String TAG = "SMMI";
    private AudioManager audioManager;
    private MediaPlayer mp;
    private MediaRecorder mRecorder = null;
    private String fileName = null;

    @Override
    public void initTesting() {
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        fileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        fileName += "/audiorecordtest.3gp";
    }

    @Override
    public void startTesting() {
        if (checkHeadset()) {
            finishTesting(false);
        } else {
            startRecording();
        }
    }

    @Override
    public void finishTesting(Boolean testResult) {
        if (testResult == true) {
            setResult(RESULT_OK);
        } else {
            setResult(RESULT_CANCELED);
        }
        mRecorder = null;
        mp = null;
        audioManager = null;
        finish();
    }

    private boolean checkHeadset() {
        if (audioManager.isWiredHeadsetOn()) {
            return true;
        }
        return false;
    }

    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(fileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            mRecorder.prepare();
            mRecorder.start();
            Log.d(TAG, "startRecord");
            Thread.sleep(5000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (mRecorder != null) {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
        }
        Log.d(TAG, "stopRecord");
        mp = new MediaPlayer();
        mp.setOnCompletionListener(this);
        try {
            mp.setDataSource(fileName);
            mp.prepare();
            mp.start();
            while (mp.isPlaying()) {
                Log.d(TAG, AudioManager.MODE_NORMAL + "MediaPlayer.isPlaying()");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        mp.release();
//        mp = null;
//        Log.d(TAG, "stopPlaying");
    }

    @Override
    public void saveResult() {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mp.stop();
        mp.release();
        finishTesting(true);
    }
}
