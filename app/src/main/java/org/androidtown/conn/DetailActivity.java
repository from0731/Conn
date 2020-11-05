package org.androidtown.conn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;


public class DetailActivity extends AppCompatActivity {
    Button CloseBtn;
    EditText Writer, Title, Content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        setTitle("상세화면");

        Writer = (EditText) findViewById(R.id.writer);
        Title = (EditText) findViewById(R.id.title);
        Content = (EditText) findViewById(R.id.content);
        CloseBtn = (Button) findViewById(R.id.closeBtn);

        Intent intent = getIntent();

        String writer = intent.getExtras().getString("writer");
        String title = intent.getExtras().getString("title");
        String content = intent.getExtras().getString("content");

        Writer.setText(writer);
        Title.setText(title);
        Content.setText(content);

        CloseBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}