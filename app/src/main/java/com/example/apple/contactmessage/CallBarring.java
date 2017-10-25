package com.example.apple.contactmessage;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by apple on 23/10/17.
 */

public class CallBarring extends BroadcastReceiver {
    // This String will hold the incoming phone number
    private String number;
    //CustomDialog dialog;
    TelephonyManager telephonyManager;
    PhoneStateListener listener;
    Context context;
    TextView tv1;
    LayoutInflater inflater;
    @Override
    public void onReceive(final Context context, Intent intent) {
//        Log.d("riot","riot");
//        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
//        View mView;
//        WindowManager wm = null;
//        LinearLayout ly1 = null;
//        if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {
//            Log.d("fuck","riot");
//            Toast t = Toast.makeText(context,"fuck yar",Toast.LENGTH_LONG);
//            t.show();
//            String mVal;
//            try {
//                mVal = "plesdr";
//
//                wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//                WindowManager.LayoutParams params1 = new WindowManager.LayoutParams(
//                        WindowManager.LayoutParams.MATCH_PARENT,
//                        WindowManager.LayoutParams.MATCH_PARENT,
//                        WindowManager.LayoutParams.TYPE_SYSTEM_ALERT |
//                                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
//                        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
//                                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
//                        PixelFormat.TRANSPARENT);
//
//                params1.height = 280;
//                params1.width = WindowManager.LayoutParams.MATCH_PARENT;
//                params1.gravity = Gravity.TOP;
//                params1.format = PixelFormat.TRANSLUCENT;
//
//                ly1 = new LinearLayout(context.getApplicationContext());
//                ly1.setOrientation(LinearLayout.HORIZONTAL);
//                ly1.setBackgroundColor(Color.WHITE);
//                inflater =
//                        (LayoutInflater) context
//                                .getSystemService(LAYOUT_INFLATER_SERVICE);
//                View hiddenInfo = inflater.inflate(R.layout.popup_main, ly1,
//                        false);
//                tv1 = (TextView) hiddenInfo.findViewById(R.id.text);
//               // tv1.setText(mVal);
//                ly1.addView(hiddenInfo);
//                wm.addView(ly1, params1);
//            } catch (Exception e) {
//                Log.d("fuck","fuck");
//                Log.e(state, e.toString());
//            }
//        } else if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)
//                || state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
//
//            try {
//                if(ly1!=null)
//                {
//                    Log.d("STage", "Remove View");
//                    wm.removeView(ly1);
//                    ly1 = null;
//                }else{
//                    Log.d("bna ni","bna ni");
//                }
//            } catch (Exception e) {
//                Log.d("fucke","fucke");
//            }
//        }
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
    // Method to disconnect phone automatically and programmatically
    // Keep this method as it is
    @SuppressWarnings({ "rawtypes", "unchecked" })


    class CustomDialog extends Dialog implements View.OnClickListener {

        public CustomDialog(Context context) {
            super(context);
            // TODO Auto-generated constructor stub
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            // TODO Auto-generated method stub
            super.onCreate(savedInstanceState);
            setContentView(R.layout.popup);
            Button btnEndCall = (Button) findViewById(R.id.dismiss);
            Log.d("finally","aya yha tak");
            btnEndCall.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
           // dialog.dismiss();
        }
    }

    private class MyPhoneStateListener extends PhoneStateListener {
        Context c;
        public MyPhoneStateListener(Context context){
            super();
            c = context;
        }
        public void onCallStateChanged(final int state, String incomingNumber) {
            if(state == 1) {
                final View mView;
                Log.d("MyPhoneListener", state + "   incoming no:" + incomingNumber);
                new Handler().postDelayed(new Runnable() {

                    WindowManager wm;
                                              LinearLayout ly1;
                    public void run() {
                        Log.d("fuck","riotr");
                        String mVal;
                        try {
                            mVal = "plesdr";
                            Log.d("fuckr","riotr");
                            Toast t = Toast.makeText(c,"daaaaam",Toast.LENGTH_LONG);
                            t.show();
                            wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
                            WindowManager.LayoutParams params1 = new WindowManager.LayoutParams(
                                    WindowManager.LayoutParams.MATCH_PARENT,
                                    WindowManager.LayoutParams.MATCH_PARENT,
                                    WindowManager.LayoutParams.TYPE_SYSTEM_ALERT |
                                            WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                                            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                                    PixelFormat.TRANSLUCENT);

                            params1.height = 280;
                            params1.width = WindowManager.LayoutParams.MATCH_PARENT;
                            params1.gravity = Gravity.TOP;
                            params1.format = PixelFormat.TRANSLUCENT;

                            ly1 = new LinearLayout(c);
                            ly1.setOrientation(LinearLayout.HORIZONTAL);
                            ly1.setBackgroundColor(Color.WHITE);
                            inflater =
                                    (LayoutInflater) c
                                            .getSystemService(LAYOUT_INFLATER_SERVICE);
                            View hiddenInfo = inflater.inflate(R.layout.popup_main, ly1,
                                    false);
                            tv1 = (TextView) hiddenInfo.findViewById(R.id.text);
                            // tv1.setText(mVal);
                            ly1.addView(hiddenInfo);
                            wm.addView(ly1, params1);
                        } catch (Exception e) {
                            Log.d("fuck","fuck");
                            Log.e(String.valueOf(state), e.toString());
                        }
                    }
                    }
                , 1000);
            }
        }
    }
}