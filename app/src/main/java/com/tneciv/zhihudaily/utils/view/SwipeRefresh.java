package com.tneciv.zhihudaily.utils.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by Tneciv on 3-16-0016.
 */
public class SwipeRefresh extends SwipeRefreshLayout {

    private boolean mMeasured = false;
    private boolean mRefresh = false;

    public SwipeRefresh(Context context) {
        super(context);
    }

    public SwipeRefresh(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (!mMeasured) {
            mMeasured = true;
            setRefreshing(mRefresh);
        }

    }

    @Override
    public void setRefreshing(boolean refreshing) {
        if (mMeasured) {
            super.setRefreshing(mMeasured);
            Log.d("SwipeRefresh", "refreshing:" + refreshing + mMeasured);
        } else {
            mRefresh = refreshing;
            Log.d("SwipeRefresh", "refreshing:" + refreshing + mMeasured);
        }
    }
}
