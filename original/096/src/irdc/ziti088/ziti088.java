package irdc.ziti088;

import irdc.ziti088.R;
import android.app.Activity;
/*必须引用graphics.Typeface才能使用creatFromAsset()来改变字体*/
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ziti088 extends Activity 
{
  /** Called when the activity is first created. */
  private TextView mText;
  private Button sizeButton;
  private Button fontButton;
  @Override
 
  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    mText=(TextView)findViewById(R.id.mytextview);
    sizeButton=(Button) findViewById(R.id.sizebutton);
    fontButton=(Button) findViewById(R.id.fontbutton);
    /*设置onClickListener与按钮对象连接*/
    sizeButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        /*使用setTextSize()来改变字体大小 */
        mText.setTextSize(20);
      }       
    }
    );
    fontButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View v)
      {
        /*必须事先在assets底下创建一fonts文件夹
         * 并放入要使用的字体文件(.ttf)
         * 并提供相对路径给creatFromAsset()来创建Typeface对象*/
        mText.setTypeface
        (Typeface.createFromAsset(getAssets(),
        "fonts/HandmadeTypewriter.ttf"));
      }
    }
    );
  }
}
