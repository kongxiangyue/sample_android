package com.tianfeng.bootstartdemo;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootBroadcastReceiver extends BroadcastReceiver{
	private static final String ACTION = "android.intent.action.BOOT_COMPLETED";
  	private final static String TAG = "TIANFENG:-->PMBootBroadcastReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.v(TAG, "+++++++++++just try to open PowerManager Servers++++++++++++");
		if(intent.getAction().equals(ACTION))
		{
	        Log.e(TAG, "try to start powerManager servers");
			Intent mDemoService=new Intent();//Æô¶¯·þÎñ
			mDemoService.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			mDemoService.setClass(context, StartBootDemoServer.class);
			context.startService(mDemoService);  
			
		}	
		
	}

}
