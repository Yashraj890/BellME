package com.example.bellme;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class BellDetails extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bell_details);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	public void scheduleAlarm(View V) {
		int muldays = 0;
		Calendar localcalendar = Calendar.getInstance(TimeZone.getDefault());
		// time at which alarm will be scheduled here alarm is scheduled at 1
		// day from current time,
		// we fetch the current time in milliseconds and added 1 day time
		// i.e. 24*60*60*1000= 86,400,000 milliseconds in a day
		int x = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
		int y = localcalendar.get(Calendar.DATE);
		Toast.makeText(this, "Current day :: " + y + "", Toast.LENGTH_LONG)
				.show();
		Toast.makeText(this, "End day :: " + x + "", Toast.LENGTH_LONG).show();
		if (y < x) {
			muldays = x - y;
		}
		Toast.makeText(this, "Mul Days :: " + muldays+"", Toast.LENGTH_LONG).show();
		Long time = new GregorianCalendar().getTimeInMillis() + (24 * 60 * 60 * 1000) * muldays; // (long) 15000; //
		Toast.makeText(this, "MS :: " + time.toString(), Toast.LENGTH_LONG).show();

		// create an Intent and set the class which will execute when Alarm
		// triggers, here we have
		// given AlarmReciever in the Intent, the onRecieve() method of this
		// class will execute when
		// alarm triggers and
		// we will write the code to send SMS inside onRecieve() method pf
		// Alarmreciever class
		Intent intentAlarm = new Intent(this, AlarmReceiver.class);

		// create the object
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

		// set the alarm for particular time
		alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent
				.getBroadcast(this, 1, intentAlarm,
						PendingIntent.FLAG_UPDATE_CURRENT));
		Toast.makeText(this, "Alarm Scheduled for Tommrrow", Toast.LENGTH_LONG)
				.show();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bell_details, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_bell_details,
					container, false);
			return rootView;
		}
	}

}
