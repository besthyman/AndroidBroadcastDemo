package com.hyman.demo.android.broadcast.client.custom.permission;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class DynamicPermissionBroadcastReceiver2 extends BroadcastReceiver {
	private static final String TAG = "DynamicPermissionBroadcastReceiver2";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "onReceive not Require Permission");
	}

}
