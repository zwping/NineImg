package win.zwping.nineimg.review;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.lzy.imagepicker.ui.ImageGridActivity;

import win.zwping.nineimg.NineImgInterface;
import win.zwping.nineimg.R;
import win.zwping.nineimg_lib.NineImg;
import win.zwping.nineimg_lib.listener.OnPlusItemLoaderListener;

/**
 * <p>describe：高度定制9图
 * <p>    note：
 * <p> @author：zwp on 2017/11/30 0030 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public class SelectPhotoNineImg extends NineImg {

    public SelectPhotoNineImg(Context context) {
        super(context);
        initView();
    }

    public SelectPhotoNineImg(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SelectPhotoNineImg(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        this.setNineImgLoader(new NineImgInterface.SelectPicNineImgLoader())
                .setDisplayNineImgLoader(new NineImgInterface.SelectPicDisplayNineImgLoader())
                .setWidth(getResources().getDisplayMetrics().widthPixels)
                .setClickAutoDisplayNineImg(true)
                .setAddPlusItem(true, 9, new OnPlusItemLoaderListener() {
                    @Override
                    public void onPlusItemLoader(Context context, RecyclerView.ViewHolder viewHolder) {
                        viewHolder.itemView.findViewById(R.id.exit).setVisibility(View.GONE);
                        ((ImageView) viewHolder.itemView.findViewById(R.id.nine_img_img)).setImageResource(R.drawable.ic_add_black_24dp);
                    }

                    @Override
                    public void onPlusItemClick(Context context) {
                        Intent intent = new Intent(context, ImageGridActivity.class);
                        ((Activity) getContext()).startActivityForResult(intent, 100);
                    }
                });
    }
}
