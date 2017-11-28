package win.zwping.nineimg;

import android.content.Context;
import android.view.LayoutInflater;
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

    public static class NineImgLoader implements NineImgLoaderInterface {

        @Override
        public void displayImage(Context context, NineImg.ViewHolder holder, String url) {
            ((PImageView) holder.getView(R.id.nine_img_img)).displayImage(url);
        }

        @Override
        public View createView(ViewGroup parent) {
//            PImageView pImageView = new PImageView(parent.getContext());
//            pImageView.showGifFlag();
//            pImageView.setTransitionImg();
//            pImageView.setPlaceholder(R.mipmap.error_picture, R.mipmap.error_picture);
            return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_small_nine_img_layout, parent, false);
        }
    }

    public static class DisplayNineImgLoader implements DisplayNineImgLoaderInterface {

        @Override
        public void displayImage(Context context, String url, ImageView imageView) {
            PImageView pImageView = (PImageView) imageView;
            pImageView.setBigModel();
            pImageView.showGifFlag();
            pImageView.playerGif(true);
            pImageView.setPlaceholder(R.mipmap.error_picture, R.mipmap.error_picture);
            pImageView.display(url);
        }

        @Override
        public ImageView createView(Context context) {
            return new PImageView(context);
        }

        @Override
        public void saveReturn(String imgUrl) {

        }
    }
}
