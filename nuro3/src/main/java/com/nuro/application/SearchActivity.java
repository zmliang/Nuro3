package com.nuro.application;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

/**
 * Created by nuro on 7/13/15.
 */
public class SearchActivity extends NuroBaseActivity {

    public GridView gridView;

    public String[] keyboard = {
            "A", "B", "C", "D", "E", "F", "0", "5",
            "G", "H", "I", "J", "K", "L", "1", "6",
            "M", "N", "O", "P", "Q", "R", "2", "7",
            "S", "T", "U", "V", "W", "X", "3", "8",
            "Y", "Z",  "",  "",  "",  "", "4", "9"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        gridView = (GridView) findViewById(R.id.search_keyboard);
        gridView.setAdapter(new NuroKeyboardAdapter());
    }

    class NuroKeyboardAdapter extends BaseAdapter {

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
            Button btn = new Button(SearchActivity.this);
            GridView.LayoutParams lp =
                    new GridView.LayoutParams(GridView.LayoutParams.WRAP_CONTENT,
                            DisplayUtil.dip2px(getApplicationContext(), 41));
            btn.setLayoutParams(lp);
            btn.setText(keyboard[position]);
            btn.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
//            btn.setBackgroundColor(0xffdddddd);
            btn.setTextColor(0xffffffff);
            if (TextUtils.isEmpty(keyboard[position])) {
                btn.setBackgroundResource(R.drawable.nuro_key_board_disable);
            } else {
                btn.setBackgroundResource(R.drawable.nuro_keyboard_bg_selector);
            }
            return btn;
        }
    }

    public void onSubmit(View view){
        Intent intent = new Intent();
        intent.setClass(this, SearchResultActivity.class);
        startActivity(intent);
    }
}
