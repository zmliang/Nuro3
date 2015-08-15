package com.nuror1;

import java.nio.ByteBuffer;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.util.Log;

public class MainActivity extends Activity {
	
	private final String TAG = "MainActivity";
	public MyView m_myview = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(
        	    WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
        	    WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(m_myview = new MyView(this));
        NuroCore.nuroOnActivityCreate(this);
        ByteBuffer bbf = null;
        bbf = ByteBuffer.allocateDirect(NuroCore.NURO_WIDTH*NuroCore.NURO_HEIGHT*2);
        NuroCore.buffer = bbf.asShortBuffer();
        NuroCore.setNuroBuffer(NuroCore.buffer, NuroCore.NURO_WIDTH, NuroCore.NURO_HEIGHT);
        NuroCore.startNuro();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
    
    Bitmap bitmap = Bitmap.createBitmap(NuroCore.NURO_WIDTH,
			NuroCore.NURO_HEIGHT, Bitmap.Config.RGB_565);

	public void flushSurface() {

		Canvas cvs = null;
		SurfaceHolder holder = m_myview.getHolder();
		//holder.setFixedSize(800, 480);
		try {
			cvs = holder.lockCanvas();
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }
    class MyView extends SurfaceView implements SurfaceHolder.Callback {

		private SurfaceHolder holder;

		public MyView(Context context) {
			super(context);
			holder = getHolder();
			holder.setFixedSize(800, 480);
			holder.addCallback(this);
			setFocusable(true);
			setFocusableInTouchMode(true);
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			Log.i(TAG, "surfaceChanged");
		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			Log.i(TAG, "surfaceCreated");
			flushSurface();
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			Log.i(TAG, "surfaceDestroyed");
		}
	}

	/*static NuroDrawInfo DrawinfoData = null;
	DrawinfoData = new NuroDrawInfo();
	Log.d("TAG", "StartDrawInfo");
	NuroCore.DrawInfo(DrawinfoData);//DrawInfo*/
	
}
