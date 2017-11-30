package win.zwping.nineimg.review;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import win.zwping.nineimg.NineImgInterface;
import win.zwping.nineimg_lib.NineImg;

/**
 * <p>describe：高度定制9图
 * <p>    note：
 * <p> @author：zwp on 2017/11/30 0030 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public class WeChatPhotoNineImg extends NineImg {

    public WeChatPhotoNineImg(Context context) {
        super(context);
        initView();
    }

    public WeChatPhotoNineImg(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public WeChatPhotoNineImg(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        this.setDisplayNineImgLoader(new NineImgInterface.WeChatDisplayNineImgLoader())
                .setNineImgLoader(new NineImgInterface.WeChatNineImgLoader())
                .setWidth(getResources().getDisplayMetrics().widthPixels)
                .setClickAutoDisplayNineImg(true)
                .setAutoSize(true)
                .setMaxItem(9);
    }
}
