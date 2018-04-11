package win.zwping.nineimg_lib.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

import win.zwping.nineimg_lib.NineImg;
import win.zwping.nineimg_lib.listener.OnNineImgListener;

/**
 * <p>describe：9图专属的viewHolder
 * <p>    note：
 * <p> @author：zwp on 2018/4/10 0010 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public class NineImgViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;
    private NineImg nineImg;

    public NineImgViewHolder(View itemView, NineImg nineImg) {
        super(itemView);
        mViews = new SparseArray<>();
        this.nineImg = nineImg;
    }

    /*** add child view as click listener, return child view is position and view form adapter ***/
    /*** 增加子控件的点击事件监听 ***/
    public void addChildViewClickListener(final int viewId) {
        final View view = getView(viewId);
        if (view != null) {
            if (!view.isClickable()) {
                view.setClickable(true);
            }
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (nineImg.getListener() != null) {
                        nineImg.getListener().onChildItemClick(v, getAdapterPosition());
                    }
                }
            });
        }
    }

    /*** view复用 ***/
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

}
