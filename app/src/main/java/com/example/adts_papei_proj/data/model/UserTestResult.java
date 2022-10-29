package com.example.adts_papei_proj.data.model;

import java.util.ArrayList;
import java.util.Date;

public class UserTestResult {

    private ArrayList<Question> questions = new ArrayList<>();
    private String score;
    private double scorePercentage;
    private String uid;
    private ArrayList<Question> correctAnsweredQuestions = new ArrayList<>();
    private ArrayList<Question> wrongAnsweredQuestions = new ArrayList<>();
    private Date date;
    private String username = "";
    private int level = 0;

    public UserTestResult() {
    }

    public UserTestResult(ArrayList<Question> questions, String score, double scorePercentage,
                          String uid, ArrayList<Question> correctAnsweredQuestions,
                          ArrayList<Question> wrongAnsweredQuestions, Date date, int level) {
        this.questions = questions;
        this.score = score;
        this.scorePercentage = scorePercentage;
        this.uid = uid;
        this.correctAnsweredQuestions = correctAnsweredQuestions;
        this.wrongAnsweredQuestions = wrongAnsweredQuestions;
        this.date = date;
        this.level = level;

    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public double getScorePercentage() {
        return scorePercentage;
    }

    public void setScorePercentage(double scorePercentage) {
        this.scorePercentage = scorePercentage;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public ArrayList<Question> getCorrectAnsweredQuestions() {
        return correctAnsweredQuestions;
    }

    public void setCorrectAnsweredQuestions(ArrayList<Question> correctAnsweredQuestions) {
        this.correctAnsweredQuestions = correctAnsweredQuestions;
    }

    public ArrayList<Question> getWrongAnsweredQuestions() {
        return wrongAnsweredQuestions;
    }

    public void setWrongAnsweredQuestions(ArrayList<Question> wrongAnsweredQuestions) {
        this.wrongAnsweredQuestions = wrongAnsweredQuestions;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
