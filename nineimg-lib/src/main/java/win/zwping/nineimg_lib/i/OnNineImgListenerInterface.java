package win.zwping.nineimg_lib.i;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

/**
 * <p>describe：9图相关的事件传递接口
 * <p>    note：
 * <p> @author：zwp on 2018/4/10 0010 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public interface OnNineImgListenerInterface {

    /**
     * adapter子控件的点击回执
     *
     * @param holder
     * @param data       当前nine data
     */
    void onItemClick(RecyclerView.ViewHolder holder, ArrayList<String> data);

    /**
     * adapter子布局的点击回执
     *
     * @param view
     * @param position
     */
    void onChildItemClick(View view, int position);

    /**
     * adapter中空白区域点击回执
     * todo 事件透传？
     */
    void onEmptyItemClick();

    /**
     * + 模块的点击事件
     *
     * @param context
     */
    void onPlusItemClick(Context context);

    /**
     * 显示9图中保存的回执
     *
     * @param url
     */
    void onDisplayNineImgIsSave(String url);

}
