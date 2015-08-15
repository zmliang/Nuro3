package com.nuro.application;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by nuro on 7/12/15.
 */
public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private AnimationList animationList;

    public GridViewAdapter(Context context) {
        this.context = context;
        animationList = new AnimationList(context);
    }

    @Override
    public int getCount() {
        String [] strings = context.getResources().getStringArray(R.array.nuro_main_menu_array);
        if (strings == null) {
            return 0;
        }
        return strings.length;
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
        final TextView textView; // 声明ImageView的对象
        if (convertView == null) {
            textView = new TextView(context); // 实例化ImageView的对象
            GridView.LayoutParams lp = new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.WRAP_CONTENT);
            textView.setLayoutParams(lp);
            textView.setGravity(Gravity.CENTER);
            //imageview.setScaleType(ImageView.ScaleType.CENTER_INSIDE); // 设置缩放方式
            textView.setPadding(8, 8, 8, 8); // 设置ImageView的内边距
            textView.setTextColor(0xffaaaaaa);
            textView.setTypeface(Typeface.create("monospace",Typeface.NORMAL));
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            textView.setVisibility(View.INVISIBLE);
            animationList.add(textView, NuroAnimation.animationTranslate(position));

            if (position == 7) {
                animationList.startAnimation();
            }

        } else {
            textView = (TextView) convertView;
        }

        String [] strings = context.getResources().getStringArray(R.array.nuro_main_menu_array);
        textView.setText(strings[position]);
//        textView.setBackgroundColor(0xffdddddd);
        textView.setBackgroundResource(R.drawable.bg_common_button);
        //imageview.setImageResource(imageId[position]); // 为ImageView设置要显示的图片
        return textView; // 返回ImageView
    }
}
