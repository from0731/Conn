package org.androidtown.conn;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class PushListItemView extends LinearLayout {
    TextView TextView1;
    TextView TextView2;
    TextView TextView3;

    public PushListItemView(Context context) {
        super(context);
        init(context);
    }

    public PushListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.pushlistitem, this, true);

        TextView1 = (TextView) findViewById(R.id.textView1);
        TextView2 = (TextView) findViewById(R.id.textView2);
        TextView3 = (TextView) findViewById(R.id.textView3);

    }

    public void setTitle(String title) {
        TextView1.setText(title);
    }

    public void setDay(String day) {
        TextView2.setText(day);
    }

    public void setBody(String body) {
        TextView3.setText(body);
    }

}
