package com.hyman.demo.android.broadcast.register.system.timechanged;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hyman.demo.android.broadcast.R;

public class TimeChangedRegisterActivity extends Activity {
	private static final String TAG = "TimeChangedRegisterActivity";
	private BroadcastReceiver receiver;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.system_timechanged_register);
    }
    
    public void onRegisterClick(View src) {
    	Log.d(TAG, "onRegisterClick");
    	receiver = new TimeChangedRegisterReceiver();    	
    	IntentFilter filter = new IntentFilter("android.intent.action.TIME_SET");
    	registerReceiver(receiver, filter);    	
    }    
    
    public void onUnRegisterClick(View src) {
    	Log.d(TAG, "onUnRegisterClick");
    	unregisterReceiver(receiver);    	
    }    
}
