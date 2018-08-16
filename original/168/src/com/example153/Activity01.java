package com.example153;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

public class Activity01 extends Activity {

	private GameView mGameView = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mGameView = new GameView(this);

		setContentView(mGameView);

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (mGameView == null) {
			return false;
		}
		return mGameView.onKeyDown(keyCode, event);
	}
}