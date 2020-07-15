package com.example.pilot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(Intent.ACTION_POWER_CONNECTED.equals(intent.getAction())){
            Toast.makeText(context,"Intent detected",Toast.LENGTH_SHORT).show();
            Toast.makeText(context,"Power connected",Toast.LENGTH_SHORT).show();
        }

    }
}
