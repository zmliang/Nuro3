package com.nuror1;

import java.io.IOException;
import java.nio.ShortBuffer;

//import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class NuroCore {

	static {
		try {
			System.loadLibrary("nuror1");
		} catch (UnsatisfiedLinkError e) {
	 		Log.i("Mytest", e.getMessage());
	 	}
	}



	/**
	 * Nuro local constant declaration.
	 */
	public static final String TAG = "NuroCore";
	public static final int NURO_WIDTH = 800;
	public static final int NURO_HEIGHT = 480;
	public static final boolean m_bHaveTMCFunc = true;
	public static  boolean m_bNetWorkstate = false;

	/**
	 * Nuro local static declaration.
	 */
	public static ShortBuffer buffer = null;
	public static MainActivity activity = null;
	public static  boolean m_bHaveObuOneKeyCall = false;
	public static  Handler serHandler;

	public static void setSerHandler(Handler serHand){
		serHandler = serHand;
	}

	/**
	 * Java static method declaration for java calling.
	 */
	public static void nuroOnActivityCreate(MainActivity act) {
		NuroCore.activity = act;
		Log.i(TAG, "nuroOnActivityCreate");
	}

	/**
	 * Java static method declaration for c++ calling.
	 */

    public static void callExit() {
    	//NuroCore.stopNaviSerive();
    	Log.i(TAG, "callExit");
    	Log.i(TAG, "Activity ResultCode = " + NuroCore.activity.RESULT_OK);
    	NuroCore.activity.finish();
    }

	public static void flushSurface() {
		if (NuroCore.activity != null) {
			NuroCore.activity.flushSurface();
		}
	}

//	@SuppressLint("SdCardPath")
	public static void playSoundCallback() {
		float vvf = (float)NuroCore.getVolume();
		//from 16 to 10;
		vvf = (float) (vvf*10.0f)/16.0f;

		vvf = vvf * vvf / 100f; // f(x) = 1/100 * x^2;
		//Log.i("dengxu", String.valueOf(vvf));
		//NuroCore.playSound(vvf, /*Environment.getExternalStorageDirectory().getPath() + */"/mnt/nurownds/media/sound/tts.wav");
		NuroCore.playSound(vvf, "/mnt/sdcard/extsd/nurownds/media/sound/tts.wav");
		//NuroCore.playSound(vvf, "/storage/sdcard1/extsd/nurownds/media/sound/tts.wav");

	}


//	@SuppressLint("SdCardPath")
	public static void playSound(float fVol, String file) {

		MediaPlayer mp = null;

		if (file == null) {
			//file = /*Environment.getExternalStorageDirectory().getPath() + */"/nurownds/media/sound/0.wav";
			file = "/mnt/sdcard/extsd/nurownds/media/sound/0.wav";
			//file = "/storage/sdcard1/extsd/nurownds/media/sound/0.wav";

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


		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if (null != mp) {
				mp.release();
				mp = null;
				Log.i("Music", "Release ..... ...!!");
			}
		}

	}

	static final String BROADCAST_MSG_NAME = "com.joinlink.broadcast";
    final static int TYPE_Position = 0x9500;
    final static int TYPE_Centernumber = 0x9505;
    final static int TYPE_Dial = 0x9501;
    final static int TYPE_Hangup = 0x9502;
    final static int TYPE_Volume = 0x9503;
    final static int TYPE_Pickup = 0x9504;
    final static int TYPE_Destcancel = 0x9506;
    final static int TYPE_NaviExit = 0x9511;

	public static void hangUp() {
        Intent msg = new Intent();
        msg.setAction(BROADCAST_MSG_NAME);
        msg.putExtra("MSG_TYPE", TYPE_Hangup);

        if (activity != null) {
        	activity.sendBroadcast(msg);
        	Log.i("onkey", "====================hangUp ..... ...!!");
        }
	}

	public static void pickUp() {
        Intent msg = new Intent();
        msg.setAction(BROADCAST_MSG_NAME);
        msg.putExtra("MSG_TYPE", TYPE_Pickup);

        if (activity != null) {
        	activity.sendBroadcast(msg);
        	Log.i("onkey", "====================pickUp ..... ...!!");
        }
	}

//	public static void Identification() {
//		if (NuroConfig.url == null) {
//
//			Message msg = new Message();
//			msg.what = 1;//Service_Msg_What_Identification;
//			msg.arg1 = 0;
//			serHandler.sendMessage(msg);
//			//NuroConfig.url = "http://cloud.palmgo.cn/tc/";
//		}
//	}

	public static void sendPosition(int lat, int lng, int dir, int speed) {
		Intent msg = new Intent();
		msg.setAction(BROADCAST_MSG_NAME);
        // һ��绰ʱ��ȡ�ն˵�ǰ��λ����Ϣ����
		msg.putExtra("MSG_TYPE", TYPE_Position);
        msg.putExtra("LATITUDE", lat);
        msg.putExtra("LONGITUDE", lng);
        msg.putExtra("DIRECTION", dir);
        msg.putExtra("SPEED", speed);

        if (activity != null) {
        	activity.sendBroadcast(msg);
        }
	}

	public static void dial(int n) {
		Intent msg = new Intent();
		msg.setAction(BROADCAST_MSG_NAME);
		msg.putExtra("MSG_TYPE", TYPE_Dial);
		msg.putExtra("CALLTYPE", n);

		if (activity != null) {
			activity.sendBroadcast(msg);
			Log.i("onkey", "====================dial ..... ...!!");
		}
	}

	public static void volume(int n) {
		Intent msg = new Intent();
		msg.setAction(BROADCAST_MSG_NAME);
		msg.putExtra("MSG_TYPE", TYPE_Volume);
		msg.putExtra("VOLUME", n);
		if (activity != null) {
			activity.sendBroadcast(msg);
		}
	}

	public static void naviexit() {
		Intent msg = new Intent();
		msg.setAction(BROADCAST_MSG_NAME);
		msg.putExtra("MSG_TYPE", TYPE_NaviExit);//0x9511;

		if (activity != null) {
			activity.sendBroadcast(msg);
			Log.i("nurocore", "====================naviexit ..... ...!!");
		}
	}

	public static void sendPhoneNum(String strServiceCenter,String strNearInfo,String strOneKey,String strRoadService,String strSMS,String strTrackPh){

		Intent msg = new Intent();
		msg.setAction(BROADCAST_MSG_NAME);
		msg.putExtra("MSG_TYPE", TYPE_Centernumber);
        msg.putExtra("SERVICECENTER", strServiceCenter);
        msg.putExtra("NEARINFO", strNearInfo);
        msg.putExtra("ONEKEY", strOneKey);
        msg.putExtra("ROADSERVICE", strRoadService);
        msg.putExtra("SMS", strSMS);
        msg.putExtra("TRACKPH", strTrackPh);

        if (activity != null) {
        	activity.sendBroadcast(msg);
        }
	}

	static String TMCSTR="";
	static boolean bNeedGetXML=false;

	public static void setTMCStr(String str)	{
		TMCSTR="";
		TMCSTR = str;
		bNeedGetXML = true;
	}
	public static String getTMCStr()	{

		if(bNeedGetXML)
		{
			bNeedGetXML = false;
			return TMCSTR;
		}
		else
		{
			return null;
		}
	}

	public static native boolean SetTMCstate(boolean bTMCused);
	public static native boolean GetTMCstate();


	public static String cityId = "330100";
	public static double currentCarPosLng;
	public static double currentCarPosLat;

	public static void setCallBackCityId(String cityId) {
		NuroCore.cityId = cityId;
	}

	/**
	 * Nuro local method declaration.
	 */
	public static native boolean setNuroPath(String strGpsResourcePath);
	public static native boolean setNuroBuffer(ShortBuffer buffer, int w, int h);
	public static native boolean injectEventMap(Object obj);
	public static native int     getFromEventMap(Object obj);
	public static native void    setToEventMap(Object obj, int e);
	public static native String  getPreparedAudio();
	public static native void    setPreparedAudio();
	/**
	 * @return
	 */
	public static native int     getVolume();
	public static native boolean startNuro();
	public static native void    stopNuro();
	public static native boolean onMouseDown(int x, int y);
	public static native boolean onMouseUp(int x, int y);
	public static native boolean onMouseMove(int x, int y);
//	public static native boolean setGps(NuroGpsStatus status);
	public static native boolean setGpsNema(String nema, boolean bOK);
	public static native boolean sendPoiStringUTF16BE(byte[] hzUTF16);
	public static native boolean getNuroState(); // new added
	public static native boolean keySound();     // new added
	public static native boolean goPreDialog();  // new added
	public static native void    setMechineUID(String strUID); // new added
//	public static native void	sendCopyData(CopyData copydata);
	public static native void 	sendWindProc(long message, long wParam, long lParam);
	public static native String	getTMCMapName();
	//public static native boolean setNuroEventMap(IntBuffer eventmap, int count);
}
