package com.mdss.smeiling.mdss.controller.activity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mdss.smeiling.mdss.R;
import com.mdss.smeiling.mdss.common.adapter.ItemListAdapter;
import com.mdss.smeiling.mdss.common.base.BaseActivity;
import com.mdss.smeiling.mdss.common.util.DialogUtil;
import com.mdss.smeiling.mdss.model.TestItemBean;

import java.util.ArrayList;
import java.util.List;

public class SingleActivity extends BaseActivity implements AbsListView.OnScrollListener {

    private ListView single_lv;
    private ItemListAdapter itemListAdapter;
    private List<TestItemBean> testItemBeanList;
    private int mPosition;
    public static int[] testSingleResult = new int[21];

    @Override
    public void initData() {
        testItemBeanList = new ArrayList<TestItemBean>();
        String[] titles = getResources().getStringArray(R.array.testcase_name);
        TypedArray typedArray = getResources().obtainTypedArray(R.array.testcase_icons);
        for (int i = 0; i < typedArray.length(); i++) {
            TestItemBean itemBean = new TestItemBean(titles[i], typedArray.getResourceId(i, 0), testSingleResult[i]);
            testItemBeanList.add(itemBean);
        }
        typedArray.recycle();
    }

    @Override
    public void findViewById() {
        single_lv = (ListView) findViewById(R.id.single_lv);
    }

    @Override
    public void initView() {
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(false);
        itemListAdapter = new ItemListAdapter(testItemBeanList, R.layout.testcase_item, this);
        single_lv.setAdapter(itemListAdapter);

        single_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                TestItemBean testItemBean = (TestItemBean) parent.getSelectedItem();
                //startActivityForResult(testItemBean.getTitle());
                startActivityForResult(position);
            }
        });
        single_lv.setOnScrollListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_single);
    }

    public void startActivityForResult(int id) {
        Intent intent = new Intent(this, TestActivity.class);
        intent.putExtra("testCase", id);
        startActivityForResult(intent, id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        initData();
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        single_lv.setSelection(mPosition);
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                break;
            case R.id.action_testResult:
                startActivity(new Intent(this, ResultActivity.class));
                break;
            case R.id.action_reset:
                DialogUtil.resetDialog(this);
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
            mPosition = single_lv.getFirstVisiblePosition();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

}
 
