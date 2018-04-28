package de.curamatik.library.disclaimerviews.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

public class BottomAwareScrollView extends ScrollView {
    OnBottomReachedListener bottomReachedListener;
    OnScrolledFromBottomListener scrollFromBottomListener;
    boolean scrolledFromBottom;

    public BottomAwareScrollView(Context context) {
        super(context);
    }

    public BottomAwareScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomAwareScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public OnScrolledFromBottomListener getScrollFromBottomListener() {
        return scrollFromBottomListener;
    }

    public void setScrollFromBottomListener(OnScrolledFromBottomListener scrollFromBottomListener) {
        this.scrollFromBottomListener = scrollFromBottomListener;
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        View view = getChildAt(getChildCount() - 1);
        int diff = (view.getBottom() - (getHeight() + getScrollY()));

        if (diff == 0 && bottomReachedListener != null) {
            bottomReachedListener.onBottomReached();
            scrolledFromBottom = true;
        }
        if (diff > 0 && scrolledFromBottom && scrollFromBottomListener != null) {
            scrollFromBottomListener.onScrolled();
            scrolledFromBottom = false;
        }

        super.onScrollChanged(l, t, oldl, oldt);
    }

    public OnBottomReachedListener getOnBottomReachedListener() {
        return bottomReachedListener;
    }

    public void setOnBottomReachedListener(OnBottomReachedListener scrollBottomAwareListener) {
        bottomReachedListener = scrollBottomAwareListener;
    }

    /**
     * Event listener.
     */
    public interface OnBottomReachedListener {
        void onBottomReached();
    }

    public interface OnScrolledFromBottomListener {
        void onScrolled();
    }

}


