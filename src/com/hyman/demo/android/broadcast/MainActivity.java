package com.hyman.demo.android.broadcast;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

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
		if ("System ACTION_TIME_CHANGED".equals(value)) {
//			Intent intent = new Intent(this, TimeChangedActivity.class);
//			MainActivity.this.startActivity(intent);
		}
    }
}