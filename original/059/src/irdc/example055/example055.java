package irdc.example055;

import android.app.Activity;
import android.os.Bundle;
/*����������Ҫʹ��Handler����Message�������������߳�*/
import android.os.Handler;
import android.os.Message;
import android.widget.AnalogClock;
import android.widget.TextView;
/*��Ҫʹ��Java��Calendar��Thread����ȡ��ϵͳʱ��*/
import irdc.example055.R;

import java.util.Calendar;
import java.lang.Thread;

public class example055 extends Activity 
{
  /*����һ������Ϊ�б���Ϣ��*/
  protected static final int GUINOTIFIER = 0x1234;
  
  /*��������widget�������*/
  private TextView mTextView;
  public AnalogClock mAnalogClock;
  
  /*������ʱ����صı���*/
  public Calendar mCalendar;
  public int mMinutes;
  public int mHour;
  
  /*�����ؼ�Handler��Thread����*/
  public Handler mHandler;
  private Thread mClockThread;

  /** Called when the activity is first created. */
  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    /*ͨ��findViewByIdȡ������widget����*/
    mTextView=(TextView)findViewById(R.id.myTextView);
    mAnalogClock=(AnalogClock)findViewById(R.id.myAnalogClock);
    
    /*ͨ��Handler�����������߳������ݵ���Ϣ������TextView*/
    mHandler = new Handler()
    {
      public void handleMessage(Message msg) 
      {
        /*�����Ǵ�����Ϣ�ķ���*/
        switch (msg.what)
        { 
          case example055.GUINOTIFIER:
          /* ���⴦��ҪTextView����Showʱ����¼� */           
            mTextView.setText(mHour+" : "+mMinutes);
            break; 
        } 
        super.handleMessage(msg); 
      }
    };
    
    /*ͨ�������߳�������ȡ��ϵͳʱ��*/
     mClockThread=new LooperThread();
     mClockThread.start();
  }
    
  /*��дһ��Thread Class��������ȡ��ϵͳʱ��*/     
  class LooperThread extends Thread
  {
    public void run() 
    {
      super.run();
      try
      {
        do
        {
          /*ȡ��ϵͳʱ��*/
          long time = System.currentTimeMillis();
          /*ͨ��Calendar������ȡ��Сʱ�����*/ 
          final Calendar mCalendar = Calendar.getInstance();
          mCalendar.setTimeInMillis(time);
          mHour = mCalendar.get(Calendar.HOUR);
          mMinutes = mCalendar.get(Calendar.MINUTE);
          
          /*�������߳���Ϣһ��*/
          Thread.sleep(1000); 
           /*��Ҫ�ؼ�����:ȡ��ʱ��󷢳���Ϣ��Handler*/
           Message m = new Message();
           m.what = example055.GUINOTIFIER;
           example055.this.mHandler.sendMessage(m);
        }while(example055.LooperThread.interrupted()==false);
        /*��ϵͳ�����ж���Ϣʱֹͣ��ѭ��*/
      }
      catch(Exception e)
      {
        e.printStackTrace();
      }
    }
  }
}
