package com.hyman.demo.android.broadcast.custom.ordered;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
/*
 * 
 * Content.sendOrderedBroadcast, resultReceiver, 
 * Intent.setPriority, 
 * BroadcastReceiver.getResultExtras, BroadcastReceiver.abortBroadcast
 */
public class OrderedBroadcastReceiver extends BroadcastReceiver {
	private static final String TAG = "OrderedBroadcastReceiver";
	private static String EXTRA_MESSAGE = "extra message";
	private String id;
	
	public OrderedBroadcastReceiver(String id) {
		this.id = id;
		Log.d(TAG, id);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "onReceive, " + id);
		Log.d(TAG, "intent:" + intent);
		Bundle results = getResultExtras(true);
        String message = results.getString(EXTRA_MESSAGE);
        if (message == null) {
        	message = id;
        } else {
        	message += "," + id;
        }
        results.putString(EXTRA_MESSAGE, message);
        String abortID = intent.getExtras().getString("abort");
		Log.d(TAG, "abort:" + abortID);
        if (id.equals(abortID)) {
    		Log.d(TAG, "abortBroadcast, " + id);
        	abortBroadcast();
        }
	}

}
