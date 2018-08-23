package win.zwping.nineimg;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import win.zwping.nineimg.config.NineImgConfig;
import win.zwping.nineimg_lib.NineImg;
import win.zwping.nineimg_lib.adapter.NineImgViewHolder;
import win.zwping.nineimg_lib.listener.OnNineImgListener;
import win.zwping.nineimg_lib.loader.OnNineImgLoader;
import win.zwping.plib.Utils;
import win.zwping.plib.frame.review.PImageView;
import win.zwping.plib.natives.utils.ToastUtils;

public class MainActivity extends AppCompatActivity {

    NineImg simple, standard;

    NineImg nineImg;

    private boolean hide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Utils.init(this);

        simple();

        standard();


        final ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("111");
        }
        nineImg = findViewById(R.id.nine_img);
        nineImg.setColumn(4).setMaxItem(10).setNineImgLoader(new OnNineImgLoader() {
            @Override
            public View createItemView(ViewGroup parent) {
                return LayoutInflater.from(MainActivity.this).inflate(R.layout.item_nine_img_approval_people_view, parent, false);
            }

            @Override
            public void loadItemView(Context context, NineImgViewHolder holder, String url) {
                ((PImageView) holder.getView(R.id.head_img_piv)).displayResourceImage(R.mipmap.yanhua);
                ((TextView) holder.getView(R.id.name_tv)).setText("占占");
                ((TextView) holder.getView(R.id.name_tv)).setVisibility(View.VISIBLE);
                holder.getView(R.id.clear_iv).setVisibility(View.VISIBLE);
                holder.getView(R.id.temp_rl).setVisibility(hide ? View.GONE : View.VISIBLE);
                holder.addChildViewClickListener(R.id.clear_iv);
            }

            @Override
            public void loadPlusItemView(Context context, NineImgViewHolder holder) {
                ((PImageView) holder.getView(R.id.head_img_piv)).displayResourceImage(R.mipmap.add_icon);
                holder.getView(R.id.clear_iv).setVisibility(View.GONE);
                holder.getView(R.id.name_tv).setVisibility(View.GONE);
                holder.getView(R.id.temp_rl).setVisibility(View.GONE);
            }

        }).setList(list).setAddPlusItem(true).setNineImgListener(new OnNineImgListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, ArrayList<String> data) {

            }

            @Override
            public void onChildItemClick(View view, int position) {
                super.onChildItemClick(view, position);
                nineImg.remove(position);
            }

            @Override
            public void onPlusItemClick(Context context) {
                super.onPlusItemClick(context);
                nineImg.addString("123").init();
            }
        }).init();


    }


    /*** - 不推荐使用这种方式 建议标准用法{@link #standard()}***/
    private void simple() {
        simple = findViewById(R.id.simple_nine_img);

        ArrayList<String> list = new ArrayList<>();
        list.add("http://zwping.oss-cn-shenzhen.aliyuncs.com/lu_big_gif.gif");
        list.add("http://zwping.oss-cn-shenzhen.aliyuncs.com/lu_big_gif.gif");
        simple.setNineImgLoader(new OnNineImgLoader() {
            @Override
            public View createItemView(ViewGroup parent) {
                PImageView iv = new PImageView(parent.getContext());
                iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                return iv;
            }

            @Override
            public void loadItemView(Context context, NineImgViewHolder holder, String url) {
                ((PImageView) holder.itemView).display(url);
            }

            @Override
            public View createDisplayView(Context context) {
                PImageView iv = new PImageView(context);
                iv.setBigModel();
                iv.playerGif(true);
                return iv;
            }

            @Override
            public void loadDisplayView(Context context, String url, View view) {
                super.loadDisplayView(context, url, view);
                ((PImageView) view).display(url);
            }
        }).setOpenDisplayNineImg(true).setList(list).init();
        list.add("http://zwping.oss-cn-shenzhen.aliyuncs.com/lu_big_gif.gif");
        list.add("http://zwping.oss-cn-shenzhen.aliyuncs.com/lu_big_gif.gif");
        list.add("http://zwping.oss-cn-shenzhen.aliyuncs.com/lu_big_gif.gif");
        // TODO: 2018/4/11 0011  NineImg 内部post监听绘制完成，数据源内存地址不变，数据内容改变，导致数据稍有错乱，很好规避，但确实是一个Bug，暂不处理
    }

    /*** 标准用法 ***/
    private void standard() {
        standard = findViewById(R.id.standard_nine_img);

        ArrayList<String> list = new ArrayList<>();
        list.add("http://zwping.oss-cn-shenzhen.aliyuncs.com/lu_big_gif.gif");
        list.add("http://zwping.oss-cn-shenzhen.aliyuncs.com/lu_big_gif.gif");
        list.add("http://zwping.oss-cn-shenzhen.aliyuncs.com/lu_big_gif.gif");
        standard.setNineImgLoader(NineImgConfig.Loader).setNineImgListener(listener).setList(list).init();
    }

    OnNineImgListener listener = new OnNineImgListener() {
        @Override
        public void onItemClick(RecyclerView.ViewHolder holder, ArrayList<String> data) {
            ToastUtils.showToast(holder.getAdapterPosition() + "");
        }

        @Override
        public void onChildItemClick(View view, int position) {
            super.onChildItemClick(view, position);
            standard.remove(position);
        }

        @Override
        public void onEmptyItemClick() {
            super.onEmptyItemClick();
            ToastUtils.showToast("点了空白区域");
        }

        @Override
        public void onPlusItemClick(Context context) {
            super.onPlusItemClick(context);
            ToastUtils.showToast("成功选择相片");
            //假象
            ArrayList<String> list = new ArrayList<>();
            list.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=762344264,3115248829&fm=27&gp=0.jpg");
            standard.addList(list).init();
        }

        @Override
        public void onDisplayNineImgIsSave(String url) {
            super.onDisplayNineImgIsSave(url);
            ToastUtils.showToast("保存至本地的相关逻辑");
        }
    };
}
