package com.xiangqi;

import com.xiangqi.R;

import android.app.Activity;//引入相关的包
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

public class XIActivity extends Activity {
	boolean isSound = true;//是否有声音
	MediaPlayer kaisheng;//开始的音乐
	MediaPlayer yousheng;//游戏时的声音
	
	Handler myHandler = new Handler(){//更新UI线程控件
        public void handleMessage(Message msg) {
        	if(msg.what == 1){	//huanyingView或Help或Game传来的消息
        		initMenuView();//来到菜单界面
        	}
        	else if(msg.what == 2){//MenuView传来的消息，切换到Game
        		initGameView();//初始游戏界面
        	}
        	else if(msg.what == 3){
        		initHelpView();//初始化帮助界面
        	}
        }
	}; 	
    public void onCreate(Bundle savedInstanceState) {//重写的onCreate
        super.onCreate(savedInstanceState);
		//全屏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);
		kaisheng= MediaPlayer.create(this, R.raw.kaisheng);//欢迎音
		kaisheng.setLooping(true);//循环播放游戏声音 
		yousheng  = MediaPlayer.create(this, R.raw.yousheng);//游戏中的背景声音
		yousheng.setLooping(true);//循环播放 游戏声音
        this.initWelcomeView();//欢迎界面初始化
    }
    public void initWelcomeView(){//欢迎界面 初始化
    	this.setContentView(new huanying(this,this));//来到欢迎界面
    	if(isSound){
    		kaisheng.start();//播放声音
		}
    }
    
    public void initGameView(){//游戏界面初始化
    	this.setContentView(new Game(this,this)); //来到游戏界面
    }
    
    public void initMenuView(){//菜单界面初始化
    	if(kaisheng != null){
    		kaisheng.stop();//停止播放声音
    		kaisheng = null;
    	}
    	if(this.isSound){
    		yousheng.start();//播放声音
    	}
    	this.setContentView(new caidan(this,this));//切换View
    } 
    public void initHelpView(){//帮助界面初始化
    	this.setContentView(new Help(this,this));//来到帮助界面
    }
}