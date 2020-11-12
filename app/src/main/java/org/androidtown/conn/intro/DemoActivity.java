package org.androidtown.conn.intro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.androidtown.conn.DetailActivity;
import org.androidtown.conn.MainActivity;
import org.androidtown.conn.R;


/**
 * Created by matt on 5/27/14.
 */
public class DemoActivity extends Activity {

    SecretTextView secretTextView;
    Button change;
    public static final int REQUEST_CODE_ANOTHER = 1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        secretTextView = (SecretTextView)findViewById(R.id.textview);
        secretTextView.setDuration(7000);
        secretTextView.setIsVisible(true);
        secretTextView.toggle();

//        secretTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                secretTextView.toggle();
//            }
//        });

        change = (Button) findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ANOTHER);
            }
        });
    }
}
