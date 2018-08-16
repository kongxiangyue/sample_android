package wenzi076;

import irdc.wenzi076.R;
import android.app.Activity;
import android.os.Bundle;

/*必须引用widget.TextView才能在程序里声明TextView对象*/
import android.widget.TextView;

public class wenzi extends Activity
{
  /*必须引用widget.TextView才能在程序里声明TextView对象*/
  private TextView mTextView01;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    
    /* 载入main.xml Layout，此时myTextView01:text为str_1 */
    setContentView(R.layout.main);
    
    /* 使用findViewBtId函数，利用ID找到该TextView对象 */
    mTextView01 = (TextView) findViewById(R.id.myTextView01);
    
    String str_2 = "欢迎来购买本书...";
    mTextView01.setText(str_2);
  }
}
