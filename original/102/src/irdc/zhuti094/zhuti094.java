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
     * 应用透明背景的主题
     * setTheme(R.style.Theme_Transparent);
    */    
    /*
     * 应用布景主题1
    */
      setTheme(R.style.Theme_Translucent);     
    /*
     * 应用布景主题2
    * setTheme(R.style.Theme_Translucent2);
    */
    setContentView(R.layout.main);
  }
}