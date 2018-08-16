package mm.example117;

import mm.example117.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.Toast;

public class example117 extends Activity
{
  private Button mButton01;
  private int intWidth, intHeight, intButtonX, intButtonY;
  
  /* 存储屏幕的分辨率 */
  private int intScreenX, intScreenY;
  
  /* 按钮位移的平移量 */
  private int intShift = 2;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    DisplayMetrics dm = new DisplayMetrics(); 
    getWindowManager().getDefaultDisplay().getMetrics(dm);
    
    /* 取得屏幕解析像素 */
    intScreenX = dm.widthPixels;
    intScreenY = dm.heightPixels;
    
    /* 定义按钮的宽x高 */
    intWidth = 80;
    intHeight = 40;
    
    mButton01 =(Button) findViewById(R.id.myButton1);
    
    /* 初始化按钮位置居中 */
    RestoreButton();
    
    /* 当点击按钮，还原初始位置 */
    mButton01.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        // TODO Auto-generated method stub
        RestoreButton();
      }
    });
  }
  
  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event)
  {
    // TODO Auto-generated method stub
    switch(keyCode)
    {
      /* 中间按键 */
      case KeyEvent.KEYCODE_DPAD_CENTER:
        /* keyCode=23 */
        RestoreButton();
        break;
      /* 上按键 */
      case KeyEvent.KEYCODE_DPAD_UP:
        /* keyCode=19 */
        MoveButtonUp();
        break;
      /* 下按键 */
      case KeyEvent.KEYCODE_DPAD_DOWN:
        /* keyCode=20 */
        MoveButtonDown();
        break;
      /* 左按键 */
      case KeyEvent.KEYCODE_DPAD_LEFT:
        /* keyCode=21 */
        MoveButtonLeft();
        break;
      /* 右按键 */
      case KeyEvent.KEYCODE_DPAD_RIGHT:
        /* keyCode=22 */
        MoveButtonRight();
        break;
    }
    return super.onKeyDown(keyCode, event);
  }
  
  /* 还原按钮位置的事件处理 */
  public void RestoreButton()
  {
    intButtonX = ((intScreenX-intWidth)/2);
    intButtonY = ((intScreenY-intHeight)/2);
    mMakeTextToast
    (
      "("+
      Integer.toString(intButtonX)+
      ","+
      Integer.toString(intButtonY)+")",true
    );
    
    /* 以setLayoutParams方法，重新安排Layout上的位置 */
    mButton01.setLayoutParams
    (
      new AbsoluteLayout.LayoutParams
      (intWidth,intHeight,intButtonX,intButtonY)
    );
  }
  
  /* 点击DPAD上按键时事件处理 */
  public void MoveButtonUp()
  {
    intButtonY = intButtonY-intShift;
    /* 预防按钮到达下边界时的处理 */
    if(intButtonY<0)
    {
      intButtonY = 0;
    }
    mButton01.setLayoutParams
    (
      new AbsoluteLayout.LayoutParams
      (intWidth,intHeight,intButtonX,intButtonY)
    );
  }
  
  /* 点击DPAD下按键时事件处理 */
  public void MoveButtonDown()
  {
    intButtonY = intButtonY+intShift;
    /* 预防按钮到达下边界时的处理 */
    if(intButtonY>(intScreenY-intHeight))
    {
      intButtonY = intScreenX-intHeight;
    }
    mButton01.setLayoutParams
    (
      new AbsoluteLayout.LayoutParams
      (intWidth,intHeight,intButtonX,intButtonY)
    );
  }
  
  /* 点击DPAD左按键时事件处理 */
  public void MoveButtonLeft()
  {
    intButtonX = intButtonX-intShift;
    /* 预防按钮到达左边界时的处理 */
    if(intButtonX<0)
    {
      intButtonX = 0;
    }
    mButton01.setLayoutParams
    (
      new AbsoluteLayout.LayoutParams
      (intWidth,intHeight,intButtonX,intButtonY)
    );
  }
  
  /* 点击DPAD右按键时事件处理 */
  public void MoveButtonRight()
  {
    intButtonX = intButtonX+intShift;
    /* 预防按钮到达右边界时的处理 */
    if(intButtonX>(intScreenX-intWidth))
    {
      intButtonX = intScreenX-intWidth;
    }
    mButton01.setLayoutParams
    (
      new AbsoluteLayout.LayoutParams
      (intWidth,intHeight,intButtonX,intButtonY)
    );
  }
  
  public void mMakeTextToast(String str, boolean isLong)
  {
    if(isLong==true)
    {
      Toast.makeText(example117.this, str, Toast.LENGTH_LONG).show();
    }
    else
    {
      Toast.makeText(example117.this, str, Toast.LENGTH_SHORT).show();
    }
  }
}
