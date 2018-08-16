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

	// ����Paint����
	private Paint mPaint = null;

	// ��������ͼƬ����
	Bitmap mBitmapQQ = null;
	Bitmap mBitDestTop = null;

	int miDTX = 0;
	int miDTY = 0;

	public GameView(Context context) {
		super(context);

		mPaint = new Paint();
		miDTX = 0; // ����ͼƬ��ߵĺ����� ���������ƶ�ͼƬ
		miDTY = 170;// ����ͼƬ�ϱߵ������� ���������ƶ�ͼƬ
		/* ����Դ�ļ���װ��ͼƬ */
		// getResources()-->�õ�Resources
		// getDrawable()-->�õ���Դ�е�Drawable���󣬲���Ϊ��Դ����ID
		// getBitmap()-->�õ�Bitmap
		mBitmapQQ = ((BitmapDrawable) getResources().getDrawable(R.drawable.xh))
				.getBitmap();

		mBitDestTop = ((BitmapDrawable) getResources().getDrawable(
				R.drawable.chrysler)).getBitmap();
		new Thread(this).start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		/* ����Ч�� */
		canvas.drawColor(Color.GRAY);

		/* ����Ļ(0,0)������ͼƬmBitQQ */
		GameView.drawImage(canvas, mBitmapQQ, 0, 0);

		/* ��ָ��λ�ð�ָ���ü���������л��� */
		// getWidth()-->�õ�ͼƬ�Ŀ��
		// getHeight()-->�õ�ͼƬ�ĸ߶�
		GameView.drawImage(canvas, mBitDestTop, miDTX, miDTY, mBitDestTop
				.getWidth(), mBitDestTop.getHeight(), 0, 0);
	}

	// �����¼�
	public boolean onTouchEvent(MotionEvent event) {
		return true;
	}

	// ���������¼�
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// �����
		if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
			if (miDTX > 0) {
				miDTX -= 4;
			}
		}
		// �ҷ����
		else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
			if (miDTX + mBitDestTop.getWidth() < 320) {
				miDTX += 4;
			}
		}
		// �Ϸ����
		else if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
			if (miDTY > 0) {
				// miDTY + mBitDestTop.getHeight() < 450
				miDTY -= 4;
			}
		}
		// �·����
		else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
			if (miDTY < 322) {
				miDTY += 4;
			}
		}
		return true;
	}

	// ���������¼�
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		return false;
	}

	public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
		return true;
	}

	// �̴߳���
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			// ʹ��postInvalidate����ֱ�����߳��и��½���
			postInvalidate();
		}
	}

	/*---------------------------------
	 * ����ͼƬ
	 * @param		x��Ļ�ϵ�x����
	 * @param		y��Ļ�ϵ�y����
	 * @param		wҪ���Ƶ�ͼƬ�Ŀ��
	 * @param		hҪ���Ƶ�ͼƬ�ĸ߶�
	 * @param		bxͼƬ�ϵ�x����
	 * @param		byͼƬ�ϵ�y����
	 * 
	 * @return 		null
	 ------------------------------------*/

	public static void drawImage(Canvas canvas, Bitmap blt, int x, int y,
			int w, int h, int bx, int by) {
		Rect src = new Rect();// ͼƬ >>ԭ����
		Rect dst = new Rect();// ��Ļ >>Ŀ�����

		src.left = bx;
		src.top = by;
		src.right = bx + w;
		src.bottom = by + h;

		dst.left = x;
		dst.top = y;
		dst.right = x + w;
		dst.bottom = y + h;
		// ����ָ����λͼ��λͼ���Զ�--������/�Զ�ת�������Ŀ�����
		canvas.drawBitmap(blt, null, dst, null);
		src = null;
		dst = null;
	}

	/**
	 * ����һ��Bitmap
	 * 
	 * @param canvas
	 *            ����
	 * @param bitmap
	 *            ͼƬ
	 * @param x
	 *            ��Ļ�ϵ�x����
	 * @param y
	 *            ��Ļ�ϵ�y����
	 */

	public static void drawImage(Canvas canvas, Bitmap bitmap, int x, int y) {
		// ����ͼ�� ��bitmap������ʾ������ x,y��
		canvas.drawBitmap(bitmap, x, y, null);
	}

}
