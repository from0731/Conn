package org.androidtown.conn;

public class Book {
//    private String title;
//    private String author;
//    private String imageUrl;
    String title;
    String day;
    String writer;
    int resId;
    String content;

//    public Book(String title, String author, String imageUrl) {
    public Book(String title, String day, String writer, String content,int resId) {

//        this.title = title;
//        this.author = author;
//        this.imageUrl = imageUrl;
        this.title = title;
        this.day = day;
        this.writer = writer;
        this.content = content;
        this.resId = resId;
    }



//    public String getTitle() {
//        return title;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public String getImageUrl() {
//        return imageUrl;
//    }


    public String getWriter() {
        return writer;
    }
    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getResId() {
        return resId;
    }
    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
