package com.example.apple.contactmessage;

import android.Manifest;
import android.app.ActivityManager;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.Set;

import static com.example.apple.contactmessage.PopupActivity.MY_PERMISSIONS_REQUEST_READ_PHONE_STATE;

public class MainActivity extends AppCompatActivity {

    Button b;
    Button b2;
    TextView t;
    EditText e;
    String phone_number;
    String number;
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(this.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
//        if(!isMyServiceRunning(MyServices.class)){
//            Intent i = new Intent(this,MyServices.class);
//            startService(i);
//        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPermissionToReadUserContacts();
        getPermissionToAlert();
        getPermission();
        e = (EditText)findViewById(R.id.editText2);
        b = (Button)findViewById(R.id.button);
        b2 = (Button)findViewById(R.id.button2);
        t = (TextView)findViewById(R.id.textView2);
        b.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent, 2015);
            }});
        b2.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                ContactData mDbHelper = new ContactData(MainActivity.this);
                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("NUMBER", t.getText().toString());
                values.put("DATA",e.getText().toString());
                long newRowId = db.insert("CONTACT_DATA", null, values);
                Log.d("newRow",String.valueOf(newRowId));
                e.setText("");
                t.setText("Another Number");
                db.close();
            }});
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2015 && resultCode == RESULT_OK) {
            Log.d("data",data.toString());
//            Uri contactUri = data.getData();
//            Cursor cursor = getContentResolver().query(contactUri, null, null, null, null);
//            cursor.moveToFirst();
//            int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
//            Log.d("phone number", cursor.getString(column));
//            phone_number = cursor.getString(column);
//            t.setText(cursor.getString(column));
            Uri contactUri = data.getData();
            String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER};
            Cursor cursor = this.getContentResolver().query(contactUri, projection,
                    null, null, null);

            // If the cursor returned is valid, get the phone number
            if (cursor != null && cursor.moveToFirst()) {
                Log.d("cursor",cursor.toString());
                int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                number = cursor.getString(numberIndex);
                Log.d("number",number);
                // Do something with the phone number
                //...
                Uri uri=Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,Uri.encode(number));
                Log.d("uri",uri.toString());
                projection = new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME};
                Log.d("projection",projection.toString());
                String contactName="";
                cursor = this.getContentResolver().query(uri,projection,null,null,null);
                if (cursor != null) {
                    if(cursor.moveToFirst()) {
                        contactName=cursor.getString(0);
                        t.setText(contactName);
                    }
                    cursor.close();
                }
                Log.d("name",contactName);
            }

        }
    }
    private static final int READ_CONTACTS_PERMISSIONS_REQUEST = 1;
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getPermission(){
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            Toast toast = Toast.makeText(this, "fuck bb", Toast.LENGTH_SHORT);
            toast.show();
            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_PHONE_STATE)) {
                toast = Toast.makeText(this, "fuck bbc", Toast.LENGTH_SHORT);
                toast.show();
            }
                toast = Toast.makeText(this, "fuck bbd", Toast.LENGTH_SHORT);
                toast.show();
                requestPermissions(
                        new String[]{Manifest.permission.READ_PHONE_STATE},
                        MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);


        }else{
            Toast toast = Toast.makeText(this, "fuck again", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    // Called when the user is performing an action which requires the app to read the
    // user's contacts
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getPermissionToReadUserContacts() {
        // 1) Use the support library version ContextCompat.checkSelfPermission(...) to avoid
        // checking the build version since Context.checkSelfPermission(...) is only available
        // in Marshmallow
        // 2) Always check for permission (even if permission has already been granted)
        // since the user can revoke permissions at any time through Settings
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // The permission is NOT already granted.
            // Check if the user has been asked about this permission already and denied
            // it. If so, we want to give more explanation about why the permission is needed.
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_CONTACTS)) {
                // Show our own UI to explain to the user why we need to read the contacts
                // before actually requesting the permission and showing the default UI
            }

            // Fire off an async request to actually get the permission
            // This will show the standard permission request dialog UI
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                    READ_CONTACTS_PERMISSIONS_REQUEST);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getPermissionToAlert() {
//        Intent myIntent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
//        startActivity(myIntent);
//        Toast t = Toast.makeText(this,"hhhkhkhkhkhk",Toast.LENGTH_LONG);
//        t.show();
        // 1) Use the support library version ContextCompat.checkSelfPermission(...) to avoid
        // checking the build version since Context.checkSelfPermission(...) is only available
        // in Marshmallow
        // 2) Always check for permission (even if permission has already been granted)
        // since the user can revoke permissions at any time through Settings
        if (!Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, 1234);
        }
    }

    // Callback with the request from calling requestPermissions(...)
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        // Make sure it's our original READ_CONTACTS request
        if (requestCode == READ_CONTACTS_PERMISSIONS_REQUEST) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Read Contacts permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Read Contacts permission denied", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}
