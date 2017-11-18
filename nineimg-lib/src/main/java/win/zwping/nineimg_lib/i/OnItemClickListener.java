package win.zwping.nineimg_lib.i;

import android.view.View;

/**
 * <p>describe：9图点击事件interface
 * <p>    note：
 * <p> @author：zwp on 2017/11/1 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public interface OnItemClickListener {
    /**
     * 子控件点击事件
     *
     * @param v
     * @param position
     */
    void onItemClick(View v, int position);

    void onEmptyItemClick();

    //void onItemLongClick(View view, int position);
}
