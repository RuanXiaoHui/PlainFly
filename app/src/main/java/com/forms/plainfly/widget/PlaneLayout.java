package com.forms.plainfly.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by forms on 2017/12/8.
 */

public class PlaneLayout extends AppCompatImageView {
    public PlaneLayout(Context context) {
        this(context,null);
    }

    public PlaneLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PlaneLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setPercent(float percent) {
        this.setRotation(-45*percent);
    }
}
