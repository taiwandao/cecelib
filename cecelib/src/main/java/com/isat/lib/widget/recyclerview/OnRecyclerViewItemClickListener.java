package com.isat.lib.widget.recyclerview;

import android.view.View;

/**
 * 针对RecylerView没有onItemClickListener事件
 */
public interface OnRecyclerViewItemClickListener {
    public void onItemClick(View view,int position);
} 