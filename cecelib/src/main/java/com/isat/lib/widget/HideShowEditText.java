package com.isat.lib.widget;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

import test.isat.com.cecelib.R;

public class HideShowEditText extends EditText implements  
OnFocusChangeListener, TextWatcher { 
	/**
	 * 删除按钮的引用
	 */
	private Drawable mHideDrawable , mShowDrawable;
	private boolean mDownTouch; 
	private boolean isShow;

	public HideShowEditText(Context context) { 
		this(context, null); 
	} 

	public HideShowEditText(Context context, AttributeSet attrs) { 
		//这里构造方法也很重要，不加这个很多属性不能再XML里面定义
		this(context, attrs, android.R.attr.editTextStyle); 
	} 

	public HideShowEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}


	private void init() { 
		//获取EditText的DrawableRight,假如没有设置我们就使用默认的图片
		mHideDrawable = getCompoundDrawables()[2]; 
		if (mHideDrawable == null) { 
			mHideDrawable = getResources() 
					.getDrawable(R.drawable.ic_hide);
		} 
		mShowDrawable = getResources().getDrawable(R.drawable.ic_show);
		mHideDrawable.setBounds(0, 0, mHideDrawable.getIntrinsicWidth(), mHideDrawable.getIntrinsicHeight()); 
		mShowDrawable.setBounds(0, 0, mShowDrawable.getIntrinsicWidth(), mShowDrawable.getIntrinsicHeight()); 
		//		setCompoundDrawables(getCompoundDrawables()[0], 
		//				getCompoundDrawables()[1], mHideDrawable, getCompoundDrawables()[3]); 
		setOnFocusChangeListener(this); 
		addTextChangedListener(this); 
	} 


	/**
	 * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件
	 * 当我们按下的位置 在  EditText的宽度 - 图标到控件右边的间距 - 图标的宽度  和
	 * EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向没有考虑
	 */
	@Override 
	public boolean onTouchEvent(MotionEvent event) { 

		if (getCompoundDrawables()[2] != null) { 
			
			if (event.getAction() == MotionEvent.ACTION_UP) { 
				boolean touchable = event.getX() > (getWidth() 
						- getPaddingRight() - mHideDrawable.getIntrinsicWidth()) 
						&& (event.getX() < ((getWidth() - getPaddingRight())));
				if (touchable) {
					performClick();
					if (isShow) {
						setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
					}else {
						setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
					}
					isShow = !isShow;
					setIconShow();
					//切换后将EditText光标置于末尾
					CharSequence charSequence = getText();
					if (charSequence instanceof Spannable) {
						Spannable spanText = (Spannable) charSequence;
						Selection.setSelection(spanText, charSequence.length());
					}
					return true;
				}

			} 
		} 
		
		return super.onTouchEvent(event); 
	} 

	@Override
	public boolean performClick() {
		return super.performClick();
	}

	/**
	 * 当ClearEditText焦点发生变化的时候，判断里面字符串长度设置清除图标的显示与隐藏
	 */
	@Override 
	public void onFocusChange(View v, boolean hasFocus) { 
		//		if (hasFocus) { 
		//			setClearIconVisible(getText().length() > 0); 
		//		} else { 
		//			setClearIconVisible(false); 
		//		} 

	} 


	/**
	 * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
	 * @param visible
	 */
	//	protected void setClearIconVisible(boolean visible) { 
	//		Drawable right = visible ? mHideDrawable : null; 
	//		//    	Drawable right = true ? mClearDrawable : null; 
	//		setCompoundDrawables(getCompoundDrawables()[0], 
	//				getCompoundDrawables()[1], right, getCompoundDrawables()[3]); 
	//	} 

	protected void setIconShow() { 
		Drawable right = isShow ? mShowDrawable : mHideDrawable; 
		//    	Drawable right = true ? mClearDrawable : null; 
		setCompoundDrawables(getCompoundDrawables()[0], 
				getCompoundDrawables()[1], right, getCompoundDrawables()[3]); 
	} 


	/**
	 * 当输入框里面内容发生变化的时候回调的方法
	 */
	@Override 
	public void onTextChanged(CharSequence s, int start, int count, 
			int after) { 
		//		setClearIconVisible(s.length() > 0); 
	} 

	@Override 
	public void beforeTextChanged(CharSequence s, int start, int count, 
			int after) { 

	} 

	@Override 
	public void afterTextChanged(Editable s) { 

	} 


	/**
	 * 设置晃动动画
	 */
	public void setShakeAnimation(){
		this.setAnimation(shakeAnimation(5));
	}


	/**
	 * 晃动动画
	 * @param counts 1秒钟晃动多少下
	 * @return
	 */
	public static Animation shakeAnimation(int counts){
		Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
		translateAnimation.setInterpolator(new CycleInterpolator(counts));
		translateAnimation.setDuration(1000);
		return translateAnimation;
	}


}
