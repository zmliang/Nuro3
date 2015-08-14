package com.nuro.application;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * Created by nuro on 7/26/15.
 */
public class NuroAnimation {
    public static void animationAction(View view) {
        AnimationSet animationSet = new AnimationSet(true);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(1000);
        animationSet.addAnimation(alphaAnimation);

//        RotateAnimation rotateAnimation = new RotateAnimation(
//                0, 360*3,
//                Animation.RELATIVE_TO_SELF, 0.5f,
//                Animation.RELATIVE_TO_SELF, 0.5f);
//        rotateAnimation.setDuration(800);
//        animationSet.addAnimation(rotateAnimation);

        ScaleAnimation scaleAnimation = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        scaleAnimation.setDuration(1000);
        animationSet.addAnimation(scaleAnimation);
        view.startAnimation(animationSet);
    }

    public static TranslateAnimation animationTranslate(int position) {
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 12f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 12f,
                Animation.RELATIVE_TO_SELF, 0f);
        translateAnimation.setDuration(500 - 30 * position);
        return translateAnimation;
    }

    public static TranslateAnimation animationTranslateAuto(int position) {
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 12f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 12f,
                Animation.RELATIVE_TO_SELF, 0f);
        translateAnimation.setDuration(500 - 30 * position);
        return translateAnimation;
    }

}
