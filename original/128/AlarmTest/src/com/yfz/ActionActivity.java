/*
 *	Copyright (c) 2011, Yulong Information Technologies
 *	All rights reserved.
 *  
 *  @Project: AlarmTest
 *  @author: Robot	
 */
package com.yfz;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;


public class ActionActivity extends Activity {
	private static int num = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.e("ActionActivity", "Activity New Message !" + num++);
		Button button = new Button(this);
		button.setText("¼àÌý×´Ì¬");
		
		setContentView(button);
	}
	
}	
