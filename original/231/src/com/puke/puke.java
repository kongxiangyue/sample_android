package com.puke;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.widget.*;
import java.util.*;

import com.puke.R;

import android.os.*;
import android.view.View;
import android.view.View.OnClickListener;

public class puke extends Activity {
    /** Called when the activity is first created. */
    //�����ؼ�
	private TextView outPut;
	private ImageView imageFace;
	private ImageView image_View1;
	private ImageView image_View2;
	private ImageView image_View3;
	private ProgressBar progressBar;
	private Button button_start;
	private Button button_end;
	private MyHandler myHandler;	//�������߳�
	private int i;	//����������
    
    private static int[] poker = {R.drawable.p_1,R.drawable.p_2,R.drawable.p_3};
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //����ѭ����Ϣ����
        HandlerThread handlerThread = new HandlerThread("thread");
        //����Ҫ������Ҫд
        handlerThread.start();
        myHandler = new MyHandler(handlerThread.getLooper());
        //ȡ�ÿؼ�ʵ��
        outPut = (TextView)findViewById(R.id.outPut);
        imageFace = (ImageView)findViewById(R.id.imageface);
        image_View1 = (ImageView)findViewById(R.id.image1);
        image_View2 = (ImageView)findViewById(R.id.image2);
        image_View3 = (ImageView)findViewById(R.id.image3);
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        button_start = (Button)findViewById(R.id.start);
        button_end = (Button)findViewById(R.id.end);
        //ϴ��
        random();
        //��1������
        image_View1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				image_View1.setImageDrawable(getResources().getDrawable(poker[0]));
				image_View2.setImageDrawable(getResources().getDrawable(poker[1]));
				image_View3.setImageDrawable(getResources().getDrawable(poker[2]));
				
				image_View2.setAlpha(100);	//����û��ѡ�е��ƽ���Ч��
				image_View3.setAlpha(100);
				
				if(poker[0] == R.drawable.p_1){
					
					outPut.setText("WOW��ѡ����Ŷ����������������ô�����ģ�");
					imageFace.setImageDrawable(getResources().getDrawable(R.drawable.qq_suprise));
					
				}else{
					
					outPut.setText("���ź�~~������������ã�����һ�ΰɣ�");
					imageFace.setImageDrawable(getResources().getDrawable(R.drawable.qq_despise));
					
				}
				
				
			}
        	
        });
        
        image_View2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				image_View1.setImageDrawable(getResources().getDrawable(poker[0]));
				image_View2.setImageDrawable(getResources().getDrawable(poker[1]));
				image_View3.setImageDrawable(getResources().getDrawable(poker[2]));
				
				image_View1.setAlpha(100);
				image_View3.setAlpha(100);
				
				if(poker[1] == R.drawable.p_1){
					
					outPut.setText("WOW��ѡ����Ŷ����������������ô�����ģ�");
					imageFace.setImageDrawable(getResources().getDrawable(R.drawable.qq_suprise));
					
				}else{
					
					outPut.setText("���ź�~~������������ã�����һ�ΰɣ�");
					imageFace.setImageDrawable(getResources().getDrawable(R.drawable.qq_despise));
					
				}
				
				
			}
        	
        });
        
        image_View3.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				image_View1.setImageDrawable(getResources().getDrawable(poker[0]));
				image_View2.setImageDrawable(getResources().getDrawable(poker[1]));
				image_View3.setImageDrawable(getResources().getDrawable(poker[2]));
				
				image_View1.setAlpha(100);
				image_View2.setAlpha(100);
				
				if(poker[2] == R.drawable.p_1){
					
					outPut.setText("WOW��ѡ����Ŷ����������������ô�����ģ�");
					imageFace.setImageDrawable(getResources().getDrawable(R.drawable.qq_suprise));
					
				}else{
					
					outPut.setText("���ź�~~������������ã�����һ�ΰɣ�");
					imageFace.setImageDrawable(getResources().getDrawable(R.drawable.qq_despise));
					
				}
				
				
			}
        	
        });
        
        button_start.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				outPut.setText("�²¿�����A�� ��һ�ţ�");
				//���ý������ɼ��������������߳�
				progressBar.setVisibility(View.VISIBLE);
				myHandler.post(progressBarThread);
				
				imageFace.setImageDrawable(getResources().getDrawable(R.drawable.qq_laugh));
				image_View1.setImageDrawable(getResources().getDrawable(R.drawable.poker_back));
				image_View2.setImageDrawable(getResources().getDrawable(R.drawable.poker_back));
				image_View3.setImageDrawable(getResources().getDrawable(R.drawable.poker_back));
				
				image_View1.setAlpha(255);
				image_View2.setAlpha(255);
				image_View3.setAlpha(255);
				
				random();
			}
        	
        });
        //end����������
        button_end.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
        	
        });
        
    }
	//���ϴ�ƺ���
	private void random(){
		
		Random rand = new Random();
		int temp1 = 0; 
		int temp2 = 0;
		
		
		for(int i=0; i<3; i++){
			temp1 = rand.nextInt(3);
			temp2 = poker[i];
			poker[i] = poker[temp1];
			poker[temp1] = temp2;  
		}
		
	}
	//�������߳�
	Runnable progressBarThread = new Runnable(){
		@Override
		public void run() {
			// TODO Auto-generated method stub
			i+=10;
			
			try{
				Thread.sleep(20);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
			Message msg = myHandler.obtainMessage();
			msg.arg1 = i;
			myHandler.sendMessage(msg);
		}
		
	};
	//���ý��������ɼ������ֱ�ӹرջ���Ϊ�����������̵߳�View��������˲�����runOnUiThread(progressInvisible);
	Runnable progressInvisible = new Runnable(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			progressBar.setVisibility(View.INVISIBLE);
		}
		
	};
	
	class MyHandler extends Handler{
		
		MyHandler(Looper looper){
			super(looper);
		}
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			
			progressBar.setProgress(msg.arg1);
			
			if(i >= 100){
				//ע�� ������Ҫ��i��0�������´ν�����ֱ����ʾ��״̬��
				i = 0;
				progressBar.setProgress(i);//�������ִ��һ�Σ��´�����������������ʾ����������գ��������ܹ�
				myHandler.removeCallbacks(progressBarThread);
				puke.this.runOnUiThread(progressInvisible);
				return;
			}
			
			myHandler.post(progressBarThread);
		}
		
	}
}