package com.example.apple.contactmessage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Toast;

public class PhoneStateReceiver extends BroadcastReceiver {


	@Override
	public void onReceive(final Context context, Intent intent) {
		// This is not working when app is closed
		//This will call customPhoneListener
		Log.d("debug","in receiver");
		Toast toast = Toast.makeText(context, "fuck inbroadcast", Toast.LENGTH_SHORT);
		toast.show();
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		telephonyManager.listen(new CustomPhoneStateListener(context), PhoneStateListener.LISTEN_CALL_STATE);
	}

}
