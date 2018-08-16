package irdc.fenbian;

import irdc.fenbian.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class fenbian080 extends Activity
{
  private TextView mTextView01;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    /* 必须引用 android.util.DisplayMetrics */
    DisplayMetrics dm = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(dm);
    
    String strOpt = "分辨率是：" + 
           dm.widthPixels + " × " + dm.heightPixels;
    
    mTextView01 = (TextView) findViewById(R.id.myTextView01);
    mTextView01.setText(strOpt);
  }
}
