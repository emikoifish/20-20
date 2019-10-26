package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.util.Log;
import android.widget.TextView;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.TimerTask;

public class DisplayMessageActivity extends AppCompatActivity {
    public int boolCursor = 0;
    public int counter;
    public int oldCounter;
    public MyCountDownTimer myCountDownTimer;
    @TargetApi(Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);

        final TextView countTime = findViewById(R.id.countTime);
        final int amountTime = Integer.parseInt(message);
        final int amountTimeMillis = amountTime * 1000;



        myCountDownTimer = new MyCountDownTimer(amountTimeMillis, 1000);
        myCountDownTimer.start();

        final Handler handler = new Handler();
        final int delay = 1000; //milliseconds

        handler.postDelayed(new Runnable(){
            public void run(){
                if (counter > oldCounter) {
                    PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
                    boolean isScreenOn = pm.isInteractive();
                    if (isScreenOn == false) {
                        boolCursor++;
                    } else { // if screen is on, then reset the amount off
                        if (boolCursor >= 5) {
                            counter = 0;
                            boolCursor = 0;
                            myCountDownTimer.cancel();
                            myCountDownTimer = new MyCountDownTimer(amountTimeMillis, 1000);
                            myCountDownTimer.start();
                        }
                        boolCursor = 0;
                    }

                    oldCounter++;
                }
                handler.postDelayed(this, delay);
            }
        }, delay);

//
//
//        while(true){
//            if (counter > oldCounter) {
//                if (isScreenOn == false) {
//                    boolCursor++;
//                } else { // if screen is on, then reset the amount off
//                    if (boolCursor >= 5) {
//                        counter = 0;
//                        boolCursor = 0;
//                        myCountDownTimer.cancel();
//                        myCountDownTimer = new MyCountDownTimer(amountTimeMillis, 1000);
//                        myCountDownTimer.start();
//                    }
//                    boolCursor = 0;
//                }
//
//                oldCounter++;
//            }
//        }

    }
    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            int seconds = (int)millisUntilFinished / 1000;
            int minutes = seconds / 60;
            seconds = seconds % 60;
            TextView countTime = findViewById(R.id.countTime);
            countTime.setText(String.format("%d:%02d", minutes, seconds));
            Log.d("counter", String.valueOf(counter));
            Log.d("millisUntilFinished", String.valueOf(millisUntilFinished));
            counter++;
        }

        @Override
        public void onFinish() {
            TextView countTime = findViewById(R.id.countTime);
            countTime.setText("Finished");
//            finish();
        }
    }
}