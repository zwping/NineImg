package win.zwping.nineimg_lib.util;

import android.content.Context;

/**
 * <p>describe：小方法
 * <p>    note：
 * <p> @author：zwp on 2018/4/10 0010 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public class Utils {

    public static int spToPx(Context context, float sp) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * fontScale);
    }
}
