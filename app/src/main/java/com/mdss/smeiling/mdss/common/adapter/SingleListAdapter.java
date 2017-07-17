package com.mdss.smeiling.mdss.common.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mdss.smeiling.mdss.R;
import com.mdss.smeiling.mdss.common.util.SingleListViewHolder;
import com.mdss.smeiling.mdss.model.TestItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songmeiling on 2016/1/27.
 */
public class SingleListAdapter extends BaseAdapter {

    private static int PASS = 1;
    private static int FAIL = -1;
    private static int NONE = 0;

    private LayoutInflater inflater;
    private List<TestItemBean> items;
    private int resourceId;

    public SingleListAdapter(List<TestItemBean> testItemBeans, int resourceId, Context context) {
        super();
        items = new ArrayList<TestItemBean>();
        inflater = LayoutInflater.from(context);
        this.resourceId = resourceId;
        for (TestItemBean tib : testItemBeans) {
            TestItemBean item = new TestItemBean(tib.getTitle(), tib.getIconId(), tib.getResult());
            items.add(item);

        }
    }

    public void refresh(List<TestItemBean> list) {
        items = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (null != items) {
            return items.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SingleListViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(resourceId, null);
            viewHolder = new SingleListViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.icon);
            viewHolder.result = (ImageView) convertView.findViewById(R.id.res_lv_result);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SingleListViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(items.get(position).getTitle());
        viewHolder.icon.setImageResource(items.get(position).getIconId());
        if (items.get(position).getResult() == NONE) {
            viewHolder.result.setVisibility(View.INVISIBLE);
        } else if (items.get(position).getResult() == PASS) {
            viewHolder.result.setImageResource(R.drawable.asus_diagnostic_ic_pass2);
        } else if (items.get(position).getResult() == FAIL) {
            viewHolder.result.setImageResource(R.drawable.asus_diagnostic_ic_fail2);
        }
        return convertView;
    }

}
