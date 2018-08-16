package com.wuzi;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

public class mainA extends Activity {
    
    GameV gameView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	// 隐藏标题栏
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	// 全屏显示
	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);
	// 获取屏幕宽高
	Display display = getWindowManager().getDefaultDisplay();
	// 现实GameView
	GameV.init(this, display.getWidth(), display.getHeight());
	gameView = GameV.getInstance();
	setContentView(gameView);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
	return super.onKeyDown(keyCode, event);
    }
}