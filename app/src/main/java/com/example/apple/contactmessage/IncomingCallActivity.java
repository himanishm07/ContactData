package com.example.apple.contactmessage;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
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

public class IncomingCallActivity extends Activity {

    String[] DayOfWeek = {"Sunday", "Monday", "Tuesday",
            "Wednesday", "Thursday", "Friday", "Saturday"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        LayoutInflater layoutInflater =
                (LayoutInflater)getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.abc);
        View popupView = layoutInflater.inflate(R.layout.popup, null);
        final PopupWindow popupWindow = new PopupWindow(
                popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,true);
            //popupWindow.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
//        //popupWindow.showAsDropDown(btnOpenPopup, 50, -30);
//
//        popupView.setOnTouchListener(new View.OnTouchListener() {
//            int orgX, orgY;
//            int offsetX, offsetY;
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        orgX = (int) event.getX();
//                        orgY = (int) event.getY();
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        offsetX = (int)event.getRawX() - orgX;
//                        offsetY = (int)event.getRawY() - orgY;
//                        popupWindow.update(offsetX, offsetY, -1, -1, true);
//                        break;
//                }
//                return true;
//            }});
//        final Button btnOpenPopup = (Button) findViewById(R.id.openpopup);
//        btnOpenPopup.setOnClickListener(new Button.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                LayoutInflater layoutInflater =
//                        (LayoutInflater)getBaseContext()
//                                .getSystemService(LAYOUT_INFLATER_SERVICE);
//                View popupView = layoutInflater.inflate(R.layout.popup, null);
//                final PopupWindow popupWindow = new PopupWindow(
//                        popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
//
//                Button btnDismiss = (Button)popupView.findViewById(R.id.dismiss);
//
//                //Spinner popupSpinner = (Spinner)popupView.findViewById(R.id.popupspinner);
//
//                btnDismiss.setOnClickListener(new Button.OnClickListener(){
//
//                    @Override
//                    public void onClick(View v) {
//                        popupWindow.dismiss();
//                    }});
//
//                popupWindow.showAsDropDown(btnOpenPopup, 50, -30);
//
//                popupView.setOnTouchListener(new View.OnTouchListener() {
//                    int orgX, orgY;
//                    int offsetX, offsetY;
//
//                    @Override
//                    public boolean onTouch(View v, MotionEvent event) {
//                        switch (event.getAction()) {
//                            case MotionEvent.ACTION_DOWN:
//                                orgX = (int) event.getX();
//                                orgY = (int) event.getY();
//                                break;
//                            case MotionEvent.ACTION_MOVE:
//                                offsetX = (int)event.getRawX() - orgX;
//                                offsetY = (int)event.getRawY() - orgY;
//                                popupWindow.update(offsetX, offsetY, -1, -1, true);
//                                break;
//                        }
//                        return true;
//                    }});
//            }
//
//        });
    }

}
