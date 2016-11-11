package safebox.yiye.apackage.com.indextest.utils;

import android.view.View;
import android.view.animation.AlphaAnimation;

/**
 * Name: MyAnimationUtils
 * Author: aina
 * Email:
 * Comment: //TODO
 * Date: 2016-11-09 14:04
 */
public class MyAnimationUtils {
    /**
     * 显示透明度动画 从完全透明到完全不透明(显示)
     * @param view
     */
    public static void startAnimation(View view) {
        AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(2000);
        view.startAnimation(animation);
    }
}
