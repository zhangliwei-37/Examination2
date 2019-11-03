package com.example.teach1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Trace;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class teach1 extends AppCompatActivity {
    private TextView textView;
    private Button btn;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Question[] data={
            new Question("中国是不是世界上最大的国家？",true),
            new Question("android是最简单的课程？",true),
            new Question("考研的人是不是很多？",false),
            new Question("今天天气是不是很冷？",false),

    };

    private String test[]={"你好！","dhafs"};

    private int currentIndex=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teach1);
        if(savedInstanceState==null){
            currentIndex=0;
            Log.i("1","没有保存");

        }else{
            currentIndex=savedInstanceState.getInt("currentIndex");

            Log.i("1",String.valueOf(currentIndex));

        }


        textView=findViewById(R.id.textView);
        btn=findViewById(R.id.button);
        btn2=findViewById(R.id.button2);
        btn3=findViewById(R.id.button3);
        btn4=findViewById(R.id.button4);
        btn5=findViewById(R.id.button8);

        //或通过生命周期解决

        if(savedInstanceState==null){
            Intent intent=getIntent();
            currentIndex=intent.getIntExtra("currentIndex",0);

        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.i("btn-clickinfo","safdasfsaf");
                if(data[currentIndex].isRes()){

                    Toast.makeText(teach1.this,R.string.msg,Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(teach1.this,R.string.msg2,Toast.LENGTH_SHORT).show();

                }

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data[currentIndex].isRes()){
                    Toast.makeText(teach1.this,R.string.msg2,Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(teach1.this,R.string.msg,Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex=currentIndex+1;
                if(currentIndex>=data.length){
                    //currentIndex=0;
                    Toast.makeText(teach1.this,"已经是最后一题了",Toast.LENGTH_SHORT).show();
                    currentIndex=data.length-1;
                    btn3.setEnabled(false);
                    btn4.setEnabled(true);
                    return;
                }
                textView.setText(data[currentIndex].getContent());

            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex=currentIndex-1;
                if(currentIndex==0){
                    btn4.setEnabled(false);
                    btn3.setEnabled(true);
                }
                textView.setText(data[currentIndex].getContent());
            }
        });



        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转
                Intent intent =new Intent(teach1.this,TishiActivity.class);
                //传递参数
                intent.putExtra("tishi",data[currentIndex].isRes());
                intent.putExtra("currentIndex",currentIndex);
                intent.putExtra("data",test);
                //startActivity(intent);
                //结果回调跳转
                startActivityForResult(intent,200);


            }
        });
    }





    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("currentIndex",currentIndex);

    }
    protected void onRestoreInstanceState(Bundle outState){
        super.onRestoreInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this,"你查看了",Toast.LENGTH_SHORT).show();
    }


}
