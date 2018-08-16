package com.example141;

import android.app.Activity;
import android.os.Bundle;

public class Activity01 extends Activity
{
	private example141 mGameView = null;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		mGameView = new example141(this);
		
		setContentView(mGameView);
	}
}
