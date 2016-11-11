package safebox.yiye.apackage.com.indextest.utils;

import com.google.gson.Gson;

/**
 * Name: ParseUtils
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-09 19:28
 */
public class ParseUtils {
    private static Gson gson;

    public static <T> T parseJson(String json, Class<T> clazz) {
        if (gson == null) {
            gson = new Gson();
        }
        T t = gson.fromJson(json, clazz);
        return t;
    }
}
