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
	// ���ر�����
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	// ȫ����ʾ
	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);
	// ��ȡ��Ļ���
	Display display = getWindowManager().getDefaultDisplay();
	// ��ʵGameView
	GameV.init(this, display.getWidth(), display.getHeight());
	gameView = GameV.getInstance();
	setContentView(gameView);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
	return super.onKeyDown(keyCode, event);
    }
}