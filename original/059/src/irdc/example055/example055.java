package irdc.example055;

import android.app.Activity;
import android.os.Bundle;
/*这里我们需要使用Handler类与Message类来处理运行线程*/
import android.os.Handler;
import android.os.Message;
import android.widget.AnalogClock;
import android.widget.TextView;
/*需要使用Java的Calendar与Thread类来取得系统时间*/
import irdc.example055.R;

import java.util.Calendar;
import java.lang.Thread;

public class example055 extends Activity 
{
  /*声明一常数作为判别信息用*/
  protected static final int GUINOTIFIER = 0x1234;
  
  /*声明两个widget对象变量*/
  private TextView mTextView;
  public AnalogClock mAnalogClock;
  
  /*声明与时间相关的变量*/
  public Calendar mCalendar;
  public int mMinutes;
  public int mHour;
  
  /*声明关键Handler与Thread变量*/
  public Handler mHandler;
  private Thread mClockThread;

  /** Called when the activity is first created. */
  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    /*通过findViewById取得两个widget对象*/
    mTextView=(TextView)findViewById(R.id.myTextView);
    mAnalogClock=(AnalogClock)findViewById(R.id.myAnalogClock);
    
    /*通过Handler来接收运行线程所传递的信息并更新TextView*/
    mHandler = new Handler()
    {
      public void handleMessage(Message msg) 
      {
        /*这里是处理信息的方法*/
        switch (msg.what)
        { 
          case example055.GUINOTIFIER:
          /* 在这处理要TextView对象Show时间的事件 */           
            mTextView.setText(mHour+" : "+mMinutes);
            break; 
        } 
        super.handleMessage(msg); 
      }
    };
    
    /*通过运行线程来持续取得系统时间*/
     mClockThread=new LooperThread();
     mClockThread.start();
  }
    
  /*改写一个Thread Class用来持续取得系统时间*/     
  class LooperThread extends Thread
  {
    public void run() 
    {
      super.run();
      try
      {
        do
        {
          /*取得系统时间*/
          long time = System.currentTimeMillis();
          /*通过Calendar对象来取得小时与分钟*/ 
          final Calendar mCalendar = Calendar.getInstance();
          mCalendar.setTimeInMillis(time);
          mHour = mCalendar.get(Calendar.HOUR);
          mMinutes = mCalendar.get(Calendar.MINUTE);
          
          /*让运行线程休息一秒*/
          Thread.sleep(1000); 
           /*重要关键程序:取得时间后发出信息给Handler*/
           Message m = new Message();
           m.what = example055.GUINOTIFIER;
           example055.this.mHandler.sendMessage(m);
        }while(example055.LooperThread.interrupted()==false);
        /*当系统发出中断信息时停止本循环*/
      }
      catch(Exception e)
      {
        e.printStackTrace();
      }
    }
  }
}
