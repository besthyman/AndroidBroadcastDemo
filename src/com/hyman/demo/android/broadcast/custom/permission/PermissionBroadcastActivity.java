package com.hyman.demo.android.broadcast.custom.permission;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hyman.demo.android.broadcast.R;

public class PermissionBroadcastActivity extends Activity {
	private static final String TAG = "PermissionBroadcastActivity";
	private static final String ACTION = "com.hyman.demo.android.broadcast.permission.PERMISSION_ACTION";
	private static final String PERMISSION = "com.hyman.demo.android.broadcast.permission.PERMISSION_PERMISSION";
	private int sendedCount = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_permission_broadcast);
	}

	/*
	 * This package does not have permission 'com.hyman.demo.android.broadcast.permission.PERMISSION_PERMISSION'.
	 * but it can sendBroadcast required that permission.
	 */
	public void onSendClick(View src) {
		Log.d(TAG, "onSendClick");
		sendedCount++;
		Intent intent = new Intent();
		intent.putExtra("msg", "msg" + sendedCount);
		intent.setAction(ACTION);
		sendBroadcast(intent);
	}

	public void onSendPermissionClick(View src) {
		Log.d(TAG, "onSendPermissionClick");
		sendedCount++;
		Intent intent = new Intent();
		intent.putExtra("msg", "msg" + sendedCount);
		intent.setAction(ACTION);
		sendBroadcast(intent, PERMISSION);
	}
	/*
	 * Send "android.intent.action.TIME_SET" broadcast successfully.
	 * And the receiver can receive this broadcast, onReceive is called.
	 * Is it a little wierd?
	 */
	public void onSendTimeClick(View src) {
		Log.d(TAG, "onSendTimeClick");
		Intent intent = new Intent();
		intent.setAction("android.intent.action.TIME_SET");
		sendBroadcast(intent);
	}
	/*
	 * java.lang.SecurityException: Permission Denial: not allowed to send broadcast android.intent.action.BOOT_COMPLETED
	 */
	public void onSendBootClick(View src) {
		Log.d(TAG, "onSendBootClick");
		Intent intent = new Intent();
		intent.setAction("android.intent.action.BOOT_COMPLETED");
		sendBroadcast(intent);
	}
}
/*
 * AndroidBroadcastDemo				sendBroadcast		sendBroadcast with permission
 * AndroidBroadcastDemoClient(A receiver declared with tag and :permission attribute)
 * AndroidBroadcastDemoClient2(Dynamic receivers)
 * AndroidBroadcastDemoClient3(No permission: sendBroadcast, declared receiver and dynamic receivers)	
 */

/*
sendBroadcast: 2(sendBroadcast with parameter permission) * 2(package with permission/package without permission)

sendBroadcast from package with permission						s1
 
sendBroadcast with permission from package with permission		s2

sendBroadcast from package without permission					s3

sendBroadcast with permission from package without permission	s4


receivers: 2 (declared/dynamic) * 2(require permission/not require permission) * 2(package with permission/package without permission)

declared receiver require permission in package with permission					r1

declared receiver not require permission in package with permission					r1

dynamic receiver require permission in package with permission						r2

dynamic receiver not require permission in package with permission						r2

declared receiver require permission in package without permission					r3

declared receiver not require permission in package without permission					r3

dynamic receiver require permission in package without permission					r4

dynamic receiver not require permission in package without permission					r4

 */

/*sendBroadcast from package with permission
 * Receivers in package with permission
 * 
 * 
 * sendBroadcast without parameter permission
07-02 03:30:23.319: DEBUG/PermissionBroadcastActivity(353): onSendClick
07-02 03:30:23.360: DEBUG/DynamicPermissionBroadcastReceiver(573): onReceive Require Permission
07-02 03:30:23.439: DEBUG/DynamicPermissionBroadcastReceiver2(573): onReceive not Require Permission
07-02 03:30:23.499: DEBUG/DeclaredPermissionBroadcastReceiver(573): onReceive Require Permission
07-02 03:30:23.569: DEBUG/DeclaredPermissionBroadcastReceiver2(573): onReceive not Require Permission

 * sendBroadcast with parameter permission
07-02 03:32:58.519: DEBUG/PermissionBroadcastActivity(353): onSendPermissionClick
07-02 03:32:58.549: DEBUG/DynamicPermissionBroadcastReceiver(573): onReceive Require Permission
07-02 03:32:58.599: DEBUG/DynamicPermissionBroadcastReceiver2(573): onReceive not Require Permission
07-02 03:32:58.699: DEBUG/DeclaredPermissionBroadcastReceiver(573): onReceive Require Permission
07-02 03:32:58.739: DEBUG/DeclaredPermissionBroadcastReceiver2(573): onReceive not Require Permission
 */

/*sendBroadcast from package with permission
 * Receivers in package without permission
 * 
 * 
 * sendBroadcast without parameter permission
07-02 03:36:45.799: DEBUG/PermissionBroadcastActivity(660): onSendClick
07-02 03:36:45.879: DEBUG/DynamicPermissionBroadcastReceiver(628): onReceive Require Permission
07-02 03:36:45.879: DEBUG/DynamicPermissionBroadcastReceiver2(628): onReceive not Require Permission
07-02 03:36:45.929: DEBUG/DeclaredPermissionBroadcastReceiver(628): onReceive Require Permission
07-02 03:36:47.379: DEBUG/DeclaredPermissionBroadcastReceiver2(628): onReceive not Require Permission

 * sendBroadcast with parameter permission
07-02 03:37:21.029: DEBUG/PermissionBroadcastActivity(660): onSendPermissionClick
07-02 03:37:21.079: WARN/ActivityManager(68): Permission Denial: receiving Intent { act=com.hyman.demo.android.broadcast.permission.PERMISSION_ACTION (has extras) } to ProcessRecord{406d06e0 628:com.hyman.demo.android.broadcast.client/10040} (pid=628, uid=10040) requires com.hyman.demo.android.broadcast.permission.PERMISSION_PERMISSION due to sender com.hyman.demo.android.broadcast (uid 10039)
07-02 03:37:21.121: WARN/ActivityManager(68): Permission Denial: receiving Intent { act=com.hyman.demo.android.broadcast.permission.PERMISSION_ACTION (has extras) } to ProcessRecord{406d06e0 628:com.hyman.demo.android.broadcast.client/10040} (pid=628, uid=10040) requires com.hyman.demo.android.broadcast.permission.PERMISSION_PERMISSION due to sender com.hyman.demo.android.broadcast (uid 10039)
07-02 03:37:21.121: WARN/ActivityManager(68): Permission Denial: receiving Intent { act=com.hyman.demo.android.broadcast.permission.PERMISSION_ACTION (has extras) } to com.hyman.demo.android.broadcast.client requires com.hyman.demo.android.broadcast.permission.PERMISSION_PERMISSION due to sender com.hyman.demo.android.broadcast (uid 10039)
07-02 03:37:21.149: WARN/ActivityManager(68): Permission Denial: receiving Intent { act=com.hyman.demo.android.broadcast.permission.PERMISSION_ACTION (has extras) } to com.hyman.demo.android.broadcast.client requires com.hyman.demo.android.broadcast.permission.PERMISSION_PERMISSION due to sender com.hyman.demo.android.broadcast (uid 10039)
 */


/* sendBroadcast from package without permission
 * Receivers in package with permission
 * 
 * 
 * sendBroadcast without parameter permission
07-02 03:41:39.328: DEBUG/PermissionBroadcastActivity(726): onSendClick
07-02 03:41:39.390: WARN/ActivityManager(68): Permission Denial: broadcasting Intent { act=com.hyman.demo.android.broadcast.permission.PERMISSION_ACTION (has extras) } from com.hyman.demo.android.broadcast (pid=726, uid=10039) requires com.hyman.demo.android.broadcast.permission.PERMISSION_PERMISSION due to registered receiver BroadcastFilter{4076eae8 ReceiverList{4076ea48 693 com.hyman.demo.android.broadcast.client/10040 remote:4076e7f8}}
07-02 03:41:39.405: DEBUG/DynamicPermissionBroadcastReceiver2(693): onReceive not Require Permission
07-02 03:41:39.470: WARN/ActivityManager(68): Permission Denial: broadcasting Intent { act=com.hyman.demo.android.broadcast.permission.PERMISSION_ACTION (has extras) } from com.hyman.demo.android.broadcast (pid=726, uid=10039) requires com.hyman.demo.android.broadcast.permission.PERMISSION_PERMISSION due to receiver com.hyman.demo.android.broadcast.client/com.hyman.demo.android.broadcast.client.custom.permission.DeclaredPermissionBroadcastReceiver
07-02 03:41:39.569: DEBUG/DeclaredPermissionBroadcastReceiver2(693): onReceive not Require Permission

 * sendBroadcast with parameter permission
07-02 03:43:07.939: DEBUG/PermissionBroadcastActivity(726): onSendPermissionClick
07-02 03:43:08.099: WARN/ActivityManager(68): Permission Denial: broadcasting Intent { act=com.hyman.demo.android.broadcast.permission.PERMISSION_ACTION (has extras) } from com.hyman.demo.android.broadcast (pid=726, uid=10039) requires com.hyman.demo.android.broadcast.permission.PERMISSION_PERMISSION due to registered receiver BroadcastFilter{4076eae8 ReceiverList{4076ea48 693 com.hyman.demo.android.broadcast.client/10040 remote:4076e7f8}}
07-02 03:43:08.159: WARN/ActivityManager(68): Permission Denial: broadcasting Intent { act=com.hyman.demo.android.broadcast.permission.PERMISSION_ACTION (has extras) } from com.hyman.demo.android.broadcast (pid=726, uid=10039) requires com.hyman.demo.android.broadcast.permission.PERMISSION_PERMISSION due to receiver com.hyman.demo.android.broadcast.client/com.hyman.demo.android.broadcast.client.custom.permission.DeclaredPermissionBroadcastReceiver
07-02 03:43:08.219: DEBUG/DynamicPermissionBroadcastReceiver2(693): onReceive not Require Permission
07-02 03:43:08.289: DEBUG/DeclaredPermissionBroadcastReceiver2(693): onReceive not Require Permission
 */

/* sendBroadcast from package without permission
 * Receivers in package without permission
 * 
 * 
 * sendBroadcast without parameter permission
07-02 03:47:36.999: DEBUG/PermissionBroadcastActivity(793): onSendClick
07-02 03:47:37.082: WARN/ActivityManager(68): Permission Denial: broadcasting Intent { act=com.hyman.demo.android.broadcast.permission.PERMISSION_ACTION (has extras) } from com.hyman.demo.android.broadcast (pid=793, uid=10039) requires com.hyman.demo.android.broadcast.permission.PERMISSION_PERMISSION due to registered receiver BroadcastFilter{40739ce8 ReceiverList{40739c48 759 com.hyman.demo.android.broadcast.client/10040 remote:407399f8}}
07-02 03:47:37.109: DEBUG/DynamicPermissionBroadcastReceiver2(759): onReceive not Require Permission
07-02 03:47:37.123: WARN/ActivityManager(68): Permission Denial: broadcasting Intent { act=com.hyman.demo.android.broadcast.permission.PERMISSION_ACTION (has extras) } from com.hyman.demo.android.broadcast (pid=793, uid=10039) requires com.hyman.demo.android.broadcast.permission.PERMISSION_PERMISSION due to receiver com.hyman.demo.android.broadcast.client/com.hyman.demo.android.broadcast.client.custom.permission.DeclaredPermissionBroadcastReceiver
07-02 03:47:37.269: DEBUG/DeclaredPermissionBroadcastReceiver2(759): onReceive not Require Permission

 * sendBroadcast with parameter permission
07-02 03:48:56.229: DEBUG/PermissionBroadcastActivity(793): onSendPermissionClick
07-02 03:48:56.259: WARN/ActivityManager(68): Permission Denial: broadcasting Intent { act=com.hyman.demo.android.broadcast.permission.PERMISSION_ACTION (has extras) } from com.hyman.demo.android.broadcast (pid=793, uid=10039) requires com.hyman.demo.android.broadcast.permission.PERMISSION_PERMISSION due to registered receiver BroadcastFilter{40739ce8 ReceiverList{40739c48 759 com.hyman.demo.android.broadcast.client/10040 remote:407399f8}}
07-02 03:48:56.259: WARN/ActivityManager(68): Permission Denial: receiving Intent { act=com.hyman.demo.android.broadcast.permission.PERMISSION_ACTION (has extras) } to ProcessRecord{4066a838 759:com.hyman.demo.android.broadcast.client/10040} (pid=759, uid=10040) requires com.hyman.demo.android.broadcast.permission.PERMISSION_PERMISSION due to sender com.hyman.demo.android.broadcast (uid 10039)
07-02 03:48:56.279: WARN/ActivityManager(68): Permission Denial: receiving Intent { act=com.hyman.demo.android.broadcast.permission.PERMISSION_ACTION (has extras) } to ProcessRecord{4066a838 759:com.hyman.demo.android.broadcast.client/10040} (pid=759, uid=10040) requires com.hyman.demo.android.broadcast.permission.PERMISSION_PERMISSION due to sender com.hyman.demo.android.broadcast (uid 10039)
07-02 03:48:56.279: WARN/ActivityManager(68): Permission Denial: broadcasting Intent { act=com.hyman.demo.android.broadcast.permission.PERMISSION_ACTION (has extras) } from com.hyman.demo.android.broadcast (pid=793, uid=10039) requires com.hyman.demo.android.broadcast.permission.PERMISSION_PERMISSION due to receiver com.hyman.demo.android.broadcast.client/com.hyman.demo.android.broadcast.client.custom.permission.DeclaredPermissionBroadcastReceiver
07-02 03:48:56.299: WARN/ActivityManager(68): Permission Denial: receiving Intent { act=com.hyman.demo.android.broadcast.permission.PERMISSION_ACTION (has extras) } to com.hyman.demo.android.broadcast.client requires com.hyman.demo.android.broadcast.permission.PERMISSION_PERMISSION due to sender com.hyman.demo.android.broadcast (uid 10039)
07-02 03:48:56.299: WARN/ActivityManager(68): Permission Denial: receiving Intent { act=com.hyman.demo.android.broadcast.permission.PERMISSION_ACTION (has extras) } to com.hyman.demo.android.broadcast.client requires com.hyman.demo.android.broadcast.permission.PERMISSION_PERMISSION due to sender com.hyman.demo.android.broadcast (uid 10039)

 */