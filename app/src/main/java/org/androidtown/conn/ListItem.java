package org.androidtown.conn;

/**
 * 11월 5일 해야할일은 이 영역하고 ListItem 부분 현행화
 * content 받아온
 */
public class ListItem {

    String name;
    String mobile;
    String age;
    int resId;
    String content;


    public ListItem(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }

    public ListItem(String name, String mobile, String age, int resId) {
        this.name = name;
        this.mobile = mobile;
        this.age = age;
        this.resId = resId;
    }

    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }

    public int getResId() {
        return resId;
    }
    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String name) {
        this.content = content;
    }


}

