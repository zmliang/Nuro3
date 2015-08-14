package com.nuro.application;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by nuro on 7/26/15.
 */
public class AnimationList {
    Context context;

    HashMap<View, Animation> map = new LinkedHashMap<View, Animation>();
    Animation lastAnimation;

    public AnimationList(Context context) {
        this.context = context;
    }


    public void add(View view, Animation animation) {
        map.put(view, animation);

        //if (lastAnimation != null) {
        //    lastAnimation.setAnimationListener(new ListListener(view, animation));
        //}

        //lastAnimation = animation;
    }


    public class ListListener implements Animation.AnimationListener {

        public View nextView;
        public Animation nextAnimation;

        public ListListener(View view, Animation animation) {
            nextView = view;
            nextAnimation = animation;
        }

        @Override
        public void onAnimationStart(Animation animation) {
            nextView.setVisibility(View.VISIBLE);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            //if (nextView != null) {
            //    nextView.startAnimation(this.nextAnimation);
            //}
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    public void startAnimation() {

        int time = 0;
        for (Map.Entry<View, Animation> e : map.entrySet()) {
            View view = e.getKey();
            Animation animation = e.getValue();
            animation.setAnimationListener(new ListListener(view, animation));
            view.postDelayed(new PostAction(view, animation), time);
            time += 300;
        }

        //Map.Entry<View, Animation> e = map.entrySet().iterator().next();
        //final View view = e.getKey();
        //final Animation animation = e.getValue();
        //view.postDelayed(new PostAction(view, animation), 1000);
    }

    static class PostAction implements Runnable {

        public View view;
        public Animation animation;

        public PostAction(View view, Animation animation) {
            this.view = view;
            this.animation = animation;
        }

        @Override
        public void run() {
            view.startAnimation(animation);
        }
    }

}
