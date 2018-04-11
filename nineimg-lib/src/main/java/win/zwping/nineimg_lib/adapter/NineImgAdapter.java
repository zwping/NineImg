package win.zwping.nineimg_lib.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import win.zwping.nineimg_lib.NineImg;

/**
 * <p>describe：9图专属Adapter
 * <p>    note：
 * <p> @author：zwp on 2018/4/10 0010 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public class NineImgAdapter extends RecyclerView.Adapter<NineImgViewHolder> {

    private NineImg nineImg;

    public NineImgAdapter(NineImg nineImg) {
        this.nineImg = nineImg;
    }

    @NonNull
    @Override
    public NineImgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (null == nineImg.getLoader()) {
            throw new IllegalStateException("Un Set NineImgLoader");
        }
        if (null == nineImg.getLoader().createItemView(parent)) {
            throw new IllegalStateException("Un Set NineImgLoader createItemView");
        }
        return new NineImgViewHolder(nineImg.getLoader().createItemView(parent), nineImg);
    }

    @Override
    public void onBindViewHolder(@NonNull final NineImgViewHolder holder, int position) {
        if (0 != nineImg.getWidth()) {
            // 防止在loader中直接new 一个视图导致params缺失
            if (null == holder.itemView.getLayoutParams()) {
                holder.itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            } else {

            }

            holder.itemView.getLayoutParams().height = (int) ((nineImg.getWidth() - (((nineImg.getColumn() - 1) * nineImg.dividerSize))) / (nineImg.getColumn() == 1 ? 2.1f : nineImg.getColumn()));
            holder.itemView.getLayoutParams().width = (int) ((nineImg.getWidth() - (((nineImg.getColumn() - 1) * nineImg.dividerSize))) / (nineImg.getColumn() == 1 ? 2.5f : nineImg.getColumn()));
            holder.itemView.setLayoutParams(holder.itemView.getLayoutParams());
        } else {

        }
        //加载视图，注意一些控制需要成对出现
        if (nineImg.enablePlusItem && nineImg.data.size() - 1 == position && TextUtils.isEmpty(nineImg.data.get(position))) {
            System.out.println("222222222222222222222222222222222222");
            nineImg.getLoader().loadPlusItemView(nineImg.getContext(), holder);
        } else {
            System.out.println("111111111111111111111111111111111111");
            nineImg.getLoader().loadItemView(nineImg.getContext(), holder, nineImg.data.get(position));
        }

        //子项点击监听
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nineImg.enablePlusItem && nineImg.data.size() - 1 == holder.getAdapterPosition() && TextUtils.isEmpty(nineImg.data.get(holder.getAdapterPosition()))) {
                    if (null != nineImg.getListener()) {
                        nineImg.getListener().onPlusItemClick(nineImg.getContext());
                    } else {

                    }
                } else {
                    if (nineImg.enableOpenDisplayNineImg) {
                        nineImg.displayNineImg(nineImg.getCurrentData(), holder.getAdapterPosition());
                    } else {
                        if (null != nineImg.getListener()) {
                            nineImg.getListener().onItemClick(holder, nineImg.getCurrentData());
                        } else {

                        }
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return nineImg.data.size();
    }
}
