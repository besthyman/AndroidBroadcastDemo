package com.hyman.demo.android.broadcast.system.bootcompleted;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootCompletedReceiver extends BroadcastReceiver {
	private static final String TAG = "BootCompletedReceiver";


	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "onReceive");
		Log.d(TAG, "intent:" + intent);
	}


}
