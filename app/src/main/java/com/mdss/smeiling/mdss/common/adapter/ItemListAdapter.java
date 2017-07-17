package com.mdss.smeiling.mdss.common.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mdss.smeiling.mdss.R;
import com.mdss.smeiling.mdss.common.util.ItemListViewHolder;
import com.mdss.smeiling.mdss.model.TestItemBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by songmeiling on 2016/1/19.
 */

public class ItemListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<TestItemBean> items;
    private int resourceId;


    public ItemListAdapter(List<TestItemBean> testItemBeans, int resourceId, Context context) {
        super();
        items = new ArrayList<TestItemBean>();
        inflater = LayoutInflater.from(context);
        this.resourceId = resourceId;
        for (TestItemBean tib : testItemBeans) {
            TestItemBean item = new TestItemBean(tib.getTitle(), tib.getIconId(), tib.getResult());
            items.add(item);
        }
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
        ItemListViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(resourceId, null);
            viewHolder = new ItemListViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.icon);
            viewHolder.result = (ImageView) convertView.findViewById(R.id.im_st_result);
            if (resourceId == R.layout.picture_item) {
                if (position == 3) {
                    viewHolder.title.setAlpha(0.2f);
                    viewHolder.icon.setColorFilter(Color.GRAY);
                    viewHolder.icon.setAlpha(0.2f);
                }
            }
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ItemListViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(items.get(position).getTitle());
        viewHolder.icon.setImageResource(items.get(position).getIconId());
        if (resourceId == R.layout.testcase_item) {
            if (items.get(position).getResult() == 0) {
                viewHolder.result.setVisibility(View.INVISIBLE);
            } else if (items.get(position).getResult() == 1) {
                viewHolder.result.setImageResource(R.drawable.asus_diagnostic_ic_pass2);
                viewHolder.result.setVisibility(View.VISIBLE);
            } else if (items.get(position).getResult() == 2) {
                viewHolder.result.setImageResource(R.drawable.asus_diagnostic_ic_fail2);
                viewHolder.result.setVisibility(View.VISIBLE);
            }
        }
        return convertView;
    }

}
 
