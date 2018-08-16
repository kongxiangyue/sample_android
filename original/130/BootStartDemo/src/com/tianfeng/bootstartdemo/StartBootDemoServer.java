package com.tianfeng.bootstartdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class StartBootDemoServer extends Service{
	
	private final static String TAG = "StartBootDemoServer";

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onStart(Intent intent, int startId){
		while(true){
			Log.i(TAG, "==============tianfeng I am demo Run===============");
			SystemClock.sleep(1000);
		}
	}

}
