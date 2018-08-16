package com.example156;
import android.graphics.Canvas;					//������ذ�
import android.view.SurfaceHolder;				//������ذ�
//�����������ʱʱˢ��onDraw�����л�����ػ�
public class OnDrawThread extends Thread{
	MySurfaceView msv;		//�õ�MySurfaceView������
	SurfaceHolder sh;		//SurfaceHolder����
	public OnDrawThread(MySurfaceView msv) {
		super();
		this.msv = msv;			//���췽���У���msv����ָ������˸����MySurfaceView�Ķ���
		sh=msv.getHolder();
	}
	@Override
	public void run() {
		super.run();
		Canvas canvas = null;	//Canvas������
		while(true){
			try{
				canvas=sh.lockCanvas(null);			//��canvas������ָ��surfaceView��canvas�Ķ���
				synchronized(this.sh){				//���ƹ��̣����ܴ���ͬ����������⣬����
					if(canvas!=null){
					msv.onDraw(canvas);
					}
				}
			}finally{
				try{
						if(sh!=null){
							sh.unlockCanvasAndPost(canvas);	//����������
						}
				}catch(Exception e){e.printStackTrace();}
			}
			try{
				Thread.sleep(Constant.ONDRAWSPEED);					//��Ϣ1����
			}catch(Exception e){e.printStackTrace();}
		}
	}
}
