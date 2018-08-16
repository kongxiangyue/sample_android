package com.example152;

import com.example152.R;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class Activity01 extends Activity
{
	example152 render = new example152();
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		GLImage.load(this.getResources());
		GLSurfaceView glView = new GLSurfaceView(this);
		
		glView.setRenderer(render);
		setContentView(glView);
	}
	
//	//处理事件
//	public boolean onKeyUp(int keyCode, KeyEvent event)
//	{
//		render.onKeyUp(keyCode, event);
//		return super.onKeyUp(keyCode, event);
//	}
//	
//	public boolean onKeyDown(int keyCode, KeyEvent event)
//	{
//		render.onKeyDown(keyCode, event);
//		return true;
//	}
}


class GLImage
{
	public static Bitmap mBitmap;
	public static void load(Resources resources)
	{
		mBitmap = BitmapFactory.decodeResource(resources, R.drawable.particle);
	}
}