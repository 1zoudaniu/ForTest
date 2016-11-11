package safebox.yiye.apackage.com.indextest.utils;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

/**
 * Name: ToastDebugUtils
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-09 14:19
 */
public class ToastDebugUtils {


    /**
     * 显示短时间的toast提示
     *
     * @param activity
     * @param msg
     */
    //提示框  短时间内弹出土司
    static Toast mToast;

    public static void showShortMsg(final Activity activity, final String msg) {

        if (!TextUtils.isEmpty(msg)) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mToast == null) {
                        mToast = Toast.makeText(activity, msg,
                                Toast.LENGTH_SHORT);
                    } else {
                        mToast.setText(msg);
                    }
                    mToast.show();
                }
            });
        }
    }

    /**
     * 显示长时间的toast提示
     *
     * @param activity
     * @param msg
     */
    public static void showLongMsg(final Activity activity, final String msg) {

        if (!TextUtils.isEmpty(msg)) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mToast == null) {
                        mToast = Toast.makeText(activity, msg,
                                Toast.LENGTH_SHORT);
                    } else {
                        mToast.setText(msg);
                    }
                    mToast.show();
                }
            });
        }
    }

    private static String tagPrefix = "";
    private static boolean showV = true;
    private static boolean showD = true;
    private static boolean showI = true;
    private static boolean showW = true;
    private static boolean showE = true;
    private static boolean showWTF = true;

    /**
     * 得到tag（所在类.方法（L:行））
     *
     * @return
     */
    private static String generateTag() {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
        String callerClazzName = stackTraceElement.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        String tag = "%s.%s(L:%d)";

        Object[] args = {callerClazzName, stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber())};
        tag = String.format(tag, args);
        //给tag设置前缀
        tag = TextUtils.isEmpty(tagPrefix) ? tag : tagPrefix + ":" + tag;
        return tag;
    }

    public static void v(String msg) {
        if (showV) {
            String tag = generateTag();
            Log.v(tag, msg);
        }
    }

    public static void v(String msg, Throwable tr) {
        if (showV) {
            String tag = generateTag();
            Log.v(tag, msg, tr);
        }
    }

    public static void d(String msg) {
        if (showD) {
            String tag = generateTag();
            Log.d(tag, msg);
        }
    }

    public static void d(String msg, Throwable tr) {
        if (showD) {
            String tag = generateTag();
            Log.d(tag, msg, tr);
        }
    }

    //TODO
    public static void i(String tag, String msg) {
        if (showI) {
            String tag1 = generateTag();
            Log.i(tag, msg);
        }
    }

    public static void i(String msg, Throwable tr) {
        if (showI) {
            String tag = generateTag();
            Log.i(tag, msg, tr);
        }
    }

    public static void w(String msg) {
        if (showW) {
            String tag = generateTag();
            Log.w(tag, msg);
        }
    }

    public static void w(String msg, Throwable tr) {
        if (showW) {
            String tag = generateTag();
            Log.w(tag, msg, tr);
        }
    }

    public static void e(String msg) {
        if (showE) {
            String tag = generateTag();
            Log.e(tag, msg);
        }
    }

    public static void e(String msg, Throwable tr) {
        if (showE) {
            String tag = generateTag();
            Log.e(tag, msg, tr);
        }
    }

    public static void wtf(String msg) {
        if (showWTF) {
            String tag = generateTag();
            Log.wtf(tag, msg);
        }
    }

    public static void wtf(String msg, Throwable tr) {
        if (showWTF) {
            String tag = generateTag();
            Log.wtf(tag, msg, tr);
        }
    }
}
