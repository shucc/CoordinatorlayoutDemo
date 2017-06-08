package org.cchao.coordinatorlayoutdemo;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.cchao.coordinatorlayoutdemo.widget.StickyGridHeadersBaseAdapter;

import java.util.List;
import java.util.Map;

/**
 * Created by chenchao on 2017/6/8.
 */

public class TestAdapter extends BaseAdapter implements StickyGridHeadersBaseAdapter {

    private List<String> data;

    private List<Integer> headInt;

    private List<String> headStr;

    public TestAdapter(List<String> data, List<Integer> headInt, List<String> headStr) {
        this.data = data;
        this.headInt = headInt;
        this.headStr = headStr;
    }

    @Override
    public int getCountForHeader(int header) {
        Log.d("TestAdatper", String.valueOf(header));
        return headInt.get(header);
    }

    @Override
    public int getNumHeaders() {
        return headInt.size();
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderHolder headerHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_head, parent, false);
            headerHolder = new HeaderHolder();
            headerHolder.textHead = (TextView) convertView.findViewById(R.id.text_head);
            convertView.setTag(headerHolder);
        } else {
            headerHolder = (HeaderHolder) convertView.getTag();
        }
        headerHolder.textHead.setText(headStr.get(position));
        return convertView;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NormalHolder normalHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test, parent, false);
            normalHolder = new NormalHolder();
            normalHolder.textContent = (TextView) convertView.findViewById(R.id.text_content);
            convertView.setTag(normalHolder);
        } else {
            normalHolder = (NormalHolder) convertView.getTag();
        }
        normalHolder.textContent.setText(data.get(position));
        return convertView;
    }

    public class NormalHolder {
        TextView textContent;
    }

    public class HeaderHolder {
        TextView textHead;
    }
}
