package safebox.yiye.apackage.com.indextest.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.widget.ProgressBar;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import safebox.yiye.apackage.com.indextest.activity.SplashActivity;
import safebox.yiye.apackage.com.indextest.bean.UpdateInfo;
import safebox.yiye.apackage.com.indextest.constant.SpConstent;
import safebox.yiye.apackage.com.indextest.gloable.MyApplication;

/**
 * Name: APIClient
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-09 14:53
 * 与服务器交互请求更新apk的工具类
 */
public class APIClient {


    /**
     * 得到最新版本的信息对象
     *
     * @param context
     *
     * @return
     * @throws Exception
     */
    public static UpdateInfo getUpdateInfo(Context context) throws Exception {

        UpdateInfo updateInfo = null;
        SpUtils spUtils = SpUtils.getInstance(context);
        // 得到连接对象
        URL url = new URL(spUtils.getString(SpConstent.BASE_URL, null)
                + ConnectUtil.URL_APK_UPDATE);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // 设置
        connection.setConnectTimeout(3000);
        connection.setReadTimeout(2000);
        connection.setDoInput(true);
        // 连接
        connection.connect();

        if (connection.getResponseCode() == 200) {

            // 读取数据
            InputStream is = connection.getInputStream();
            String jsonString = readToString(is);
            // 解析json字符串, 生成对应的数据 对象
            updateInfo = parseJson(jsonString);
            connection.disconnect();
        } else {
            connection.disconnect();
            throw new RuntimeException("没能正常得到数据!");
        }
        return updateInfo;
    }

    /**
     * 解析json字符串, 生成对应的数据 对象
     *
     * @param jsonString
     * @return
     * @throws Exception
     */
    private static UpdateInfo parseJson(String jsonString) throws Exception {
        // 创建包含json字符串的json对象
        JSONObject object = new JSONObject(jsonString);
        // 从中读取对应的数据
        String version = object.getString("version");
        // 封装为对象
        UpdateInfo info = new UpdateInfo(version);
        return info;
    }

    /**
     * 读取输入流中的数据, 生成一个字符串
     *
     * @param is
     * @return
     * @throws IOException
     */
    private static String readToString(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = is.read(buffer)) > 0) {
            baos.write(buffer, 0, len);
        }
        baos.close();
        is.close();
        return baos.toString();
    }

    /**
     * 下载apk, 显示最新下载进度, 保存到指定的file中
     *
     * @param apkUrl
     * @param pd
     * @throws Exception
     */
    public static void downloadApk(String apkUrl, ProgressBar pd)
            throws Exception {
        // 得到连接对象
        URL url = new URL(apkUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // 设置
        connection.setConnectTimeout(3000);
        connection.setReadTimeout(10000);
        connection.setDoInput(true);
        // 连接
        connection.connect();

        if (connection.getResponseCode() == 200) {
            // 设置最大进度
            pd.setMax(connection.getContentLength());
            // 数据读取并保存到文件
            FileOutputStream fos = new FileOutputStream(apkFile);
            InputStream is = connection.getInputStream();
            byte[] buffer = new byte[2048];
            int len = -1;
            while ((len = is.read(buffer)) > 0
                    && !SplashActivity.cancelDownLoad) {
                fos.write(buffer, 0, len);
                pd.incrementProgressBy(len);// 更新进度
            }
            is.close();
            fos.close();
            connection.disconnect();
        } else {
            throw new RuntimeException("没能正常得到数据!");
        }
    }

    /**
     * apk文件
     */
    private static File apkFile;

    /**
     * 创建保存apk的file对象 sd/files
     */
    public static void createApkFile(Context context) {
        File dirFile = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            dirFile = context.getExternalFilesDir(null); // /storage/mnt/Android/data/com.zcsoft.zhichengsoft/ZCSoft.apk
        } else {
            dirFile = context.getFilesDir();// /data/data/com.zcsoft.zhichengsoft/files
        }
        apkFile = new File(dirFile, "yiye.apk");
        if (apkFile.exists()) {
            apkFile.delete(); // 删除已有的文件
        } else {
            try {
                apkFile.createNewFile();// 创建一个空文件
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 安装apk
     */
    public static void installApk(Activity context) {
        // 文件不存在就直接退出
        if (!apkFile.exists()) {
            return;
        }
        MyApplication mAppInfoApplication = ((MyApplication) (context
                .getApplication()));
        try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(apkFile),
                    "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            mAppInfoApplication.setNewVersion(true);
        } catch (Exception e) {
            mAppInfoApplication.setNewVersion(false);
        }
    }
}
