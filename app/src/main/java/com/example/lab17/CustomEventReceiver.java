package com.example.lab17;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomEventReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("com.example.lab17.CUSTOM_EVENT".equals(intent.getAction())) {
            String message = intent.getStringExtra("message");
            Toast.makeText(context, "Custom reçu : " + message, Toast.LENGTH_LONG).show();
        }
    }
}
