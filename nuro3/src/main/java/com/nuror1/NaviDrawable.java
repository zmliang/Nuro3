package com.nuror1;

import android.graphics.Bitmap;

import java.nio.ShortBuffer;

/**
 * Created by nuro on 8/15/15.
 */
public interface NaviDrawable {
    void draw(ShortBuffer buffer, Bitmap bitmap);
}
