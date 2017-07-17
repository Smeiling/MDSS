package com.mdss.smeiling.mdss.controller.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.mdss.smeiling.mdss.R;
import com.mdss.smeiling.mdss.common.adapter.ItemListAdapter;
import com.mdss.smeiling.mdss.common.base.BaseActivity;
import com.mdss.smeiling.mdss.common.receiver.BatteryReceiver;
import com.mdss.smeiling.mdss.common.thread.CPUThread;
import com.mdss.smeiling.mdss.common.thread.MemoryThread;
import com.mdss.smeiling.mdss.common.util.DialogUtil;
import com.mdss.smeiling.mdss.common.util.FileUtil;
import com.mdss.smeiling.mdss.model.TestItemBean;
import com.mdss.smeiling.mdss.view.DataLineView;
import com.mdss.smeiling.mdss.view.RateView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private GridView gridView;
    private List<TestItemBean> testItemBeanList;
    private DataLineView dataLineView;
    private RateView rateView;
    public static int[] testResultNum = new int[42];
    public static int[] testResult = new int[21];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FileUtil.newfile(this);
    }


    @Override
    public void initData() {
        testItemBeanList = new ArrayList<TestItemBean>();
        String[] titles = getResources().getStringArray(R.array.menu_name);
        TypedArray typedArray = getResources().obtainTypedArray(R.array.menu_icons);
        for (int i = 0; i < typedArray.length(); i++) {
            TestItemBean itemBean = new TestItemBean(titles[i], typedArray.getResourceId(i, 0), 0);
            testItemBeanList.add(itemBean);
        }
        typedArray.recycle();

    }

    @Override
    public void findViewById() {
        gridView = (GridView) findViewById(R.id.gridView_main);
        dataLineView = (DataLineView) findViewById(R.id.DateLine);
        rateView = (RateView) findViewById(R.id.display);
    }

    @Override
    public void initView() {
        ItemListAdapter adapter = new ItemListAdapter(testItemBeanList, R.layout.picture_item, this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(TestAllActivity.class);
                        break;
                    case 1:
                        startActivity(SingleActivity.class);
                        break;
                    case 2:
                        break;
                }
            }
        });
        reflashView();
    }


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main);
    }

    public void startActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                break;
            case R.id.action_testResult:
                startActivity(ResultActivity.class);
                break;
            case R.id.action_clearCache:
                FileUtil.newfile(this);
                break;
            case R.id.action_reset:
                DialogUtil.resetDialog(this);
                break;
            case R.id.action_about:
                //startActivity(AboutActivity.class);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //开启线程
    private void reflashView() {
        //开启计算内存的线程
        Handler myHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0x1000:
                        dataLineView.getDate(msg.arg1);
                        break;
                    case 0x0100:
                        rateView.setCpuData(msg.arg1);
                        break;
                    case 0x0010:
                        rateView.setBatteryData(msg.arg1);
                        break;
                    default:
                        break;
                }
                super.handleMessage(msg);
            }
        };
        MemoryThread memoryThread = new MemoryThread(this, myHandler);
        CPUThread cpuThread = new CPUThread(myHandler);
        new Thread(memoryThread).start();
        new Thread(cpuThread).start();
        BatteryReceiver receiver = new BatteryReceiver(myHandler);
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(receiver, filter);
    }

}
 
