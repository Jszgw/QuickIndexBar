package com.weizh.quickindexbar;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.weizh.quickindexbar.adapter.MyAdapter;
import com.weizh.quickindexbar.domain.Friend;
import com.weizh.quickindexbar.widget.QuickIndexBar;

import java.util.ArrayList;
import java.util.Collections;

import static com.weizh.quickindexbar.R.id.tv_currentLetter;

public class MainActivity extends AppCompatActivity {

    private QuickIndexBar quickIndexBar;
    private ListView lvListView;
    private ArrayList<Friend> list = new ArrayList<Friend>();
    private TextView tvCurrentLetter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        quickIndexBar.setOnTouchLetterListener(new QuickIndexBar.OnTouchLetterListener() {
            @Override
            public void onTouch(String letter) {
                //根据当前所选中的字母，去friend集合中找首字母
                for (int i = 0; i < list.size(); i++) {
                    String firstLetter = list.get(i).getPinyin().charAt(0) + "";
                    if (letter.equals(firstLetter)) {
                        lvListView.setSelection(i);
                        break;
                    }
                }
                showCurrentLetter(letter);
            }
        });
        fillFriend();//填充数据
        if (!list.isEmpty()) Collections.sort(list); //对数据进行排序
        lvListView.setAdapter(new MyAdapter(list, getApplicationContext()));

//        System.out.println(PinyinUtil.getPinyin("#黑马"));
//        System.out.println(PinyinUtil.getPinyin(":-)黑 马"));
//        System.out.println(PinyinUtil.getPinyin("a黑 马"));
    }

    private Handler handler = new Handler();

    //显示当前被选中的字母
    private void showCurrentLetter(String letter) {
        tvCurrentLetter.setText(letter);
        tvCurrentLetter.setVisibility(View.VISIBLE);

        //先移除之前的任务
        handler.removeCallbacksAndMessages(null);
        //延时隐藏currentLetter
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvCurrentLetter.setVisibility(View.GONE);
            }
        }, 1500);
    }

    private void initView() {
        quickIndexBar = (QuickIndexBar) findViewById(R.id.quickIndexBar);
        lvListView = (ListView) findViewById(R.id.lv_listView);
        tvCurrentLetter = (TextView) findViewById(tv_currentLetter);
    }

    private void fillFriend() {
        // 虚拟数据
        list.add(new Friend("李伟"));
        list.add(new Friend("张三"));
        list.add(new Friend("阿三"));
        list.add(new Friend("阿四"));
        list.add(new Friend("段誉"));
        list.add(new Friend("段正淳"));
        list.add(new Friend("张三丰"));
        list.add(new Friend("陈坤"));
        list.add(new Friend("林俊杰1"));
        list.add(new Friend("陈坤2"));
        list.add(new Friend("王二a"));
        list.add(new Friend("林俊杰a"));
        list.add(new Friend("张四"));
        list.add(new Friend("林俊杰"));
        list.add(new Friend("王二"));
        list.add(new Friend("王二b"));
        list.add(new Friend("赵四"));
        list.add(new Friend("杨坤"));
        list.add(new Friend("赵子龙"));
        list.add(new Friend("杨坤1"));
        list.add(new Friend("李伟1"));
        list.add(new Friend("宋江"));
        list.add(new Friend("宋江1"));
        list.add(new Friend("李伟3"));

    }
}
