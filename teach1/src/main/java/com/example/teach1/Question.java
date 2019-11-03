package com.example.teach1;

public class Question {
   public String Content;
    private String Question;
    Boolean Res;



    public Question(String Content,boolean res){
        this.Content=Content;
        this.Res=res;
    }


    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }



    public boolean isRes(){
        return Res;
    }
}
