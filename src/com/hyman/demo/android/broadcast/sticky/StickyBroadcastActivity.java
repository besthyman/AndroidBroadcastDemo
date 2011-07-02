package com.hyman.demo.android.broadcast.sticky;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hyman.demo.android.broadcast.R;

public class StickyBroadcastActivity extends Activity {
	private static final String TAG = "StickyBroadcastActivity";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sticky_broadcast);
	}
	
	private void logIntent(Intent intent) {
		if (intent != null) {
    		Log.d(TAG, intent.toString());
    		Bundle extras = intent.getExtras();
			if (extras != null) {
				for (String key : extras.keySet()) {					
	        		Log.d(TAG, key + ":" + extras.get(key).toString());
				}
    		}
    	}
	}
	
	private void fetchBatteryChanged() {
		Log.d(TAG, "fetchBatteryChanged");
    	IntentFilter filter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
    	Intent result = registerReceiver(null, filter);
    	logIntent(result);
	}
	
	public void onSendClick(View src) {
    	Log.d(TAG, "onSendClick");
    	fetchBatteryChanged();
    	String action = "com.hyman.demo.android.broadcast.sticy.STICY";
		Intent intent;


    	Log.d(TAG, "sendStickyBroadcast");
		intent = new Intent();
		intent.setAction(action);
		intent.putExtra("msg", "msg1");
		sendStickyBroadcast(intent);
		
    	Log.d(TAG, "sendStickyBroadcast");
		intent = new Intent();
		intent.setAction(action);
		intent.putExtra("msg", "msg2");
		sendStickyBroadcast(intent);
		
		
		BroadcastReceiver receiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
		    	Log.d(TAG, "onReceive");
				logIntent(intent);
			}
			
		};
		IntentFilter filter = new IntentFilter(action);
    	Intent result = registerReceiver(receiver, filter);
    	Log.d(TAG, "registerReceiver result");
    	logIntent(result);

    	Log.d(TAG, "sendStickyBroadcast");
    	intent = new Intent();
		intent.setAction(action);
		intent.putExtra("msg", "msg3");
		sendStickyBroadcast(intent);
		
		unregisterReceiver(receiver);
    	Log.d(TAG, "onSendClick end");
	}
}
/*
 * Permission Denial: broadcastIntent() requesting a sticky broadcast from pid=978, uid=10039 requires android.permission.BROADCAST_STICKY
 */
/*
 * conclusion:
 * can use 'registerReceiver(null, filter)' to fetch the last broadcast intent.
 * 
 * registerReceiver(receiver, filter) will trigger the receiver by the last broadcast intent.
 * 
07-02 09:57:54.568: DEBUG/StickyBroadcastActivity(1112): onSendClick
07-02 09:57:54.568: DEBUG/StickyBroadcastActivity(1112): fetchBatteryChanged
07-02 09:57:54.618: DEBUG/StickyBroadcastActivity(1112): Intent { act=android.intent.action.BATTERY_CHANGED flg=0x60000000 (has extras) }
07-02 09:57:54.659: DEBUG/StickyBroadcastActivity(1112): icon-small:17302174
07-02 09:57:54.669: DEBUG/StickyBroadcastActivity(1112): present:true
07-02 09:57:54.681: DEBUG/StickyBroadcastActivity(1112): scale:100
07-02 09:57:54.681: DEBUG/StickyBroadcastActivity(1112): level:50
07-02 09:57:54.681: DEBUG/StickyBroadcastActivity(1112): technology:Li-ion
07-02 09:57:54.681: DEBUG/StickyBroadcastActivity(1112): status:2
07-02 09:57:54.681: DEBUG/StickyBroadcastActivity(1112): voltage:0
07-02 09:57:54.681: DEBUG/StickyBroadcastActivity(1112): plugged:1
07-02 09:57:54.681: DEBUG/StickyBroadcastActivity(1112): health:2
07-02 09:57:54.681: DEBUG/StickyBroadcastActivity(1112): temperature:0
07-02 09:57:54.681: DEBUG/StickyBroadcastActivity(1112): sendStickyBroadcast
07-02 09:57:54.829: DEBUG/StickyBroadcastActivity(1112): sendStickyBroadcast
07-02 09:57:54.889: DEBUG/StickyBroadcastActivity(1112): registerReceiver result
07-02 09:57:54.889: DEBUG/StickyBroadcastActivity(1112): Intent { act=com.hyman.demo.android.broadcast.sticy.STICY (has extras) }
07-02 09:57:54.889: DEBUG/StickyBroadcastActivity(1112): msg:msg2
07-02 09:57:54.889: DEBUG/StickyBroadcastActivity(1112): sendStickyBroadcast
07-02 09:57:55.022: DEBUG/StickyBroadcastActivity(1112): onSendClick end
07-02 09:57:55.199: DEBUG/StickyBroadcastActivity(1112): onReceive
07-02 09:57:55.209: DEBUG/StickyBroadcastActivity(1112): Intent { act=com.hyman.demo.android.broadcast.sticy.STICY (has extras) }
07-02 09:57:55.219: DEBUG/StickyBroadcastActivity(1112): msg:msg2
07-02 09:57:55.229: DEBUG/StickyBroadcastActivity(1112): onReceive
07-02 09:57:55.259: DEBUG/StickyBroadcastActivity(1112): Intent { act=com.hyman.demo.android.broadcast.sticy.STICY (has extras) }
07-02 09:57:55.268: DEBUG/StickyBroadcastActivity(1112): msg:msg3
 */
