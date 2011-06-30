package com.hyman.demo.android.broadcast.system.timechanged;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

// intent-filter.action = android.intent.action.TIME_SET
/*
 * use adb to simulator the time change
1. start shell
C:\> adb shell
2. get seconds since Jan 1st 1970
#date ¨C- 2009-10-01 14:24:59
20070325.123456
3. change time
#date ¨Cs 20070325.123456
 */
public class TimeChangedReceiver extends BroadcastReceiver {
	private static final String TAG = "TimeChangedReceiver";
	
	public TimeChangedReceiver() {
		super();
		Log.d(TAG, "TimeChangedReceiver");
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "onReceive");
		Log.d(TAG, "intent:" + intent);
	}

}
/*
 * Log by change time 1:
04-14 12:34:56.388: DEBUG/TimeChangedReceiver(428): TimeChangedReceiver
04-14 12:34:56.388: DEBUG/TimeChangedReceiver(428): onReceive
04-14 12:34:56.388: DEBUG/TimeChangedReceiver(428): intent:Intent { act=android.intent.action.TIME_SET flg=0x20000000 cmp=com.hyman.demo.android.broadcast/.system.timechanged.TimeChangedReceiver }

Log by change time 2:
04-14 12:34:56.350: DEBUG/TimeChangedReceiver(428): TimeChangedReceiver
04-14 12:34:56.350: DEBUG/TimeChangedReceiver(428): onReceive
04-14 12:34:56.350: DEBUG/TimeChangedReceiver(428): intent:Intent { act=android.intent.action.TIME_SET flg=0x20000000 cmp=com.hyman.demo.android.broadcast/.system.timechanged.TimeChangedReceiver }

Compare the logs above, we can find out that constructor TimeChangedReceiver is called twice.
*/
