package com.weizh.quickindexbar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.weizh.quickindexbar.R;
import com.weizh.quickindexbar.domain.Friend;

import java.util.ArrayList;

/**
 * Created by weizh_000 on 2016/8/23.
 */
public class MyAdapter extends BaseAdapter {


    private ArrayList<Friend> list;
    private Context context;

    public MyAdapter(ArrayList<Friend> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.list_item, null);
            holder = new ViewHolder();
            holder.tvFirstLetter = (TextView) convertView.findViewById(R.id.tv_firstLetter);
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String letter = list.get(position).getPinyin().charAt(0) + "";
        if (position > 0) {
            String lastLetter = list.get(position - 1).getPinyin().charAt(0) + "";
            if (letter.equals(lastLetter)) {
                //需要隐藏当前的字母
                holder.tvFirstLetter.setVisibility(View.GONE);
            }else {
                //需要显示当前的字母
                holder.tvFirstLetter.setVisibility(View.VISIBLE);
                holder.tvFirstLetter.setText(letter);
            }
        } else {
            //第一个条目
            holder.tvFirstLetter.setVisibility(View.VISIBLE);
            holder.tvFirstLetter.setText(letter);
        }
        holder.tvName.setText(list.get(position).getName());
        return convertView;
    }

    class ViewHolder {
        private TextView tvFirstLetter;
        private TextView tvName;
    }
}
