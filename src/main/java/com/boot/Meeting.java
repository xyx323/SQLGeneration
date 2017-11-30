package com.boot;

/**
 * Created by Bruinx on 2017/11/30.
 */
public class Meeting {
    private final long id;
    private final String content;

    public Meeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}

