package com.ngockhuong.gsm.util;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.widget.Toast;

public class SmsUtil {
    public void sendSms(final Activity activity, String phone, String message) {
        // Get default SmsManager
        final SmsManager smsManager = SmsManager.getDefault();
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setType("vnd.android-dir/mms-sms");
        // declare pendingIntent to check result
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(activity, 0, sendIntent, 0);
        activity.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int result = getResultCode();
                String msg = "Thành công!";
                if (result != Activity.RESULT_OK) {
                    msg = "Có lỗi!";
                }

                Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
            }
        }, new IntentFilter(Intent.ACTION_VIEW));

        // Send SMS
        smsManager.sendTextMessage(phone, null, message, pendingIntent, null);

        // unregisterReceiver(broadcastReceiver);
    }
}
