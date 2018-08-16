package com.example139;

import android.app.Activity;
import android.os.Bundle;

public class Activity01 extends Activity
{
	
	private draw mGameView;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		mGameView = new draw(this);
		
		setContentView(mGameView);
	}
}
