package win.zwping.nineimg;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import win.zwping.nineimg_lib.NineImg;
import win.zwping.nineimg_lib.i.DisplayNineImgLoaderInterface;
import win.zwping.nineimg_lib.i.NineImgLoaderInterface;
import win.zwping.plib.frame.review.PImageView;

/**
 * <p>describe：
 * <p>    note：
 * <p> @author：zwp on 2017/11/28 0028 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public class NineImgInterface {

    public class NineImgLoader implements NineImgLoaderInterface {

        @Override
        public void displayImage(Context context, NineImg.ViewHolder holder, String url) {

        }

        @Override
        public View createView(ViewGroup parent) {
            PImageView pImageView = new PImageView(parent.getContext());
//            pImageView
            return pImageView;
        }
    }

    public class DisplayNineImgLoader implements DisplayNineImgLoaderInterface {

        @Override
        public void displayImage(Context context, String url, ImageView imageView) {

        }

        @Override
        public ImageView createView(Context context) {
            return null;
        }

        @Override
        public void saveReturn(String imgUrl) {

        }
    }
}
