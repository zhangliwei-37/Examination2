package com.example.teach1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class TishiActivity extends AppCompatActivity {

    private Button btn;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tishi);
        //接受上一页参数
        //Intent intent=new Intent();
        Intent intent=this.getIntent();

        Boolean res=intent.getBooleanExtra("tishi",false);

        final int currentIndex=intent.getIntExtra("currentIndex",0);

        String data[]=intent.getStringArrayExtra("data");

        String msg="";

        if(res){
            msg="该题的正确答案是正确的";
        }else{
            msg="该题的正确答案是错误的";
        }





        textView=findViewById(R.id.tishi);
        textView.setText(data[1]);
        btn = findViewById(R.id.button6);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TishiActivity.this, teach1.class);

                intent.putExtra("currentIndex",currentIndex);
                //startActivity(intent);

                setResult(200,intent);
                finish();
            }
        });
    }
}
