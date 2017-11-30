package win.zwping.nineimg_lib.i;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

/**
 * <p>describe：9图点击事件interface
 * <p>    note：
 * <p> @author：zwp on 2017/11/1 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public interface OnItemClickListener {
    /**
     * 子控件点击事件
     *
     * @param viewHolder
     * @param data       当前nine data
     */
    void onItemClick(RecyclerView.ViewHolder viewHolder, ArrayList<String> data);
//    void onItemClick(RecyclerView.ViewHolder viewHolder, ArrayList<String> data);
}
