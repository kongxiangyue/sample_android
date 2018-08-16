package irdc.zhuanhuan;

/* import相关class */
import irdc.zhuanhuan.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class zhuanhuan083 extends Activity 
{
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    /* 载入main.xml Layout */
    setContentView(R.layout.main);
    
    /* 以findViewById()取得Button对象，并添加onClickListener */
    Button b1 = (Button) findViewById(R.id.button1);
    b1.setOnClickListener(new Button.OnClickListener()
    {
      public void onClick(View v)
      {
        jumpToLayout2();
      }
    });
  }
  
  /* method jumpToLayout2：将layout由main.xml切换成layout2.xml */
  public void jumpToLayout2()
  {
    /* 将layout改成mylayout.xml */
    setContentView(R.layout.layout2);
    
    /* 以findViewById()取得Button对象，并添加onClickListener */
    Button b2 = (Button) findViewById(R.id.button2);
    b2.setOnClickListener(new Button.OnClickListener()
    {
      public void onClick(View v)
      {
        jumpToLayout1();
      }
    });
  }
  
  /* method jumpToLayout1：将layout由mylayout.xml切换成main.xml */
  public void jumpToLayout1()
  {
    /* 将layout改成main.xml */
    setContentView(R.layout.main);
    
    /* 以findViewById()取得Button对象，并添加onClickListener */
    Button b1 = (Button) findViewById(R.id.button1);
    b1.setOnClickListener(new Button.OnClickListener()
    {
      public void onClick(View v)
    {
        jumpToLayout2();
      }
    });
  }
}
