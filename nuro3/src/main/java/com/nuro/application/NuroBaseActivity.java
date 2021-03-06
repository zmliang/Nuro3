package com.nuro.application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by nuro on 8/5/15.
 */
public class NuroBaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        window.setBackgroundDrawableResource(R.drawable.nuro_bg);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        View v = findViewById(R.id.actionbar_return);
        if (v != null) {
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        v = findViewById(R.id.actionbar_map);
        if (v != null) {
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(NuroBaseActivity.this, NaviActivity.class);
                    NuroBaseActivity.super.startActivity(intent);
                    overridePendingTransition(R.anim.left_in, R.anim.right_out);
                }
            });
        }
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }
}
