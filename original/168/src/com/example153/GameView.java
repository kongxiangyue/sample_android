package com.example153;

import com.example153.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View implements Runnable {

	// 声明Paint对象
	private Paint mPaint = null;

	// 创建两个图片对象
	Bitmap mBitmapQQ = null;
	Bitmap mBitDestTop = null;

	int miDTX = 0;
	int miDTY = 0;

	public GameView(Context context) {
		super(context);

		mPaint = new Paint();
		miDTX = 0; // 代表图片左边的横坐标 用来左右移动图片
		miDTY = 170;// 代表图片上边的纵坐标 用来上下移动图片
		/* 从资源文件中装载图片 */
		// getResources()-->得到Resources
		// getDrawable()-->得到资源中的Drawable对象，参数为资源索引ID
		// getBitmap()-->得到Bitmap
		mBitmapQQ = ((BitmapDrawable) getResources().getDrawable(R.drawable.xh))
				.getBitmap();

		mBitDestTop = ((BitmapDrawable) getResources().getDrawable(
				R.drawable.chrysler)).getBitmap();
		new Thread(this).start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		/* 清屏效果 */
		canvas.drawColor(Color.GRAY);

		/* 在屏幕(0,0)处绘制图片mBitQQ */
		GameView.drawImage(canvas, mBitmapQQ, 0, 0);

		/* 在指定位置按指定裁剪的区域进行绘制 */
		// getWidth()-->得到图片的宽度
		// getHeight()-->得到图片的高度
		GameView.drawImage(canvas, mBitDestTop, miDTX, miDTY, mBitDestTop
				.getWidth(), mBitDestTop.getHeight(), 0, 0);
	}

	// 触笔事件
	public boolean onTouchEvent(MotionEvent event) {
		return true;
	}

	// 按键按下事件
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 左方向键
		if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
			if (miDTX > 0) {
				miDTX -= 4;
			}
		}
		// 右方向键
		else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
			if (miDTX + mBitDestTop.getWidth() < 320) {
				miDTX += 4;
			}
		}
		// 上方向键
		else if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
			if (miDTY > 0) {
				// miDTY + mBitDestTop.getHeight() < 450
				miDTY -= 4;
			}
		}
		// 下方向键
		else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
			if (miDTY < 322) {
				miDTY += 4;
			}
		}
		return true;
	}

	// 按键弹起事件
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		return false;
	}

	public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
		return true;
	}

	// 线程处理
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			// 使用postInvalidate可以直接在线程中更新界面
			postInvalidate();
		}
	}

	/*---------------------------------
	 * 绘制图片
	 * @param		x屏幕上的x坐标
	 * @param		y屏幕上的y坐标
	 * @param		w要绘制的图片的宽度
	 * @param		h要绘制的图片的高度
	 * @param		bx图片上的x坐标
	 * @param		by图片上的y坐标
	 * 
	 * @return 		null
	 ------------------------------------*/

	public static void drawImage(Canvas canvas, Bitmap blt, int x, int y,
			int w, int h, int bx, int by) {
		Rect src = new Rect();// 图片 >>原矩形
		Rect dst = new Rect();// 屏幕 >>目标矩形

		src.left = bx;
		src.top = by;
		src.right = bx + w;
		src.bottom = by + h;

		dst.left = x;
		dst.top = y;
		dst.right = x + w;
		dst.bottom = y + h;
		// 画出指定的位图，位图将自动--》缩放/自动转换，以填补目标矩形
		canvas.drawBitmap(blt, null, dst, null);
		src = null;
		dst = null;
	}

	/**
	 * 绘制一个Bitmap
	 * 
	 * @param canvas
	 *            画布
	 * @param bitmap
	 *            图片
	 * @param x
	 *            屏幕上的x坐标
	 * @param y
	 *            屏幕上的y坐标
	 */

	public static void drawImage(Canvas canvas, Bitmap bitmap, int x, int y) {
		// 绘制图像 将bitmap对象显示在坐标 x,y上
		canvas.drawBitmap(bitmap, x, y, null);
	}

}
