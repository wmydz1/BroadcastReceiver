package com.logoocc.br;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by samchen on 8/3/15.
 */
public class LocalActivity extends Activity {
    private Button btSend;
    private LocalBroadcastManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btSend = (Button) findViewById(R.id.main_order);
        manager = LocalBroadcastManager.getInstance(this);
        // 实例化
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent in = new Intent();
               manager.sendBroadcast(new Intent("com.logoocc.br"));
            }
        });
        // com.xxx.xx也可
        IntentFilter filter = new IntentFilter("com.logoocc.br");


        manager.registerReceiver(receiver, filter); // 注册本地广播
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("receiver","------local-----");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.unregisterReceiver(receiver); // 取消广播
    }
}
