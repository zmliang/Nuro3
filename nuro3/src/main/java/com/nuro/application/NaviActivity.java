package com.nuro.application;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.nuro.uikit.NaviView;
import com.nuror1.CopyData;
import com.nuror1.NuroCore;

/**
 * Created by nuro on 7/13/15.
 */
public class NaviActivity extends NuroBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi);

        findViewById(R.id.nuro_go_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(NaviActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.zoomIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zoomIn(v);
            }
        });

        NuroCore.currNaviDrawable = (NaviView)findViewById(R.id.naviView);
    }


    static final int NURO_DEF_UI_SET_BOTTON_EVENT  =  0x01;
    static final int NURO_DEF_UI_SET_MOVE_MAP  =  0x02;   //Single Touch
    static final int NURO_DEF_UI_SET_USER_CONFIG  =  0x03;
    static final int NURO_DEF_UI_SET_CAR_POSITION  =  0x04;
    static final int NURO_DEF_UI_SET_NAVIGATION  =  0x05;

    static final int MOUSE_ZOOMOUT = 13;//Zoom out Button

    static class CNURO_BUTTON_EVENT {
        public int nCode = MOUSE_ZOOMOUT;
        public byte nButState;
        public int nX;
        public int nY;
    }

    public void zoomIn(View view) {
        CopyData data = new CopyData();
        data.dwData = NURO_DEF_UI_SET_BOTTON_EVENT;
        data.lpData = new CNURO_BUTTON_EVENT();
        NuroCore.sendCopyData(data);
        Log.i("test", "zoomIn");
        Toast.makeText(this, "缩小试验", Toast.LENGTH_SHORT).show();
    }
}
