package com.hyman.demo.android.broadcast.client.custom.permission;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.hyman.demo.android.broadcast.client.R;

public class PermissionBroadcastActivity extends Activity {
	private static final String TAG = "Client2 PermissionBroadcastActivity";
	private static final String ACTION = "com.hyman.demo.android.broadcast.permission.PERMISSION_ACTION";
	private static final String PERMISSION = "com.hyman.demo.android.broadcast.permission.PERMISSION_PERMISSION";
	private BroadcastReceiver receiver;
	private BroadcastReceiver receiver2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_permission_broadcast);
		
		Log.d(TAG, "PermissionBroadcastActivity");
		
		IntentFilter intentFilter = new IntentFilter(ACTION);
		receiver = new DynamicPermissionBroadcastReceiver();
		registerReceiver(receiver, intentFilter, PERMISSION, null);
		receiver2 = new DynamicPermissionBroadcastReceiver2();
		registerReceiver(receiver2, intentFilter);
	}

	@Override
	public void onDestroy() {
		if (receiver != null) {
			unregisterReceiver(receiver);
		}
		if (receiver2 != null) {
			unregisterReceiver(receiver2);
		}
		super.onDestroy();
	}
}
