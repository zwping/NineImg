package win.zwping.nineimg_lib.listener;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

/**
 * <p>describe：9图中+item对应的加载和点击事件
 * <p>    note：
 * <p> @author：zwp on 2017/11/30 0030 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public interface OnPlusItemLoaderListener {
    /**
     * + 模块的
     *
     * @param context
     * @param viewHolder
     */
    void onPlusItemLoader(Context context, RecyclerView.ViewHolder viewHolder);

    /**
     * + 模块的点击事件
     *
     * @param context
     */
    void onPlusItemClick(Context context);
}
