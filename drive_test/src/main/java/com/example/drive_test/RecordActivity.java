package com.example.drive_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RecordActivity extends AppCompatActivity {

    private TextView r_question;
    private TextView r_answer;
    private Button btn_r_next;
    private Button btn_r_previous;
    private Button btn_r_exit;
    //private Record[] record;
    int recordIndex;
    int recordlength=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        r_question=findViewById(R.id.r_question);
        r_answer=findViewById(R.id.r_answer);
        btn_r_next=findViewById(R.id.btn_r_next);
        btn_r_previous=findViewById(R.id.btn_r_previous);
        btn_r_exit=findViewById(R.id.btn_r_exit);

        Intent intent=this.getIntent();
        //接收主页传过来的错题添加信息
        final Record[] record=(Record[]) intent.getSerializableExtra("record");
        //接收从主页传过来的历史记录参数
        final Question[] data=(Question[]) intent.getSerializableExtra("data");
        final String answers[]=intent.getStringArrayExtra("answer");

        for(int i=0;i<record.length;i++){
            if(record[i]!=null){
                recordlength++;
            }
        }
        Log.i("test","record真实长度："+recordlength);

        if(record[0].getQuestion()!=null&&record[0].getAnswer()!=null){
        r_question.setText(recordIndex+1+". "+record[0].getQuestion());
        r_answer.setText(record[0].getAnswer());

        }
        else{
            Log.i("test","record为空！");
        }

        btn_r_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recordIndex>0){
                    recordIndex--;
                }else{
                    recordIndex=recordlength-1;
                }
                if(record[recordIndex]!=null) {
                    r_question.setText(recordIndex+1+". "+record[recordIndex].getQuestion());
                    r_answer.setText(record[recordIndex].getAnswer());
                }
            }
        });

        btn_r_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recordIndex<recordlength-1) {
                    recordIndex++;

                }else{
                    Toast.makeText(RecordActivity.this,"没有了！",Toast.LENGTH_SHORT).show();
                    recordIndex=0;
                }
                if(record[recordIndex]!=null) {
                    r_question.setText(recordIndex+1+". "+record[recordIndex].getQuestion());
                    r_answer.setText(record[recordIndex].getAnswer());
                }
            }
        });

        btn_r_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RecordActivity.this,HomeActivity.class);
                intent.putExtra("record",record);
                //向主页返回接收过来的历史记录参数
                intent.putExtra("data", data);
                intent.putExtra("answer", answers);
                startActivity(intent);
            }
        });

    }
}
