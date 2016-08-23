package com.weizh.quickindexbar.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;


/**
 * Created by weizh_000 on 2016/8/23.
 */

public class QuickIndexBar extends FrameLayout {
    private Paint paint;
    private String[] indexArr = {"A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"};
    private float cellHeight;
    private OnTouchLetterListener touchLetterListener;

    public QuickIndexBar(Context context) {
        super(context);
        init();
    }

    public QuickIndexBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public QuickIndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);//设置抗锯齿
        paint.setColor(Color.WHITE);
        paint.setTextSize(32);
        paint.setTextAlign(Paint.Align.CENTER);//文字居中显示
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        cellHeight = getMeasuredHeight() * 1f / indexArr.length;//获取每个格子的高度
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float x = getMeasuredWidth() / 2;//让文字水平居中
        float y = 0;
        for (int i = 0; i < indexArr.length; i++) {
            y = cellHeight / 2 + getTextHeight(indexArr[i]) / 2 + i * cellHeight;//设置文字的位置y大小
            paint.setColor(lastIndex==i?Color.BLACK:Color.WHITE);//重绘时，改变画笔颜色，让被按下的字母变成黑色，其他字母白色
            canvas.drawText(indexArr[i], x, y, paint);
        }
    }

    //获取文本高度
    private float getTextHeight(String s) {
        Rect bounds = new Rect();
        paint.getTextBounds(s, 0, 1, bounds);
        return bounds.height();
    }

    private int lastIndex = -1;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                int index = (int) (y / cellHeight);//获取当前所触摸的字母索引
                if (index != lastIndex) {
                    if(index<indexArr.length)//容错处理
                    touchLetterListener.onTouch(indexArr[index]);
                }
                lastIndex = index;
                break;
            case MotionEvent.ACTION_UP:
                //重置上一个字母索引
                lastIndex = -1;
                break;
        }
        invalidate();//引起重绘
        return true;//消费掉事件
    }

    public void setOnTouchLetterListener(OnTouchLetterListener touchLetterListener) {

        this.touchLetterListener = touchLetterListener;
    }

    public interface OnTouchLetterListener {

        void onTouch(String letter);
    }
}
