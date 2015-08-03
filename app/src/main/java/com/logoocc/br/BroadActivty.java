package com.logoocc.br;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by samchen on 8/3/15.
 */

public class BroadActivty extends Activity {
    private Button btNormal;
    private Button bt_Order;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btNormal = (Button) findViewById(R.id.main_normal);
        btNormal.setOnClickListener(clickListener);

        bt_Order = (Button) findViewById(R.id.main_order);
        bt_Order.setOnClickListener(clickListener);

        IntentFilter filter = new IntentFilter();
        filter.addAction("com.logoocc.br");

        // 设置优先级
        filter.setPriority(400);
        registerReceiver(receiver1, filter);

        filter.setPriority(600);
        registerReceiver(receiver, filter);

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.main_normal:

                    Intent in = new Intent("com.logoocc.br");
                    in.putExtra("name", "samchen");
                    // 不涉及顺序 跟优先级没关系
                    sendBroadcast(in);

                    break;
                case R.id.main_order:
                    in = new Intent("com.logoocc.br");
                    in.putExtra("name", "logoocc");
                    sendOrderedBroadcast(in, null);
                    break;
            }


        }
    };

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String name = intent.getStringExtra("name");
            Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
            Log.i("receiver", "--1---");
            // 中断广播
//            abortBroadcast();
            // 添加结果附加数据 ,因为Intent 是复制品
            Bundle b = new Bundle();
            b.putString("sex", "male");

            setResultExtras(b);

            Bundle bb =getResultExtras(true);
            bb.putInt("age",100);

            setResultExtras(bb);
        }
    };
    private BroadcastReceiver receiver1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String name = intent.getStringExtra("name");
            Toast.makeText(context, name + "----2", Toast.LENGTH_SHORT).show();
            Log.i("receiver", "--2---");

            Bundle b = getResultExtras(true);
            String sex = b.getString("sex");

             int age =b.getInt("age");
            Toast.makeText(context, name + "---" + sex+"---"+age, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 一定要取消注册
        unregisterReceiver(receiver);
        unregisterReceiver(receiver1);
    }
}
