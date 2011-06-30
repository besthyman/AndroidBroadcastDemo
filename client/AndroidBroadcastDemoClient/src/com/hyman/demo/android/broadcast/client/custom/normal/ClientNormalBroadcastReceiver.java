package com.hyman.demo.android.broadcast.client.custom.normal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

public class ClientNormalBroadcastReceiver extends BroadcastReceiver {
	private static final String TAG = "ClientNormalBroadcastReceiver";
	
	public ClientNormalBroadcastReceiver() {
		Log.d(TAG, "ClientNormalBroadcastReceiver");
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "onReceive");
		Log.d(TAG, "intent:" + intent);
        Log.d(TAG, "Before Sleep");
        SystemClock.sleep(5 * 1000); // 5 seconds
        Log.d(TAG, "After Sleep");
	}

}
/*
 * If the package is installed but not started, a new Broadcast message will start the package
 * Log:
04-14 13:38:02.931: INFO/ActivityManager(70): Start proc com.hyman.demo.android.broadcast.client for broadcast com.hyman.demo.android.broadcast.client/.custom.normal.ClientNormalBroadcastReceiver: pid=951 uid=10040 gids={1015}
04-14 13:38:03.700: DEBUG/ClientNormalBroadcastReceiver(951): ClientNormalBroadcastReceiver
04-14 13:38:03.700: DEBUG/ClientNormalBroadcastReceiver(951): onReceive
04-14 13:38:03.700: DEBUG/ClientNormalBroadcastReceiver(951): intent:Intent { act=com.hyman.demo.android.broadcast.normal.Normal cmp=com.hyman.demo.android.broadcast.client/.custom.normal.ClientNormalBroadcastReceiver (has extras) }
 * 
 * 
 */
