package safebox.yiye.apackage.com.indextest.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Name: NetUtils
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-09 14:11
 */
public class NetUtils {

    private static HttpUtils httpUtils;
    public NetUtils() {
        httpUtils = new HttpUtils(1000 * 30);//设置请求超时的时间
        httpUtils.configCurrentHttpCacheExpiry(1000 * 0);//设置缓存5秒,5秒内直接返回上次成功请求的结果。
        httpUtils.configRequestThreadPoolSize(5);//设置由几条线程进行下载

    }
        /**
         * 判断网络是否连接
         * @param context
         * @return
         */
    public static boolean isConnected(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivity) {
            NetworkInfo activeNetworkInfo = connectivity.getActiveNetworkInfo();
            if (null != activeNetworkInfo && activeNetworkInfo.isConnected())
            {
                if (activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED)
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否是wifi连接
     * @param context
     * @return
     */
    public static boolean isWifi(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm == null)
            return false;
        return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     *  打开网络设置界面
     * @param activity
     */
    public static void openSetting(Activity activity)
    {
        Intent intent = new Intent("/");
        ComponentName cm = new ComponentName("com.android.settings",
                "com.android.settings.WirelessSettings");
        intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, 0);
    }

    /**
     * post请求网络
     *
     * @param url
     */
    public void getNetGetRequest(final String url, RequestParams params) {
        httpUtils.send(HttpRequest.HttpMethod.POST, url, params,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        if (mOnResponseNetFinish == null)
                            return;
                        mOnResponseNetFinish.onResponseSucceed(
                                responseInfo.result, url);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        if (mOnResponseNetFinish == null)
                            return;
                        mOnResponseNetFinish.onResponseFailure(msg, url);
                    }
                });
    }
    /**
     * 设置监听，当请求结束的时候
     *
     * @param mOnResponseNetFinish
     */
    public void setmOnResponseNetFinishListener(
            OnResponseNetFinishListener mOnResponseNetFinish) {
        this.mOnResponseNetFinish = mOnResponseNetFinish;
    }

    private OnResponseNetFinishListener mOnResponseNetFinish;
    /**
     * 当请求网络结束的时候回调
     */
    public interface OnResponseNetFinishListener {
        /**
         * 当请求成功的时候回调此方法
         *
         * @param result
         *            请求的信息
         * @param url
         *            链接的url
         */
        void onResponseSucceed(String result, String url);

        /**
         * 当请求失败的时候回调此方法
         *
         * @param result
         *            失败信息
         * @param url
         *            链接的url
         */
        void onResponseFailure(String result, String url);
    }

}
