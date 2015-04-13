package com.example.bellme;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

	Context context;
	public static int NOTIFICATION_ID = 21321;

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		this.context = context;
		// here you can start an activity or service depending on your need
		// for ex you can start an activity to vibrate phone or to ring the
		// phone

		/*
		 * String phoneNumberReciver="9718202185";// phone number to which SMS
		 * to be send String message="Hi I will be there later, See You soon";//
		 * message to send SmsManager sms = SmsManager.getDefault();
		 * sms.sendTextMessage(phoneNumberReciver, null, message, null, null);
		 */
		// Show the toast like in above screen shot
		showNotification();
		Log.v("MS :: ", "Alarm Triggered");
		Toast.makeText(context, "Alarm Triggered and SMS Sent",
				Toast.LENGTH_LONG).show();
	}

	@SuppressWarnings({ "deprecation" })
	private void showNotification() {

		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification(R.drawable.ic_launcher,
				"Alarm Triggered", System.currentTimeMillis());

		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
				new Intent(context, AlarmReceiver.class), 0);
		notification.setLatestEventInfo(context, "Alarm Triggered", null,
				contentIntent);
		notificationManager.notify(NOTIFICATION_ID, notification);
	}

}
