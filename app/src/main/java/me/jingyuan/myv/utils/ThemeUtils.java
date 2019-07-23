package me.jingyuan.myv.utils;

import android.content.Context;
import android.content.res.TypedArray;

/**
 * 主题工具类
 * 作者：degel on 2019-07-22 15:50
 */
public class ThemeUtils {
    public static int getThemeColor(Context context, int attrRes) {
        TypedArray typedArray = context.obtainStyledAttributes(new int[]{attrRes});
        int color = typedArray.getColor(0, 0xffffff);
        typedArray.recycle();
        return color;
    }
}
