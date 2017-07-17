package com.mdss.smeiling.mdss.controller.activity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mdss.smeiling.mdss.R;
import com.mdss.smeiling.mdss.common.adapter.ResultListAdapter;
import com.mdss.smeiling.mdss.common.base.BaseActivity;
import com.mdss.smeiling.mdss.model.ResultBean;
import com.mdss.smeiling.mdss.model.ResultItemBean;
import com.mdss.smeiling.mdss.model.TestItemBean;

import java.util.ArrayList;
import java.util.List;


public class ResultActivity extends BaseActivity {

    private ListView lv_result;
    private ResultListAdapter resultListAdapter;
    private List<ResultItemBean> resultItemBeanList;
    private List<ResultBean> resultBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_result);
    }

    @Override
    public void initData() {
        resultItemBeanList = new ArrayList<ResultItemBean>();
        resultBeanList = getIntent().getParcelableArrayListExtra("resultList");
        String[] titles = getResources().getStringArray(R.array.testcase_name);
        TypedArray typedArray = getResources().obtainTypedArray(R.array.testcase_icons);
        for (int i = 0; i < typedArray.length(); i++) {
            ResultItemBean itemBean = new ResultItemBean(titles[i], typedArray.getResourceId(i, 0), resultBeanList.get(i));
            resultItemBeanList.add(itemBean);
        }
        typedArray.recycle();
    }

    @Override
    public void findViewById() {
        lv_result = (ListView) findViewById(R.id.lv_result);
    }

    @Override
    public void initView() {
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(false);
        resultListAdapter = new ResultListAdapter(resultItemBeanList, this);
        lv_result.setAdapter(resultListAdapter);

        lv_result.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                TestItemBean testItemBean = (TestItemBean) parent.getSelectedItem();
                //startActivityForResult(testItemBean.getTitle());
                //startActivityForResult(position);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }


        return super.onOptionsItemSelected(item);
    }
}
