package com.example129;

import com.example129.R;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;

public class example129 extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final Button btnSendSMS = (Button) findViewById(R.id.btnSendSMS);
        final EditText txtPhoneNo = (EditText) findViewById(R.id.txtPhoneNo);
        final EditText txtMessage = (EditText) findViewById(R.id.txtMessage);
 
        btnSendSMS.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {                
                String phoneNo = txtPhoneNo.getText().toString();
                String message = txtMessage.getText().toString();                 
                if (phoneNo.length()>0 && message.length()>0){                
                	Log.v("ROGER", "will begin sendSMS");
                   	sendSMS(phoneNo, message);
                }
                else
                    Toast.makeText(example129.this, 
                        "请重新输入电话号码和短信内容", 
                        Toast.LENGTH_LONG).show();
            }
        });
    }
    
    private void sendSMS(String phoneNumber, String message)
    {        
        PendingIntent pi = PendingIntent.getActivity(this, 0,
            new Intent(this, example129.class), 0);   
        Log.v("ROGER", "will init SMS Manager");
        @SuppressWarnings("deprecation")
		SmsManager sms = SmsManager.getDefault();
        
        Log.v("ROGER", "will send SMS");
        sms.sendTextMessage(phoneNumber, null, message, pi, null);        
    }  
}