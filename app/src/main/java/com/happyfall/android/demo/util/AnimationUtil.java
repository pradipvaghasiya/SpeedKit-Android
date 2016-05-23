package com.happyfall.android.demo.util;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;

import java.util.List;

/**
 * Project: UIAutomation-Android
 * Created by Pradip on 5/27/2015.
 */
public class AnimationUtil {

    public static AnimatorSet animateObjectAnimatorsWithDuration(List<ObjectAnimator> objectAnimators, int durationInMilliSeconds){
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether((ObjectAnimator[]) objectAnimators.toArray());
        animatorSet.setDuration(durationInMilliSeconds);
        animatorSet.start();

        return animatorSet;
    }
}
