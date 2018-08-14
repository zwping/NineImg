package win.zwping.nineimg_lib;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import win.zwping.nineimg_lib.listener.OnNineImgListener;
import win.zwping.nineimg_lib.util.AnimationUtil;

/**
 * <p>describe：9图大图展示时，长按弹出的默认dialog
 * <p>    note：
 * <p> @author：zwp on 2017/11/28 0028 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public class SaveDialog extends DialogFragment {

    private View rootView;
    private String imgUrl;

    public static SaveDialog newInstance(String imgUrl) {
        SaveDialog fragment = new SaveDialog();
        fragment.setCurrentImageUrl(imgUrl);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        rootView = inflater.inflate(R.layout.dialog_save_layout, container, false);
        AnimationUtil.slideToUp(rootView);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(params);
        View decorView = window.getDecorView();
        decorView.setPadding(0, 0, 0, 0);
        decorView.setBackground(new ColorDrawable(Color.TRANSPARENT));
        decorView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    slideDown();
                }
                return true;
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayNineImgActivity.listener.onDisplayNineImgIsSave(imgUrl);
                slideDown();
            }
        });

        view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideDown();
            }
        });
    }

    private void setCurrentImageUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    private void slideDown() {
        AnimationUtil.slideToDown(rootView, new AnimationUtil.AnimationEndListener() {
            @Override
            public void onFinish() {
                dismiss();
            }
        });
    }
}
