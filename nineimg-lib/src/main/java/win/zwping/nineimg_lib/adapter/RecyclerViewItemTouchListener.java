package win.zwping.nineimg_lib.adapter;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import win.zwping.nineimg_lib.listener.OnNineImgListener;

/**
 * <p>describe：通过触摸事件获取空白区域的点击事件
 * <p>    note：
 * <p> @author：zwp on ${DATE} mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public class RecyclerViewItemTouchListener extends RecyclerView.SimpleOnItemTouchListener {
    private GestureDetectorCompat mGestureDetector;

    public RecyclerViewItemTouchListener(final RecyclerView recyclerView, final OnNineImgListener listener) {
        mGestureDetector = new GestureDetectorCompat(recyclerView.getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (null != listener && null == childView) {
                    listener.onEmptyItemClick();
                } else {
                }
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
        return false;
    }
}