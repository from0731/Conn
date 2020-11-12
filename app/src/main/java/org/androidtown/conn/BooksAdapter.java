package org.androidtown.conn;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;


public class BooksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Book> items;


    /** References to the views for each data item **/
    public class BookViewHolder extends SwipeToAction.ViewHolder<Book> {
        public TextView titleView;
        public TextView authorView;
        public TextView dayView;
        public TextView contentView;
        public SimpleDraweeView imageView;

        public BookViewHolder(View v) {
            super(v);

            titleView = (TextView) v.findViewById(R.id.title);
            authorView = (TextView) v.findViewById(R.id.author);
            dayView = (TextView) v.findViewById(R.id.day);
            contentView = (TextView) v.findViewById(R.id.content);

            imageView = (SimpleDraweeView) v.findViewById(R.id.image);
        }
    }

    /** Constructor **/
    public BooksAdapter(List<Book> items) {
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);

        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Book item = items.get(position);
        BookViewHolder vh = (BookViewHolder) holder;
        vh.titleView.setText(item.getTitle());
        vh.authorView.setText(item.getContent());
        vh.dayView.setText(item.getDay());
        vh.contentView.setText(item.getWriter());
//        vh.imageView.setImageURI(Uri.parse(item.getImageUrl()));
        vh.imageView.setImageURI(Uri.parse("https://image.aladin.co.kr/product/25398/90/cover500/8954675379_1.jpg"));

        vh.data = item;
    }
}