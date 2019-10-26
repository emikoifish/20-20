package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class DisplayMessageActivity extends AppCompatActivity {

    public int counter;
    public int boolCursor = 0;
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
        int amountTimeMillis = amountTime * 1000;
        new CountDownTimer(amountTimeMillis,1000) {


            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = amountTime - counter;
                int minutes = seconds / 60;
                seconds = seconds % 60;
                countTime.setText(String.format("%d:%02d", minutes, seconds));
                PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
                boolean isScreenOn = pm.isInteractive();

                if (isScreenOn == false) {
                    if (boolCursor >= 20){
                        counter = 0;
                        boolCursor = 0;
                    }
                    else {
                        boolCursor++;
                    }
                }
                else{ // if screen is on, then reset the amount off
                    boolCursor = 0;
                }
                String counterStr = String.valueOf(counter);
                Log.d("hi",counterStr);
                Log.d("hi",String.valueOf(isScreenOn));
                counter++;



            }
            @Override
            public void onFinish() {
                countTime.setText("Finished");
            }
        }.start();
    }
}
