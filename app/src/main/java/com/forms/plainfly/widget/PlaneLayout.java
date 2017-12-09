package com.forms.plainfly.widget;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

import com.forms.plainfly.BezierEvaluator;
import com.forms.plainfly.utils.DensityUtil;
import com.forms.plainfly.utils.ScreenUtils;

/**
 * Created by forms on 2017/12/8.
 */

public class PlaneLayout extends AppCompatImageView {

    private PointF mOldPoint;
    private int startYOne;
    private int startXOne;
    private int endXOne;
    private int endYOne;

    private int endXTwo;
    private int endYTwo;

    private int endXThree;
    private int endYThree;

    private float skyHeight;
    private float planeHeight;

    private ValueAnimator animatorDown;
    private ValueAnimator animatorUp;
    private ValueAnimator animatorLine;
    private AnimatorSet animator;

    public PlaneLayout(Context context) {
        this(context, null);
    }

    public PlaneLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlaneLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mOldPoint = new PointF(0, 0);
        startYOne = DensityUtil.dip2px(getContext(), 175.0f);
        startXOne = DensityUtil.dip2px(getContext(), 66.0f);

        endXOne = ScreenUtils.getScreenWidth(getContext()) + 100;
        endYOne = 50;

        endXTwo = -50;
        endYTwo = DensityUtil.dip2px(getContext(), 175.0f);

        endXThree = DensityUtil.dip2px(getContext(), 41.0f);
        endYThree = DensityUtil.dip2px(getContext(), 175.0f);

        //计算天空大小以及飞机的大小
        skyHeight = DensityUtil.dip2px(getContext(), 175.0f);
        planeHeight = DensityUtil.dip2px(getContext(), 26.0f);

        //初始化飞机飞翔动画
        drawBezierDown();
        drawBezierUp();
        drawBezierLine();
    }

    public void setPercent(float percent) {
        this.setRotation(-45 * percent);
    }

    //绘制上升路径
    private void drawBezierUp() {
        endXOne = ScreenUtils.getScreenWidth(getContext()) + 100;
        endYOne = 50;
        PointF pointControl = new PointF(700, 250);
        PointF pointStart = new PointF(startXOne, startYOne);
        PointF pointEnd = new PointF(endXOne, endYOne);

        BezierEvaluator bezierEvaluator = new BezierEvaluator(pointControl);
        ValueAnimator valueUp = ValueAnimator.ofObject(bezierEvaluator, pointStart, pointEnd);
        valueUp.setDuration(1000);
        valueUp.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                PointF currentPoint = (PointF) valueAnimator.getAnimatedValue();

                //这里面要相减去天空布局的高度，应为是相对于天空去做margin
                setLT((int) (currentPoint.x - planeHeight / 2),
                        (int) (currentPoint.y - planeHeight / 2 - skyHeight));

                //这里面是计算弧长
                float angle = (float) Math.atan2((currentPoint.y - mOldPoint.y)
                        ,(currentPoint.x - mOldPoint.x));

                //根据弧长求角度 angle*180/π
                setRotation((float) (angle * 180 / Math.PI));

                //将新的点坐标赋予老的点坐标
                mOldPoint = currentPoint;
            }
        });
        valueUp.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorUp=valueUp;
    }

    //绘制下落路径
    private void drawBezierDown() {

        PointF pointControl = new PointF(230, 50);
        PointF pointStart = new PointF(endXOne, endYOne);
        PointF pointEnd = new PointF(endXTwo, endYTwo);

        BezierEvaluator bezierEvaluator = new BezierEvaluator(pointControl);
        ValueAnimator valueDown = ValueAnimator.ofObject(bezierEvaluator, pointStart, pointEnd);
        valueDown.setDuration(1000);
        valueDown.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                PointF currentPoint = (PointF) valueAnimator.getAnimatedValue();

                setLT((int) (currentPoint.x - planeHeight / 2),
                        (int) (currentPoint.y - planeHeight / 2 - skyHeight));

                float angle = (float) Math.atan2((currentPoint.y
                        - mOldPoint.y),(currentPoint.x - mOldPoint.x));

                setRotation((float) (angle * 180 / Math.PI));
                mOldPoint = currentPoint;
            }
        });
        valueDown.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorDown=valueDown;
    }

    //绘制下落路径
    private void drawBezierLine() {

        PointF pointControl = new PointF(0, endYThree);
        PointF pointStart = new PointF(endXTwo, endYTwo);
        PointF pointEnd = new PointF(endXThree, endYThree);

        BezierEvaluator bezierEvaluator = new BezierEvaluator(pointControl);
        ValueAnimator valueLine = ValueAnimator.ofObject(bezierEvaluator, pointStart, pointEnd);
        valueLine.setDuration(1000);
        valueLine.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                PointF currentPoint = (PointF) valueAnimator.getAnimatedValue();

                setLT((int) (currentPoint.x - planeHeight / 2),
                        (int) (currentPoint.y - planeHeight / 2 - skyHeight));

                float angle = (float) Math.atan2((currentPoint.y
                        - mOldPoint.y),(currentPoint.x - mOldPoint.x));

                setRotation((float) (angle * 180 / Math.PI));
                mOldPoint = currentPoint;
            }
        });
        valueLine.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorLine=valueLine;
    }


    public void playPlayFly(){
        animator=new AnimatorSet();
        animator.play(animatorDown).after(animatorUp).before(animatorLine);
        animator.start();
    }
    /****
     * 设置飞机的左边距与顶部边距
     * @param leftMargin
     * @param topMargin
     */
    public void setLT(int leftMargin, int topMargin) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getLayoutParams();
        params.leftMargin = leftMargin;
        params.topMargin = topMargin;
        setLayoutParams(params);
    }

}
