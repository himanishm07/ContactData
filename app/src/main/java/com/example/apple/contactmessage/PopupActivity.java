package com.example.apple.contactmessage;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class PopupActivity extends Activity implements View.OnClickListener{

    TextView t;
    public final static int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 11;
    PopupWindow popupWindow;
    LinearLayout mainLayout;
    int flag = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_main);
        t = (TextView)findViewById(R.id.text);
        t.setOnClickListener(this);
        LayoutInflater layoutInflater =
                (LayoutInflater)getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
        mainLayout = (LinearLayout) findViewById(R.id.ab);
        View popupView = layoutInflater.inflate(R.layout.popup, null);
        popupWindow = new PopupWindow(
                popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,true);
        Log.d("layouts",mainLayout.toString());
        //popupWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
        Button btnDismiss = (Button)popupView.findViewById(R.id.dismiss);
        // dismiss the popup window when touched
        btnDismiss.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }});
        popupView.setOnTouchListener(new View.OnTouchListener() {

            int orgX, orgY;
            int offsetX, offsetY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        orgX = (int) event.getX();
                        orgY = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        offsetX = (int)event.getRawX() - orgX;
                        offsetY = (int)event.getRawY() - orgY;
                        popupWindow.update(offsetX, offsetY, -1, -1, true);
                        break;
                }
                return true;
            };
        });
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            Toast toast = Toast.makeText(this, "fuck bb", Toast.LENGTH_SHORT);
            toast.show();
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
                toast = Toast.makeText(this, "fuck bbc", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                toast = Toast.makeText(this, "fuck bbd", Toast.LENGTH_SHORT);
                toast.show();
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_PHONE_STATE},
                        1);

            }
        }else{
            Toast toast = Toast.makeText(this, "fuck again", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus&&flag==0) {
            popupWindow.showAtLocation(mainLayout, Gravity.TOP, 0, 0);
            flag = 1;
        }
    }
    public void onClick(View view) {
        Toast toast = Toast.makeText(this, "fuck", Toast.LENGTH_SHORT);
        toast.show();
        Intent i = new Intent("com.hmkcode.android.USER_ACTION");
        sendBroadcast(i);
    }
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_PHONE_STATE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                }
                return;
            }
        }
    }
}
