package win.zwping.nineimg_lib.i;

import android.content.Context;
import android.widget.ImageView;

/**
 * <p>describe：打开9图相册对应的操作（展示）
 * <p>    note：
 * <p> @author：zwp on 2017/11/30 0030 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public interface SmallDisplayNineImgLoaderInterface {

    /**
     * 创建viewPager中的每一张图片
     *
     * @param context
     * @return
     */
    ImageView createView(Context context);

    /**
     * 展示图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    void displayImage(Context context, String url, ImageView imageView);
}
