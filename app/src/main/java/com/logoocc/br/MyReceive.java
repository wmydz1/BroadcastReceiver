package com.logoocc.br;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by samchen on 8/3/15.
 */
public class MyReceive extends BroadcastReceiver {

    // 接收到广播时被调用的方法
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i("MyReceive",intent.getAction()+"xxxxxxxxxxxxxx");

        String action =intent.getAction();
        if (action.equals(Intent.ACTION_NEW_OUTGOING_CALL)){

        }else{

            if (action.equals(TelephonyManager.ACTION_PHONE_STATE_CHANGED)){
                 String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
                if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                    // 响铃
                    // 获取来电号
                    intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                }else if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
// 摘机
                }else if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                    // 空闲
                }
            }

        }


    }
}
