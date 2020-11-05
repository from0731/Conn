package org.androidtown.conn;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ListItemView extends LinearLayout {
    TextView TextView1;
    TextView TextView2;
    TextView TextView3;
    ImageView imageView;

    public ListItemView(Context context) {
        super(context);

        init(context);
    }

    public ListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.listitem, this, true);

        TextView1 = (TextView) findViewById(R.id.textView1);
        TextView2 = (TextView) findViewById(R.id.textView2);
        TextView3 = (TextView) findViewById(R.id.textView3);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    public void setTitle(String title) {
//        TextView1.setText(name);
        TextView1.setText(title);
    }


//    public void setMobile(String mobile) {
//        TextView2.setText(mobile);
//    }

    public void setDay(String day) {
        TextView2.setText(day);
    }


//    public void setAge(String age) {
//        TextView3.setText(String.valueOf(age));
//    }

    public void setWriter(String writer) {
        TextView3.setText(writer);
    }



    public void setImage(int resId) {
        imageView.setImageResource(resId);
    }
}
