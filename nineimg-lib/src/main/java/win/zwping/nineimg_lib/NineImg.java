package win.zwping.nineimg_lib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import win.zwping.nineimg_lib.i.OnItemClickListener;
import win.zwping.nineimg_lib.view.GridSpacingItemDecoration;
import win.zwping.plib.frame.PImageView;

/**
 * <p>describe：使用recyclerView使用9图功能
 * <p>    note：
 * <p> @author：zwp on 2017/10/31 mail：1101558280@qq.com web: http://www.zwping.win </p>
 * <p>
 */
public class NineImg extends RelativeLayout {


    //<editor-fold desc="构造函数">

    public NineImg(Context context) {
        super(context);
        initView(null);
    }

    public NineImg(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public NineImg(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public NineImg(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(attrs);
    }

    //</editor-fold>
    //<editor-fold desc="参数">

    private RecyclerView recyclerView;
    private Adapter adapter;
    private ArrayList<String> data;
    private float width;
    private int loadingImg, errorImg;

    /**
     * 自动设置图片大小（根据图片数量设置图片的宽高，默认为1/3宽度）
     */
    private boolean autoSize;
    /**
     * 分割线的大小
     */
    private int dividerSize = 2;

    /**
     * 子控件的监听
     */
    private OnItemClickListener onItemClickListener;
    /**
     * 展示大图
     */
    private boolean displayBigImg;
    /**
     * 展示大图需要的上下文
     */
    private Activity fromContext;
    //</editor-fold>
    //<editor-fold desc="功能变现">

    private void initView(AttributeSet attrs) {
        inflate(getContext(), R.layout.nine_img_layout, this);
        recyclerView = findViewById(R.id.nine_img_recycler);

        if (null == adapter) {
            data = new ArrayList<>();
            adapter = new Adapter();
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), getColumn()) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
            recyclerView.addItemDecoration(new GridSpacingItemDecoration(getColumn(), dividerSize, false));
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    private int getColumn() {
        int size = data.size();
        return autoSize ? size == 1 ? 1 : 3 : 3;
    }

    /**
     * 更新adapter
     */
    private void notifyData() {
        if (null != adapter) {
            recyclerView.removeItemDecoration(recyclerView.getItemDecorationAt(0));
            recyclerView.addItemDecoration(new GridSpacingItemDecoration(getColumn(), dividerSize, false));
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), getColumn()) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
            adapter.notifyDataSetChanged();
        }
    }
    //</editor-fold>
    //<editor-fold desc="API">


    /**
     * 设置资源
     *
     * @param list
     */
    public void setList(int layoutWidth, ArrayList<String> list) {
        width = layoutWidth;
        data = list;
        notifyData();
    }

    /**
     * 设置过渡图片
     *
     * @param loadingImgResId 不需要可填写0
     * @param errorImgResId   不需要可填写0
     */
    public void setPlaceholder(int loadingImgResId, int errorImgResId) {
        this.loadingImg = loadingImgResId;
        this.errorImg = errorImgResId;
    }

    /**
     * 根据图片数量，自动调整图片大小
     *
     * @param autoSize
     */
    public void setAutoSize(boolean autoSize) {
        this.autoSize = autoSize;
        notifyData();
    }

    /**
     * 设置子控件的监听
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    /**
     * 大图显示模式
     *
     * @param displayBigImg
     * @param activity
     */
    public void setBigImgDisplay(boolean displayBigImg, Activity activity) {
        this.displayBigImg = displayBigImg;
        this.fromContext = activity;
    }

    /**
     * 设置子控件之间的间距
     *
     * @param px
     */
    public void setLineWidth(int px) {
        this.dividerSize = px;
        notifyData();
    }
    //</editor-fold>
    //<editor-fold desc="adapter">

    private class Adapter extends RecyclerView.Adapter<ViewHolder> implements OnClickListener {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_nine_img_layout, parent, false);
            view.setOnClickListener(this);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (0 != width) {
                holder.imageView.getLayoutParams().height = (int) ((width - (((getColumn() - 1) * dividerSize))) / (autoSize && getColumn() == 1 ? 2.1f : 3));
                holder.imageView.getLayoutParams().width = (int) ((width - (((getColumn() - 1) * dividerSize))) / (autoSize && getColumn() == 1 ? 2.5f : 3));
                holder.imageView.setLayoutParams(holder.imageView.getLayoutParams());
            }
            holder.imageView.displayImage(data.get(position));
            holder.itemView.setTag(position);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        @Override
        public void onClick(View v) {
            if (null != onItemClickListener) {
                onItemClickListener.onItemClick(v, (Integer) v.getTag());
            } else if (displayBigImg && null != fromContext) {
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("list", data);
                bundle.putInt("currentPosition", (Integer) v.getTag());
                bundle.putInt("loadingImg", loadingImg);
                bundle.putInt("errorImg", errorImg);
                Intent intent = new Intent(fromContext, DisplayNineImgActivity.class);
                intent.putExtras(bundle);
//                if (Build.VERSION.SDK_INT >= 21) {
//                    fromContext.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(fromContext, v, "itemNineImg").toBundle());
//                } else {
                fromContext.startActivity(intent);
                fromContext.overridePendingTransition(R.anim.create_zoomin, R.anim.create_zoomout);
            }
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private PImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.nine_img_img);
            imageView.showGifFlag();
            if (0 != loadingImg) {
                imageView.setLoadingImg(loadingImg);
            }
            if (0 != errorImg) {
                imageView.setErrorImg(errorImg);
            }
        }
    }
    //</editor-fold>
}
