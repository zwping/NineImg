package win.zwping.nineimg.config;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import win.zwping.nineimg.R;
import win.zwping.nineimg_lib.adapter.NineImgViewHolder;
import win.zwping.nineimg_lib.listener.OnNineImgListener;
import win.zwping.nineimg_lib.loader.OnNineImgLoader;
import win.zwping.plib.frame.review.PImageView;
import win.zwping.plib.natives.utils.ToastUtils;

/**
 * <p>describe：
 * <p>    note：
 * <p> @author：zwp on 2017/11/28 0028 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public class NineImgConfig {

    public static OnNineImgLoader Loader = new OnNineImgLoader() {
        @Override
        public View createItemView(ViewGroup parent) {
            return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nine_img_layout, parent, false);
        }

        @Override
        public void loadItemView(Context context, NineImgViewHolder holder, String url) {
            ((PImageView) holder.itemView.findViewById(R.id.nine_img_img)).display(url);
            holder.addChildViewClickListener(R.id.exit);
            ((ImageView) holder.getView(R.id.exit)).setVisibility(View.VISIBLE);
        }

        @Override
        public void loadPlusItemView(Context context, NineImgViewHolder holder) {
            super.loadPlusItemView(context, holder);
            ((PImageView) holder.itemView.findViewById(R.id.nine_img_img)).setBackgroundResource(R.drawable.ic_camera_black_24dp);
            ((ImageView) holder.getView(R.id.exit)).setVisibility(View.GONE);
        }

        @Override
        public View createDisplayView(Context context) {
            PImageView iv = new PImageView(context);
            iv.setBigModel();
            return iv;
        }

        @Override
        public void loadDisplayView(Context context, String url, View view) {
            super.loadDisplayView(context, url, view);
            ((PImageView) view).display(url);
        }
    };

}
