package com.example.xuepeng.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
   // private NetworkChangeReceiver networkChangeReceiver;
    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localBroadcastManager=LocalBroadcastManager.getInstance(this);

        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent("com.example.xuepeng.broadcasttest.LOCAL_BRODCAST");
                localBroadcastManager.sendBroadcast(intent);
                //sendOrderedBroadcast(intent,null);
            }
        });
        intentFilter=new IntentFilter();
        intentFilter.addAction("com.example.xuepeng.broadcasttest.LOCAL_BRODCAST");
        localReceiver=new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    class LocalReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"received localBroadcast",Toast.LENGTH_SHORT).show();
        }
    }
}
