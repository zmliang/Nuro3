package com.nuror1;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.nuro.uikit.NaviView;

public class MainActivity extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(
        	    WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
        	    WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		NaviView naviView = new NaviView(this);
		setContentView(naviView);

		NuroCore.currNaviDrawable = naviView;
        NuroCore.setNuroBuffer(NuroCore.buffer, NuroCore.NURO_WIDTH, NuroCore.NURO_HEIGHT);
        NuroCore.startNuro();
    }

}

