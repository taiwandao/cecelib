package com.isat.lib.widget.listview;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.Scroller;
 
public class HorizontalListView extends AdapterView<ListAdapter> {
 
    public static final String TAG = HorizontalListView.class.getSimpleName();
    private static final boolean DEBUG = false;
 
    public boolean mAlwaysOverrideTouch = true;
    protected ListAdapter mAdapter;
    private int mLeftViewIndex = -1;
    private int mRightViewIndex = 0;
    protected int mCurrentX;
    protected int mNextX;
    private int mMaxX = Integer.MAX_VALUE;
    private int mDisplayOffset = 0;
    protected Scroller mScroller;
    private GestureDetector mGesture;
    private Queue<View> mRemovedViewQueue = new LinkedList<View>();
    private OnItemSelectedListener mOnItemSelected;
    private OnItemClickListener mOnItemClicked;
    private OnItemLongClickListener mOnItemLongClicked;
    private OnDownListener mOnDownListener;
    private OnUpListener mOnUpListener;
    private boolean mDataChanged = false;
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            requestLayout();
        }
    };
 
    public HorizontalListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
 
    private synchronized void initView() {
        mLeftViewIndex = -1;
        mRightViewIndex = 0;
        mDisplayOffset = 0;
        mCurrentX = 0;
        mNextX = 0;
        mMaxX = Integer.MAX_VALUE;
        mScroller = new Scroller(getContext());
        mGesture = new GestureDetector(getContext(), mOnGesture);
    }
 
    @Override
    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener) {
        mOnItemSelected = listener;
    }
 
    @Override
    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        mOnItemClicked = listener;
    }
 
    @Override
    public void setOnItemLongClickListener(AdapterView.OnItemLongClickListener listener) {
        mOnItemLongClicked = listener;
    }
 
    public void setOnDownListener(OnDownListener listener) {
        mOnDownListener = listener;
    }
 
    public void setOnUpListener(OnUpListener listener) {
        mOnUpListener = listener;
    }
 
    private DataSetObserver mDataObserver = new DataSetObserver() {
 
        @Override
        public void onChanged() {
            synchronized (HorizontalListView.this) {
                mDataChanged = true;
            }
            invalidate();
            requestLayout();
        }
 
        @Override
        public void onInvalidated() {
            reset();
            invalidate();
            requestLayout();
        }
 
    };
 
    @Override
    public ListAdapter getAdapter() {
        return mAdapter;
    }
 
    @Override
    public View getSelectedView() {
        // TODO: implement
        return null;
    }
 
    @Override
    public void setAdapter(ListAdapter adapter) {
        if (mAdapter != null) {
            mAdapter.unregisterDataSetObserver(mDataObserver);
        }
        mAdapter = adapter;
        mAdapter.registerDataSetObserver(mDataObserver);
        reset();
    }
 
    private synchronized void reset() {
        initView();
        removeAllViewsInLayout();
        requestLayout();
    }
 
    @Override
    public void setSelection(int position) {
        // TODO: implement
    }
 
    private void addAndMeasureChild(final View child, int viewPos) {
        LayoutParams params = child.getLayoutParams();
        if (params == null) {
            params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        } else {
        }
 
        addViewInLayout(child, viewPos, params, true);
        measureChild(child);
    }
 
    private void measureChild(final View child) {
        child.measure(MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(getHeight(), MeasureSpec.AT_MOST));
    }
 
    @Override
    protected synchronized void onLayout(boolean changed, int left, int top, int right, int bottom) {
        verbose(TAG, "onLayout");
        super.onLayout(changed, left, top, right, bottom);
 
        if (mAdapter == null) {
            return;
        }
 
        if (mDataChanged) {
            verbose(TAG, "data changed");
            int oldCurrentX = mCurrentX;
            initView();
            removeAllViewsInLayout();
            mNextX = oldCurrentX;
            mDataChanged = false;
        }
 
        if (mScroller.computeScrollOffset()) {
            int scrollx = mScroller.getCurrX();
            mNextX = scrollx;
            verbose(TAG, "Computed scroll offset. Current x = %1$d", mNextX);
        }
 
        if (mNextX <= 0) {
            verbose(TAG, "mNextX <= 0: %1$d", mNextX);
            mNextX = 0;
            mScroller.forceFinished(true);
        }
        if (mNextX >= mMaxX) {
            verbose(TAG, "mNextX >= max: %1$d : %2$d", mNextX, mMaxX);
            mNextX = mMaxX;
            mScroller.forceFinished(true);
        }
 
        verbose(TAG, "mCurrentX = %1$d; ; mNextX = %2$d", mCurrentX, mNextX);
        int dx = mCurrentX - mNextX;
        verbose(TAG, "dx = %1$d", dx);
 
        removeNonVisibleItems(dx);
        fillList(dx);
        positionItems(dx);
 
        mCurrentX = mNextX;
 
        if (!mScroller.isFinished()) {
            verbose(TAG, "Scroller is not finished");
            post(mRunnable);
        }
    }
 
    private void fillList(final int dx) {
        verbose(TAG, "fillList: %1$d; getChildCount = %2$d", dx, getChildCount());
        int edge = mDisplayOffset;
        View child = getChildAt(getChildCount() - 1);
        if (child != null) {
            edge = child.getRight();
        }
        fillListRight(edge, dx);
 
        edge = 0;
        child = getChildAt(0);
        if (child != null) {
            edge = child.getLeft();
        }
        fillListLeft(edge, dx);
 
    }
 
    private void fillListRight(int rightEdge, final int dx) {
        verbose(TAG, "fillListRight: rightEdge = %1$d; dx = %2$d", rightEdge, dx);
        int totalWidth = 0;
        Queue<View> viewQueue = new LinkedList<View>();
        while (rightEdge + dx < getWidth() && mRightViewIndex < mAdapter.getCount()) {
            verbose(TAG, "mRemovedViewQueue.size = %1$d", mRemovedViewQueue.size());
            View child = mAdapter.getView(mRightViewIndex, mRemovedViewQueue.poll(), this);
            measureChild(child);
            viewQueue.offer(child);
            int childWidth = child.getMeasuredWidth();
            rightEdge += childWidth;
            totalWidth += childWidth;
            totalWidth = removeNonVisibleItemsFromLeft(totalWidth, getWidth() + childWidth,
                    viewQueue);
            verbose(TAG, "rightEdge = %1$d; childWidth = %2$d; rightViewIndex = %3$d"
                    + "; count = %4$d; prognosed max width = %5$d", rightEdge,
                    child.getMeasuredWidth(), mRightViewIndex, mAdapter.getCount(),
                    child.getMeasuredWidth() * mAdapter.getCount() - getWidth());
 
            if (mRightViewIndex == mAdapter.getCount() - 1) {
                mMaxX = mCurrentX + rightEdge - getWidth();
                verbose(TAG, "Setting maxX to %1$d", mMaxX);
                verbose(TAG, "mCurrentX = %1$d; rightEdge = %2$d; width = %3$d", mCurrentX,
                        rightEdge, getWidth());
            }
 
            if (mMaxX < 0) {
                mMaxX = 0;
            }
            mRightViewIndex++;
        }
        View child;
        while ((child = viewQueue.poll()) != null) {
            addAndMeasureChild(child, -1);
        }
 
    }
 
    private void fillListLeft(int leftEdge, final int dx) {
        verbose(TAG, "fillListLeft: leftEdge = %1$d; dx = %2$d", leftEdge, dx);
        int totalWidth = 0;
        Queue<View> viewQueue = new LinkedList<View>();
        while (leftEdge + dx > 0 && mLeftViewIndex >= 0) {
            verbose(TAG, "fillListLeft: mRemovedViewQueue.size = %1$d", mRemovedViewQueue.size());
            View child = mAdapter.getView(mLeftViewIndex, mRemovedViewQueue.poll(), this);
            measureChild(child);
            viewQueue.offer(child);
            int childWidth = child.getMeasuredWidth();
            leftEdge -= childWidth;
            totalWidth += childWidth;
            totalWidth = removeNonVisibleItemsFromRight(totalWidth, getWidth() + childWidth,
                    viewQueue);
            mLeftViewIndex--;
            mDisplayOffset -= child.getMeasuredWidth();
            verbose(TAG, "fillListLeft: mLeftViewIndex = %1$d; mDisplayOffset = %2$d",
                    mLeftViewIndex, mDisplayOffset);
        }
        View child;
        while ((child = viewQueue.poll()) != null) {
            addAndMeasureChild(child, 0);
        }
    }
 
    private void removeNonVisibleItems(final int dx) {
        verbose(TAG, "removeNonVisibleItems: %1$d", dx);
        View child = getChildAt(0);
        while (child != null && child.getRight() + dx <= 0) {
            mDisplayOffset += child.getMeasuredWidth();
            mRemovedViewQueue.offer(child);
            removeViewInLayout(child);
            mLeftViewIndex++;
            child = getChildAt(0);
 
        }
 
        child = getChildAt(getChildCount() - 1);
        while (child != null && child.getLeft() + dx >= getWidth()) {
            mRemovedViewQueue.offer(child);
            removeViewInLayout(child);
            mRightViewIndex--;
            child = getChildAt(getChildCount() - 1);
        }
        verbose(TAG, "mDisplayOffset = %1$d", mDisplayOffset);
    }
 
    private int removeNonVisibleItemsFromLeft(int totalWidth, int minRestWidth,
            Queue<View> viewQueue) {
        verbose(TAG, "removeNonVisibleItemsFromLeft: totalWidth = %1$d, minRestWidth = %2$d",
                totalWidth, minRestWidth);
        verbose(TAG, "mLeftViewIndex = %1$d", mLeftViewIndex);
        View child = viewQueue.peek();
        while (child != null) {
            int childWidth = child.getMeasuredWidth();
 
            verbose(TAG, "removeNonVisibleItemsFromLeft: totalWidth = %1$d, childWidth = %2$d",
                    totalWidth, childWidth);
            if (totalWidth - childWidth < minRestWidth) {
                break;
            }
            viewQueue.poll();
            mDisplayOffset += childWidth;
            totalWidth -= childWidth;
            mRemovedViewQueue.offer(child);
            mLeftViewIndex++;
            child = viewQueue.peek();
        }
 
        verbose(TAG,
                "removeNonVisibleItemsFromLeft: mDisplayOffset = %1$d; totalWidth = %2$d; mLeftViewIndex = %3$d",
                mDisplayOffset, totalWidth, mLeftViewIndex);
        return totalWidth;
    }
 
    private int removeNonVisibleItemsFromRight(int totalWidth, int minRestWidth,
            Queue<View> viewQueue) {
        verbose(TAG, "removeNonVisibleItemsFromRight: totalWidth = %1$d, minRestWidth = %2$d",
                totalWidth, minRestWidth);
        verbose(TAG, "mRightViewIndex = %1$d", mRightViewIndex);
        View child = viewQueue.peek();
        while (child != null) {
            int childWidth = child.getMeasuredWidth();
 
            verbose(TAG, "totalWidth = %1$d, childWidth = %2$d", totalWidth, childWidth);
            if (totalWidth - childWidth < minRestWidth) {
                break;
            }
            viewQueue.poll();
            totalWidth -= childWidth;
            mRemovedViewQueue.offer(child);
            mRightViewIndex--;
            child = viewQueue.peek();
        }
        verbose(TAG, "totalWidth = %1$d; mRightViewIndex = %2$d", totalWidth, mRightViewIndex);
        return totalWidth;
    }
 
    private void positionItems(final int dx) {
        verbose(TAG, "positionItems: dx = %1$d", dx);
        if (getChildCount() > 0) {
            mDisplayOffset += dx;
            int left = mDisplayOffset;
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                int childWidth = child.getMeasuredWidth();
                child.layout(left, 0, left + childWidth, child.getMeasuredHeight());
                left += childWidth + child.getPaddingRight();
            }
        }
    }
 
    public int getScrollOffsetForIndex(int index) {
//        Queue<View> viewQueue = new LinkedList<View>();
//        int i = 0;
        int result = 0;
//        while (i < index && i < mAdapter.getCount()) {
//        	System.out.println("mRightViewIndex = " + mRightViewIndex);
//            verbose(TAG, "mRemovedViewQueue.size = %1$d", mRemovedViewQueue.size());
//            View child = mAdapter.getView(mRightViewIndex - 1, viewQueue.poll(), this);
//            measureChild(child);
//            viewQueue.offer(child);
//            result += child.getMeasuredWidth();
//            i++;
//        }
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        if ( index < mAdapter.getCount()) {
        	View child = mAdapter.getView(index , null, this);
        	measureChild(child);
        	result = index * child.getMeasuredWidth();
        	if (result > mAdapter.getCount() * child.getMeasuredWidth() - width) {
				result = mAdapter.getCount() * child.getMeasuredWidth() - width;
			}
        }
        return result;
    }
 
    public void scrollToIndex(int index) {
        scrollTo(getScrollOffsetForIndex(index));
    }
 
    public synchronized void scrollTo(int x) {
        verbose(TAG, "Requested scroll from x = %1$d to x = %2$d", mNextX, x);
        mScroller.startScroll(mNextX, 0, x - mNextX, 0);
        requestLayout();
    }
 
    public int getStartX() {
        return mNextX;
    }
 
    public int getMaxX() {
        return mMaxX;
    }
 
    float mInitialX;
    float mInitialY;
    boolean mIsMoving;
 
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // experimental update to avoid view flickers and onclick events fired
        // after move
        boolean handled = mGesture.onTouchEvent(ev);
        switch (ev.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                mInitialX = ev.getX();
                mInitialY = ev.getY();
                mIsMoving = false;
                break;
            case MotionEvent.ACTION_MOVE: {
                final float deltaX = Math.abs(ev.getX() - mInitialX);
                final float deltaY = Math.abs(ev.getY() - mInitialY);
                mIsMoving = deltaX > 5 || deltaY > 5;
            }
                break;
            case MotionEvent.ACTION_UP:
                onUp(ev);
                break;
        }
        if (!mIsMoving) {
            handled |= super.dispatchTouchEvent(ev);
        }
        return handled;
    }
 
    protected boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        synchronized (HorizontalListView.this) {
            mScroller.fling(mNextX, 0, (int) -velocityX, 0, 0, mMaxX, 0, 0);
        }
        requestLayout();
 
        return true;
    }
 
    protected boolean onDown(MotionEvent e) {
        mScroller.forceFinished(true);
        if (mOnDownListener != null) {
            mOnDownListener.onDown(e);
        }
        return true;
    }
 
    protected boolean onUp(MotionEvent e) {
        if (mOnUpListener != null) {
            mOnUpListener.onUp(e);
        }
        return true;
    }
 
    private OnGestureListener mOnGesture = new GestureDetector.SimpleOnGestureListener() {
 
        @Override
        public boolean onDown(MotionEvent e) {
            return HorizontalListView.this.onDown(e);
        }
 
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            verbose(TAG, "onFling");
            return HorizontalListView.this.onFling(e1, e2, velocityX, velocityY);
        }
 
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
 
            verbose(TAG, "onScroll: distanceX = %1$f", distanceX);
            synchronized (HorizontalListView.this) {
                mNextX += (int) distanceX;
            }
            requestLayout();
 
            return true;
        }
 
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                if (isEventWithinView(e, child)) {
                    if (mOnItemClicked != null) {
                        mOnItemClicked.onItemClick(HorizontalListView.this, child, mLeftViewIndex
                                + 1 + i, mAdapter.getItemId(mLeftViewIndex + 1 + i));
                    }
                    if (mOnItemSelected != null) {
                        mOnItemSelected.onItemSelected(HorizontalListView.this, child,
                                mLeftViewIndex + 1 + i, mAdapter.getItemId(mLeftViewIndex + 1 + i));
                    }
                    break;
                }
 
            }
            return true;
        }
 
        @Override
        public void onLongPress(MotionEvent e) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                if (isEventWithinView(e, child)) {
                    if (mOnItemLongClicked != null) {
                        mOnItemLongClicked.onItemLongClick(HorizontalListView.this, child,
                                mLeftViewIndex + 1 + i, mAdapter.getItemId(mLeftViewIndex + 1 + i));
                    }
                    break;
                }
 
            }
        }
 
        private boolean isEventWithinView(MotionEvent e, View child) {
            Rect viewRect = new Rect();
            int[] childPosition = new int[2];
            child.getLocationOnScreen(childPosition);
            int left = childPosition[0];
            int right = left + child.getWidth();
            int top = childPosition[1];
            int bottom = top + child.getHeight();
            viewRect.set(left, top, right, bottom);
            return viewRect.contains((int) e.getRawX(), (int) e.getRawY());
        }
    };
 
    public static interface OnDownListener {
        void onDown(MotionEvent e);
    }
 
    public static interface OnUpListener {
        void onUp(MotionEvent e);
    }
 
    public int getRightViewIndex() {
        return mRightViewIndex;
    }
 
    public int getLeftViewIndex() {
        return mLeftViewIndex;
    }
 
    public boolean isMoving() {
        return mIsMoving;
    }
 
    /**
     * Write message to the debug log
     * 
     * @param TAG
     * @param message
     * @param params
     */
    public static void debug(String TAG, String message, Object... params) {
        try {
            if (DEBUG) {
                if (params == null || params.length == 0) {
                    Log.d(TAG, message);
                } else {
                    Log.d(TAG, format(message, params));
                }
            }
        } catch (Exception ex) {
            error(TAG, null, ex);
        }
    }
 
    /**
     * Write message to the verbose log
     * 
     * @param TAG
     * @param message
     * @param params
     */
    public static void verbose(String TAG, String message, Object... params) {
        try {
            if (DEBUG) {
                if (params == null || params.length == 0) {
                    Log.v(TAG, message);
                } else {
                    Log.v(TAG, format(message, params));
                }
            }
        } catch (Exception ex) {
            error(TAG, null, ex);
        }
    }
 
    /**
     * Format string with params
     * 
     * @param message
     * @param params
     * @return
     */
    public static String format(String message, Object... params) {
        try {
            return String.format(Locale.ENGLISH, message, params);
        } catch (Exception ex) {
            error(TAG, null, ex);
        }
        return null;
    }
 
    /**
     * Write message to the error log
     * 
     * @param TAG
     * @param message
     */
    public static void error(String TAG, String message) {
        error(TAG, message, null);
    }
 
    /**
     * Write message to the error log
     * 
     * @param TAG
     * @param message
     * @param tr
     */
    public static void error(String TAG, String message, Throwable tr) {
        Log.e(TAG, message, tr);
    }
}