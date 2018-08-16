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

public class caidan extends SurfaceView implements SurfaceHolder.Callback {
	XIActivity activity;
	private TutorialThread thread;//刷帧线程
	Bitmap kai;//开始图片
	Bitmap da;//打开声音图片
	Bitmap guan;//关闭声音的图片
	Bitmap help;//帮助图片
	Bitmap exit;//退出图片 
	public caidan(Context context,XIActivity activity) {
		super(context);
		this.activity = activity;
        getHolder().addCallback(this);
        this.thread = new TutorialThread(getHolder(), this);//启动刷帧线程
        initBitmap();//图片资源初始化
	}
	public void initBitmap(){//图片资源图片初始化
		kai = BitmapFactory.decodeResource(getResources(), R.drawable.kai);//开始按钮
		da = BitmapFactory.decodeResource(getResources(), R.drawable.da);//开始声音按钮
		guan = BitmapFactory.decodeResource(getResources(), R.drawable.guan);//关闭声音按钮
		help = BitmapFactory.decodeResource(getResources(), R.drawable.help);//帮助按钮
		exit = BitmapFactory.decodeResource(getResources(), R.drawable.exit);//退出按钮
	}
	public void onDraw(Canvas canvas){//绘制方法
		canvas.drawColor(Color.BLACK);//清屏
		canvas.drawBitmap(kai, 50, 50, null);//绘制图片
		if(activity.isSound){//开音乐时，关闭声音图片
			canvas.drawBitmap(guan, 50, 150, null);//关闭声音
		}else{//绘制打开声音图片
			canvas.drawBitmap(da, 50, 150, null);//开始声音
		}
		canvas.drawBitmap(help, 50, 250, null);//帮助按钮
		canvas.drawBitmap(exit, 50, 350, null);//退出按钮
	}
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}
	public void surfaceCreated(SurfaceHolder holder) {//启动刷帧
        this.thread.setFlag(true);//循环标志
        this.thread.start();//启动线程
	}
	public void surfaceDestroyed(SurfaceHolder holder) {//释放刷帧线程
        boolean retry = true;//循环标志
        thread.setFlag(false);//循环标志
        while (retry) {//循环
            try {
                thread.join();//线程结束
                retry = false;//停止循环
            }catch (InterruptedException e){}//循环到刷帧线程结束
        }
	}
	public boolean onTouchEvent(MotionEvent event) {//监听屏幕
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			if(event.getX()>105 && event.getX()<220
					&&event.getY()>60 && event.getY()<95){//开始游戏
				activity.myHandler.sendEmptyMessage(2);
			}else if(event.getX()>105 && event.getX()<220
					&&event.getY()>160 && event.getY()<195){//声音按钮
				activity.isSound = !activity.isSound;//取反声音开关
				if(!activity.isSound){
					if(activity.yousheng != null){//是否正在播放声音
						if(activity.yousheng.isPlaying()){
							activity.yousheng.pause();//停止播放
						}	
					}
				}else{
					if(activity.yousheng != null){//有声音时
						if(!activity.yousheng.isPlaying()){//没有播放的声音
							activity.yousheng.start();//则播放它
						}	
					}
				}
			}else if(event.getX()>105 && event.getX()<220
					&&event.getY()>260 && event.getY()<295){//帮助按钮
				activity.myHandler.sendEmptyMessage(3);
			}else if(event.getX()>105 && event.getX()<220
					&&event.getY()>360 && event.getY()<395){//退出游戏
				System.exit(0);//退出
			}
		}
		return super.onTouchEvent(event);
	}
	class TutorialThread extends Thread{//刷帧线程
		private int span = 500;//睡眠500毫秒
		private SurfaceHolder surfaceHolder;
		private caidan menuView;
		private boolean flag = false;//循环标记
        public TutorialThread(SurfaceHolder surfaceHolder, caidan menuView) {
            this.surfaceHolder = surfaceHolder;
            this.menuView = menuView;
        }
        public void setFlag(boolean flag) {//循环标记
        	this.flag = flag;
        }
		public void run() {//重写方法
			Canvas c;//画布
            while (this.flag) {//循环
                c = null;
                try {
                	// 锁定画布
                    c = this.surfaceHolder.lockCanvas(null);
                    synchronized (this.surfaceHolder) {//同步锁
                    	menuView.onDraw(c);//绘制方法
                    }
                } finally {
                    if (c != null) {
                    	//更新屏幕内容
                        this.surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
                try{
                	Thread.sleep(span);//睡眠时间，单位毫秒
                }catch(Exception e){//捕获异常
                	e.printStackTrace();//输出印异常堆栈信息
                }
            }
		}
	}
}