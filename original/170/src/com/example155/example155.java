package com.example155;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class example155 extends SurfaceView implements
		SurfaceHolder.Callback, Runnable {

	// 控制循环
	boolean mbLoop = false;

	// 定义SurfaceHolder对象
	SurfaceHolder mSurfaceHolder = null;
	int miCount = 0;
	int y = 50;

	public example155(Context context) {
		super(context);
		// 实例化SurfaceHolder
		mSurfaceHolder = this.getHolder();

		// 添加回调 函数
		// 注意这里这句 mSurfaceHolder.addCallback(this)这句执行完了之后
		// 马上就会回调 surfaceCreated方法了 然后开启线程 执行绘图方法这里这个执行顺序要搞清楚
		mSurfaceHolder.addCallback(this);

		this.setFocusable(true);

		mbLoop = true;
	}

	// 在surface的大小发生改变时激发
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	// surface创建时激发 此方法在主线程总执行
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// 开启绘图线程
		new Thread(this).start();
	}

	// 在surface销毁时激发
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// 停止循环
		mbLoop = false;
	}

	// 绘图循环
	@Override
	public void run() {
		while (mbLoop) {
			try {
				Thread.sleep(200);
			} catch (Exception e) {

			}
			// 至于这里为什么同步这就像一块画布 你不能让两个人同时往上边画画
			synchronized (mSurfaceHolder) {
				Draw();
			}
		}
	}

	// 绘图方法 注意这里是另起一个线程来执行绘图方法了不是在UI 线程了
	public void Draw() {
		// 锁定画布，得到canvas 用SurfaceHolder对象的lockCanvas方法
		Canvas canvas = mSurfaceHolder.lockCanvas();
		if (mSurfaceHolder == null || canvas == null) {
			return;
		}
		if (miCount < 100) {
			miCount++;
		} else {
			miCount = 0;
		}
		// 绘图
		Paint mPaint = new Paint();
		// 给Paint对象加上抗锯齿标志
		// [img]http://tech.techweb.com.cn/thread-459611-1-1.html[/img]
		// 详细说明可以看看这里
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.BLACK);
		// 绘制矩形---清屏作用
		canvas.drawRect(0, 0, 320, 480, mPaint);
		switch (miCount % 4) {
		case 0:
			mPaint.setColor(Color.BLUE);
			break;
		case 1:
			mPaint.setColor(Color.GREEN);
			break;
		case 2:
			mPaint.setColor(Color.RED);
		case 3:
			mPaint.setColor(Color.YELLOW);
		default:
			mPaint.setColor(Color.WHITE);
			break;
		}
		// 绘制矩形
		canvas.drawCircle((320 - 25) / 2, y, 50, mPaint);
		// 绘制后解锁，绘制后必须解锁才能显示
		mSurfaceHolder.unlockCanvasAndPost(canvas);
	}
}
