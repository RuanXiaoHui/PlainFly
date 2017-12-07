package com.forms.plainfly.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;

import com.forms.plainfly.Constant;
import com.forms.plainfly.utils.DensityUtil;

/**
 * Created by forms on 2017/12/7.
 */

public class SkyLayout extends AppCompatImageView {
    private float mOriginHeight;
    private float mPercent;
    private AppCompatImageView ivSkyLine;
    private AppCompatImageView ivMountain;
    private AppCompatImageView ivLand;
    private ValueAnimator mAnimator;

    public SkyLayout(Context context) {
        this(context, null);
    }

    public SkyLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {

        if (attrs != null) {
            String strHeight = attrs.getAttributeValue(2);
            mOriginHeight = Integer.parseInt(strHeight.split("\\.")[0]);
            mOriginHeight = DensityUtil.dip2px(getContext(), mOriginHeight);
        }
    }

    public void setViews(AppCompatImageView ivSkyLine,AppCompatImageView ivMountain,AppCompatImageView ivLand){
        this.ivSkyLine=ivSkyLine;
        this.ivMountain=ivMountain;
        this.ivLand=ivLand;
    }

    private void setHeight(AppCompatImageView imageView,float height){
        RelativeLayout.LayoutParams layoutParms = (RelativeLayout.LayoutParams)imageView.getLayoutParams();
        layoutParms.height = (int) height;
        imageView.setLayoutParams(layoutParms);
    }
    public void setPercent(float percent) {
        this.mPercent = percent;
        RelativeLayout.LayoutParams layoutParms = (RelativeLayout.LayoutParams) getLayoutParams();
        layoutParms.height = (int) (mOriginHeight + Constant.MAX_MOVE_VALUE * percent);
        setLayoutParams(layoutParms);

        setHeight(ivSkyLine,getHeight()*Constant.SKYLINE_SCALE);
        setHeight(ivMountain,getHeight()*Constant.MOUNT_SCALE);
        setHeight(ivLand,getHeight()*Constant.LAND_SCALE);
    }

    public void releaseView() {
        if (mAnimator==null){
            final ValueAnimator valAnimator=ValueAnimator.ofFloat(mPercent,0f);
            valAnimator.setDuration(500);
            valAnimator.setInterpolator(new OvershootInterpolator());
            valAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {

                   float animValue= (float) valAnimator.getAnimatedValue();
                    setPercent(animValue);

                }
            });
            this.mAnimator=valAnimator;
        }else {
            mAnimator.cancel();
            mAnimator.setFloatValues(mPercent,0f);
        }
        mAnimator.start();
    }
}
