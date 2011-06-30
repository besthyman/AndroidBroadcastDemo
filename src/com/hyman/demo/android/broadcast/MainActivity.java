package com.hyman.demo.android.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import com.hyman.demo.android.broadcast.custom.normal.NormalBroadcastActivity;
import com.hyman.demo.android.broadcast.register.system.timechanged.TimeChangedRegisterActivity;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void onLoadClick(View src) {
        Spinner spinner = (Spinner) this.findViewById(R.id.broadcastsspinner);
		String value = spinner.getSelectedItem().toString();
		if ("System TIME Register".equals(value)) {
			Intent intent = new Intent(this, TimeChangedRegisterActivity.class);
			MainActivity.this.startActivity(intent);
		} else if ("Custom Normal".equals(value)) {
			Intent intent = new Intent(this, NormalBroadcastActivity.class);
			MainActivity.this.startActivity(intent);
		}
    }
}