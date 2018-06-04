package win.zwping.nineimg_lib;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import win.zwping.nineimg_lib.listener.OnNineImgListener;
import win.zwping.nineimg_lib.loader.OnNineImgLoader;


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
    /*** 是否启用长按保存功能 ***/
    private boolean saveState;

    /**
     * 长按显示保存的dialog
     */
    private SaveDialog saveDialog;

    /*** 子项单例 ***/
    public static OnNineImgLoader loader;
    public static OnNineImgListener listener;
    //</editor-fold>
    //<editor-fold desc="全局静态参数">

    //</editor-fold>
    //<editor-fold desc="内部方法">

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*set it to be no title*/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        /*set it to be full screen*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_display_nine_img);
        position = getIntent().getExtras().getInt("currentPosition");
        list = getIntent().getExtras().getStringArrayList("list");
        saveState = getIntent().getExtras().getBoolean("saveState");
        if (null == loader || null == loader.createDisplayView(this)) {
            throw new IllegalStateException("Un Set DisplayNineImgLoader");
        }

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

    /*** 解决ArrayIndexOutOfBoundsException 与 IllegalArgumentException：pointerIndex out of range的问题 Issues：https://github.com/panpf/sketch/issues/29 ***/
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result = true;
        try {
            result = super.dispatchTouchEvent(ev);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return result;
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
            View view = loader.createDisplayView(DisplayNineImgActivity.this);
            loader.loadDisplayView(DisplayNineImgActivity.this, list.get(position), view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            if (saveState && null != listener) {
                view.setOnLongClickListener(new View.OnLongClickListener() {
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
            }
            container.addView(view);
            return view;
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
