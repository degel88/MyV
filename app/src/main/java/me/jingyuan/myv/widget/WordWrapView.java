package me.jingyuan.myv.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author degel
 * @Description 自动换行TextView
 * @Version v1.0
 */
public class WordWrapView extends ViewGroup {

    /**
     * // 水平方向padding
     */
    private static final int PADDING_HOR = 10;
    /**
     * // 垂直方向padding
     */
    private static final int PADDING_VERTICAL = 10;
    /**
     * // 左右间距
     */
    private static final int SIDE_MARGIN = 10;
    /**
     * 文本外边距
     */
    private static final int TEXT_MARGIN = 10;

    /**
     * @param context
     */
    public WordWrapView(Context context) {
        super(context);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public WordWrapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * @param context
     * @param attrs
     */
    public WordWrapView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int autualWidth = r - l;
        /**
         * // 横坐标开始
         */
        int x = SIDE_MARGIN;
        /**
         * // 纵坐标开始
         */
        int y = 0;
        int rows = 1;
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
//			view.setBackgroundColor(Color.GREEN);
            int width = view.getMeasuredWidth();
            int height = view.getMeasuredHeight();
            x += width + TEXT_MARGIN;
            if (x > autualWidth) {
                x = width + SIDE_MARGIN;
                rows++;
            }
            y = rows * (height + TEXT_MARGIN);
            if (i == 0) {
                view.layout(x - width - TEXT_MARGIN, y - height, x - TEXT_MARGIN, y);
            } else {
                view.layout(x - width, y - height, x, y);
            }
        }
    }

    ;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * // 横坐标
         */
        int x = 0;
        /**
         * // 纵坐标
         */
        int y = 0;
        /**
         * // 总行数
         */
        int rows = 1;
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        /**
         * // 实际宽度
         */
        int actualWidth = specWidth - SIDE_MARGIN * 2;
        int childCount = getChildCount();
        for (int index = 0; index < childCount; index++) {
            View child = getChildAt(index);
            child.setPadding(PADDING_HOR, 0, PADDING_HOR, PADDING_VERTICAL);
            child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            x += width + TEXT_MARGIN;
            /**
             * // 换行
             */
            if (x > actualWidth) {
                x = width;
                rows++;
            }
            y = rows * (height + TEXT_MARGIN);
        }
        setMeasuredDimension(actualWidth, y);
    }

}
