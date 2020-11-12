package org.androidtown.conn;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class FragPushList extends Fragment {

    private View view;
    SingerAdapter adapter;
    ArrayList<PushListItem> items = new ArrayList<PushListItem>();
    ListView listView;

    public static final int REQUEST_CODE_ANOTHER = 1001;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.frag_pushlist, container, false);
        adapter = new SingerAdapter();

        listView = rootView.findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

//이벤트 리스트 클릭시 할 부분
                PushListItem item = (PushListItem) adapter.getItem(position);
//                Intent intent = new Intent(getActivity(), DetailActivity.class);
//
//                intent.putExtra("body", item.getBody());
//                intent.putExtra("title", item.getTitle());
//                intent.putExtra("day", item.getDay());
//
//                startActivityForResult(intent, REQUEST_CODE_ANOTHER);
                Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

            String sendmsg = "push_list";
            String receiveMsg; //전체출력 result;

            try {
                receiveMsg = new Task(sendmsg).execute("push_list").get();//디비값을 가져오기
                Log.i("통신 결과", receiveMsg);

                JSONObject jObject = new JSONObject(receiveMsg); // 가장 큰 JSONObject를 가져옵니다.
                JSONArray jArray = jObject.getJSONArray("Dataset"); // 배열을 가져옵니다.

                items.clear();

                // 배열의 모든 아이템을 출력합니다.
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject obj = jArray.getJSONObject(i);

                    String day = obj.getString("DAY");
                    String title = obj.getString("TITLE");
                    String body = obj.getString("BODY");

                    adapter.addItem(new PushListItem(title, body, day));
                    listView.setAdapter(adapter);

                    adapter.notifyDataSetChanged();

                    System.out.println("body(" + i + "): " + body);
                    System.out.println("day(" + i + "): " + day);
                    System.out.println("title(" + i + "): " + title);
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

        public void addItem(PushListItem item) {
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
            PushListItemView view = new PushListItemView(getActivity());

            PushListItem item = items.get(position);
            view.setTitle(item.getTitle());
            view.setDay(item.getDay());
            view.setBody(item.getBody());

            return view;
        }
    }
}