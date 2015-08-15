package com.nuror1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.Log;

public class NuroCore {

	static {
		try {
			System.loadLibrary("nuro_r1");
		} catch (UnsatisfiedLinkError e) {
	 		Log.i("Mytest", e.getMessage());
			throw e; // go on throw
	 	}
	}

	/**
	 * Nuro local constant declaration.
	 */
	public static final int NURO_WIDTH = 800;
	public static final int NURO_HEIGHT = 480;

	/**
	 * Nuro local static declaration.
	 */
	public static ShortBuffer buffer;
	public static Bitmap bitmap;
	public static NaviDrawable currNaviDrawable;
	public static NaviDrawable swapNaviDrawable;

	public static void init() {
		NuroCore.buffer = ByteBuffer.allocateDirect(NuroCore.NURO_WIDTH * NuroCore.NURO_HEIGHT * 2).asShortBuffer();
		NuroCore.bitmap = Bitmap.createBitmap(NuroCore.NURO_WIDTH, NuroCore.NURO_HEIGHT, Bitmap.Config.RGB_565);
		NuroCore.setNuroBuffer(NuroCore.buffer, NuroCore.NURO_WIDTH, NuroCore.NURO_HEIGHT);
		NuroCore.startNuro();
	}


	/**
	 * START Java static method declaration for c++ calling.
	 */

	public static void flushSurface() {
		if (currNaviDrawable != null) {
			currNaviDrawable.draw(buffer, bitmap);
		}
	}

	@Deprecated
	public static void callExit() {
	}

	public static void playSoundCallback() {
		float vvf = (float)NuroCore.getVolume();
		//from 16 to 10;
		vvf = vvf * 10.0f / 16.0f;
		vvf = vvf * vvf / 100f; // f(x) = 1/100 * x^2;
		NuroCore.playSound(vvf, "/mnt/sdcard/extsd/nurownds/media/sound/tts.wav");
	}

	/**
	 * END Java static method declaration for c++ calling.
	 */


	public static void playSound(float fVol, String file) {
		MediaPlayer mp = null;

		if (file == null) {
			file = "/mnt/sdcard/extsd/nurownds/media/sound/0.wav";
		}

		try {
			mp = new MediaPlayer();
			mp.reset();
			mp.setDataSource(file);
			mp.setVolume(fVol, fVol);

			mp.setOnCompletionListener(new OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                	mp.stop();
                }
            });

			mp.prepare();
			mp.start();

			do {
				try {
					Thread.sleep(60);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} while (mp.isPlaying());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != mp) {
				mp.release();
				mp = null;
				Log.i("Music", "Release ..... ...!!");
			}
		}
	}

	/**
	 * Nuro local method declaration.
	 */
	public static native boolean setNuroPath(String strGpsResourcePath);
	public static native boolean setNuroBuffer(ShortBuffer buffer, int w, int h);

	/**
	 * @return
	 */
	public static native int     getVolume();
	public static native boolean startNuro();
	public static native void    stopNuro();
	public static native boolean onMouseDown(int x, int y);
	public static native boolean onMouseUp(int x, int y);
	public static native boolean onMouseMove(int x, int y);
	public static native boolean setGpsNema(String nema, boolean bOK);
	public static native boolean sendPoiStringUTF16BE(byte[] hzUTF16);
	public static native boolean getNuroState(); // new added
	public static native boolean keySound();     // new added
	public static native boolean goPreDialog();  // new added
	public static native void    setMechineUID(String strUID); // new added
	public static native void	 sendCopyData(CopyData copydata);
	public static native void 	 sendWindProc(long message, long wParam, long lParam);
	public static native String	 getTMCMapName();
}
