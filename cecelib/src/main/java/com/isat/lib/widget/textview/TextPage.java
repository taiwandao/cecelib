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


/**
 * 
 * @Title: TextPage.java
 * @Package com.isat.toefl.com.isat.toefl.widget.textview
 * @Description: 文字显示图片并且变色
 * @author brian.hou
 * @date 2014-11-14 下午10:12:43
 * @version V1.0
 */
@SuppressLint("ClickableViewAccessibility")
public class TextPage extends EditText {
//	WordsTranslatePopwindow translatePopwindow;
//	private long queId = 0;
//	private long wkId = 0;
//	private String queNumContent;
//
//	public void setQueNumContent(String queNumContent) {
//		this.queNumContent = queNumContent;
//	}
//
//	public void setQueId(long queId) {
//		this.queId = queId;
//	}
//
//	public void setWkId(long wkId) {
//		this.wkId = wkId;
//	}

	public TextSelectedCallback textSelectedCallback;

	public void setTextSelectedCallback(TextSelectedCallback textSelectedCallback) {
		this.textSelectedCallback = textSelectedCallback;
	}

	public TextPage(Context context) {
		super(context);
	}


	public TextPage(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public TextPage(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreateContextMenu(ContextMenu menu) {
	}

	@Override
	public boolean getDefaultEditable() {
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		Layout layout = getLayout();
		int line = 0;
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			SpannableStringBuilder builder = new SpannableStringBuilder(
					getText());
			ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.BLACK);
			builder.setSpan(redSpan, 0, getText().toString().length(),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			
//			SimulationExerciesBusiness.getInstance().setQueNumberStyle(queNumContent, builder);
			
			setText(builder);
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			if (layout == null)
				break;
			line = layout.getLineForVertical(getScrollY() + (int) event.getY());
			int curOff = layout
					.getOffsetForHorizontal(line, (int) event.getX());
			int start = layout.getLineStart(line);
			int end = layout.getLineEnd(line);
			String queConent = getText().toString() + " ";
			String lineContent = queConent.substring(start, end);
			String beforeStr = queConent.substring(start, curOff);
			String afterStr = queConent.substring(curOff, end);
			StringBuffer content = new StringBuffer();
			int startPosition = curOff;
			int endPosition = curOff;

			for (int i = beforeStr.length(); i > 0; i--) {
				int clickChar = beforeStr.charAt(i - 1);
				if (clickChar < 65 || (clickChar > 90 && clickChar < 97)
						|| clickChar > 122) {
					break;
				} else {
					startPosition = curOff - beforeStr.length() + i - 1;
					content.append(lineContent.substring(i - 1, i));
				}
			}
			content = new StringBuffer(StringUtil
					.reverseStr(content.toString()));
			for (int i = 0; i < afterStr.length(); i++) {
				int clickChar = afterStr.charAt(i);

				if (clickChar < 65 || (clickChar > 90 && clickChar < 97)
						|| clickChar > 122) {
					break;
				} else {
					endPosition = curOff + i + 1;
					content.append(afterStr.substring(i, i + 1));
				}
			}
			String word = StringUtil
					.StringFilter(content.toString());
			// 判斷文字是否符合要求
			if (TextUtils.isEmpty(word) || StringUtil
					.isChinese(word)
					|| word.length() == 0)
				break;
//			builder = new SpannableStringBuilder(
//					getText());
//			ForegroundColorSpan balckSpan = new ForegroundColorSpan(Color.BLACK);
//			builder.setSpan(balckSpan, 0, getText().toString().length(),
//					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			
//			SimulationExerciesBusiness.getInstance().setQueNumberStyle(queNumContent, builder);
			
//			redSpan = new ForegroundColorSpan(Color.BLACK);
//			builder.setSpan(redSpan, startPosition, endPosition,
//					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//			setText(builder);
//			LogUtil.i("test","word:"+word);
			textSelectedCallback.selectedText(word);
//			translatePopwindow = new WordsTranslatePopwindow(this, word);
//			if(queId>0){
//				translatePopwindow.setQueId(queId);
//			}
//			if(wkId>0){
//				translatePopwindow.setWkId(wkId);
//			}
//			translatePopwindow.show((int) event.getY(), (int) event.getX());
			break;
		}
		return true;
	}

}