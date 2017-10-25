package com.example.apple.contactmessage;

import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class CustomPhoneStateListener extends PhoneStateListener {

	Context context;
	int prev_state = -1;
	public CustomPhoneStateListener(Context context) {
	    super();
	    this.context = context;
	}

	@Override
	public void onCallStateChanged(int state, String incomingNumber) {
		super.onCallStateChanged(state, incomingNumber);
		Log.d("debug","in Listener");
		Intent i;

	    switch (state) {
	    	case 1:
                if(prev_state!=1) {
                    i = new Intent(context, MyService.class);
                    i.putExtra("incomingNumber", incomingNumber);
                    context.startService(i);
                    Log.d("debug", "calling service");
                    prev_state = 1;
                }
	            break;

	    	default:
                if(prev_state==1) {
                    i = new Intent(context, MyService.class);
                    Log.d("debug", "ending service");
                    prev_state = state;
                    context.stopService(i);
                }
	    		break;
	    }
		// do check here
		// for incoming call state should be 1
        Log.d("MyPhoneListener",state+"   incoming no:"+incomingNumber);

//        if (state == 1) {
//
//            i = new Intent(context, MyService.class);
//            i.putExtra("incomingNumber", incomingNumber);
//            context.startService(i);
//            Log.d("debug","calling service");
//
//        }else{
//            i = new Intent(context, MyService.class);
//            Log.d("debug","ending service");
//
//            context.stopService(i);
//
//
//        }
	}
}
