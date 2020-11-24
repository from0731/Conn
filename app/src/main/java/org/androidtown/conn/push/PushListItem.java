package org.androidtown.conn.push;

public class PushListItem {

    String title;
    String day;
    String body;
    int resId;
    String content;


    public PushListItem(String title, String day) {
        this.title = title;
        this.day = day;
    }

    public PushListItem(String title, String day, String body) {
        this.title = title;
        this.day = day;
        this.body = body;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
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

