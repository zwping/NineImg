package win.zwping.nineimg_lib.util;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * <p>describe：saveDialog中使用的动画
 * <p>    note：
 * <p> @author：zwp on 2017/11/28 0028 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public class AnimationUtil {

    public static void slideToDown(View view, final AnimationEndListener listener) {
        Animation slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 1.0f);

        slide.setDuration(200);
        slide.setFillEnabled(true);
        slide.setFillAfter(true);
        view.startAnimation(slide);

        slide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onFinish();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }


    public static void slideToUp(View view) {
        Animation slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);

        slide.setDuration(400);
        slide.setFillEnabled(true);
        slide.setFillAfter(true);
        view.startAnimation(slide);

    }


    public interface AnimationEndListener {
        void onFinish();
    }
}
