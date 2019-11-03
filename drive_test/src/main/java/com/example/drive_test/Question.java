package com.example.drive_test;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {
    private int id;
    private int type;
    private String question;
    private String answer;
    private String a,b,c,d;

    public  Question(){}

    public Question(int id,int type,String question,String a,String b,String c,String d,String answer){
        this.id=id;
        this.type=type;
        this.question=question;
        this.a=a;
        this.b=b;
        this.c=c;
        this.d=d;
        this.answer=answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }
}
