package com.example.drive_test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;
import java.util.ArrayList;

public class Drive_testActivity extends AppCompatActivity {

    private RadioGroup SingleChoice;
    private RadioGroup MultipleChoice;
    private RadioGroup Judgement;

    private TextView Questions;

    private RadioButton SingleA;
    private RadioButton SingleB;
    private RadioButton SingleC;
    private RadioButton SingleD;

    private RadioButton JudgementTrue;
    private RadioButton JudgementFalse;

    private CheckBox MultipleA;
    private CheckBox MultipleB;
    private CheckBox MultipleC;
    private CheckBox MultipleD;

    private Button btn_previous;
    private Button btn_next;
    private Button btn_submit;
    private Button btn_hint;
    private Button btn_exit;
    //private Button btn_history;
    int currenIndex;
    int pointRight;
    int pointWrong;
    int score;
    int tishi1;
    private Question answer;
    private String[] answers;
    private Boolean[] tishi;
    String answer1;
    String comment="";
    private Question[] data={
            new Question(1,3,"夜间行车，遇对面来车未关闭远光灯时，应减速行驶，以防两车灯光的交汇处有行人通过时发生事故。",
                    "正确","错误","","","A"),
            new Question(2,2,"机动车驾驶人造成事故后逃逸构成犯罪的，吊销驾驶证且多长时间不得重新取得驾驶证？",
                    "5年内","10年内","终生","20年内","AB"),
            new Question(3,1,"机动车驾驶人造成事故后逃逸构成犯罪的，吊销驾驶证且多长时间不得重新取得驾驶证？",
                    "5年内","10年内","终生","20年内","B"),
            new Question(4,2,"机动车驾驶人造成事故后逃逸构成犯罪的，吊销驾驶证且多长时间不得重新取得驾驶证？",
                    "5年内","10年内","终生","20年内","ABC"),
            new Question(5,1,"机动车驾驶人造成事故后逃逸构成犯罪的，吊销驾驶证且多长时间不得重新取得驾驶证？",
                    "5年内","10年内","终生","20年内","C"),

    };

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive_test);
        SingleChoice=findViewById(R.id.SingleChoice);
        MultipleChoice=findViewById(R.id.MultipleChoice);
        Judgement = findViewById(R.id.Judgement);
        Questions=findViewById(R.id.Questions);
        SingleA=findViewById(R.id.SingleA);
        SingleB=findViewById(R.id.SingleB);
        SingleC=findViewById(R.id.SingleC);
        SingleD=findViewById(R.id.SingleD);
        JudgementTrue=findViewById(R.id.JudgementTrue);
        JudgementFalse=findViewById(R.id.JudgementFalse);
        MultipleA=findViewById(R.id.MultipleA);
        MultipleB=findViewById(R.id.MultipleB);
        MultipleC=findViewById(R.id.MultipleC);
        MultipleD=findViewById(R.id.MultipleD);

        btn_previous=findViewById(R.id.btn_previous);
        btn_next=findViewById(R.id.btn_next);
        btn_submit=findViewById(R.id.btn_submit);
        btn_hint=findViewById(R.id.btn_hint);
        btn_exit=findViewById(R.id.btn_exit);
        //btn_history=findViewById(R.id.btn_history);

        answers = new String[data.length];
        tishi = new Boolean[data.length];
        //设置初始页面
        setByType(data[currenIndex].getType());

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //saveAnswer(data[currenIndex].getType());
                if(currenIndex>0){
                    currenIndex--;
                }else{
                    currenIndex=data.length-1;
                }
                setByType(data[currenIndex].getType());
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveAnswer(data[currenIndex].getType());
                Log.i("test",(currenIndex+1)+answers[currenIndex]);
                if(currenIndex<data.length-1){
                    currenIndex++;
                }else{
                    currenIndex=0;
                }
                setByType(data[currenIndex].getType());
            }

        });

        btn_hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Intent intent=new Intent(Drive_testActivity.this,);

                String answer3=data[currenIndex].getAnswer();
                answer3=getAnswer(data[currenIndex].getType(),answer3);

                AlertDialog.Builder builder = new AlertDialog.Builder(Drive_testActivity.this);
                builder.setIcon(R.mipmap.ic_launcher_round)
                        .setTitle("提示")
                        .setMessage(answer3)
                        .setPositiveButton("确定", null)
                        .create()
                        .show();

                tishi[currenIndex]=true;

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveAnswer(data[currenIndex].getType());

                pointRight=0;
                pointWrong=0;
                tishi1=0;
                for (int i = 0; i < data.length; i++) {
                    if(tishi[i]!=null){
                        if(tishi[i]==true){
                            answers[i]=null;//查看提示的题目相当于作废
                            tishi1++;
                        }
                    }
                    if (answers[i]!=null) {
                        if (answers[i].equals(data[i].getAnswer())) {
                            pointRight++;
                        }
                    }
                }
                pointWrong=data.length-pointRight;
                //score=data.length*1-pointWrong*1;//答错不扣分，防止负分
                score=pointRight*1-(pointWrong-tishi1)*1;//答错扣分，有负分
                if (score > 25) {
                    comment="你的分数为："+score+"\n及格!";
                }else if(score>27){
                    comment="你的分数为："+score+"\n三颗星";
                }
                else if(score>27&&score<30){
                    comment="你的分数为："+score+"\n四颗星";
                }
                else if(score==30){
                    comment="你的分数为："+score+"\n五颗星";
                }else{
                    comment="你的分数为："+score+"\n不及格！";
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(Drive_testActivity.this);

                builder.setIcon(R.mipmap.ic_launcher_round)
                        .setTitle("确定提交？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                AlertDialog.Builder builder2 = new AlertDialog.Builder(Drive_testActivity.this);
                                builder2.setIcon(R.mipmap.ic_launcher_round)
                                        .setTitle("最终得分：")
                                        .setMessage(comment)
                                        .setPositiveButton("返回首页", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Intent intent=new Intent(Drive_testActivity.this,HomeActivity.class);
                                                intent.putExtra("data", data);
                                                intent.putExtra("answer",answers);
                                                startActivity(intent);
                                                //startActivityForResult(intent,200);
                                            }
                                        })
                                        .create()
                                        .show();
                            }
                        })
                        .setNegativeButton("取消",null)
                        .create()
                        .show();
            }
        });

        /*
        btn_history.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Drive_testActivity.this,HistoryActivity.class);
                intent.putExtra("data", data);
                intent.putExtra("answer",answers);
                startActivityForResult(intent,200);
            }
        });
        */
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private void setByType(int type) {


        switch (type) {
            case 1://单选
                SingleChoice.setVisibility(View.VISIBLE);
                MultipleChoice.setVisibility(View.INVISIBLE);
                Judgement.setVisibility(View.INVISIBLE);
                Questions.setText("[单选] " + data[currenIndex].getId() + ". " + data[currenIndex].getQuestion());
                Questions.setTextColor(Color.RED);
                SingleA.setText(data[currenIndex].getA());
                SingleB.setText(data[currenIndex].getB());
                SingleC.setText(data[currenIndex].getC());
                SingleD.setText(data[currenIndex].getD());

                // 清空控件选中状态
                SingleA.setChecked(false);
                SingleB.setChecked(false);
                SingleC.setChecked(false);
                SingleD.setChecked(false);
                SingleChoice.check(-1);

                answer1 = answers[currenIndex];
                if (answer1 != null) {
                    // 根据用户答案来设定单选按钮状态
                    switch (answer1) {
                        case "A":
                            SingleA.setChecked(true);
                            break;
                        case "B":
                            SingleB.setChecked(true);
                            break;
                        case "C":
                            SingleC.setChecked(true);
                            break;
                        case "D":
                            SingleD.setChecked(true);
                            break;
                    }
                }



                break;
            case 2://多选
                MultipleChoice.setVisibility(View.VISIBLE);
                SingleChoice.setVisibility(View.INVISIBLE);
                Judgement.setVisibility(View.INVISIBLE);
                Questions.setText("[多选] " + data[currenIndex].getId() + ". " + data[currenIndex].getQuestion());
                Questions.setTextColor(Color.BLACK);
                MultipleA.setText(data[currenIndex].getA());
                MultipleB.setText(data[currenIndex].getB());
                MultipleC.setText(data[currenIndex].getC());
                MultipleD.setText(data[currenIndex].getD());

                // 清空控件选中状态
                MultipleA.setChecked(false);
                MultipleB.setChecked(false);
                MultipleC.setChecked(false);
                MultipleD.setChecked(false);


                answer1 = answers[currenIndex];
                if (answer1 != null) {
                    for (int i = 0; i < answer1.length(); i++) {
                        switch (answer1.charAt(i)) {
                            case 'A':
                                MultipleA.setChecked(true);
                                break;
                            case 'B':
                                MultipleB.setChecked(true);
                                break;
                            case 'C':
                                MultipleC.setChecked(true);
                                break;
                            case 'D':
                                MultipleD.setChecked(true);
                                break;
                        }
                    }
                }


                break;
            case 3://判断
                 Judgement.setVisibility(View.VISIBLE);
                 SingleChoice.setVisibility(View.INVISIBLE);
                 MultipleChoice.setVisibility(View.INVISIBLE);
                 Questions.setText("[判断] " + data[currenIndex].getId() + ". " + data[currenIndex].getQuestion());
                 Questions.setTextColor(Color.BLUE);
                 JudgementTrue.setText(data[currenIndex].getA());
                 JudgementFalse.setText(data[currenIndex].getB());

                // 清空控件选中状态
                JudgementTrue.setChecked(false);
                JudgementFalse.setChecked(false);
                Judgement.check(-1);

                answer1 = answers[currenIndex];
                if (answer1 != null) {
                    switch (answer1) {
                        case "A":
                            JudgementTrue.setChecked(true);
                            break;
                        case "B":
                            JudgementFalse.setChecked(true);
                            break;
                    }
                }

                break;

        }
    }


    private void saveAnswer(int type) {
        switch (type) {
            case 1: // 单选题
                    if (SingleA.isChecked()) {
                        answers[currenIndex] = "A";
                    } else if (SingleB.isChecked()) {
                        answers[currenIndex] = "B";
                    } else if (SingleC.isChecked()) {
                        answers[currenIndex] = "C";
                    } else if(SingleD.isChecked()){
                        answers[currenIndex]= "D";
                    }else{
                        answers[currenIndex]=null;
                    }

                break;
            case 2: // 多选题
                StringBuilder builder = new StringBuilder();
                if(MultipleA.isChecked()||MultipleB.isChecked()||MultipleC.isChecked()||MultipleD.isChecked()) {
                    if (MultipleA.isChecked()) {
                        builder.append("A");
                    }
                    if (MultipleB.isChecked()) {
                        builder.append("B");
                    }
                    if (MultipleC.isChecked()) {
                        builder.append("C");
                    }
                    if (MultipleD.isChecked()) {
                        builder.append("D");
                    }
                }else{
                    answers[currenIndex]=null;
                }
                    answers[currenIndex] = builder.toString();

                break;
            case 3: // 判断题
                if (JudgementTrue.isChecked()) {
                    answers[currenIndex] = "A";
                } else if(JudgementFalse.isChecked()){
                    answers[currenIndex] = "B";
                }else{
                    answers[currenIndex] = null;
                }
                break;
        }
    }


    public String getAnswer(int type,String answer){
        String get_answer="找不到提示！";
        if(type==1||type==3){
            switch(answer){
                case"A":
                    get_answer=data[currenIndex].getA();
                    break;
                case"B":
                    get_answer=data[currenIndex].getB();
                    break;
                case"C":
                    get_answer=data[currenIndex].getC();
                    break;
                case"D":
                    get_answer=data[currenIndex].getD();
                    break;
            }
        }else{
            switch (answer){
                case"AB":
                    get_answer=data[currenIndex].getA()+"\n"+data[currenIndex].getB();
                    break;
                case"AC":
                    get_answer=data[currenIndex].getA()+"\n"+data[currenIndex].getC();
                    break;
                case"AD":
                    get_answer=data[currenIndex].getA()+"\n"+data[currenIndex].getD();
                    break;
                case"ABC":
                    get_answer=data[currenIndex].getA()+"\n"+data[currenIndex].getB()+"\n"+data[currenIndex].getC();
                    break;
                case"ABD":
                    get_answer=data[currenIndex].getA()+"\n"+data[currenIndex].getB()+"\n"+data[currenIndex].getD();
                    break;
                case"ACD":
                    get_answer=data[currenIndex].getA()+"\n"+data[currenIndex].getC()+"\n"+data[currenIndex].getD();
                    break;
                case"BCD":
                    get_answer=data[currenIndex].getB()+"\n"+data[currenIndex].getC()+"\n"+data[currenIndex].getD();
                    break;
                case"ABCD":
                    get_answer=data[currenIndex].getA()+"\n"+data[currenIndex].getB()+"\n"+data[currenIndex].getC()+"\n"+data[currenIndex].getD();
                    break;
            }
        }
        return get_answer;
    }


}
