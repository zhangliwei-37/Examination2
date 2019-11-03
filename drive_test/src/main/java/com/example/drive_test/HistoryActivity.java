package com.example.drive_test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//import de.greenrobot.event.EventBus;

public class HistoryActivity extends AppCompatActivity {

    private TextView H_question;
    private TextView answer1;
    private TextView answer2;
    private Button btn_h_next;
    private Button btn_h_previous;
    private Button btn_h_addquestion;
    private Button btn_back;

    private Record[] record;
    //private String[] record;
    int recordIndex;
    int recordlength=0;

    int currenIndex=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        H_question=findViewById(R.id.H_question);
        answer1=findViewById(R.id.answer1);
        answer2=findViewById(R.id.answer2);
        btn_h_next=findViewById(R.id.btn_h_next);
        btn_h_previous=findViewById(R.id.btn_h_previous);
        btn_h_addquestion=findViewById(R.id.addquestion);
        btn_back=findViewById(R.id.btn_back);


        //EventBus.getDefault().register(this);

        final Intent intent=this.getIntent();
        //接收从主页传过来的问题及答案
        final Question[] data=(Question[]) intent.getSerializableExtra("data");
        final String answers[]=intent.getStringArrayExtra("answer");
        //从历史记录传过来的Record对象
        final Record[] record_data=(Record[]) intent.getSerializableExtra("record");

        record = new Record[data.length];
        if(record_data==null) {

        }else{
            for(int i=0;i<record_data.length-1;i++){
                if(record_data[i]!=null){
                    recordlength++;
                }
            }

        }
        setInterface(data,answers);
        if(record_data!=null){
            for(int i=0;i<recordlength;i++){
                if(data[0].getQuestion().equals(record_data[i].getQuestion())){
                    btn_h_addquestion.setText("已添加");
                    btn_h_addquestion.setTextColor(Color.GREEN);
                    //btn_h_addquestion.setEnabled(false);
                }else{
                    btn_h_addquestion.setText("未添加");
                    btn_h_addquestion.setTextColor(Color.RED);
                    //btn_h_addquestion.setEnabled(true);
                }
            }
        }

        final Intent h_intent=new Intent(HistoryActivity.this,HomeActivity.class);

        btn_h_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currenIndex<data.length-1){
                    currenIndex++;
                }else{
                    currenIndex=0;
                }
                setInterface(data,answers);

                Log.i("test","历史记录中的record真实值为："+recordlength);
                if(record_data!=null){
                    for(int i=0;i<recordlength;i++){
                        if(data[currenIndex].getQuestion().equals(record_data[i].getQuestion())){
                            btn_h_addquestion.setText("已添加");
                            btn_h_addquestion.setTextColor(Color.GREEN);
                            //btn_h_addquestion.setEnabled(false);
                        }else{
                            btn_h_addquestion.setText("未添加");
                            btn_h_addquestion.setTextColor(Color.RED);
                            //btn_h_addquestion.setEnabled(true);
                        }
                    }
                }
            }
        });

        btn_h_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currenIndex>0){
                    currenIndex--;
                }else{
                    currenIndex=data.length-1;
                }
                setInterface(data,answers);
                if(record_data!=null){
                    for(int i=0;i<recordlength;i++){
                        if(data[currenIndex].getQuestion().equals(record_data[i].getQuestion())){
                            btn_h_addquestion.setText("已添加");
                            btn_h_addquestion.setTextColor(Color.GREEN);
                            //btn_h_addquestion.setEnabled(false);
                        }else{
                            btn_h_addquestion.setText("未添加");
                            btn_h_addquestion.setTextColor(Color.RED);
                            //btn_h_addquestion.setEnabled(true);
                        }
                    }
                }
            }
        });

        btn_h_addquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(record_data!=null){
                    AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this);
                    builder.setIcon(R.mipmap.ic_launcher_round)
                            .setTitle("警告⚠️")
                            .setMessage("您已经有一个错题本了，继续添加将覆盖之前的错题本。")
                            .setPositiveButton("继续", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if(recordIndex<=data.length) {
                                        record[recordIndex] = new Record();
                                        record[recordIndex].setQuestion(data[currenIndex].getQuestion());
                                        String getAnswer=getAnswer(data,data[currenIndex].getType(),data[currenIndex].getAnswer());
                                        record[recordIndex].setAnswer(getAnswer);
                                        recordIndex+=1;
                                    }
                                    h_intent.putExtra("record",record);
                                    Toast.makeText(HistoryActivity.this,"添加成功！",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("取消",null)
                            .create()
                            .show();
                }else {
                    if (recordIndex <= data.length) {
                        record[recordIndex] = new Record();
                        record[recordIndex].setQuestion(data[currenIndex].getQuestion());
                        String getAnswer = getAnswer(data, data[currenIndex].getType(), data[currenIndex].getAnswer());
                        record[recordIndex].setAnswer(getAnswer);
                        recordIndex += 1;
                    }
                    h_intent.putExtra("record", record);
                    //startActivityForResult(intent,300);
                    //setResult(300,h_intent);
                    Toast.makeText(HistoryActivity.this, "添加成功！", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent=new Intent(HistoryActivity.this,HomeActivity.class);
                h_intent.putExtra("data",data);
                h_intent.putExtra("answer",answers);
                if(record_data!=null&&record==null) {
                    h_intent.putExtra("record", record_data);
                }
                startActivity(h_intent);
                //startActivityForResult(h_intent,200);
            }
        });
    }

    public void setInterface(Question[] data,String []answers){
            H_question.setText(currenIndex+1+". "+data[currenIndex].getQuestion());
            if(data[currenIndex].getAnswer().equals(answers[currenIndex])){
                H_question.setTextColor(Color.GREEN);
            }else{
                H_question.setTextColor(Color.RED);
            }
            answer1.setText(answers[currenIndex]);
            answer2.setText(data[currenIndex].getAnswer());
    }

    public String getAnswer(Question[] data,int type,String answer){
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



