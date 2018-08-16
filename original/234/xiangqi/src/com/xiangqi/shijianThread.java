package com.xiangqi;

 
public class shijianThread extends Thread{
	private boolean flag = true;//循环标志
	Game gameView;
	public shijianThread(Game gameView){
		this.gameView = gameView;
	}
	public void setFlag(boolean flag){//设置循环标记
		this.flag = flag;
	}
	@Override
	public void run(){//重写方法
		while(flag){//循环
			if(gameView.caiPan == false){//自动加黑方时间
			}
			else if(gameView.caiPan == true){//为红方走棋、思考
				gameView.hongTime++;//自动加红方时间
			}
			try{
				Thread.sleep(1000);//睡眠1000毫秒，即1秒钟
			}
			catch(Exception e){//捕获异常
				e.printStackTrace();//输出异常信息
			}
		}
	}
}