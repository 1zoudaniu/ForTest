package safebox.yiye.apackage.com.indextest.utils;

import android.content.Context;
import android.content.SharedPreferences;

import safebox.yiye.apackage.com.indextest.constant.SpConstent;

/**
 * Name: SpUtils
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-09 14:56
 */
public class SpUtils {
    private static SpUtils instance = new SpUtils();
    private static SharedPreferences sp;// 单例
    public static final String USER_NAME = "user_name";// 用户名
    public static final String PWD = "pwd";// 密码


    private SpUtils() {
    }

    public static SpUtils getInstance(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences(SpConstent.NAME, Context.MODE_PRIVATE);
        }
        return instance;
    }
    /**
     * 根据key读取对应的String数据
     */
    public String getString(String key, String defValue) {
        return sp.getString(key, defValue);
    }
    /**
     * 定义统一的保存数据的方法
     *
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        SharedPreferences.Editor edit = sp.edit();
        if (value instanceof String) {
            edit.putString(key, (String) value);
        } else if (value instanceof Integer) {
            edit.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            edit.putBoolean(key, (Boolean) value);
        }
        edit.commit();
    }
}
