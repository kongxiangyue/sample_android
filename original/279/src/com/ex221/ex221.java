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
  /*��������*/
  private TextView mTextView01; 
  private ImageView mImageView01;
  private SensorManager mSensorManager01;  
  private float velocityFinal = 0; 
  /*����ý�岥����*/
  private MediaPlayer mMediaPlayer01; 
  private MediaPlayer mMediaPlayer02;
  private MediaPlayer mMediaPlayer03;
  private Date lastUpdateTime; 
  private boolean bIfMakeSound = true; 
  private int godanswer = 0;
  /*��������*/
  private final int saint=1;
  private final int laugh=2;
  private final int bad=3;
   
  /** Called when the activity is first created. */ 
  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
    super.onCreate(savedInstanceState); 
    setContentView(R.layout.main); 
    /*ȡ��Ŀǰϵͳʱ��*/ 
    lastUpdateTime = new Date(System.currentTimeMillis()); 
    /*ͨ��findViewById����������TextView��ImageView����*/
    mTextView01 = (TextView)findViewById(R.id.myTextView1);
    mImageView01 = (ImageView)findViewById(R.id.myImageView1);
    /*ȡ��ϵͳ�ļ��ٶȸв�װ��*/ 
    mSensorManager01 =  
    (SensorManager)getSystemService(Context.SENSOR_SERVICE); 
    /*ͨ��MediaPlayer.create()��ȡ��raw���������ļ�*/
    mMediaPlayer01 = new MediaPlayer(); 
    mMediaPlayer01 = MediaPlayer.create(ex221.this, R.raw.saint); 
    mMediaPlayer02 = new MediaPlayer(); 
    mMediaPlayer02 = MediaPlayer.create(ex221.this, R.raw.laugh); 
    mMediaPlayer03 = new MediaPlayer(); 
    mMediaPlayer03 = MediaPlayer.create(ex221.this, R.raw.bad); 
  } 

  /*����onResuem����*/
  @Override 
  protected void onResume() 
  { 
    // TODO Auto-generated method stub 
    /*ע��SensorListener�����ò���*/
    mSensorManager01.registerListener 
    ( 
      mSensorLisener, 
      SensorManager.SENSOR_ACCELEROMETER, 
      SensorManager.SENSOR_DELAY_UI 
    ); 
    super.onResume(); 
  } 
   /*����onPause����*/
  @Override 
  protected void onPause() 
  { 
    /*���SensorListener������*/
    // TODO Auto-generated method stub 
    mSensorManager01.unregisterListener(mSensorLisener); 
    super.onPause(); 
  }   
  
   /*����SensorListener����*/
  private final SensorListener mSensorLisener = new SensorListener()
  { 
    /*��������*/
    private float previousAcceleration; 
    /*����onSensorChanged�¼�������ֻ�Ͷ���¼�*/
    @Override 
    public void onSensorChanged(int sensor, float[] values) 
    { 
      // TODO Auto-generated method stub 
      /*���ٶ��Ƿ����*/
      if(sensor == SensorManager.SENSOR_ACCELEROMETER) 
      { 
        float x = values[SensorManager.DATA_X]; 
        float y = values[SensorManager.DATA_Y]; 
        float z = values[SensorManager.DATA_Z]; 
         /*�ֻ��Ƿ����µ��ٶȱ�׼*/
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
          
        /*�ж��ֻ�ʹ�����µ��ж�ʽ
         * ��ص�ʱ�ٶ���С��1.5f, ���ǰ�ٶȱ������1.5f*/
        if((appliedAcceleration < forceThreshHold) &&  
           (previousAcceleration > forceThreshHold)) 
        { 
          /* Shake�ֻ��¼����� */ 
          Date timeNow = new Date(System.currentTimeMillis()); 
           
          /* �ӳ��ٶȵ�ĩ�ٶ���������ʱ�� */ 
          long timePeriod =  
          (long)timeNow.getTime() - (long)lastUpdateTime.getTime(); 
           
          /* (v) = a*t */ 
          velocityFinal =  
          (float) (appliedAcceleration * ((float)timePeriod/1000)); 
          Log.i("V=",Float.toString(velocityFinal));

          if(bIfMakeSound==true) 
          { 
            /*Random Number������*/
            Random generator=new Random();
            /*�ϱ�*/
            godanswer = generator.nextInt(3) + 1;
            
            switch (godanswer)
            {
             /*ʥ��*/
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
            /*Ц��*/
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
              /*����*/
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
          /* ĩ�ٶ�=0 */ 
          /* ����lastUpdateTimeΪ���� */ 
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