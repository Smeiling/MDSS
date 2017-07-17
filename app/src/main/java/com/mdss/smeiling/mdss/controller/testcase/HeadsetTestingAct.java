package com.mdss.smeiling.mdss.controller.testcase;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import com.mdss.smeiling.mdss.R;
import com.mdss.smeiling.mdss.common.base.TestingAct;

import java.io.IOException;

public class HeadsetTestingAct extends TestingAct implements MediaPlayer.OnCompletionListener {
    private static String TAG = "SMMI";
    private AudioManager audioManager;
    private MediaPlayer mp;

    @Override
    public void initTesting() {
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

    }

    @Override
    public void startTesting() {
        if (!checkHeadset()) {
            finishTesting(false);
        } else {
            playAudio();
        }
    }

    @Override
    public void finishTesting(Boolean testResult) {
        if (testResult == true) {
            setResult(RESULT_OK);
        } else {
            setResult(RESULT_CANCELED);
        }
        finish();
    }

    private boolean checkHeadset() {
        if (audioManager.isWiredHeadsetOn()) {
            return true;
        }
        return false;
    }

    private void playAudio() {
        mp = MediaPlayer.create(this, R.raw.check_spk_l_r);
        mp.setOnCompletionListener(this);
        try {
            if (mp != null)
                mp.stop();
            mp.prepare();
            mp.start();
            while (mp.isPlaying()) {
                Log.d(TAG, AudioManager.MODE_IN_CALL + "MediaPlayer.isPlaying()");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveResult() {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mp.release();
        finishTesting(true);
    }
}
