package com.nuro.application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

    }
}
