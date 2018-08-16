package com.xiangqi;
import com.xiangqi.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class huanying extends SurfaceView implements SurfaceHolder.Callback {
	XIActivity activity;
	private TutorialThread thread;//ˢ֡�߳�
	private huanyingThread moveThread;//�ƶ�����߳�
	Bitmap welcomebackage;//�󱳾�
	Bitmap biao;
	Bitmap boy;//С��ͼ
	Bitmap oldboy;//��ͷͼ
	Bitmap bordbackground;//���ֱ���
	Bitmap biao2;
	Bitmap menu;//�˵���ť
	
	int biaoX = -120;//�ƶ�ͼƬ�����ʼ��
	int boyX = -100;
	int oldboyX = -120;
	int biao2X = 320;
	
	int bordbackgroundY = -100;//����y����
	int menuY = 520;//�˵�y����
	public huanying(Context context,XIActivity activity) {
		super(context);
		this.activity = activity;//��ȡactivity����
        getHolder().addCallback(this);
        this.thread = new TutorialThread(getHolder(), this);//ˢ֡�̳߳�ʼ��
        this.moveThread = new huanyingThread(this);//�ƶ�ͼƬ�̳߳�ʼ��
        initBitmap();//��ʼ��ȫ��ͼƬ
	}
	public void initBitmap(){//��ʼ��ȫ��ͼƬ����
		welcomebackage = BitmapFactory.decodeResource(getResources(), R.drawable.huanying);
		biao = BitmapFactory.decodeResource(getResources(), R.drawable.biao);
		boy = BitmapFactory.decodeResource(getResources(), R.drawable.boy);
		oldboy = BitmapFactory.decodeResource(getResources(), R.drawable.oldboy);
		bordbackground = BitmapFactory.decodeResource(getResources(), R.drawable.bordbackground);
		biao2 = BitmapFactory.decodeResource(getResources(), R.drawable.biao2);
		menu = BitmapFactory.decodeResource(getResources(), R.drawable.menu);
	}
	//���Ʒ��������ز�ͼƬ��������Ļ�����������ز�ͼƬ������
	public void onDraw(Canvas canvas){
		canvas.drawColor(Color.BLACK);
		canvas.drawBitmap(welcomebackage, 0, 100, null);
		canvas.drawBitmap(biao, biaoX, 110, null);
		canvas.drawBitmap(boy, boyX, 210, null);
		canvas.drawBitmap(oldboy, oldboyX, 270, null);
		canvas.drawBitmap(bordbackground, 150, bordbackgroundY, null);
		canvas.drawBitmap(biao2, biao2X, 100, null);
		canvas.drawBitmap(menu, 200, menuY, null);
	}
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}
	public void surfaceCreated(SurfaceHolder holder) {//������������
        this.thread.setFlag(true);//ѭ����־λ
        this.thread.start();//�����߳�
        
        this.moveThread.setFlag(true);//ѭ����־λ
        this.moveThread.start();//�����߳�
	}
	public void surfaceDestroyed(SurfaceHolder holder) {//�����ͷŽ���
        boolean retry = true;
        thread.setFlag(false);//ѭ����־λ
        moveThread.setFlag(false);
        while (retry) {//ѭ��
            try {
                thread.join();//�߳̽���
                moveThread.join();
                retry = false;//ֹͣѭ��
            } 
            catch (InterruptedException e) {//ѭ����ˢ֡�߳̽���
            }
        }
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {//������Ļ
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			if(event.getX()>200 && event.getX()<200+menu.getWidth()
					&& event.getY()>355 && event.getY()<355+menu.getHeight()){//����˵���ť
				activity.myHandler.sendEmptyMessage(1);
			}
		}
		return super.onTouchEvent(event);
	}
	class TutorialThread extends Thread{//ˢ֡�߳�
		private int span = 100;//����˯��100������
		private SurfaceHolder surfaceHolder;//SurfaceHolder����
		private huanying welcomeView;//WelcomeView����
		private boolean flag = false;
        public TutorialThread(SurfaceHolder surfaceHolder, huanying welcomeView) {//������
            this.surfaceHolder = surfaceHolder;
            this.welcomeView = welcomeView;
        }
        public void setFlag(boolean flag) {//ѭ�����λ
        	this.flag = flag;
        }
		@Override
		public void run() {//��д����
			Canvas c;//����
            while (this.flag) {//ѭ��
                c = null;
                try {
                	// ��������
                    c = this.surfaceHolder.lockCanvas(null);
                    synchronized (this.surfaceHolder) {//ͬ��
                    	welcomeView.onDraw(c);//����
                    }
                } finally {
                    if (c != null) {
                    	//������Ļ��ʾ����
                        this.surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
                try{
                	Thread.sleep(span);//˯��ʱ��
                }
                catch(Exception e){//�����쳣
                	e.printStackTrace();//�����ջ��Ϣ
                }
            }
		}
	}
}