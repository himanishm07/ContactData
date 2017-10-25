package com.example.apple.contactmessage;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import static android.R.attr.id;
import static java.security.AccessController.getContext;

public class IncomingCall extends BroadcastReceiver {
    String no,data;
    public void onReceive(final Context context, Intent intent) {
        Log.d("here","here");
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, "msg", duration);
        toast.show();
//        Intent i = new Intent(context, IncomingCallActivity.class);
//        i.putExtras(intent);
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        context.startActivity(i);


//        final Intent i = new Intent(context, IncomingCallActivity.class);
//        i.putExtras(intent);
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                context.startActivity(i);
//            }
//        }, 1000);
       // Wait.oneSec();

        try {
            // TELEPHONY MANAGER class object to register one listner
            TelephonyManager tmgr = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);

            //Create Listner
            MyPhoneStateListener PhoneListener = new MyPhoneStateListener(context);

            // Register listener for LISTEN_CALL_STATE
            tmgr.listen(PhoneListener, PhoneStateListener.LISTEN_CALL_STATE);

        } catch (Exception e) {
            Log.e("Phone Receive Error", " " + e);
        }
    }
    private class MyPhoneStateListener extends PhoneStateListener {
        Context c;
        public MyPhoneStateListener(Context context){
            super();
            c = context;
        }
        public void onCallStateChanged(int state, String incomingNumber) {

            Log.d("MyPhoneListener",state+"   incoming no:"+incomingNumber);

            if (state == 1) {
                ContactData mDbHelper = new ContactData(c);
                //SQLiteDatabase db = mDbHelper.getWritableDatabase();
                SQLiteDatabase db = mDbHelper.getReadableDatabase();
                Cursor cursor = db.query("CONTACT_DATA", new String[] { "NUMBER",
                                "DATA" }, "DATA = ?", new String[] { incomingNumber },
                        null, null, null, null);
                if (cursor != null) {
                    if(cursor.moveToFirst()){
                        no = cursor.getString(0);
                        data = cursor.getString(1);
                    }
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(c, MyCustomDialog.class);
                        //i.putExtras(intent);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        c.startActivity(i);
                    }
                }, 1000);
            }
        }
    }
}
