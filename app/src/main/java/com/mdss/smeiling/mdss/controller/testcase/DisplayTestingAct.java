package com.mdss.smeiling.mdss.controller.testcase;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.mdss.smeiling.mdss.R;
import com.mdss.smeiling.mdss.common.base.TestingAct;

public class DisplayTestingAct extends TestingAct implements View.OnClickListener {
    private ImageView imageView;
    private int num = 0;
    private int[] color;

    @Override
    public void initTesting() {
        setContentView(R.layout.display_test_act);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnClickListener(this);
        color = new int[]{Color.RED, Color.BLUE, Color.GREEN, Color.GRAY};
    }

    @Override
    public void startTesting() {
        Toast.makeText(this, "Please click the screen", Toast.LENGTH_LONG).show();
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

    @Override
    public void saveResult() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView:
                if (num < 4) {
                    imageView.setBackgroundColor(color[num++]);
                } else {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                    builder.setTitle("Test Finished");
//                    builder.setMessage("Test finished.Please make a judgement");
//                    builder.setPositiveButton("PASS", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                            Toast.makeText(this, "pass", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                    builder.setNegativeButton("FAIL", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                            Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                    builder.create().show();
                    finishTesting(true);
                }
                break;
            default:
                Toast.makeText(this, "default", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
