package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class changeProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile);
    }
    public static final String EXTRA_MESSAGE_Notify = "com.example.myfirstapp.notificationType";
    public static final String EXTRA_MESSAGE_Vibe = "com.example.myfirstapp.vibrateType";
    public static final String EXTRA_MESSAGE_Seconds = "com.example.myfirstapp.seconds";
    public static final String EXTRA_MESSAGE_Profile = "com.example.myfirstapp.profile";
    public static final String EXTRA_MESSAGE_Screen = "com.example.myfirstapp.screen";
    /** Called when the user taps the Send button */
    public void reading(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, showTimer.class);
        intent.putExtra(EXTRA_MESSAGE_Notify, "background");
        intent.putExtra(EXTRA_MESSAGE_Vibe, "none");
        intent.putExtra(EXTRA_MESSAGE_Seconds, "20");
        intent.putExtra(EXTRA_MESSAGE_Profile, "Reading");
        intent.putExtra(EXTRA_MESSAGE_Screen, "5");
        startActivity(intent);
    }

    public void changeSettings(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, changeSettings.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}