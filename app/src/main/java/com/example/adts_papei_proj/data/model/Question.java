package com.example.adts_papei_proj.data.model;

public class Question {

    public String questionText = "";
    public String possibleAnswer1 = "";
    public String possibleAnswer2 = "";
    public String possibleAnswer3 = "";
    public String possibleAnswer4 = "";
    public String questionAnswer = "";
    public int userChoice = 0;
    public int correctAnswer = 0;
    public int questionLevel = 0;//0 is b1 level 1 is b2 level

    public Question() {
    }

    public Question(String questionText, String possibleAnswer1, String possibleAnswer2,
                    String possibleAnswer3, String possibleAnswer4, String questionAnswer,
                    int userChoice, int correctAnswer, int questionLevel) {
        this.questionText = questionText;
        this.possibleAnswer1 = possibleAnswer1;
        this.possibleAnswer2 = possibleAnswer2;
        this.possibleAnswer3 = possibleAnswer3;
        this.possibleAnswer4 = possibleAnswer4;
        this.questionAnswer = questionAnswer;
        this.userChoice = userChoice;
        this.correctAnswer = correctAnswer;
        this.questionLevel = questionLevel;

    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getPossibleAnswer1() {
        return possibleAnswer1;
    }

    public void setPossibleAnswer1(String possibleAnswer1) {
        this.possibleAnswer1 = possibleAnswer1;
    }

    public String getPossibleAnswer2() {
        return possibleAnswer2;
    }

    public void setPossibleAnswer2(String possibleAnswer2) {
        this.possibleAnswer2 = possibleAnswer2;
    }

    public String getPossibleAnswer3() {
        return possibleAnswer3;
    }

    public void setPossibleAnswer3(String possibleAnswer3) {
        this.possibleAnswer3 = possibleAnswer3;
    }

    public String getPossibleAnswer4() {
        return possibleAnswer4;
    }

    public void setPossibleAnswer4(String possibleAnswer4) {
        this.possibleAnswer4 = possibleAnswer4;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public int getUserChoice() {
        return userChoice;
    }

    public void setUserChoice(int userChoice) {
        this.userChoice = userChoice;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getQuestionLevel() {
        return questionLevel;
    }

    public void setQuestionLevel(int questionLevel) {
        this.questionLevel = questionLevel;
    }
}
