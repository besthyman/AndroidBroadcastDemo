package com.hyman.demo.android.broadcast.custom.ordered;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.hyman.demo.android.broadcast.R;

public class OrderedBroadcastActivity extends Activity {
	private static final String TAG = "OrderedBroadcastActivity";
	private static final String ACTION = "com.hyman.demo.android.broadcast.ordered.ORDERED";
	private static String EXTRA_MESSAGE = "extra message";
	private int sendedCount = 0;
	private List<BroadcastReceiver> receivers = new ArrayList<BroadcastReceiver>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_ordered_broadcast);
		int count = 10;
		for (int i = 0; i < count; i++) {
			IntentFilter intentFilter = new IntentFilter(ACTION);
			if (i % 2 == 0) {
				intentFilter.setPriority(1);
			} else {
				intentFilter.setPriority(2);
			}
			BroadcastReceiver receiver = new OrderedBroadcastReceiver("id"
					+ (i + 1));
			registerReceiver(receiver, intentFilter);
			receivers.add(receiver);
		}
	}

	public void onSendClick(View src) {
		Log.d(TAG, "onSendClick");
		sendedCount++;
		EditText editText = (EditText) findViewById(R.id.abortReceiverId);
		String abortID = editText.getText().toString();
		Intent intent = new Intent();
		intent.putExtra("abort", abortID);
		intent.putExtra("msg", "msg" + sendedCount);
		intent.setAction(ACTION);
		BroadcastReceiver resultReceiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
	                Bundle results = getResultExtras(true);
	                Log.d(TAG,
	                        "In Result Receiver: Got 'extra message' = "
	                                + results.getString(EXTRA_MESSAGE));
			}
			
		};
		sendOrderedBroadcast(intent, null, resultReceiver, null, Activity.RESULT_OK, null, null);
	}

	@Override
	public void onDestroy() {
		for (BroadcastReceiver receiver : receivers) {
			unregisterReceiver(receiver);
		}
		super.onDestroy();
	}
}
