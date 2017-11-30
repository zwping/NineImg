package win.zwping.nineimg_lib.i;

import android.view.View;

/**
 * <p>describe：adapter子布局的点击事件
 * <p>    note：
 * <p> @author：zwp on 2017/11/29 0029 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public interface OnChildItemClickListener {

    /**
     * adapter子布局的点击事件
     *
     * @param view
     * @param position
     */
    void onChildItemClick(View view, int position);
}
