package com.forms.plainfly;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

import com.forms.plainfly.utils.BezierUtil;

/**
 * Created by forms on 2017/12/8.
 */

public class BezierEvaluator implements TypeEvaluator<PointF> {

    private PointF mControlPoint;

    public BezierEvaluator(PointF controlPoint) {
        this.mControlPoint=controlPoint;
    }

    @Override
    public PointF evaluate(float v, PointF pointF, PointF t1) {
        return BezierUtil.getPointFromQuadBezier(v,pointF,mControlPoint,t1);
    }
}
