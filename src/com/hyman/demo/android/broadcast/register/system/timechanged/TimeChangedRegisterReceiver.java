package com.hyman.demo.android.broadcast.register.system.timechanged;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TimeChangedRegisterReceiver extends BroadcastReceiver {
	private static final String TAG = "TimeChangedRegisterReceiver";
	
	public TimeChangedRegisterReceiver() {
		super();
		Log.d(TAG, "TimeChangedRegisterReceiver");
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "onReceive");
		Log.d(TAG, "intent:" + intent);
	}

}
