package com.javad.shopgram.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.javad.shopgram.Login;

public class IncomingMessage extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        SmsManager smsManager=SmsManager.getDefault();
        Bundle bundle=intent.getExtras();
        try{
            if (bundle!=null){
                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();
                    Login.code.setText(message);
                    Log.i("SmsReceiver", "senderNum: "+ senderNum + "; message: " + message);


                    // Show alert
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, "senderNum: "+ senderNum + ", message: " + message, duration);
                    toast.show();

                }
            }
        }catch (Exception e){
            Log.e("SMSRECIVE",e.toString());
        }
    }
}
