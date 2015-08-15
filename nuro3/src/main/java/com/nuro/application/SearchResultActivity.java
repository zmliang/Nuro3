package com.nuro.application;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by nuro on 7/13/15.
 */
public class SearchResultActivity extends NuroBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setBackgroundDrawableResource(R.drawable.nuro_bg_srp);
        setContentView(R.layout.activity_search_result);
        ListView l = (ListView) findViewById(R.id.list_result);
        l.setAdapter(new NuroListBaseAdapter());
    }

    class NuroListBaseAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 1000;
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

            if (convertView == null) {
                TextView btn = new TextView(SearchResultActivity.this);
                ListView.LayoutParams lp = new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT,
                        DisplayUtil.dip2px(SearchResultActivity.this, 57.6f));
                btn.setPadding(DisplayUtil.dip2px(SearchResultActivity.this, 10), 0, 0, 0);
                btn.setLayoutParams(lp);
                btn.setGravity(Gravity.CENTER_VERTICAL);
                btn.setBackgroundResource(R.drawable.bg_list_item);

                btn.setTextColor(Color.WHITE);
                btn.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
                convertView = btn;
            }

            ((TextView)convertView).setText("滨江高新软件园" + position);

            return convertView;
        }
    }
}
