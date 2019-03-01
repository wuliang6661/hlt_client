package com.wul.hlt_client.ui.classify;

import android.content.Context;
import android.support.v7.widget.LinearSmoothScroller;

public class TopSmoothScroller extends LinearSmoothScroller {


    TopSmoothScroller(Context context) {
        super(context);
    }

    @Override
    protected int getHorizontalSnapPreference() {
        return SNAP_TO_START;//具体见源码注释
    }

    @Override
    protected int getVerticalSnapPreference() {
        return SNAP_TO_START;//具体见源码注释
    }

}