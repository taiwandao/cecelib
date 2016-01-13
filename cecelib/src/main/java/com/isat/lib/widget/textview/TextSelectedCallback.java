package com.isat.lib.widget.textview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.MotionEvent;
import android.widget.EditText;

import com.isat.lib.util.LogUtil;
import com.isat.lib.util.StringUtil;



public interface TextSelectedCallback  {
	void selectedText(String word);
}