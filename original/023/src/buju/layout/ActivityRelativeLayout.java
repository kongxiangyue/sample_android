package buju.layout;

import buju.layout.R;

import android.app.Activity;
import android.os.Bundle;

public class ActivityRelativeLayout extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.relative_layout);
		
	}
}