package com.dubbod.bean;

import java.io.Serializable;

/**
 * Created by bin.liang on 2016/12/2.
 */
public class Blog  implements Serializable {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "title='" + title + '\'' +
                '}';
    }
}
