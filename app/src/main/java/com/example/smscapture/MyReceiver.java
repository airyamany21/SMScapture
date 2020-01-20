package com.example.smscapture;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String TAG = "SmsBroadcastReceiver";
    private String msg, phoneNo = "";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.i(TAG, "Intent Received: " + intent.getAction());
        if (intent.getAction() == SMS_RECEIVED) {
            //recibe el map de extencion data del intent
            Bundle dataBundle = intent.getExtras();
            if (dataBundle != null) {
                // creando protocolo data unit object which
                Object[] mypdu = (Object[]) dataBundle.get("pdus");
                final SmsMessage[] messages = new SmsMessage[mypdu.length];
                for (int i = 0; i < mypdu.length; i++) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        String format = dataBundle.getString("format");
                        // del PDU obtenemos el objeto 11 y el smsmessage object usando el seguimiento de linea de codigo
                        messages[i] = SmsMessage.createFromPdu((byte[]) mypdu[i], format);
                    } else {
                        messages[i] = SmsMessage.createFromPdu((byte[]) mypdu[i]);
                    }
                    msg = messages[i].getMessageBody();
                    phoneNo = messages[i].getOriginatingAddress();
                }
                Toast.makeText(context, "Message: " + msg + "\nNumber: " + phoneNo, Toast.LENGTH_SHORT).show();
            }

        }
    }
}
