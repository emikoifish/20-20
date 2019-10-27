package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.content.Intent;
import android.widget.EditText;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.CountDownTimer;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public int boolCursor = 0;
    public int counter;
    public int oldCounter;
    public MyCountDownTimer myCountDownTimer;

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//         Get the Intent that started this activity and extract the string
//        Intent intent = getIntent();
//        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
//
////         Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText("Reading");

        //set default
        String message = "20";

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
                if (counter >= amountTime) {
                    counter = 0;
                    boolCursor = 0;
                    myCountDownTimer.cancel();
                    myCountDownTimer = new MyCountDownTimer(amountTimeMillis, 1000);
                    myCountDownTimer.start();
                    oldCounter = 0;
                }
                handler.postDelayed(this, delay);
            }
        }, delay);
    }


public class MyCountDownTimer extends CountDownTimer {

    public int totalTime;

    public MyCountDownTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        totalTime = (int)millisInFuture;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        int seconds = totalTime/1000 - counter;
        int minutes = seconds / 60;
        seconds = seconds % 60;
        TextView countTime = findViewById(R.id.countTime);
        Log.d("countTime", String.valueOf(countTime));
        Log.d("minutes", String.valueOf(minutes));
        Log.d("seconds", String.valueOf(seconds));
        countTime.setText(String.format("%d:%02d", minutes, seconds));
        Log.d("counter", String.valueOf(counter));
        Log.d("millisUntilFinished", String.valueOf(millisUntilFinished));
        counter++;
    }

    @Override
    public void onFinish() {
        TextView countTime = findViewById(R.id.countTime);
        showNotification("Rest your eyes", "look up for 20 seconds, 20 feet away");
//            countTime.setText("Finished");
//            finish();
    }
    public void showNotification(String title, String message) {
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("YOUR_CHANNEL_ID",
                    "YOUR_CHANNEL_ID",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("20-20 channel");
            mNotificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "YOUR_CHANNEL_ID")
                .setSmallIcon(R.drawable.icon_eye) // notification icon
                .setContentTitle(title) // title for notification
                .setContentText(message)// message for notification
                .setAutoCancel(true); // clear notification after click
//            Intent intent = new Intent(getApplicationContext(), ACTIVITY_NAME.class);
//            PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//            mBuilder.setContentIntent(pi);
        mNotificationManager.notify(0, mBuilder.build());
    }
}




    /** Called when the user taps the Send button */
    public void changeSettings(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, changeSettings.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }


    /** Called when the user taps the Send button */
    public void changeProfile(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, changeProfile.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

}