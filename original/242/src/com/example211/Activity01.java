package com.example211;

import com.example211.R;

import android.app.Activity;
import android.os.Bundle;

public class Activity01 extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		AA  arrayMemo=(AA)Handler.newInstance(new  BB  ());
		arrayMemo.creatArray();
		arrayMemo.creatHashMap(); 
		
		setContentView(R.layout.main);
	}
}
