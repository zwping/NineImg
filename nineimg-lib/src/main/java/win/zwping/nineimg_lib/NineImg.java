package win.zwping.nineimg_lib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import java.util.ArrayList;

import win.zwping.nineimg_lib.adapter.GridSpacingItemDecoration;
import win.zwping.nineimg_lib.adapter.NineImgAdapter;
import win.zwping.nineimg_lib.adapter.RecyclerViewItemTouchListener;
import win.zwping.nineimg_lib.listener.OnNineImgListener;
import win.zwping.nineimg_lib.loader.OnNineImgLoader;
import win.zwping.nineimg_lib.util.Utils;

import static android.text.TextUtils.isEmpty;

/**
 * <p>describe：使用recyclerView使用9图功能
 * <p>    note：实现流式布局
 * <p>    note：跳转效果更换
 * <p>    note：
 * <p> @author：zwp on 2017/11/20 0020 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public class NineImg extends RecyclerView {

    //<editor-fold desc="构造函数">

    public NineImg(Context context) {
        super(context);
        initView(null);
    }

    public NineImg(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public NineImg(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(attrs);
    }
    //</editor-fold>
    //<editor-fold desc="内部参数">

    private NineImgAdapter adapter;
    /*** 加载的资源集合 可能包含占位数据 最终数据请根据这个方法获取{@link #getCurrentData()} ***/
    public ArrayList<String> data;
    /*** 固定9图的列数 ***/
    private int column;
    /*** 分割线的大小 ***/
    public int dividerSize;
    /*** 最大item数量 ***/
    private int maxItem;
    /*** 是否增加+栏目（适用于相片选择增加） ***/
    public boolean enablePlusItem;

    /*** 是否启用展示9图 ***/
    public boolean enableOpenDisplayNineImg;
    /*** 是否启用展示9图中保存的功能 ***/
    private boolean enableDisplayNineImgSave;

    /*** 当只有一个Item时,是否展示2.1f 2.5f的宽高 (美化 / plusItem功能) ***/
    private boolean oneBeautify;

    /*** 加载器 ***/
    private OnNineImgLoader loader;
    /*** Listener ***/
    private OnNineImgListener listener;

    //</editor-fold>
    //<editor-fold desc="内部方法">

    private void initView(AttributeSet attrs) {
        //去除嵌套引发自动获取焦点的bug
        setFocusableInTouchMode(false);
        requestFocus();

        getItemAnimator().setChangeDuration(0); //禁止刷新闪烁

        if (null != attrs) {
            TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.NineImg);
            try {
                column = array.getInt(R.styleable.NineImg_column, 3);
                dividerSize = array.getDimensionPixelSize(R.styleable.NineImg_dividerSize, Utils.spToPx(getContext(), 2));
                maxItem = array.getInt(R.styleable.NineImg_maxItem, 0);
                enablePlusItem = array.getBoolean(R.styleable.NineImg_enablePlusItem, false);
                enableOpenDisplayNineImg = array.getBoolean(R.styleable.NineImg_enableOpenDisplayNineImg, false);
                enableDisplayNineImgSave = array.getBoolean(R.styleable.NineImg_enableDisplayNineImgSave, false);
                oneBeautify = array.getBoolean(R.styleable.NineImg_enableOneBeautify, false);
            } finally {
                array.recycle();
            }
        }

        data = new ArrayList<>();
    }

    /*** 更新adapter ***/
    public void notifyData() {
        if (null == adapter) {
            adapter = new NineImgAdapter(this);
            setLayoutManager(new GridLayoutManager(getContext(), getColumn()) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
            addItemDecoration(new GridSpacingItemDecoration(getColumn(), dividerSize, false));
            setAdapter(adapter);
            //通过touch监听获取到空白区域的点击事件
            addOnItemTouchListener(new RecyclerViewItemTouchListener(this, getListener()));
        } else {
            removeItemDecoration(getItemDecorationAt(0));
            addItemDecoration(new GridSpacingItemDecoration(getColumn(), dividerSize, false));
            setLayoutManager(new GridLayoutManager(getContext(), getColumn()) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
            adapter.notifyDataSetChanged();
        }
    }

    /*** 截取data最大数量 ***/
    private void cutData() {
        if (maxItem != 0 && data.size() > maxItem) {
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < maxItem; i++) {
                list.add(data.get(i));
            }
            data = list;
        }
    }

    /*** 增加假数据 ***/
    private boolean addFalseDataFromPlusItem() {
        if (!enablePlusItem && data.size() > 0 && isEmpty(data.get(data.size() - 1))) { // 先保证非PlusItem的情况下，NineImg没有设置PlusItem
            data.remove(data.size() - 1);
        }
        boolean maxJudge = maxItem == 0 || maxItem > data.size();
        boolean plusItemJudge = data.size() == 0 || !isEmpty(data.get(data.size() - 1));
        if (enablePlusItem && maxJudge && plusItemJudge) {
            //System.out.println("NineImg.class 增加了占位符");
            data.add("");
            return true;
        }
        return false;
    }

    //</editor-fold>
    //<editor-fold desc="API">

    /*** 设置9图加载监听 ***/
    public NineImg setNineImgLoader(OnNineImgLoader loader1) {
        loader = loader1;
        return this;
    }

    public OnNineImgLoader getLoader() {
        return loader;
    }

    /*** 设置资源 ***/
    public NineImg setList(ArrayList<String> list) {
        data = new ArrayList<>();
        data = list;
        return this;
    }

    /*** 增加资源 ***/
    public NineImg addList(ArrayList<String> list) {
        data.addAll(0, list);
        return this;
    }

    /*** 增加资源 ***/
    public NineImg addString(String txt) {
        data.add(0, txt);
        return this;
    }

    /*** Builder初始化 ***/
    public void init() {
        cutData();
        addFalseDataFromPlusItem();
        post(new Runnable() {
            @Override
            public void run() {
                notifyData();
            }
        });
    }

    /*** 设置9图相关的监听 ***/
    public NineImg setNineImgListener(OnNineImgListener listener1) {
        listener = listener1;
        return this;
    }

    public OnNineImgListener getListener() {
        return listener;
    }

    /*** 设置9图的列数，不真真真的是9张图 ***/
    public NineImg setColumn(int c) {
        this.column = c;
        return this;
    }

    /*** 设置子控件之间的间距 ***/
    public NineImg setDividerSize(int px) {
        this.dividerSize = px;
        return this;
    }

    /*** 设置9图最大显示数量 ***/
    public NineImg setMaxItem(int max) {
        this.maxItem = max;
        return this;
    }

    /*** 设置添加+ item ***/
    public NineImg setAddPlusItem(boolean enablePlusItem) {
        this.enablePlusItem = enablePlusItem;
        return this;
    }

    /*** 获取当前9图中剩余的数据集合 ***/
    public ArrayList<String> getCurrentData() {
        if (enablePlusItem && isEmpty(data.get(data.size() - 1))) {
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                if (i != data.size() - 1) {
                    list.add(data.get(i));
                }
            }
            return list;
        } else {
            return data;
        }
    }

    /*** 获取当前列数(当前模式为1 item和多个 item的区别，多个item默认3列) ***/
    public int getColumn() {
        return getOneBeautify() && data.size() == 1 ? 1 : column;
    }

    /*** 获取当前只有一张图时是否美化 ***/
    public boolean getOneBeautify() {
        return !enablePlusItem && oneBeautify;
    }

    /*** 设置当前只有一张图时是否美化 ***/
    public NineImg setOneBeautify(boolean b) {
        this.oneBeautify = b;
        return this;
    }

    /*** 去除某个item ***/
    public void remove(int position) {
        if (null != adapter && position < data.size()) {
            data.remove(position);
            adapter.notifyItemRemoved(position);
        }
        if (addFalseDataFromPlusItem()) {
            adapter.notifyItemInserted(data.size() - 1);
        }
    }


    /*** 是否打开显示9图 ***/
    public NineImg setOpenDisplayNineImg(boolean enableOpenDisplayNineImg) {
        this.enableOpenDisplayNineImg = enableOpenDisplayNineImg;
        return this;
    }

    /*** 是否启用展示9图中保存的功能 ***/
    public NineImg setEnableDisplayNineImgSave(boolean enableDisplayNineImgSave) {
        this.enableDisplayNineImgSave = enableDisplayNineImgSave;
        return this;
    }

    /**
     * 大图预览
     * <br />外部调用这个api也必须设置{@link #setOpenDisplayNineImg(boolean)} }
     * <br />不使用框架自带的{{@link DisplayNineImgActivity}可以替换掉该方法
     *
     * @param data
     * @param position
     */
    public void displayNineImg(ArrayList<String> data, int position) {
        DisplayNineImgActivity.loader = loader;
        DisplayNineImgActivity.listener = listener;

        Bundle bundle = new Bundle();
        bundle.putStringArrayList("list", data);
        bundle.putInt("currentPosition", position);
        bundle.putBoolean("saveState", enableDisplayNineImgSave);

        Intent intent = new Intent(getContext(), DisplayNineImgActivity.class);
        intent.putExtras(bundle);
        ((Activity) getContext()).startActivity(intent);
        ((Activity) getContext()).overridePendingTransition(R.anim.create_zoomin, R.anim.create_zoomout);
    }

    //</editor-fold>
}
