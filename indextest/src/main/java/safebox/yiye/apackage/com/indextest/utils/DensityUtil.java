package safebox.yiye.apackage.com.indextest.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Name: DensityUtil
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-10 09:59
 *  dp与px之间的互换
 */
public class DensityUtil {
    private DensityUtil() {
        throw new UnsupportedOperationException("不能初始化");
    }

    /**
     * dp到像素px的转化
     *
     * @param context
     * @param dp
     * @return
     */
    public static int dp2dx(Context context, float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

    /**
     * sp到像素px的转化
     *
     * @param context
     * @param sp
     * @return
     */
    public static int sp2px(Context context, float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                sp, context.getResources().getDisplayMetrics());
    }

//	    setTextSize(TypedValue.COMPLEX_UNIT_PX,22); //22像素
//	    setTextSize(TypedValue.COMPLEX_UNIT_SP,22); //22SP
//	    setTextSize(TypedValue.COMPLEX_UNIT_DIP,22);//22DIP

    /**
     * 像素px到dp的转化
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * 像素px到sp的转化
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }
}
