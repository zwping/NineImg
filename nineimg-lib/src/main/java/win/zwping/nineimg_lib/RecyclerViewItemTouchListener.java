package win.zwping.nineimg_lib;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import win.zwping.nineimg_lib.i.OnEmptyItemClickListener;
import win.zwping.nineimg_lib.i.OnItemClickListener;

/**
 * <p>describe：通过simpleOnTouchListener获取recyclerView的点击事件，在这主要用于监听recyclerView gridLayout模式下空白区域的点击事件
 * <p>    note：
 * <p> @author：zwp on ${DATE} mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public class RecyclerViewItemTouchListener extends RecyclerView.SimpleOnItemTouchListener {
    private GestureDetectorCompat mGestureDetector;

    public RecyclerViewItemTouchListener(final RecyclerView recyclerView, final OnEmptyItemClickListener emptyItemClickListener) {
        mGestureDetector = new GestureDetectorCompat(recyclerView.getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (null == childView) emptyItemClickListener.onEmptyItemClick();
                else {}
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