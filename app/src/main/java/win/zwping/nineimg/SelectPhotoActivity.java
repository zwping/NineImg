package win.zwping.nineimg;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.loader.ImageLoader;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.io.File;
import java.util.ArrayList;

import win.zwping.nineimg_lib.DisplayNineImgActivity;
import win.zwping.nineimg_lib.NineImg;
import win.zwping.nineimg_lib.listener.OnChildItemClickListener;
import win.zwping.nineimg_lib.listener.OnPlusItemLoaderListener;

public class SelectPhotoActivity extends AppCompatActivity {

    private NineImg nineImg;
    private ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_photo);
        nineImg = findViewById(R.id.nine_img);

        imagePickerConfig();

        data = new ArrayList<>();
        nineImg
                .setNineImgLoader(new NineImgInterface.SelectPicNineImgLoader())
                .setDisplayNineImgLoader(new NineImgInterface.SelectPicDisplayNineImgLoader())
                .setWidth(getResources().getDisplayMetrics().widthPixels)
//                .setOnItemClickListener()
//                .setOnEmptyItemClickListener()
                .setOnChildItemClickListener(new OnChildItemClickListener() {
                    @Override
                    public void onChildItemClick(View view, int position) {
                        nineImg.remove(position);
                        ImagePicker.getInstance().setSelectLimit(9 - nineImg.getCurrentData().size());
                    }
                })
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
                        startActivityForResult(intent, 100);
                    }
                })
                .init()
        ;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 100) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                ArrayList<String> list = new ArrayList<>();
                for (int i = 0; i < images.size(); i++) {
                    list.add(images.get(i).path);
                }
                nineImg.addList(list).init();
                ImagePicker.getInstance().setSelectLimit(9 - nineImg.getCurrentData().size());
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //<editor-fold desc="ImagePicker config">

    public class PicassoImageLoader implements ImageLoader {

        @Override
        public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
            Glide.with(activity)
                    .load(Uri.fromFile(new File(path)))
                    .into(imageView);
        }

        @Override
        public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
            Glide.with(activity)
                    .load(Uri.fromFile(new File(path)))
                    .into(imageView);
        }

        @Override
        public void clearMemoryCache() {
            //这里是清除缓存的方法,根据需要自己实现
        }
    }

    private void imagePickerConfig() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new PicassoImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }
    //</editor-fold>
}
