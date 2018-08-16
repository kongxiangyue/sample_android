package irdc.zhihuan;

import irdc.zhihuan.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class zhihuan079 extends Activity
{
  private TextView mTextView02;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {    
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    mTextView02 = (TextView) findViewById(R.id.myTextView02);
    CharSequence str_2 = getString(R.string.str_2);
    
    String str_3 = "这是调用的Resource";
    mTextView02.setText(str_3 + str_2);
  }
}