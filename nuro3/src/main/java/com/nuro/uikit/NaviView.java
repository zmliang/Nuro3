package com.nuro.uikit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.nuror1.NaviDrawable;
import com.nuror1.NuroCore;
import java.nio.ShortBuffer;

/**
 * Created by nuro on 8/15/15.
 */
public class NaviView extends SurfaceView implements SurfaceHolder.Callback, NaviDrawable {

	private SurfaceHolder holder;

	private void init() {
		holder = getHolder();
		holder.setFixedSize(800, 480);
		holder.addCallback(this);
		setFocusable(true);
		setFocusableInTouchMode(true);
	}

	public NaviView(Context context) {
		super(context);
		init();
	}

	public NaviView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public NaviView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
							   int height) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}

	@Override
	public void draw(ShortBuffer buffer, Bitmap bitmap) {
		SurfaceHolder holder = getHolder();
		//holder.setFixedSize(800, 480);
		try {
			Canvas cvs = holder.lockCanvas();
			if (cvs != null) {
				try {
					NuroCore.buffer.rewind();
					bitmap.copyPixelsFromBuffer(NuroCore.buffer);
					cvs.drawBitmap(bitmap, 0, 0, null);
					holder.unlockCanvasAndPost(cvs);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
