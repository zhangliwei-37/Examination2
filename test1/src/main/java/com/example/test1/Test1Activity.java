package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class Test1Activity extends AppCompatActivity {

    private Button btn;
    private static final String Tag="test1";
    private int count=0;
    TextView textView;
    Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        bt1=findViewById(R.id.btn);
        textView=findViewById(R.id.textView);
        if(savedInstanceState==null){
            count=0;
            Log.i(Tag,"没有保存对象");
        }else{
            count=savedInstanceState.getInt("count");
            Log.i(Tag,"保存对象");

        }

        bt1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              count+=1;
              textView.setText(String.valueOf(count));
          }
        });

        Log.i(Tag,"回调onCreate");

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Test1Activity.this,Test2Activity.class);
                startActivity(intent);
            }
        });


    }

    protected void onStart(){
        super.onStart();
        Log.i(Tag,"回调onStart");
    }

    protected void onResume(){
        super.onResume();
        Log.i(Tag,"回调onResume");
    }

    protected void onPause(){
        super.onPause();
        Log.i(Tag,"回调onPause");
    }

    protected void onRestart(){
        super.onRestart();
        Log.i(Tag,"回调onRestart");
    }
    protected void onStop(){
        super.onStop();
        Log.i(Tag,"回调onStop");
    }

    protected void onDestroy(){
        super.onDestroy();
        Log.i(Tag,"回调onDestroy");
    }

    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("count",count);
    }

}
