package org.androidtown.conn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private FragList frag1;
    private FragCreate frag2Create;
    private FragPushList frag3;
    private FragChatting fragChatting;

    public static final int REQUEST_CODE_ANOTHER = 1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("리포터");

        bottomNavigationView = findViewById(R.id.bottomNavi);

        String TAG = "로그";

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, msg);
//                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch (menuItem.getItemId()) {
                case R.id.action_today:
                    setTitle("목록");
                    setFrag(0);
                    break;
                case R.id.action_add:
                    setTitle("작성");
                    setFrag(1);
                    break;
                case R.id.action_push:
                    setTitle("알림");
                    setFrag(2);
                    break;
                case R.id.action_setting:
                    setTitle("채팅");
                    setFrag(3);
                    break;
                }
                return true;
            }
        });

        frag1 = new FragList();
        frag2Create = new FragCreate();
        frag3 = new FragPushList();
        fragChatting = new FragChatting();
        setFrag(0); // 첫 프래그먼트 화면 지정
    }

    // 프레그먼트 교체
    private void setFrag(int n) {
        fm = getSupportFragmentManager();
        ft= fm.beginTransaction();
        switch (n) {
            case 0:
                ft.replace(R.id.Main_Frame, frag1);
                ft.commit();
                break;

            case 1:
                ft.replace(R.id.Main_Frame, frag2Create);
                ft.commit();
                break;

            case 2:
                ft.replace(R.id.Main_Frame, frag3);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.Main_Frame, fragChatting);
                ft.commit();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search :

                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ANOTHER);

                // TODO : process the click event for action_search item.
                return true ;
            // ...
            default :
                return super.onOptionsItemSelected(item) ;
        }
    }
}