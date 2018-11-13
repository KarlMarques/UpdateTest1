package com.teachsoft.updatetest1;

import java.io.Serializable;
import java.util.HashMap;

class Exercise implements Serializable {
    private static final long serialVersionUID = 1L;

    private String mTitle;
    private String mCode;
    private String mQuestion;
    private String mAnswer;
    private String mDifficulty;
    private int mScore;

    private HashMap<String, String> mAlternatives;

    public Exercise(){
        mAlternatives = new HashMap<>();
        mScore = -1;
    }

    String getTitle() {
        return mTitle;
    }
    String getCode() {
        return mCode;
    }
    String getQuestion() { return mQuestion; }
    String getAnswer() { return  mAnswer; }
    String getDifficulty() { return  mDifficulty; }
    int getScore() { return mScore; }

    HashMap<String, String> getAlternatives() { return mAlternatives; }
    String getAlternative(String key) { return mAlternatives.get(key); }

    public void setTitle(String title) {
        mTitle = title;
    }
    public void setCode(String code) {
        mCode = code;
    }
    public void setQuestion(String question) { mQuestion = question; }
    public void setAnswer(String answer) { mAnswer = answer; }
    public void setDifficulty(String difficulty) { mDifficulty = difficulty; }
    public void setScore(int score) { mScore = score; }

    public void setAlternatives (HashMap<String, String> alternatives) { mAlternatives = alternatives; }
    public void setAlternative (String key, String alternative) { mAlternatives.put(key, alternative); }
}
