package irdc.zhuti094;

import irdc.zhuti094.R;
import android.app.Activity;
import android.os.Bundle;

public class zhuti094 extends Activity {
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);   
    /*
     * Ӧ��͸������������
     * setTheme(R.style.Theme_Transparent);
    */    
    /*
     * Ӧ�ò�������1
    */
      setTheme(R.style.Theme_Translucent);     
    /*
     * Ӧ�ò�������2
    * setTheme(R.style.Theme_Translucent2);
    */
    setContentView(R.layout.main);
  }
}