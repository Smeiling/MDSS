package com.mdss.smeiling.mdss.common.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mdss.smeiling.mdss.R;
import com.mdss.smeiling.mdss.common.util.ResultListViewHolder;
import com.mdss.smeiling.mdss.model.ResultItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songmeiling on 2016/1/22.
 */

public class ResultListAdapter extends BaseAdapter {


    private static int PASS = 1;
    private static int FAIL = -1;
    private static int NONE = 0;


    private LayoutInflater inflater;
    private List<ResultItemBean> items;

    public ResultListAdapter(List<ResultItemBean> resultItemBeans, Context context) {
        super();
        items = new ArrayList<ResultItemBean>();
        inflater = LayoutInflater.from(context);
        for (ResultItemBean tib : resultItemBeans) {
            ResultItemBean item = new ResultItemBean(tib.getTestTitle(), tib.getTestIcon(), tib.getResultBean());
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
        ResultListViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.result_item, null);
            viewHolder = new ResultListViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.tv_lv_result);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.icon_lv_result);
            viewHolder.result = (ImageView) convertView.findViewById(R.id.res_lv_result);
            viewHolder.success = (TextView) convertView.findViewById(R.id.tv_passed_num);
            viewHolder.fail = (TextView) convertView.findViewById(R.id.tv_failed_num);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ResultListViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(items.get(position).getTestTitle());
        viewHolder.icon.setImageResource(items.get(position).getTestIcon());
        if (items.get(position).getResultBean().getResult() == NONE) {
            viewHolder.result.setVisibility(View.INVISIBLE);
        } else if (items.get(position).getResultBean().getResult() == PASS) {
            viewHolder.result.setImageResource(R.drawable.asus_diagnostic_ic_pass2);
        }
        viewHolder.success.setText(String.valueOf(items.get(position).getResultBean().getPass()));
        viewHolder.fail.setText(String.valueOf(items.get(position).getResultBean().getFail()));
        return convertView;
    }

}