package com.example.apple.contactmessage;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by apple on 23/10/17.
 */

public class MyServices extends Service {
    PhoneStateReceiver r;
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.PHONE_STATE");
        r = new PhoneStateReceiver();
        //registerReceiver(r, filter);

    }
    public void onDestroy()
    {
        unregisterReceiver(r);
        super.onDestroy();

        // Unregister the SMS receiver

    }
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals("android.intent.action.PHONE_STATE")){
                Log.d("debug","in receiver");
                Toast toast = Toast.makeText(context, "fuck inbroadcast", Toast.LENGTH_SHORT);
                toast.show();
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                telephonyManager.listen(new CustomPhoneStateListener(context), PhoneStateListener.LISTEN_CALL_STATE);
            }
        }
    };
    public class Receive extends BroadcastReceiver{
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals("android.intent.action.PHONE_STATE")){
                Log.d("debug","in receiver");
                Toast toast = Toast.makeText(context, "fuck inbroadcast", Toast.LENGTH_SHORT);
                toast.show();
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                telephonyManager.listen(new CustomPhoneStateListener(context), PhoneStateListener.LISTEN_CALL_STATE);
            }
            else if(action.equals(android.telephony.TelephonyManager.ACTION_PHONE_STATE_CHANGED)){
                //action for phone state changed
            }
        }
    }
}
