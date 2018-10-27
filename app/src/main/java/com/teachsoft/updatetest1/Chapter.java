package com.teachsoft.updatetest1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Chapter implements Serializable {
    private static final long serialVersionUID = 1L;

    private String mTitle;
    private String mCode;
    private List<Exercise> mExercises;

    public Chapter(){
        mExercises = new ArrayList<>();
    }

    String getTitle() {
        return mTitle;
    }
    String getCode() {
        return mCode;
    }
    List<Exercise> getExercises() { return mExercises; }

    public void setTitle(String title) {
        mTitle = title;
    }
    public void setCode(String code) {
        mCode = code;
    }
    public void setmExercises (List<Exercise> exercises) { mExercises = exercises; }
}
