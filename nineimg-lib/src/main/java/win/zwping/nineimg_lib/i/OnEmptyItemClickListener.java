package win.zwping.nineimg_lib.i;

/**
 * <p>describe：recyclerView空白区域点击监听（主要是在GridLayoutManager中空白区域）
 * <p>    note：
 * <p> @author：zwp on 2017/11/18 0018 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public interface OnEmptyItemClickListener {

    /**
     * GridLayoutManager的recyclerView中空白区域点击监听
     */
    void onEmptyItemClick();
}