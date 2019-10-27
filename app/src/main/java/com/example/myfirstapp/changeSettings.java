package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class changeSettings extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_settings);
    }

    public static final String EXTRA_MESSAGE_Notify = "com.example.myfirstapp.notificationType";
    public static final String EXTRA_MESSAGE_Vibe = "com.example.myfirstapp.vibrateType";
    public static final String EXTRA_MESSAGE_Seconds = "com.example.myfirstapp.seconds";
    public static final String EXTRA_MESSAGE_Profile = "com.example.myfirstapp.profile";
    public static final String EXTRA_MESSAGE_Screen = "com.example.myfirstapp.screen";

    /** Called when the user taps the Send button */
    public void showTimer(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, showTimer.class);
        intent.putExtra(EXTRA_MESSAGE_Notify, notificationType);
        intent.putExtra(EXTRA_MESSAGE_Vibe, vibrateType);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        if (message.matches("")){
            message = "20";
        }
        intent.putExtra(EXTRA_MESSAGE_Seconds, message);
        startActivity(intent);

        EditText profileText = (EditText) findViewById(R.id.profileName);
        String profile = profileText.getText().toString();
        if (profile.matches("")){
            profile = "Reading";
        }
        intent.putExtra(EXTRA_MESSAGE_Profile, profile);

        EditText screenOff = (EditText) findViewById(R.id.screenOff);
        String screenSec = screenOff.getText().toString();
        if (screenSec.matches("")){
            screenSec = "5";
        }
        intent.putExtra(EXTRA_MESSAGE_Screen, screenSec);


        startActivity(intent);
    }

    public String notificationType = "background";

    public void onNotifyButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.notifyAlert:
                if (checked)
                    // Pirates are the best
                    notificationType = "alert";
                    break;
            case R.id.notifyBanner:
                if (checked)
                    notificationType = "banner";
                    break;
            case R.id.notifyBackground:
                if (checked)
                    notificationType = "background";
                    break;
        }
        Log.d("notify", notificationType);
    }


    public String vibrateType = "none";

    public void onVibrateButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.vibeNone:
                if (checked)
                    // Pirates are the best
                    vibrateType = "none";
                break;
            case R.id.vibeShort:
                if (checked)
                    vibrateType = "short";
                break;
            case R.id.vibeLong:
                if (checked)
                    vibrateType = "long";
                break;
        }
        Log.d("vibe", vibrateType);
    }




}
