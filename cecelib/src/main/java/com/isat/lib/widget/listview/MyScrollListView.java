package com.isat.lib.widget.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 解決在scrollview中 listview高度問題
* @Title: MyScrollListView.java 
* @Package com.isat.toefl.com.isat.toefl.widget.listview
* @Description: TODO(用一句话描述该文件做什么) 
* @author brian.hou
* @date 2014-11-18 下午6:00:47 
* @version V1.0
 */
public class MyScrollListView extends ListView {
 
    public MyScrollListView(Context context) {
        super(context);
    }
 
    public MyScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
 
    public MyScrollListView(Context context, AttributeSet attrs,
            int defStyle) {
        super(context, attrs, defStyle);
    }
 
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,  
                MeasureSpec.AT_MOST);  
        super.onMeasure(widthMeasureSpec, expandSpec);  
    }
 
}