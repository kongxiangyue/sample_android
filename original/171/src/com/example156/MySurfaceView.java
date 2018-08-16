package com.example156;
import com.example156.R;
import android.content.Context;					//������ذ�
import android.graphics.Bitmap;					//������ذ�
import android.graphics.BitmapFactory;			//������ذ�
import android.graphics.Canvas;					//������ذ�
import android.graphics.Color;					//������ذ�
import android.graphics.Paint;					//������ذ�
import android.view.Display;					//������ذ�
import android.view.SurfaceHolder;				//������ذ�
import android.view.SurfaceView;				//������ذ�
public class MySurfaceView extends SurfaceView
implements SurfaceHolder.Callback{			
	//�˴�ʵ��SurfaceHolder.Callback�ӿڣ�ΪsurfaceView����������ڻص�����
	int dy=Display.DEFAULT_DISPLAY;
	MyActivity ma;							//�õ�MyActivity������
	Paint paint;							//���ʵ�����
	OnDrawThread odt;						//OnDrawThread������
	PicRunThread prt;						//ͼƬ�˶���Thread������
	private float picX=0;						//ͼƬx����
	private float picY=0;						//ͼƬy����
	boolean picAlphaFlag=false;					//ͼƬ�䰵Ч���ı�ǣ�falseΪ����ʾ��trueΪ��ʾ��
	int picAlphaNum=0;							//ͼƬ�䰵Ч���л��ʵ�alphaֵ
	public MySurfaceView(Context context) {
		super(context);
		this.ma=(MyActivity) context;			
		//��ma������ָ������˸�Surfaceview�๹���������Ķ��󣬱���ΪMyActivity
		this.getHolder().addCallback(this);		//ע��ص��ӿ�
		paint=new Paint();						//ʵ��������
		odt=new OnDrawThread(this);				//ʵ����OnDrawThread��
		prt=new PicRunThread(this);				//ʵ����PicRunThread��
		prt.start();
	}
	public void setPicX(float picX) {			//ͼƬx�����������
		this.picX = picX;
	}
	public void setPicY(float picY) {			//ͼƬy�����������
		this.picY = picY;
	}
	public void setPicAlphaNum(int picAlphaNum) {//ͼƬ�䰵Ч��alpha����������
		this.picAlphaNum = picAlphaNum;
	}
	@Override
	protected void onDraw(Canvas canvas) {	//onDraw�������˷������ڻ���ͼ��ͼ�ε�
		super.onDraw(canvas);
		paint.setColor(Color.WHITE);		//���û���Ϊ��ɫ
		canvas.drawRect(0, 0, Constant.SCREENWIDTH, Constant.SCREENHEIGHT, paint);
		//�˴�����һ����ɫ��ȫ��Ļ�ľ��Σ�Ŀ�������ñ���Ϊ��ɫ��ͬʱÿ���ػ�ʱ�������
		//����ƽ����ͼ
		Bitmap bitmapDuke=BitmapFactory.decodeResource(ma.getResources(), R.drawable.duke);
		canvas.drawBitmap(bitmapDuke, picX, picY, paint);
		//ͼƬ����Ч��
		if(picAlphaFlag){
			Bitmap bitmapBG=BitmapFactory.decodeResource(ma.getResources(), R.drawable.jpg1);
			paint.setAlpha(picAlphaNum);
			canvas.drawBitmap(bitmapBG, 0,0, paint);
		}
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {			//�˷���Ϊ��surfaceView�ı�ʱ���ã�����Ļ��С�ı䡣
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {//�˷���Ϊ��surfaceView����ʱ����
		odt.start();				//����onDraw�Ļ����߳�
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {//�˷���Ϊ��surfaceView����ǰ����
	}
}
