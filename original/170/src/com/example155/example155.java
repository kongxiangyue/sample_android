package com.example155;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class example155 extends SurfaceView implements
		SurfaceHolder.Callback, Runnable {

	// ����ѭ��
	boolean mbLoop = false;

	// ����SurfaceHolder����
	SurfaceHolder mSurfaceHolder = null;
	int miCount = 0;
	int y = 50;

	public example155(Context context) {
		super(context);
		// ʵ����SurfaceHolder
		mSurfaceHolder = this.getHolder();

		// ��ӻص� ����
		// ע��������� mSurfaceHolder.addCallback(this)���ִ������֮��
		// ���Ͼͻ�ص� surfaceCreated������ Ȼ�����߳� ִ�л�ͼ�����������ִ��˳��Ҫ�����
		mSurfaceHolder.addCallback(this);

		this.setFocusable(true);

		mbLoop = true;
	}

	// ��surface�Ĵ�С�����ı�ʱ����
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	// surface����ʱ���� �˷��������߳���ִ��
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// ������ͼ�߳�
		new Thread(this).start();
	}

	// ��surface����ʱ����
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// ֹͣѭ��
		mbLoop = false;
	}

	// ��ͼѭ��
	@Override
	public void run() {
		while (mbLoop) {
			try {
				Thread.sleep(200);
			} catch (Exception e) {

			}
			// ��������Ϊʲôͬ�������һ�黭�� �㲻����������ͬʱ���ϱ߻���
			synchronized (mSurfaceHolder) {
				Draw();
			}
		}
	}

	// ��ͼ���� ע������������һ���߳���ִ�л�ͼ�����˲�����UI �߳���
	public void Draw() {
		// �����������õ�canvas ��SurfaceHolder�����lockCanvas����
		Canvas canvas = mSurfaceHolder.lockCanvas();
		if (mSurfaceHolder == null || canvas == null) {
			return;
		}
		if (miCount < 100) {
			miCount++;
		} else {
			miCount = 0;
		}
		// ��ͼ
		Paint mPaint = new Paint();
		// ��Paint������Ͽ���ݱ�־
		// [img]http://tech.techweb.com.cn/thread-459611-1-1.html[/img]
		// ��ϸ˵�����Կ�������
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.BLACK);
		// ���ƾ���---��������
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
		// ���ƾ���
		canvas.drawCircle((320 - 25) / 2, y, 50, mPaint);
		// ���ƺ���������ƺ�������������ʾ
		mSurfaceHolder.unlockCanvasAndPost(canvas);
	}
}
