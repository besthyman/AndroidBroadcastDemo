package com.hyman.demo.android.broadcast.client.custom.permission;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class DeclaredPermissionBroadcastReceiver extends BroadcastReceiver {
	private static final String TAG = "DeclaredPermissionBroadcastReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "onReceive Require Permission");
	}

}
