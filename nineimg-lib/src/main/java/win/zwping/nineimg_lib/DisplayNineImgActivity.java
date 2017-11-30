package win.zwping.nineimg_lib;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import win.zwping.nineimg_lib.i.DisplayNineImgLoaderInterface;


/**
 * <p>describe：显示9图及转场动画
 * <p>    note：可以不使用该页面
 * <p>    note：
 * <p> @author：zwp on ${DATE} mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public class DisplayNineImgActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    //<editor-fold desc="内部参数">
    private ViewPager viewPager;
    private TextView textView;

    private List<String> list;
    private int position;

    /**
     * 长按显示保存的dialog
     */
    private SaveDialog saveDialog;
    //</editor-fold>
    //<editor-fold desc="全局静态参数">

    public static DisplayNineImgLoaderInterface loaderInterface;
    //</editor-fold>
    //<editor-fold desc="内部方法">

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null == loaderInterface)
            throw new RuntimeException("请预先设置displayNineImgActivity图片加载接口【DisplayNineImgLoaderInterface】");
         /*set it to be no title*/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
         /*set it to be full screen*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_display_nine_img);
        position = getIntent().getExtras().getInt("currentPosition");
        list = getIntent().getExtras().getStringArrayList("list");

        viewPager = findViewById(R.id.viewpager);
        textView = findViewById(R.id.number);

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
        viewPager.addOnPageChangeListener(this);

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.finish_zoomin, R.anim.finish_zoomout);
    }
    //</editor-fold>
    //<editor-fold desc="API">

    public static void setLoaderInterface(DisplayNineImgLoaderInterface imgLoaderInterface) {
        loaderInterface = imgLoaderInterface;
    }
    //</editor-fold>
    //<editor-fold desc="viewPager">

    PagerAdapter adapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView imageView = loaderInterface.createView(DisplayNineImgActivity.this);
            loaderInterface.displayImage(DisplayNineImgActivity.this, list.get(position), imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (null == saveDialog) {
                        saveDialog = SaveDialog.newInstance(list.get(position));
                    }
                    if (!saveDialog.isAdded()) {
                        saveDialog.show(getSupportFragmentManager(), "saveDialog");
                    }
                    return true;
                }
            });
            container.addView(imageView);
            return imageView;
        }
    };
    //<editor-fold desc="viewPager切换监听">

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        this.position = position;
        textView.setText(position + 1 + " / " + list.size());
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    //</editor-fold>
    //</editor-fold>
}
