package com.forms.plainfly.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.forms.plainfly.utils.DensityUtil;
import com.forms.plainfly.utils.ScreenUtils;

/**
 * Created by forms on 2017/12/8.
 */

public class BezierViewLayout extends View {

    private Paint mPaint;

    public BezierViewLayout(Context context) {
        this(context, null);
    }

    public BezierViewLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierViewLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(3);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBezierUp(canvas);
        drawBezierDown(canvas);
        drawBezierLine(canvas);
    }

    //绘制上升路径
    private void drawBezierUp(Canvas canvas) {
        Path path = new Path();
        int startY = DensityUtil.dip2px(getContext(), 175.0f);
        int startX = DensityUtil.dip2px(getContext(), 66.0f);
        int endX = ScreenUtils.getScreenWidth(getContext())+100;
        int endY = 50;
        path.moveTo(startX, startY);
        path.quadTo(700, 250, endX, endY);
        canvas.drawPath(path, mPaint);
    }

    //绘制下落路径
    private void drawBezierDown(Canvas canvas) {
        Path path = new Path();
        int startY = 50;
        int startX = ScreenUtils.getScreenWidth(getContext())+100;
        int endX = -50;
        int endY = DensityUtil.dip2px(getContext(), 175.0f);
        path.moveTo(startX, startY);
        path.quadTo(230, 50, endX, endY);
        canvas.drawPath(path, mPaint);
    }

    //绘制水平路径
    private void drawBezierLine(Canvas canvas) {
        Path path = new Path();
        int startY = DensityUtil.dip2px(getContext(), 175.0f);
        int startX = -50;
        int endX = DensityUtil.dip2px(getContext(), 28.0f);
        int endY = DensityUtil.dip2px(getContext(), 175.0f);
        path.moveTo(startX, startY);
        path.quadTo(0, DensityUtil.dip2px(getContext(), 175.0f), endX, endY);
        canvas.drawPath(path, mPaint);
    }
}
