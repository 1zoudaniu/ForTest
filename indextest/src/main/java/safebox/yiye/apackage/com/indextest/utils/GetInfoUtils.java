package safebox.yiye.apackage.com.indextest.utils;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Name: GetInfoUtils
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-09 13:54
 */
public class GetInfoUtils {
    /**
     *  得到当前应用的版本号
     * @return
     */
    public static String getVersion(Activity activity) {
        String version;
        // 得到包管理器对象
        PackageManager packageManager = activity.getPackageManager();
        try {
            //得到当前应用的包信息
            PackageInfo packageInfo = packageManager.getPackageInfo(activity.getPackageName(), -1);
            version =  packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            version = "版本未知";
        }
        return version;
    }
}
