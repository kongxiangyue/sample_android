package irdc.example057;

/* import相关class */
import irdc.example057.R;

import java.util.Calendar;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class example057 extends Activity 
{ 
  /*声明日期及时间变量*/
  private int mYear;
  private int mMonth;
  private int mDay;
  private int mHour;
  private int mMinute;
  /*声明对象变量*/
  TextView tv;
  TimePicker tp;
  DatePicker dp;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    /*取得目前日期与时间*/
    Calendar c=Calendar.getInstance();
    mYear=c.get(Calendar.YEAR);
    mMonth=c.get(Calendar.MONTH);
    mDay=c.get(Calendar.DAY_OF_MONTH);
    mHour=c.get(Calendar.HOUR_OF_DAY);
    mMinute=c.get(Calendar.MINUTE);
    
    super.onCreate(savedInstanceState);
    /* 载入main.xml Layout */
    setContentView(R.layout.main);    
    
    /*取得TextView对象，并调用updateDisplay()
      来设置显示的初始日期时间*/
    tv= (TextView) findViewById(R.id.showTime);
    updateDisplay();
    
    /*取得DatePicker对象，以init()
      设置初始值与onDateChangeListener() */
    dp=(DatePicker)findViewById(R.id.dPicker);
    dp.init(mYear,mMonth,mDay,new DatePicker.OnDateChangedListener()
    {
      @Override
      public void onDateChanged(DatePicker view,int year,
                          int monthOfYear,int dayOfMonth)
      {
        mYear=year;
        mMonth= monthOfYear;
        mDay=dayOfMonth;
        /*调用updateDisplay()来改变显示日期*/
        updateDisplay();
      }
    });
    
    /*取得TimePicker对象，并设置为24小时制显示*/
    tp=(TimePicker)findViewById(R.id.tPicker);
    tp.setIs24HourView(true);
    
    /*setOnTimeChangedListener，并覆盖onTimeChanged event*/
    tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener()
    {
      @Override
      public void onTimeChanged(TimePicker view,
                              int hourOfDay,
                              int minute)
      {
        mHour=hourOfDay;
        mMinute=minute;
        /*调用updateDisplay()来改变显示时间*/
        updateDisplay();
      }
    });
  }
    
  /*设置显示日期时间的方法*/
  private void updateDisplay()
  {
    tv.setText(
      new StringBuilder().append(mYear).append("/")
                         .append(format(mMonth + 1)).append("/")
                         .append(format(mDay)).append("　")
                         .append(format(mHour)).append(":")
                         .append(format(mMinute))
    );
  }
  
  /*日期时间显示两位数的方法*/
  private String format(int x)
  {
    String s=""+x;
    if(s.length()==1) s="0"+s;
    return s;
  }
}
