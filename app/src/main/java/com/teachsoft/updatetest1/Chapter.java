package com.teachsoft.updatetest1;

import java.io.Serializable;

class Chapter implements Serializable {
    private static final long serialVersionUID = 1L;

    private String mTitle;
    private String mCode;

    public Chapter(){}

    String getTitle() {
        return mTitle;
    }
    String getCode() {
        return mCode;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
    public void setCode(String code) {
        mCode = code;
    }
}
