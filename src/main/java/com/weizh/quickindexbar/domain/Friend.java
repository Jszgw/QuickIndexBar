package com.weizh.quickindexbar.domain;

import com.weizh.quickindexbar.util.PinyinUtil;

/**
 * Created by weizh_000 on 2016/8/23.
 */

public class Friend implements Comparable<Friend>{

    private String name;

    private String pinyin;

    public Friend(String name) {
        this.name = name;
        //设置名字对应的拼音
        setPinyin(PinyinUtil.getPinyin(getName()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Override
    public int compareTo(Friend another) {
        String pinyin = getPinyin();
        String anotherPinyin = another.getPinyin();
        return pinyin.compareTo(anotherPinyin);
    }
}
