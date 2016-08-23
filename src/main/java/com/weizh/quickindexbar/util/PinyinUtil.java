package com.weizh.quickindexbar.util;

import android.text.TextUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created by weizh_000 on 2016/8/23.
 */

public class PinyinUtil {
    public static String getPinyin(String chinese) {
        if (TextUtils.isEmpty(chinese)) return null;

        //1.由于只能对单个汉字进行转换，所以将汉字逐个拆开放入数组，然后对每个汉字进行转换
        char[] charArray = chinese.toCharArray();
        StringBuffer pinyin = new StringBuffer();

        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        for (char c : charArray) {
            //2.过滤掉空格
            if (Character.isWhitespace(c)) continue;
            //3.判断是否是汉字
            //汉字是2个字节，每个字节范围-128~127，所以每个汉字肯定大于127
            if (c > 127) {
                //可能是汉字
                String[] strings = new String[0];
                try {
                    strings = PinyinHelper.toHanyuPinyinStringArray(c, format);
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                    //不是汉字，不处理
                }
                pinyin.append(strings[0]);
            } else {
                //不是汉字，则是键盘上的某个字符
                pinyin.append(c);//直接加到拼音字符串
            }
        }
        return pinyin.toString();
    }
}
