package com.example.nuro.myapplication;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.security.acl.Group;

/**
 * Created by nuro on 7/13/15.
 */
public class SearchResultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ListView l = (ListView) findViewById(R.id.list_result);
        l.setAdapter(new NuroListBaseAdapter());
    }

    public void exit(View view) {
        finish();
    }

    class NuroListBaseAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 40;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView btn = new TextView(SearchResultActivity.this);
            ListView.LayoutParams lp = new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT, 180);
            btn.setLayoutParams(lp);
            btn.setGravity(Gravity.CENTER_VERTICAL);
            btn.setText("滨江高新软件园");
            btn.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
            return btn;
        }
    }
}
