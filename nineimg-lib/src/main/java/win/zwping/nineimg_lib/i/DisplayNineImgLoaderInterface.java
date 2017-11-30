package win.zwping.nineimg_lib.i;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.Serializable;

import win.zwping.nineimg_lib.NineImg;

/**
 * <p>describe：打开9图相册对应的操作（展示+保存）
 * <p>    note：
 * <p> @author：zwp on 2017/10/24 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public interface DisplayNineImgLoaderInterface extends Serializable {

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

    /**
     * 保存回执
     *
     * @param imgUrl 当前图片的路径
     */
    void saveReturn(String imgUrl);
}
