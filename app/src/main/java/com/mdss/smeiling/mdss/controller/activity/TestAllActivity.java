package com.mdss.smeiling.mdss.controller.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.mdss.smeiling.mdss.R;
import com.mdss.smeiling.mdss.common.base.BaseActivity;


public class TestAllActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_all);
    }

    @Override
    public void setContentView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void findViewById() {

    }

    @Override
    public void initView() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test_all, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
