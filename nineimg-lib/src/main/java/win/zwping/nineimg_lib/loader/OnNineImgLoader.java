package win.zwping.nineimg_lib.loader;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.Serializable;

import win.zwping.nineimg_lib.adapter.NineImgViewHolder;
import win.zwping.nineimg_lib.i.OnNineImgLoaderInterface;

/**
 * <p>describe：9图加载器
 * <p>    note：在这使用抽象类过滤，使逻辑简单{@link OnNineImgLoaderInterface}
 * <p>    note：
 * <p> @author：zwp on 2018/4/11 0011 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public abstract class OnNineImgLoader implements OnNineImgLoaderInterface {

//    @Override
//    public View createItemView(ViewGroup parent) {
//        return null;
//    }
//
//    @Override
//    public void loadItemView(Context context, NineImgViewHolder holder, String url) {
//    }

    @Override
    public void loadPlusItemView(Context context, NineImgViewHolder holder) {

    }

    @Override
    public View createDisplayView(Context context) {
        return null;
    }

    @Override
    public void loadDisplayView(Context context, String url, View view) {

    }
}
