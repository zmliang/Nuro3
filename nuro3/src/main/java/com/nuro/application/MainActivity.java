package com.nuro.application;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;

public class MainActivity extends NuroBaseActivity {
    public GridView gridView;

    public LinearLayout historyLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.online_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        gridView = (GridView)findViewById(R.id.nuro_main_menu);
        historyLayout = (LinearLayout) findViewById(R.id.history_layout);
        historyLayout.setBackgroundResource(R.drawable.bg_common_button);
//        NuroAnimation.animationAction(historyLayout);

        if (gridView != null) {
            gridView.setAdapter(new GridViewAdapter(this));
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void exit(View view) {
        finish();
    }
}
