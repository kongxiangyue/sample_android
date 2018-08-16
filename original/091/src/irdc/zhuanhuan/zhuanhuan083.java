package irdc.zhuanhuan;

/* import���class */
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
    /* ����main.xml Layout */
    setContentView(R.layout.main);
    
    /* ��findViewById()ȡ��Button���󣬲����onClickListener */
    Button b1 = (Button) findViewById(R.id.button1);
    b1.setOnClickListener(new Button.OnClickListener()
    {
      public void onClick(View v)
      {
        jumpToLayout2();
      }
    });
  }
  
  /* method jumpToLayout2����layout��main.xml�л���layout2.xml */
  public void jumpToLayout2()
  {
    /* ��layout�ĳ�mylayout.xml */
    setContentView(R.layout.layout2);
    
    /* ��findViewById()ȡ��Button���󣬲����onClickListener */
    Button b2 = (Button) findViewById(R.id.button2);
    b2.setOnClickListener(new Button.OnClickListener()
    {
      public void onClick(View v)
      {
        jumpToLayout1();
      }
    });
  }
  
  /* method jumpToLayout1����layout��mylayout.xml�л���main.xml */
  public void jumpToLayout1()
  {
    /* ��layout�ĳ�main.xml */
    setContentView(R.layout.main);
    
    /* ��findViewById()ȡ��Button���󣬲����onClickListener */
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
