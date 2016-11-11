package safebox.yiye.apackage.com.indextest.utils;

import java.security.MessageDigest;

/**
 * Name: OnlyOneUtils
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-09 15:36
 */
public class OnlyOneUtils {
    /**
     * 防止用户 疯狂连点
     */
    private static long lastClickTime;
    public static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if ( time - lastClickTime < 200) {
            lastClickTime = time;
            return true;
        }
        lastClickTime = time;
        return false;
    }
    /**
     * MD5加密
     *
     * @param string
     * @return
     * @throws Exception
     */
    public static String encode(String string) throws Exception {
        byte[] hash = MessageDigest.getInstance("MD5").digest(
                string.getBytes("UTF-8"));
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
}
