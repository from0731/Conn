package org.androidtown.conn;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.google.android.material.snackbar.Snackbar;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;

import static android.widget.Toast.LENGTH_LONG;


public class FragList extends Fragment {

    BooksAdapter badapter;
    List<Book> books = new ArrayList<Book>();
    RecyclerView recyclerView;
    SwipeToAction swipeToAction;

    PullRefreshLayout layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.frag_list, container, false);

        // 리사이클뷰 코드
        Fresco.initialize(getContext());

        recyclerView = view.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        badapter = new BooksAdapter(this.books);
        recyclerView.setAdapter(badapter);

        layout = (PullRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        layout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                layout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        layout.setRefreshing(false);
                        onResume();
                    }
                }, 4000);
            }
        });


//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//
//                if (!recyclerView.canScrollVertically(2)) {
//                    Toast.makeText(getActivity(), "Last", Toast.LENGTH_LONG).show();
//
//                }
//            }
//        });


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
                final int pos = removeBook(itemData);

                displaySnackbar(itemData.getTitle() + " loved", "action", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addBook(pos, itemData);
                    }
                });

                return true;
            }

            @Override
            public void onClick(Book itemData) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);

                intent.putExtra("writer", itemData.getWriter());
                intent.putExtra("title", itemData.getTitle());
                intent.putExtra("content", itemData.getContent());
                intent.putExtra("day", itemData.getDay());

                startActivity(intent);
            }

            @Override
            public void onLongClick(Book itemData) {
                displaySnackbar(itemData.getTitle() + " long clicked", null, null);
            }
        });

        populate();

        // use swipeLeft or swipeRight and the elem position to swipe by code
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//
//
//                swipeToAction.swipeRight(2);
//            }
//        }, 3000);
        return view;
    }

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
 }

    private void displaySnackbar(String text, String actionName, View.OnClickListener action) {

        Snackbar snack;

        snack = Snackbar.make(getActivity().findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG)
                .setAction(actionName, action);

        View v = snack.getView();
        v.setBackgroundColor(getResources().getColor(R.color.secondary));
        ((TextView) v.findViewById(R.id.snackbar_text)).setTextColor(Color.WHITE);
        ((TextView) v.findViewById(R.id.snackbar_action)).setTextColor(Color.BLACK);

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