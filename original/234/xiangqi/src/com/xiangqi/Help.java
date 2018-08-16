package com.xiangqi;

import com.xiangqi.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Help extends SurfaceView implements SurfaceHolder.Callback {
	XIActivity activity;
	private TutorialThread thread;//刷帧线程
	Bitmap back;//返回图按钮
	Bitmap bei;//背景图
	public Help(Context context,XIActivity activity) {
		super(context);
		this.activity = activity;//得到activity引用
        getHolder().addCallback(this);
        this.thread = new TutorialThread(getHolder(), this);//重绘线程初始化
        initBitmap();//图片资源初始化
	}
	public void initBitmap(){//初始化所用到的图片
		back = BitmapFactory.decodeResource(getResources(), R.drawable.back);//返回按钮图
		bei = BitmapFactory.decodeResource(
						getResources(), 
						R.drawable.bei);//背景图片初始化
	}
	public void onDraw(Canvas canvas){//绘制方法
		canvas.drawBitmap(bei, 0, 90, new Paint());//绘制背景图
		canvas.drawBitmap(back, 200, 370, new Paint());//绘制按钮
	}
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}
	public void surfaceCreated(SurfaceHolder holder) {//创建时启动刷帧线程
        this.thread.setFlag(true);//循环标志
        this.thread.start();//刷帧线程
	}
	public void surfaceDestroyed(SurfaceHolder holder) {//停止刷帧线程
        boolean retry = true;//循环标志
        thread.setFlag(false);//循环标志位
        while (retry) {
            try {
                thread.join();//线程结束
                retry = false;//停止循环
            }catch (InterruptedException e){}//循环到刷帧线程结束
        }
	}
	public boolean onTouchEvent(MotionEvent event) {//监听屏幕
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			if(event.getX()>200 && event.getX()<200+back.getWidth()
					&& event.getY()>370 && event.getY()<370+back.getHeight()){
				activity.myHandler.sendEmptyMessage(1);
			}
		}
		return super.onTouchEvent(event);
	} 
	class TutorialThread extends Thread{//刷帧线程
		private int span = 1000;//睡眠1000毫秒数
		private SurfaceHolder surfaceHolder;
		private Help helpView;//引用父类
		private boolean flag = false;//循环标记 
        public TutorialThread(SurfaceHolder surfaceHolder, Help helpView) {
            this.surfaceHolder = surfaceHolder;
            this.helpView = helpView;
        }
        public void setFlag(boolean flag) {//循环标记
        	this.flag = flag;
        }
		public void run() {//重写方法
			Canvas c;//画布
            while (this.flag) {//循环
                c = null;
                try {
                    c = this.surfaceHolder.lockCanvas(null);
                    synchronized (this.surfaceHolder) {//同步
                    	helpView.onDraw(c);//绘制方法
                    }
                } finally {
                    if (c != null) {//更新屏幕显示
                        this.surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
                try{
                	Thread.sleep(span);//设置睡眠时间，单位毫秒
                }catch(Exception e){//捕获异常
                	e.printStackTrace();//输出异常堆栈信息
                }
            }
		}
	}
}