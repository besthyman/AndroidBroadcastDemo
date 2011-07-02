package com.hyman.demo.android.broadcast.client.custom.permission;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TimeChangedReceiver extends BroadcastReceiver {
	private static final String TAG = "Client2 TimeChangedReceiver";
	
	public TimeChangedReceiver() {
		Log.d(TAG, "TimeChangedReceiver");
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "onReceive");
		Log.d(TAG, "intent:" + intent);
	}

}