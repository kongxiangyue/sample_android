package irdc.example167;

import irdc.example167.R;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class example167 extends Activity 
{
  private WebView mWebView1; 
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    mWebView1 = (WebView) findViewById(R.id.myWebView1);

    /*自行设置WebView要显示的网页内容*/
    mWebView1.
      loadData(
      "<html><body><p>aaaaaaa</p>" +
      "<div class='widget-content'> "+
      "<a href=http://www.sohu.com>" +
      "<img src=http://hiphotos.baidu.com/chaojihedan/pic/item/bbddf5efc260f133fdfa3cd8.jpg />" +
      "<a href=http://www.sohu.com>Link Blog</a>" +
      "</body></html>", "text/html", "utf-8");
    }
}
