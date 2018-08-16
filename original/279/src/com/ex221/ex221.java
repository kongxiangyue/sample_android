package com.ex221;


import java.util.Date; 
import java.util.Random;

import com.ex221.R;

import android.app.Activity; 
import android.content.Context; 
import android.hardware.SensorListener; 
import android.hardware.SensorManager; 
import android.media.MediaPlayer; 
import android.os.Bundle; 
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView; 

public class ex221 extends Activity 
{ 
  /*声明变量*/
  private TextView mTextView01; 
  private ImageView mImageView01;
  private SensorManager mSensorManager01;  
  private float velocityFinal = 0; 
  /*声明媒体播放器*/
  private MediaPlayer mMediaPlayer01; 
  private MediaPlayer mMediaPlayer02;
  private MediaPlayer mMediaPlayer03;
  private Date lastUpdateTime; 
  private boolean bIfMakeSound = true; 
  private int godanswer = 0;
  /*声明常数*/
  private final int saint=1;
  private final int laugh=2;
  private final int bad=3;
   
  /** Called when the activity is first created. */ 
  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
    super.onCreate(savedInstanceState); 
    setContentView(R.layout.main); 
    /*取得目前系统时间*/ 
    lastUpdateTime = new Date(System.currentTimeMillis()); 
    /*通过findViewById构造器创建TextView与ImageView对象*/
    mTextView01 = (TextView)findViewById(R.id.myTextView1);
    mImageView01 = (ImageView)findViewById(R.id.myImageView1);
    /*取得系统的加速度感测装备*/ 
    mSensorManager01 =  
    (SensorManager)getSystemService(Context.SENSOR_SERVICE); 
    /*通过MediaPlayer.create()来取得raw底下音乐文件*/
    mMediaPlayer01 = new MediaPlayer(); 
    mMediaPlayer01 = MediaPlayer.create(ex221.this, R.raw.saint); 
    mMediaPlayer02 = new MediaPlayer(); 
    mMediaPlayer02 = MediaPlayer.create(ex221.this, R.raw.laugh); 
    mMediaPlayer03 = new MediaPlayer(); 
    mMediaPlayer03 = MediaPlayer.create(ex221.this, R.raw.bad); 
  } 

  /*覆盖onResuem方法*/
  @Override 
  protected void onResume() 
  { 
    // TODO Auto-generated method stub 
    /*注册SensorListener与设置参数*/
    mSensorManager01.registerListener 
    ( 
      mSensorLisener, 
      SensorManager.SENSOR_ACCELEROMETER, 
      SensorManager.SENSOR_DELAY_UI 
    ); 
    super.onResume(); 
  } 
   /*覆盖onPause方法*/
  @Override 
  protected void onPause() 
  { 
    /*解除SensorListener的设置*/
    // TODO Auto-generated method stub 
    mSensorManager01.unregisterListener(mSensorLisener); 
    super.onPause(); 
  }   
  
   /*创建SensorListener对象*/
  private final SensorListener mSensorLisener = new SensorListener()
  { 
    /*声明变量*/
    private float previousAcceleration; 
    /*覆盖onSensorChanged事件来侦测手机投掷事件*/
    @Override 
    public void onSensorChanged(int sensor, float[] values) 
    { 
      // TODO Auto-generated method stub 
      /*加速度是否存在*/
      if(sensor == SensorManager.SENSOR_ACCELEROMETER) 
      { 
        float x = values[SensorManager.DATA_X]; 
        float y = values[SensorManager.DATA_Y]; 
        float z = values[SensorManager.DATA_Z]; 
         /*手机是否落下的速度标准*/
        double forceThreshHold = 1.5f;        
        double appliedAcceleration = 0.0f; 
         
        /* SensorManager.GRAVITY_EARTH = 9.8m/s2 */ 
        appliedAcceleration +=  
        Math.pow(x/SensorManager.GRAVITY_EARTH, 2.0);  
         
        appliedAcceleration +=  
        Math.pow(y/SensorManager.GRAVITY_EARTH, 2.0);  
         
        appliedAcceleration +=  
        Math.pow(z/SensorManager.GRAVITY_EARTH, 2.0);  
         
        appliedAcceleration = Math.sqrt(appliedAcceleration);  
          
        /*判断手机使否落下的判断式
         * 落地当时速度需小于1.5f, 落地前速度必须大于1.5f*/
        if((appliedAcceleration < forceThreshHold) &&  
           (previousAcceleration > forceThreshHold)) 
        { 
          /* Shake手机事件触发 */ 
          Date timeNow = new Date(System.currentTimeMillis()); 
           
          /* 从初速度到末速度所经过的时间 */ 
          long timePeriod =  
          (long)timeNow.getTime() - (long)lastUpdateTime.getTime(); 
           
          /* (v) = a*t */ 
          velocityFinal =  
          (float) (appliedAcceleration * ((float)timePeriod/1000)); 
          Log.i("V=",Float.toString(velocityFinal));

          if(bIfMakeSound==true) 
          { 
            /*Random Number生成器*/
            Random generator=new Random();
            /*跋杯*/
            godanswer = generator.nextInt(3) + 1;
            
            switch (godanswer)
            {
             /*圣杯*/
            case saint:
              mTextView01.setText 
              ( 
                getResources().getText(R.string.str_saint).toString() 
              ); 
              mImageView01.setImageDrawable(getResources().
              getDrawable(R.drawable.saint));
              if (mMediaPlayer01 != null) 
              { 
                try 
                { 
                  mMediaPlayer01.seekTo(0); 
                  mMediaPlayer01.stop(); 
                  mMediaPlayer01.prepare(); 
                  mMediaPlayer01.setVolume(10, 10);
                  mMediaPlayer01.start(); 
                } 
                catch(Exception e) 
                { 
                  mTextView01.setText(e.toString()); 
                  e.printStackTrace(); 
                } 
              } 
              else 
              { 
                try 
                { 
                  mMediaPlayer01.prepare(); 
                  mMediaPlayer01.setVolume(10, 10);
                  mMediaPlayer01.start(); 
                } 
                catch(Exception e) 
                { 
                  mTextView01.setText(e.toString()); 
                  e.printStackTrace(); 
                } 
              }
              break;
            /*笑杯*/
            case laugh:
              mTextView01.setText 
              ( 
                getResources().getText(R.string.str_laugh).toString() 
              ); 
              mImageView01.setImageDrawable(getResources().
              getDrawable(R.drawable.laugh));
              if (mMediaPlayer02 != null) 
              { 
                try 
                { 
                  mMediaPlayer02.seekTo(0); 
                  mMediaPlayer02.stop(); 
                  mMediaPlayer02.prepare(); 
                  mMediaPlayer02.setVolume(10, 10);
                  mMediaPlayer02.start(); 
                } 
                catch(Exception e) 
                { 
                  mTextView01.setText(e.toString()); 
                  e.printStackTrace(); 
                } 
              } 
              else 
              { 
                try 
                { 
                  mMediaPlayer02.prepare(); 
                  mMediaPlayer02.setVolume(10, 10);
                  mMediaPlayer02.start(); 
                } 
                catch(Exception e) 
                { 
                  mTextView01.setText(e.toString()); 
                  e.printStackTrace(); 
                } 
              }
              break;
              /*阴杯*/
            case bad:
              mTextView01.setText 
              ( 
                getResources().getText(R.string.str_bad).toString() 
              ); 
              mImageView01.setImageDrawable(getResources().
              getDrawable(R.drawable.bad));
              if (mMediaPlayer03 != null) 
              { 
                try 
                { 
                  mMediaPlayer03.seekTo(0); 
                  mMediaPlayer03.stop(); 
                  mMediaPlayer03.prepare(); 
                  mMediaPlayer03.setVolume(10, 10);
                  mMediaPlayer03.start(); 
                } 
                catch(Exception e) 
                { 
                  mTextView01.setText(e.toString()); 
                  e.printStackTrace(); 
                } 
              } 
              else 
              { 
                try 
                { 
                  mMediaPlayer03.prepare(); 
                  mMediaPlayer03.setVolume(10, 10);
                  mMediaPlayer03.start(); 
                } 
                catch(Exception e) 
                { 
                  mTextView01.setText(e.toString()); 
                  e.printStackTrace(); 
                } 
              }
              break;
            }
          }
        }
        else 
        { 
          /* 末速度=0 */ 
          /* 更新lastUpdateTime为现在 */ 
          Date timeNow = new Date(System.currentTimeMillis()); 
          lastUpdateTime.setTime(timeNow.getTime()); 
        } 
        previousAcceleration = (float) appliedAcceleration; 
      } 
    } 
     
    @Override 
    public void onAccuracyChanged(int sensor, int accuracy) 
    { 
      // TODO Auto-generated method stub    
    }     
  }; 
}
