package com.teachsoft.updatetest1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BaseActivity extends AppCompatActivity {
//    Parameters shared between activities
    static final String CURRENT_SUBJECT = "CURRENT_SUBJECT";
    static final String CURRENT_CHAPTER = "CURRENT_CHAPTER";
    static final String CURRENT_EXERCISE = "CURRENT_EXERCISE";

//    Database Paths
    static final String SUBJECTS_TITLE = "subjects";
    static final String CHAPTERS_TITLE = "chapters";
    static final String EXERCISES_TITLE = "exercises";
}
