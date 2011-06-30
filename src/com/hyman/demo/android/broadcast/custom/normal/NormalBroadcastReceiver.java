package com.hyman.demo.android.broadcast.custom.normal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

public class NormalBroadcastReceiver extends BroadcastReceiver {
	private static final String TAG = "NormalBroadcastReceiver";
	
	private String id;
	
	public NormalBroadcastReceiver(String id) {
		this.id = id;
		Log.d(TAG, id);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "onReceive, " + id);
		Log.d(TAG, "intent:" + intent);
        Log.d(TAG, "Before Sleep, " + id);
        SystemClock.sleep(3 * 1000); // 10 seconds
        Log.d(TAG, "After Sleep, " + id);
	}

}
