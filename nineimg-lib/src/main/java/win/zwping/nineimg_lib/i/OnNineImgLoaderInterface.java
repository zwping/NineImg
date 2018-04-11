package win.zwping.nineimg_lib.i;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import win.zwping.nineimg_lib.adapter.NineImgViewHolder;

/**
 * <p>describe：9图相关的视图加载接口
 * <p>    note：
 * <p> @author：zwp on 2018/4/11 0011 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public interface OnNineImgLoaderInterface {
    /**
     * 创建recyclerViewItemView
     *
     * @param parent
     * @return
     */
    View createItemView(ViewGroup parent);

    /**
     * 装载子项的视图
     *
     * @param context
     * @param holder
     * @param url
     */
    void loadItemView(Context context, NineImgViewHolder holder, String url);

    /**
     * 装载+ 模块的视图
     *
     * @param context
     * @param holder
     */
    void loadPlusItemView(Context context, NineImgViewHolder holder);


    /**
     * 创建viewPager中的每一张图片
     *
     * @param context
     * @return
     */
    View createDisplayView(Context context);

    /**
     * 装载display的视图
     *
     * @param context
     * @param url
     * @param view
     */
    void loadDisplayView(Context context, String url, View view);
}
