package com.teachsoft.updatetest1;

import java.io.Serializable;

class ClassSubject implements Serializable {
    private static final long serialVersionUID = 1L;

    private String mTitle;

    public ClassSubject(){}

    public ClassSubject(String title) {
        mTitle = title;
    }

    String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    @Override
    public String toString() {
        return "ClassSubject{" +
                "mTitle='" + mTitle + '\'' +
                '}';
    }
}
