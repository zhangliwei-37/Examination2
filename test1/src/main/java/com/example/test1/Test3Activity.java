package com.example.test1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


//import org.greenrobot.event.Subscribe;

public class Test3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test4);

        //Record event;

        EventBus.getDefault().register(this);

        //onRecord(event);


    }



    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getQuestion(Record event) {
        Log.i("test", "onMessageEventPosting(), current thread is " + Thread.currentThread().getName());
        Log.i("test", "onMessageEventPosting(), current thread is " + event.getQuestion());
    }



        @Override
        protected void onDestroy() {
            super.onDestroy();
            if(EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this);
            }
        }


}


