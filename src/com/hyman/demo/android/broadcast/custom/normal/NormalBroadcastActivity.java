package com.hyman.demo.android.broadcast.custom.normal;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hyman.demo.android.broadcast.R;

public class NormalBroadcastActivity extends Activity {
	private static final String TAG = "NormalBroadcastActivity";
	private static final String ACTION = "com.hyman.demo.android.broadcast.normal.Normal";
	private int sendedCount = 0;
	private List<BroadcastReceiver> receivers = new ArrayList<BroadcastReceiver>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_nomal_broadcast);
		int count = 10;
		for (int i = 0; i < count ; i++) {
			IntentFilter intentFilter = new IntentFilter(ACTION);
			BroadcastReceiver receiver = new NormalBroadcastReceiver("id" + (i + 1));
			registerReceiver(receiver, intentFilter);
			receivers.add(receiver);
		}
	}

	public void onSendClick(View src) {
		Log.d(TAG, "onSendClick");
		sendedCount++;
		Intent intent = new Intent();
		intent.putExtra("msg", "msg" + sendedCount);
		intent.setAction(ACTION);
		sendBroadcast(intent);
	}
	
	@Override
	public void onDestroy() {
		for (BroadcastReceiver receiver: receivers) {
			unregisterReceiver(receiver);
		}
		super.onDestroy();
	}
}
