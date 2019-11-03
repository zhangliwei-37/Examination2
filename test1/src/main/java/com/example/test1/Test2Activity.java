package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;


public class Test2Activity extends AppCompatActivity {



    private Button btn1;
    Record[] record;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);
        btn1=findViewById(R.id.btn1);
        //record=new Record[3];
        //record[0]=new Record();

        //EventBus.getDefault().register(this);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //record[0].setQuestion("werasf");
                EventBus.getDefault().post(new Record("this is an event. ","asf"));
                //Log.i("test",record[0].getQuestion());
                Intent intent=new Intent(Test2Activity.this,Test3Activity.class);
                startActivity(intent);
            }

        });
    }
}
