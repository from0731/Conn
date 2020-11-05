package org.androidtown.conn;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import org.json.JSONArray;
import org.json.JSONObject;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.util.ArrayList;
import android.widget.AdapterView;
import android.widget.ListView;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ListFrag extends Fragment {

    private View view;
    SingerAdapter adapter;
    ArrayList<ListItem> items = new ArrayList<ListItem>();
    ListView listView;

    public static final int REQUEST_CODE_ANOTHER = 1001;

    //bottom navigation 관련 정의
    private BottomNavigationView bottomNavigationView; // 바텀 네비게이션 뷰
    private FragmentManager fm;
    private FragmentTransaction ft;
    private ListFrag frag1;
    private CreateFrag frag2Create;
    private Frag3 frag3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list, container, false);
        adapter = new SingerAdapter();

        listView = rootView.findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ListItem item = (ListItem) adapter.getItem(position);
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("writer", item.getAge());
                intent.putExtra("title", item.getName());
//                intent.putExtra("content", item.getMobile());
                intent.putExtra("day", item.getMobile());

                startActivityForResult(intent, REQUEST_CODE_ANOTHER);
            }
        });

            String sendmsg = "vision_list";
            String receiveMsg; //전체출력 result;

            try {
                receiveMsg = new Task(sendmsg).execute("vision_list").get();//디비값을 가져오기
                Log.i("통신 결과", receiveMsg);

                JSONObject jObject = new JSONObject(receiveMsg); // 가장 큰 JSONObject를 가져옵니다.
                JSONArray jArray = jObject.getJSONArray("Dataset"); // 배열을 가져옵니다.

                items.clear();

                // 배열의 모든 아이템을 출력합니다.
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject obj = jArray.getJSONObject(i);

                    String wr = obj.getString("WR");
                    String day = obj.getString("DAY");
                    String title = obj.getString("CON");
//                    String content = obj.getString("CONTENT");

                    adapter.addItem(new ListItem(title, day, wr, R.drawable.singer8));
                    listView.setAdapter(adapter);

                    adapter.notifyDataSetChanged();

                    System.out.println("wr(" + i + "): " + wr);
                    System.out.println("day(" + i + "): " + day);
                    System.out.println("title(" + i + "): " + title);
//                    System.out.println("content(" + i + "): " + content);
                    System.out.println();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        return rootView;
    }


    class SingerAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(ListItem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            ListItemView view = new ListItemView(getActivity());

            ListItem item = items.get(position);
            view.setName(item.getName());
            view.setMobile(item.getMobile());
            view.setAge(item.getAge());
            view.setImage(item.getResId());

//            view.setContent(item.getContent());


            return view;
        }
    }
}