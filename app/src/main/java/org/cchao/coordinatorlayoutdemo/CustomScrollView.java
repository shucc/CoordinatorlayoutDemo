package org.cchao.coordinatorlayoutdemo;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by chenchao on 2017/6/8.
 */

public class CustomScrollView extends NestedScrollView {

    private boolean isInter = true;

    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!isInter) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    public void setInter(boolean inter) {
        isInter = inter;
    }
}
