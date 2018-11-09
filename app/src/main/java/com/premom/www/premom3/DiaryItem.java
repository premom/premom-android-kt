package com.premom.www.premom3;

import android.graphics.drawable.Drawable;

public class DiaryItem {

    public byte[] getIs_pic() {
        return is_pic;
    }

    public void setIs_pic(byte[] is_pic) {
        this.is_pic = is_pic;
    }

    byte[] is_pic;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String title;
    String content;
    String date;

}
