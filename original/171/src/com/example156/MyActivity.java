package com.example156;
/*
 * 	��������ʾsurfaceView�м򵥳����Ļ���
 * 	MyActivity.java		Ϊ�������Activity
 * 	MySurfaceView.java	Ϊ�����SurfaceView��
 * 	Constant.java		�����࣬������ȫ��д�ڸ�����
 * 	OnDrawThread.java	�����������ʱʱˢ��onDraw�����л�����ػ�
 * 	PicRunThread.java	�����ǿ���dukeͼƬ�˶�����
 * */
import android.app.Activity;						//������ذ�
import android.content.pm.ActivityInfo;				//������ذ�
import android.os.Bundle;							//������ذ�
import android.view.Window;							//������ذ�
import android.view.WindowManager;					//������ذ�	
public class MyActivity extends Activity {
    /** Called when the activity is first created. */
	private MySurfaceView msv;			//�õ�surfaceView������
    @Override
    public void onCreate(Bundle savedInstanceState) {	//Activity���������ں������ú������ڳ��򴴽�ʱ����
        super.onCreate(savedInstanceState);
        msv=new MySurfaceView(MyActivity.this);			//ʵ����MySurfaceView�Ķ���
		requestWindowFeature(Window.FEATURE_NO_TITLE);	//������Ļ��ʾû��title�� 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);	//����ȫ��
		//����ֻ�������
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(msv);							//����Activity��ʾ������Ϊmsv
    }
}