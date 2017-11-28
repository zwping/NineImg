package win.zwping.nineimg_lib;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import win.zwping.nineimg_lib.i.OnItemClickListener;

/**
 * Created by Administrator on 2017/11/18 0018.
 */

public class RecyclerViewItemTouchListener extends RecyclerView.SimpleOnItemTouchListener {
    private OnItemClickListener mClickListener;
    private GestureDetectorCompat mGestureDetector;

    public RecyclerViewItemTouchListener(final RecyclerView recyclerView, OnItemClickListener listener) {
        this.mClickListener = listener;
        mGestureDetector = new GestureDetectorCompat(recyclerView.getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (null != mClickListener) {
                    if (childView != null) {
                        mClickListener.onItemClick(recyclerView.getChildViewHolder(childView), null);
                    } else {
                        mClickListener.onEmptyItemClick();
                    }
                }
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childView != null && mClickListener != null) {
//                            mClickListener.onItemLongClick(childView);
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
        return false;
    }
}