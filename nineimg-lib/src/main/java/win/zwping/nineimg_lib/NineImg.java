package win.zwping.nineimg_lib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import win.zwping.nineimg_lib.i.NineImgLoaderInterface;
import win.zwping.nineimg_lib.i.OnEmptyItemClickListener;
import win.zwping.nineimg_lib.i.OnItemClickListener;
import win.zwping.nineimg_lib.view.GridSpacingItemDecoration;

/**
 * <p>describe：使用recyclerView使用9图功能
 * <p>    note：实现流式布局
 * <p>    note：跳转效果更换
 * <p>    note：
 * <p> @author：zwp on 2017/11/20 0020 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public class NineImg extends RecyclerView implements OnItemClickListener {

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

    private Adapter adapter;
    private ArrayList<String> data;
    /**
     * 外部传入该控件的宽度，避免内部重复计算
     */
    private float width;

    /**
     * 自动设置图片大小（根据图片数量设置图片的宽高，默认为1/3宽度）
     */
    private boolean autoSize;
    /**
     * 分割线的大小
     */
    private int dividerSize = 2;
    /**
     * 最大item数量
     */
    private int maxItem;
    /**
     * 是否增加+栏目（适用于相片选择增加）
     */
    private boolean addPlusItem;

    /**
     * + item Resource
     */
    private int plusItemResource;

    /**
     * 子控件的监听(OnItemClickListener中包含OnEmptyItemChickListener)
     */
    private OnItemClickListener onItemClickListener;
    private OnEmptyItemClickListener emptyItemClickListener;
    /**
     * 展示大图
     */
    private boolean displayNineImg;
    /**
     * 展示大图需要的上下文
     */
    private Activity fromContext;


    //</editor-fold>
    //<editor-fold desc="全局静态参数">

    public static NineImgLoaderInterface loaderInterface;
    //</editor-fold>
    //<editor-fold desc="功能变现">

    private void initView(AttributeSet attrs) {
        if (null == adapter) {
            data = new ArrayList<>();
            adapter = new Adapter();
            setLayoutManager(new GridLayoutManager(getContext(), getColumn()) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
            addItemDecoration(new GridSpacingItemDecoration(getColumn(), dividerSize, false));
            setAdapter(adapter);
            addOnItemTouchListener(new RecyclerViewItemTouchListener(this, this));
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * 获取当前列数(当前模式为1 item和多个 item的区别，多个item默认3列)
     */
    private int getColumn() {
        int size = data.size();
        return autoSize ? size == 1 ? 1 : 3 : 3;
    }

    /**
     * 更新adapter
     */
    private void notifyData() {
        if (null != adapter) {
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
    //<editor-fold desc="recyclerView点击事件">

    @Override
    public void onItemClick(RecyclerView.ViewHolder viewHolder, ArrayList<String> data) {  //recyclerView的点击事件
        if (null != onItemClickListener) {
            onItemClickListener.onItemClick(viewHolder, getCurrentData());
        } else if (displayNineImg && null != fromContext) {
            if (addPlusItem && this.data.size() - 1 == viewHolder.getAdapterPosition()) {
                //规避点击事件，让事件透传出去
            } else {
                displayNineImg(getCurrentData(), viewHolder.getAdapterPosition());
            }
        } else {

        }
    }

    @Override
    public void onEmptyItemClick() {  //recyclerView空白区域的点击事件
        if (null != emptyItemClickListener) {
            emptyItemClickListener.onEmptyItemClick();
        } else {

        }
        if (null != onItemClickListener) {
            onItemClickListener.onEmptyItemClick();
        } else {

        }
    }
    //</editor-fold>
    //</editor-fold>
    //<editor-fold desc="API">

    /**
     * 设置9图加载模式
     *
     * @param loading
     */
    public static void setNineImgLoader(NineImgLoaderInterface loading) {
        loaderInterface = loading;
    }

    /**
     * 设置资源
     *
     * @param layoutWidth 控件宽度
     * @param list
     */
    public NineImg setList(int layoutWidth, ArrayList<String> list) {
        if (null == loaderInterface)
            throw new RuntimeException("请预先设置nineImg图片加载接口【NineImgLoaderInterface】");
        // TODO: 2017/11/18 0018 动态获取控件的宽度
        width = layoutWidth;
        data = list;
        return this;
    }

    /**
     * 根据图片数量，自动调整图片大小
     * <br />控制列数{@link #getColumn()}
     *
     * @param autoSize
     */
    public NineImg setAutoSize(boolean autoSize) {
        this.autoSize = autoSize;
        return this;
    }

    /**
     * 设置子控件的监听
     *
     * @param listener
     */
    public NineImg setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
        return this;
    }

    /**
     * GridLayoutManager的recyclerView中空白区域点击监听
     *
     * @param listener
     */
    public NineImg setOnEmptyItemClickListener(OnEmptyItemClickListener listener) {
        this.emptyItemClickListener = listener;
        return this;
    }

    /**
     * 大图显示模式
     *
     * @param displayBigImg
     * @param activity
     */
    public NineImg setAutoDisplayNineImg(boolean displayBigImg, Activity activity) {
        this.displayNineImg = displayBigImg;
        this.fromContext = activity;
        return this;
    }

    /**
     * 设置子控件之间的间距
     *
     * @param px
     */
    public NineImg setLineWidth(int px) {
        this.dividerSize = px;
        return this;
    }

    /**
     * 设置9图最大显示数量
     *
     * @param max
     * @return
     */
    public NineImg setMaxItem(int max) {
        this.maxItem = max;
        return this;
    }

    /**
     * 设置添加+ item
     *
     * @param addPlusItem
     * @param max
     * @return
     */
    public NineImg setAddPlusItem(boolean addPlusItem, int max, OnPlusItemLoadListener plusItemLoadListener) {
        setMaxItem(max);
        this.plusItemLoadListener = plusItemLoadListener;
        this.addPlusItem = addPlusItem;
        return this;
    }

    public NineImg init() {
        if (maxItem != 0 && data.size() > maxItem) {
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < maxItem; i++) {
                list.add(data.get(i));
            }
            data = list;
        }
        if (addPlusItem && (0 == maxItem || data.size() < maxItem)) {
            data.add("");
        }
        notifyData();
        return this;
    }

    /**
     * 获取当前9图中剩余的数据
     *
     * @return
     */
    public ArrayList<String> getCurrentData() {
        if (addPlusItem && TextUtils.isEmpty(data.get(data.size() - 1))) {
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

    /**
     * 去除某个item
     *
     * @param position
     */
    public void remove(int position) {
        if (null != adapter && position < data.size()) {
            data.remove(position);
            adapter.notifyItemRemoved(position);
        }
    }

    /**
     * 大图预览
     * <br />外部调用这个api也必须设置{@link #setAutoDisplayNineImg(boolean, Activity)}
     *
     * @param data
     * @param position
     * @deprecated 不使用{{@link DisplayNineImgActivity}可以替换掉该方法
     */
    public void displayNineImg(ArrayList<String> data, int position) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("list", data);
        bundle.putInt("currentPosition", position);
        Intent intent = new Intent(fromContext, DisplayNineImgActivity.class);
        intent.putExtras(bundle);
        fromContext.startActivity(intent);
        fromContext.overridePendingTransition(R.anim.create_zoomin, R.anim.create_zoomout);
    }
    //</editor-fold>
    //<editor-fold desc="adapter">

    private class Adapter extends RecyclerView.Adapter<ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(loaderInterface.createView(parent));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (0 != width) {
                holder.itemView.getLayoutParams().height = (int) ((width - (((getColumn() - 1) * dividerSize))) / (autoSize && getColumn() == 1 ? 2.1f : 3));
                holder.itemView.getLayoutParams().width = (int) ((width - (((getColumn() - 1) * dividerSize))) / (autoSize && getColumn() == 1 ? 2.5f : 3));
                holder.itemView.setLayoutParams(holder.itemView.getLayoutParams());
            }
            if (addPlusItem && data.size() - 1 == position) {
                plusItemLoadListener.onPlusItemLoad(getContext(), holder);
            } else {
                loaderInterface.displayImage(getContext(), holder, data.get(position));
            }
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    public interface OnPlusItemLoadListener {
        void onPlusItemLoad(Context context, RecyclerView.ViewHolder viewHolder);
    }

    private OnPlusItemLoadListener plusItemLoadListener;

    public class ViewHolder extends RecyclerView.ViewHolder {

        private SparseArray<View> mViews;

        public ViewHolder(View itemView) {
            super(itemView);
            mViews = new SparseArray<>();
        }

        public <T extends View> T getView(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }

    }
    //</editor-fold>
}
