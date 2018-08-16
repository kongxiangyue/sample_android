package com.xiangqi;

import com.xiangqi.R;

import android.app.Activity;//������صİ�
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

public class XIActivity extends Activity {
	boolean isSound = true;//�Ƿ�������
	MediaPlayer kaisheng;//��ʼ������
	MediaPlayer yousheng;//��Ϸʱ������
	
	Handler myHandler = new Handler(){//����UI�߳̿ؼ�
        public void handleMessage(Message msg) {
        	if(msg.what == 1){	//huanyingView��Help��Game��������Ϣ
        		initMenuView();//�����˵�����
        	}
        	else if(msg.what == 2){//MenuView��������Ϣ���л���Game
        		initGameView();//��ʼ��Ϸ����
        	}
        	else if(msg.what == 3){
        		initHelpView();//��ʼ����������
        	}
        }
	}; 	
    public void onCreate(Bundle savedInstanceState) {//��д��onCreate
        super.onCreate(savedInstanceState);
		//ȫ��
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);
		kaisheng= MediaPlayer.create(this, R.raw.kaisheng);//��ӭ��
		kaisheng.setLooping(true);//ѭ��������Ϸ���� 
		yousheng  = MediaPlayer.create(this, R.raw.yousheng);//��Ϸ�еı�������
		yousheng.setLooping(true);//ѭ������ ��Ϸ����
        this.initWelcomeView();//��ӭ�����ʼ��
    }
    public void initWelcomeView(){//��ӭ���� ��ʼ��
    	this.setContentView(new huanying(this,this));//������ӭ����
    	if(isSound){
    		kaisheng.start();//��������
		}
    }
    
    public void initGameView(){//��Ϸ�����ʼ��
    	this.setContentView(new Game(this,this)); //������Ϸ����
    }
    
    public void initMenuView(){//�˵������ʼ��
    	if(kaisheng != null){
    		kaisheng.stop();//ֹͣ��������
    		kaisheng = null;
    	}
    	if(this.isSound){
    		yousheng.start();//��������
    	}
    	this.setContentView(new caidan(this,this));//�л�View
    } 
    public void initHelpView(){//���������ʼ��
    	this.setContentView(new Help(this,this));//������������
    }
}