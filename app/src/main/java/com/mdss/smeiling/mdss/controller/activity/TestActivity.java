package com.mdss.smeiling.mdss.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mdss.smeiling.mdss.R;
import com.mdss.smeiling.mdss.common.base.BaseActivity;
import com.mdss.smeiling.mdss.common.util.XMLparce;
import com.mdss.smeiling.mdss.model.ResultBean;
import com.mdss.smeiling.mdss.model.TestCaseBean;


public class TestActivity extends BaseActivity implements View.OnClickListener {

    private static int PASS = 1;
    private static int FAIL = -1;

    private Button startBtn;
    private ImageView ivCommon;
    private TestCaseBean testCaseBean;
    private TextView tvCommon;
    private ImageView ivResultCommon;
    private TextView tvResultCommon;
    private ResultBean resultBean;

    public TestActivity() {

    }

    public TestActivity(TestCaseBean testCaseBean) {
        this.testCaseBean = testCaseBean;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.single_test_act);
    }

    @Override
    public void initData() {
        resultBean = getIntent().getParcelableExtra("resultBean");
        testCaseBean = new XMLparce(this).readXML().get(getIntent().getIntExtra("testCase", 0));
//        Log.d("MainActivity", testCaseBean.getId() + "\t" + testCaseBean.getCode() + "\t" + testCaseBean.getTitle() + "\t" + testCaseBean.getImgId() + "\t" + testCaseBean.getContextId());
        Log.i("MainActivity", testCaseBean.getTestingAct());
    }

    @Override
    public void findViewById() {
        startBtn = (Button) findViewById(R.id.testStartBtn);
        ivCommon = (ImageView) findViewById(R.id.iv_common);
        tvCommon = (TextView) findViewById(R.id.tv_common);
        ivResultCommon = (ImageView) findViewById(R.id.iv_result_common);
        tvResultCommon = (TextView) findViewById(R.id.tv_result_common);
    }

    @Override
    public void initView() {
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(false);
        ivCommon.setImageResource(getResources().getIdentifier(testCaseBean.getImgId(), "drawable", "com.example.songmeiling.myapplication"));
        tvCommon.setText(getResources().getIdentifier(testCaseBean.getContextId(), "string", "com.example.songmeiling.myapplication"));
        startBtn.setOnClickListener(this);
    }

    private void startActivityForResult(String actName) {
        startActivityForResult(new Intent(actName), testCaseBean.getCode());
    }

    @Override
    public void onClick(View v) {
        startActivityForResult(testCaseBean.getTestingAct());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            resultBean.setPass(resultBean.getPass() + 1);
            resultBean.setResult(PASS);
            ivResultCommon.setImageResource(R.drawable.asus_diagnostic_ic_pass);
            ivResultCommon.setVisibility(View.VISIBLE);
        } else {
            resultBean.setFail(resultBean.getFail() + 1);
            resultBean.setResult(FAIL);
            ivResultCommon.setImageResource(R.drawable.asus_diagnostic_ic_fail);
            ivResultCommon.setVisibility(View.VISIBLE);
        }
        startBtn.setText(R.string.btn_test_again);
        saveResult();
    }

    public void saveResult() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent();
            intent.putExtra("resultBean", resultBean);
            setResult(RESULT_OK, intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }
}
