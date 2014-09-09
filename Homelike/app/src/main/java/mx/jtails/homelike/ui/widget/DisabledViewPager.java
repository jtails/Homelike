package mx.jtails.homelike.ui.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by GrzegorzFeathers on 9/3/14.
 */
public class DisabledViewPager extends ViewPager {

    public DisabledViewPager(Context context) {
        this(context, null);
    }

    public DisabledViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

}
