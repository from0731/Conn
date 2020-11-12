package org.androidtown.conn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class FragChatting extends Fragment {

    private View view;
    ListView ListView;
    EditText EditText_chat;
    Button Button_send;
    String userName;
    ArrayAdapter adapter;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    @SuppressWarnings("unchecked")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)    {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.frag_chatting, container, false);

        EditText_chat = rootView.findViewById(R.id.EditText_chat);
        Button_send = rootView.findViewById(R.id.Button_send);
        userName = "user" + new Random().nextInt(10000);  // 랜덤한 유저 이름 설정 ex) user1234

        ListView = rootView.findViewById(R.id.listView);

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1);
        ListView.setAdapter(adapter);


        Button_send.setOnClickListener((view) -> {
            if (EditText_chat.getText().toString().length() == 0) {
                Toast.makeText(getActivity(), "내용을 입력하세요", Toast.LENGTH_SHORT).show();
                EditText_chat.requestFocus();
                return;
            }

            ChatData chatData = new ChatData(userName, EditText_chat.getText().toString());  // 유저 이름과 메세지로 chatData 만들기
            databaseReference.child("message").push().setValue(chatData);  // 기본 database 하위 message라는 child에 chatData를 list로 만들기
            EditText_chat.setText("");
        });


        databaseReference.child("message").addChildEventListener(new ChildEventListener() {
            private static final String TAG = "로그";  // message는 child의 이벤트를 수신합니다.
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                ChatData chatData = dataSnapshot.getValue(ChatData.class);  // chatData를 가져오고
                adapter.add(chatData.getUserName() + ": " + chatData.getMessage());  // adapter에 추가합니다.
                Log.d(TAG, chatData.getUserName()+" "+chatData.getMessage());

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) { }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });

        return rootView;
    }
}