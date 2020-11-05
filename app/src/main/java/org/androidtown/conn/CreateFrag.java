package org.androidtown.conn;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import static android.app.Activity.RESULT_OK;


public class CreateFrag extends Fragment {

    Button UploadBtn, Geobtn;
    EditText Writer, Title, Content, Maps;

    public static final int REQUEST_CODE_ANOTHER = 1001;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.create, container, false);

        Writer = rootView.findViewById(R.id.writer);
        Title = rootView.findViewById(R.id.title);
        Content = rootView.findViewById(R.id.content);
        Geobtn = rootView.findViewById(R.id.geobtn);
        UploadBtn = rootView.findViewById(R.id.uploadBtn);
        Maps = rootView.findViewById(R.id.maps);


        Geobtn.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), MapsActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ANOTHER);
          }
        });


        UploadBtn.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {

            String sendmsg = "vision_write";

            String result0 = Writer.getText().toString();
            String result1 = Title.getText().toString();
            String result2 = Content.getText().toString();
            //자신이 보내고싶은 값을 보내시면됩니다

            if (Writer.getText().toString().length() == 0) {
                Toast.makeText(getActivity(), "이름을 입력하세요", Toast.LENGTH_SHORT).show();
                Writer.requestFocus();
                return;
            }

            if (Title.getText().toString().length() == 0) {
                Toast.makeText(getActivity(), "제목을 입력하세요", Toast.LENGTH_SHORT).show();
                Title.requestFocus();
                return;
            }

            if (Content.getText().toString().length() == 0) {
                Toast.makeText(getActivity(), "내용을 입력하세요", Toast.LENGTH_SHORT).show();
                Content.requestFocus();
                return;
            }

            try {
                String rst = new Task(sendmsg).execute(result0, "vision_write", result1, result2).get();
                Toast.makeText(getActivity(), "등록되었습니다.", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
                //  MainActivity에 intent로 리턴할 값은 이 영역에 코딩
        }
    });
        return rootView;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == REQUEST_CODE_ANOTHER) {
            Toast toast = Toast.makeText(getContext(),
                    "onActivityResult 메소드가 호출됨. 요청 코드: " + requestCode
                            + ", 결과 코드: " + resultCode, Toast.LENGTH_LONG);
            toast.show();

        if (resultCode == RESULT_OK) {
            String name = intent.getExtras().getString("name");
            Maps.setText(name);

            toast = Toast.makeText(getContext(),"응답으로 전달된 name : " + name, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
}