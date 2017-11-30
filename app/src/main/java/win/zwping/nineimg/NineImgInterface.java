package win.zwping.nineimg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import win.zwping.nineimg_lib.NineImg;
import win.zwping.nineimg_lib.i.DisplayNineImgLoaderInterface;
import win.zwping.nineimg_lib.i.NineImgLoaderInterface;
import win.zwping.nineimg_lib.i.SmallDisplayNineImgLoaderInterface;
import win.zwping.plib.frame.review.PImageView;

/**
 * <p>describe：
 * <p>    note：
 * <p> @author：zwp on 2017/11/28 0028 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public class NineImgInterface {

    /*=======================微信相册==============================*/

    public static class WeChatNineImgLoader implements NineImgLoaderInterface {

        @Override
        public void displayImage(Context context, NineImg.ViewHolder holder, String url) {
            ((PImageView) holder.itemView).displayImage(url);
        }

        @Override
        public View createView(ViewGroup parent) {
            PImageView pImageView = new PImageView(parent.getContext());
            pImageView.showGifFlag();
            pImageView.setTransitionImg();
            pImageView.setPlaceholder(R.mipmap.error_picture, R.mipmap.error_picture);
            return pImageView;
//            return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_small_nine_img_layout, parent, false);
        }
    }

    public static class WeChatDisplayNineImgLoader implements SmallDisplayNineImgLoaderInterface {

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
    }
    /*=======================微信相册==============================*/
    /*=======================选择相片==============================*/

    public static class SelectPicNineImgLoader implements NineImgLoaderInterface {

        @Override
        public View createView(ViewGroup parent) {
            return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nine_img_layout, parent, false);
        }

        @Override
        public void displayImage(Context context, NineImg.ViewHolder holder, String url) {
            Glide.with(context).load(url).asBitmap().centerCrop().into((ImageView) holder.getView(R.id.nine_img_img));
            holder.itemView.findViewById(R.id.exit).setVisibility(View.VISIBLE);
            holder.addOnClickListener(R.id.exit);
        }
    }

    public static class SelectPicDisplayNineImgLoader implements SmallDisplayNineImgLoaderInterface {
        @Override
        public ImageView createView(Context context) {
            return new ImageView(context);
        }

        @Override
        public void displayImage(Context context, String url, ImageView imageView) {
            Glide.with(context).load(url).asBitmap().centerCrop().into(imageView);
        }
    }
    /*=======================选择相片==============================*/
}
