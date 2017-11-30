package win.zwping.nineimg_lib.i;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.Serializable;

import win.zwping.nineimg_lib.NineImg;

/**
 * <p>describe：
 * <p>    note：
 * <p> @author：zwp on 2017/10/24 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public interface NineImgLoaderInterface extends Serializable {
    /**
     * 创建recyclerViewItemView
     *
     * @param parent
     * @return
     */
    View createView(ViewGroup parent);

    /**
     * 展示图片
     *
     * @param context
     * @param holder
     * @param url
     */
    void displayImage(Context context, NineImg.ViewHolder holder, String url);

}
