package irdc.wenyan;

import irdc.wenyan.R;
import android.app.Activity;
/*必须引用graphics.Color才能使用Color.*的对象*/
import android.graphics.Color;

import android.os.Bundle;
import android.view.View;

/*必须引用 widget.Button才能声明使用Button对象*/
import android.widget.Button;

/*必须引用 widget.TextView才能声明使用TestView对象*/
import android.widget.TextView;
public class wenyan extends Activity 
{
  private Button mButton;
  private TextView mText;
  private int[] mColors;
  private int colornum;

  /** Called when the activity is first created. */
  @Override

  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    /*通过findViewById构造器来使用main.xml与string.xml
    中button与textView的参数*/
    mButton=(Button) findViewById(R.id.mybutton);
    mText= (TextView) findViewById(R.id.mytext);

    /*声明并构造一整数array来存储欲使用的文字颜色*/
    mColors = new int[] 
                      { 
    Color.BLACK, Color.RED, Color.BLUE,
    Color.GREEN, Color.MAGENTA, Color.YELLOW
                       };
    colornum=0;
    
    /*使用setOnClickListener让按钮聆听事件*/
    mButton.setOnClickListener(new View.OnClickListener() 
    {             
      /*使用onClick让用户点下按钮来驱动变动文字颜色*/
      public void onClick(View v)
      {        
        if (colornum < mColors.length)
        {
          mText.setTextColor(mColors[colornum]);
          colornum++;
        }
        else
          colornum=0;
       }  
    });
  }
} 
