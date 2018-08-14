package win.zwping.nineimg_lib.listener;

import android.content.Context;
import android.view.View;

import java.io.Serializable;

import win.zwping.nineimg_lib.i.OnNineImgListenerInterface;

/**
 * <p>describe：过滤9图相关事件
 * <p>    note：
 * <p> @author：zwp on 2018/4/10 0010 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public abstract class OnNineImgListener implements OnNineImgListenerInterface {

//    @Override
//    public void onItemClick(RecyclerView.ViewHolder holder, ArrayList<String> data) {
//    }


    @Override
    public void onChildItemClick(View view, int position) {

    }

    @Override
    public void onEmptyItemClick() {

    }

    @Override
    public void onPlusItemClick(Context context) {

    }

    @Override
    public void onDisplayNineImgIsSave(String url) {

    }
}
