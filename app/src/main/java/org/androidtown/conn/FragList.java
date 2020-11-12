package org.androidtown.conn;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class FragList extends Fragment {

    View view;
//    SingerAdapter adapter;
//    ArrayList<ListItem> items = new ArrayList<ListItem>();
    ListView listView;

    BooksAdapter badapter;

    List<Book> books = new ArrayList<>();


    RecyclerView recyclerView;
    SwipeToAction swipeToAction;

    public static final int REQUEST_CODE_ANOTHER = 1001;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.frag_list, container, false);
//        adapter = new SingerAdapter();




        // 리사이클뷰 코드
        Fresco.initialize(getContext());

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        badapter = new BooksAdapter(this.books);
        recyclerView.setAdapter(badapter);

        swipeToAction = new SwipeToAction(recyclerView, new SwipeToAction.SwipeListener<Book>() {
            @Override
            public boolean swipeLeft(final Book itemData) {
                final int pos = removeBook(itemData);
                displaySnackbar(itemData.getTitle() + " removed", "Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addBook(pos, itemData);
                    }
                });
                return true;
            }

            @Override
            public boolean swipeRight(Book itemData) {
                displaySnackbar(itemData.getTitle() + " loved", null, null);
                return true;
            }

            @Override
            public void onClick(Book itemData) {
//                displaySnackbar(itemData.getTitle() + " clicked", null, null);
                Intent intent = new Intent(getActivity(), DetailActivity.class);

                intent.putExtra("writer", itemData.getWriter());
                intent.putExtra("title", itemData.getTitle());
                intent.putExtra("content", itemData.getContent());
                intent.putExtra("day", itemData.getDay());

                startActivityForResult(intent, REQUEST_CODE_ANOTHER);
            }

            @Override
            public void onLongClick(Book itemData) {
                displaySnackbar(itemData.getTitle() + " long clicked", null, null);
            }
        });


        populate();

        // use swipeLeft or swipeRight and the elem position to swipe by code
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToAction.swipeRight(2);
            }
        }, 3000);
        //리사이클로 변경하는 코드








//        listView = rootView.findViewById(R.id.listView);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                ListItem item = (ListItem) adapter.getItem(position);
//                Intent intent = new Intent(getActivity(), DetailActivity.class);
//
//                intent.putExtra("writer", item.getWriter());
//                intent.putExtra("title", item.getTitle());
//                intent.putExtra("content", item.getContent());
//                intent.putExtra("day", item.getDay());
//
//                startActivityForResult(intent, REQUEST_CODE_ANOTHER);
//            }
//        });

        String sendmsg = "vision_list";
        String receiveMsg; //전체출력 result;

//            try {
//                receiveMsg = new Task(sendmsg).execute("vision_list").get();//디비값을 가져오기
//                Log.i("통신 결과", receiveMsg);
//
//                JSONObject jObject = new JSONObject(receiveMsg); // 가장 큰 JSONObject를 가져옵니다.
//                JSONArray jArray = jObject.getJSONArray("Dataset"); // 배열을 가져옵니다.
//
//                items.clear();
//
//                // 배열의 모든 아이템을 출력합니다.
//                for (int i = 0; i < jArray.length(); i++) {
//                    JSONObject obj = jArray.getJSONObject(i);
//
//                    String wr = obj.getString("WR");
//                    String day = obj.getString("DAY");
//                    String title = obj.getString("CON");
//                    String content = obj.getString("CONTENT");
//
//                    adapter.addItem(new ListItem(title, day, wr, content, R.drawable.singer8));
//                    listView.setAdapter(adapter);
//
//                    adapter.notifyDataSetChanged();
//
//                    System.out.println("wr(" + i + "): " + wr);
//                    System.out.println("day(" + i + "): " + day);
//                    System.out.println("title(" + i + "): " + title);
//                    System.out.println("content(" + i + "): " + content);
//                    System.out.println();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        return rootView;
    }


//    class SingerAdapter extends BaseAdapter {
//
//        @Override
//        public int getCount() {
//            return items.size();
//        }
//
//        public void addItem(ListItem item) {
//            items.add(item);
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return items.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup viewGroup) {
//            ListItemView view = new ListItemView(getActivity());
//
//            ListItem item = items.get(position);
//            view.setTitle(item.getTitle());
//            view.setDay(item.getDay());
//            view.setContent(item.getContent());
//            view.setWriter(item.getWriter());
//            view.setImage(item.getResId());
//            return view;
//        }
//    }



    // 리사이클로 변경하는 코드
    private void populate() {

        String sendmsg = "vision_list";
        String receiveMsg; //전체출력 result;

        try {
            receiveMsg = new Task(sendmsg).execute("vision_list").get();//디비값을 가져오기
            Log.i("통신 결과", receiveMsg);

            JSONObject jObject = new JSONObject(receiveMsg); // 가장 큰 JSONObject를 가져옵니다.
            JSONArray jArray = jObject.getJSONArray("Dataset"); // 배열을 가져옵니다.

            books.clear();

            // 배열의 모든 아이템을 출력합니다.
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject obj = jArray.getJSONObject(i);

                String wr = obj.getString("WR");
                String day = obj.getString("DAY");
                String title = obj.getString("CON");
                String content = obj.getString("CONTENT");

//                adapter.addItem(new ListItem(title, day, wr, content, R.drawable.singer8));
//                listView.setAdapter(adapter);

                this.books.add(new Book(title, day, wr, content, R.drawable.singer8));
                badapter.notifyDataSetChanged();

                System.out.println("wr(" + i + "): " + wr);
                System.out.println("day(" + i + "): " + day);
                System.out.println("title(" + i + "): " + title);
                System.out.println("content(" + i + "): " + content);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;


//        this.books.add(new Book("Einstein: his life and universe", "Walter Isaacson", "https://image.aladin.co.kr/product/23775/60/cover500/k012639065_2.jpg"));
//        this.books.add(new Book("Zero to One: Notes on Startups, or How to Build the Future", "Peter Thiel, Blake Masters", "http://static.bookstck.com/books/zero-to-one-400.jpg"));
//        this.books.add(new Book("Tesla: Inventor of the Electrical Age", "W. Bernard Carlson", "http://static.bookstck.com/books/tesla-inventor-of-the-electrical-age-400.jpg"));
//        this.books.add(new Book("Orwell's Revenge: The \"1984\" Palimpsest", "Peter Huber", "http://static.bookstck.com/books/orwells-revenge-400.jpg"));
//        this.books.add(new Book("How to Lie with Statistics", "Darrell Huff", "http://static.bookstck.com/books/how-to-lie-with-statistics-400.jpg"));
//        this.books.add(new Book("Abundance: The Future Is Better Than You Think", "Peter H. Diamandis, Steven Kotler", "http://static.bookstck.com/books/abundance-400.jpg"));
//        this.books.add(new Book("Where Good Ideas Come From", "Steven Johnson", "http://static.bookstck.com/books/where-good-ideas-come-from-400.jpg"));
//        this.books.add(new Book("The Information: A History, A Theory, A Flood", "James Gleick", "http://static.bookstck.com/books/the-information-history-theory-flood-400.jpg"));
//        this.books.add(new Book("Turing's Cathedral: The Origins of the Digital Universe", "George Dyson", "http://static.bookstck.com/books/turing-s-cathedral-400.jpg"));
    }

    private void displaySnackbar(String text, String actionName, View.OnClickListener action) {
        Snackbar snack = Snackbar.make(getActivity().findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG)
                .setAction(actionName, action);

        View v = snack.getView();
        v.setBackgroundColor(getResources().getColor(R.color.secondary));
        ((TextView) v.findViewById(com.google.android.material.R.id.snackbar_text)).setTextColor(Color.WHITE);
        ((TextView) v.findViewById(com.google.android.material.R.id.snackbar_action)).setTextColor(Color.BLACK);

        snack.show();
    }

    private int removeBook(Book book) {
        int pos = books.indexOf(book);
        books.remove(book);
        badapter.notifyItemRemoved(pos);
        return pos;
    }

    private void addBook(int pos, Book book) {
        books.add(pos, book);
        badapter.notifyItemInserted(pos);
    }
    //리사이클 코드

}