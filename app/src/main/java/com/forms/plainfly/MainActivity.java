package com.forms.plainfly;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.forms.plainfly.widget.PlaneLayout;
import com.forms.plainfly.widget.SkyLayout;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout rltRoot;
    private SkyLayout ivSky;
    private float mDownY;
    private AppCompatImageView ivSkyLine;
    private AppCompatImageView ivMountain;
    private AppCompatImageView ivLand;
    private AppCompatImageView ivStarOne;
    private AppCompatImageView ivStarTwo;
    private AppCompatImageView ivTree;
    private PlaneLayout ivPlane;
    private float percentValue;  //下拉的百分比值


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        rltRoot = (RelativeLayout) findViewById(R.id.rltRoot);
        ivSky = (SkyLayout) findViewById(R.id.ivSky);
        ivSkyLine = (AppCompatImageView) findViewById(R.id.ivSkyLine);
        ivMountain = (AppCompatImageView) findViewById(R.id.ivMountain);
        ivLand = (AppCompatImageView) findViewById(R.id.ivLand);
        ivStarOne = (AppCompatImageView) findViewById(R.id.ivStarOne);
        ivStarTwo = (AppCompatImageView) findViewById(R.id.ivStarTwo);
        ivPlane= (PlaneLayout) findViewById(R.id.ivPlane);
        ivTree= (AppCompatImageView) findViewById(R.id.ivTree);
        ivSky.setViews(ivSkyLine, ivMountain, ivLand);
        starStartAnim();
    }

    private void initListener() {
        rltRoot.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();

                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        mDownY = motionEvent.getY();
                        ((Animatable)ivTree.getDrawable()).start();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float moveY = motionEvent.getY();
                        if (moveY >= mDownY&&PlaneLayout.ANIMATION_END) {
                            //向下拉
                            float diffValue = moveY - mDownY;
                            percentValue = diffValue > Constant.MAX_MOVE_VALUE
                                    ? 1 : diffValue / Constant.MAX_MOVE_VALUE;
                            ivSky.setPercent(percentValue);
                            ivPlane.setPercent(percentValue);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (PlaneLayout.ANIMATION_END){
                            ivSky.releaseView();
                            if (percentValue>0.7){
                                ivPlane.playPlayFly();
                                percentValue=0;
                            }else {
                                ivPlane.setPercent(0);
                            }
                        }
                        break;
                }

                return true;
            }
        });
    }

    private void starStartAnim() {
        //第一种写法
//        {
//            ObjectAnimator obj = ObjectAnimator.ofFloat(ivStarOne, "scaleX", 1f, 1.5f, 0.8f, 1f);
//            obj.setDuration(2000);
//            obj.setRepeatCount(ValueAnimator.INFINITE);
//            obj.start();
//
//            ObjectAnimator obj2 = ObjectAnimator.ofFloat(ivStarOne, "scaleY", 1f, 1.5f, 0.8f, 1f);
//            obj2.setDuration(2000);
//            obj2.setRepeatCount(ValueAnimator.INFINITE);
//            obj2.start();
//
//            ObjectAnimator obj3 = ObjectAnimator.ofFloat(ivStarTwo, "scaleX", 1f, 1.5f, 0.8f, 1f);
//            obj3.setDuration(2000);
//            obj3.setRepeatCount(ValueAnimator.INFINITE);
//            obj3.start();
//
//            ObjectAnimator obj4 = ObjectAnimator.ofFloat(ivStarTwo, "scaleY", 1f, 1.5f, 0.8f, 1f);
//            obj4.setDuration(2000);
//            obj4.setRepeatCount(ValueAnimator.INFINITE);
//            obj4.start();
//
//            ObjectAnimator obj5 = ObjectAnimator.ofFloat(ivStarThree, "scaleX", 1f, 1.5f, 0.8f, 1f);
//            obj5.setDuration(2000);
//            obj5.setRepeatCount(ValueAnimator.INFINITE);
//            obj5.start();
//
//            ObjectAnimator obj6 = ObjectAnimator.ofFloat(ivStarThree, "scaleY", 1f, 1.5f, 0.8f, 1f);
//            obj6.setDuration(2000);
//            obj6.setRepeatCount(ValueAnimator.INFINITE);
//            obj6.start();
//        }
        //第二种写法 api requires min 21
//        {
//            Path path1=new Path();
//            path1.moveTo(1,1);
//            path1.lineTo(1.5f,1.5f);
//            path1.lineTo(0.8f,0.8f);
//            path1.lineTo(1.0f,1.0f);
//            ObjectAnimator objAnimator2=ObjectAnimator.ofFloat(ivStarOne,"scaleX","scaleY",path1);
//            objAnimator2.setRepeatCount(ValueAnimator.INFINITE);
//            objAnimator2.start();
//
//        }

       //第三种写法
//        {
//            ObjectAnimator obj = ObjectAnimator.ofFloat(ivStarOne, "scaleX", 1f, 1.5f, 0.8f, 1f);
//            obj.setRepeatCount(ValueAnimator.INFINITE);
//            ObjectAnimator obj2 = ObjectAnimator.ofFloat(ivStarOne, "scaleY", 1f, 1.5f, 0.8f, 1f);
//            obj2.setRepeatCount(ValueAnimator.INFINITE);
//            AnimatorSet aniSet=new AnimatorSet();
//            aniSet.playTogether(obj,obj2);
//            aniSet.setDuration(2000);
//            aniSet.start();
//
//            ObjectAnimator ob3 = ObjectAnimator.ofFloat(ivStarTwo, "scaleX", 1f, 1.5f, 0.8f, 1f);
//            ob3.setRepeatCount(ValueAnimator.INFINITE);
//            ObjectAnimator obj4 = ObjectAnimator.ofFloat(ivStarTwo, "scaleY", 1f, 1.5f, 0.8f, 1f);
//            obj4.setRepeatCount(ValueAnimator.INFINITE);
//            AnimatorSet aniSet2=new AnimatorSet();
//            aniSet2.playTogether(ob3,obj4);
//            aniSet2.setDuration(1500);
//            aniSet2.start();
//
//            ObjectAnimator obj5 = ObjectAnimator.ofFloat(ivStarThree, "scaleX", 1f, 1.5f, 0.8f, 1f);
//            obj5.setRepeatCount(ValueAnimator.INFINITE);
//            ObjectAnimator obj6 = ObjectAnimator.ofFloat(ivStarThree, "scaleY", 1f, 1.5f, 0.8f, 1f);
//            obj6.setRepeatCount(ValueAnimator.INFINITE);
//            AnimatorSet aniSet3=new AnimatorSet();
//            aniSet3.playTogether(obj5,obj6);
//            aniSet3.setDuration(2000);
//            aniSet3.start();
//        }

        //第四种写法
        {
            PropertyValuesHolder  pr1=PropertyValuesHolder.ofFloat("scaleX",1f,1.5f,0.8f,1.0f);
            PropertyValuesHolder  pr2=PropertyValuesHolder.ofFloat("scaleY",1f,1.5f,0.8f,1.0f);
            ObjectAnimator obj1=ObjectAnimator.ofPropertyValuesHolder(ivStarOne,pr1,pr2);
            obj1.setDuration(2000);
            obj1.setRepeatCount(ValueAnimator.INFINITE);
            obj1.start();

            PropertyValuesHolder  pr3=PropertyValuesHolder.ofFloat("scaleX",1f,1.5f,0.8f,1.0f);
            PropertyValuesHolder  pr4=PropertyValuesHolder.ofFloat("scaleY",1f,1.5f,0.8f,1.0f);
            ObjectAnimator obj2=ObjectAnimator.ofPropertyValuesHolder(ivStarTwo,pr3,pr4);
            obj2.setDuration(2000);
            obj2.setRepeatCount(ValueAnimator.INFINITE);
            obj2.start();


        }

    }
}
