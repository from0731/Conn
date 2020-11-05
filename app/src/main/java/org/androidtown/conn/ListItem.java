package org.androidtown.conn;

public class ListItem {

//    String name;
    String title;

//    String mobile;
    String day;

    //    String age;
    String writer;


    int resId;
    String content;


    public ListItem(String title, String day) {
//    public ListItem(String title, String mobile) {
//    public ListItem(String name, String mobile) {
//        this.name = name;

        this.title = title;
//        this.mobile = mobile;
        this.day = day;

    }


    public ListItem(String title, String day, String writer, int resId) {
//    public ListItem(String title, String mobile, String writer, int resId) {
//    public ListItem(String title, String mobile, String age, int resId) {
//    public ListItem(String name, String mobile, String age, int resId) {
//        this.name = name;
        this.title = title;

//        this.mobile = mobile;
        this.day = day;

//        this.age = age;
        this.writer = writer;

        this.resId = resId;
    }

//    public String getAge() {
//        return age;
//    }
//    public void setAge(String age) {
//        this.age = age;
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

//    public String getMobile() {
//        return mobile;
//    }
//    public void setMobile(String mobile) {
//        this.mobile = mobile;
//    }

    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }



//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    public String getContent() {
        return content;
    }
    public void setContent(String name) {
        this.content = content;
    }


}

