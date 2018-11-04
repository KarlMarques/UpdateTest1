package com.teachsoft.updatetest1;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

class Subject implements Serializable {
    private static final long serialVersionUID = 1L;

    private String mTitle;
    private String mCode;
    private HashMap<String, Chapter> mChapters;

    public Subject(){ mChapters = new HashMap<>(); }

    String getTitle() {
        return mTitle;
    }
    String getCode() {
        return mCode;
    }
    HashMap<String, Chapter> getChapters() { return mChapters; }

    public void setTitle(String title) { mTitle = title; }
    public void setCode(String code) {
        mCode = code;
    }
    public void setChapters (HashMap<String, Chapter> chapters) { mChapters = chapters; }
}
