package com.example.adts_papei_proj.ui.test;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.example.adts_papei_proj.data.model.Question;

import java.util.ArrayList;

public class TestViewModel extends BaseObservable {

    @Bindable
    private String score = "";
    @Bindable
    private int correctAnswers = 0;
    @Bindable
    private int wrongAnswers = 0;
    @Bindable
    private ArrayList<Question> questionArrayList = new ArrayList<>();
    @Bindable
    private Context context;

    public TestViewModel(){

    }

    public TestViewModel(String score, int correctAnswers, int wrongAnswers, ArrayList<Question> questionArrayList, Context context) {
        this.score = score;
        this.correctAnswers = correctAnswers;
        this.wrongAnswers = wrongAnswers;
        this.questionArrayList = questionArrayList;
        this.context = context;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(int wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    public ArrayList<Question> getQuestionArrayList() {
        return questionArrayList;
    }

    public void setQuestionArrayList(ArrayList<Question> questionArrayList) {
        this.questionArrayList = questionArrayList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
