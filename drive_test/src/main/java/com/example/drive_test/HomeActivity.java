package com.example.drive_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.SearchEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//import de.greenrobot.event.EventBus;
//import de.greenrobot.event.ThreadMode;

public class HomeActivity extends AppCompatActivity {

    private Button btn_test;
    private Button btn_history;
    private Button btn_record;
    //SearchEvent messageEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn_test=findViewById(R.id.btn_test);
        btn_history=findViewById(R.id.btn_history);
        btn_record=findViewById(R.id.btn_record);

        //EventBus.getDefault().register(this);
        //SearchEvent messageEvent;

        Intent intent=this.getIntent();
        //从Drive_testActivity传过来的问题及答案
        final Question[] data=(Question[]) intent.getSerializableExtra("data");
        final String answers[]=intent.getStringArrayExtra("answer");
        //从历史记录传过来的Record对象
        final Record[] record_data=(Record[]) intent.getSerializableExtra("record");

        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,Drive_testActivity.class);
                startActivity(intent);
            }
        });

        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(data==null){
                    btn_history.setEnabled(false);
                    Toast.makeText(HomeActivity.this,"你还没有答过题！",Toast.LENGTH_SHORT).show();
                }else {
                    //向历史记录传递问题及答案
                    Intent intent = new Intent(HomeActivity.this, HistoryActivity.class);
                    intent.putExtra("data", data);
                    intent.putExtra("answer", answers);

                    intent.putExtra("record", record_data);
                    startActivity(intent);
                    //startActivityForResult(intent, 200);
                }
            }
        });

        btn_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(record_data==null){
                    btn_record.setEnabled(false);
                    Toast.makeText(HomeActivity.this,"你还没有添加错题！",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(HomeActivity.this, RecordActivity.class);
                    intent.putExtra("record", record_data);

                    //向错题本传递历史记录参数
                    intent.putExtra("data", data);
                    intent.putExtra("answer", answers);

                    startActivity(intent);
                    //startActivityForResult(intent, 300);
                }
            }
        });
    }
}
