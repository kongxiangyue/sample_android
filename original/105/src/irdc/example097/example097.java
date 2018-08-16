package irdc.example097;
import irdc.example097.R;
import android.app.Activity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
public class example097 extends Activity
{
  private Button mButton01;  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    mButton01 = (Button)findViewById(R.id.myButton1);    
    /*设置Button用OnClickListener启动事件*/
    mButton01.setOnClickListener(new Button.OnClickListener()
    {     
      @Override
      public void onClick(View v)
      {
        // TODO Auto-generated method stub        
        /* 创建ImageView */
        ImageView mView01 = new ImageView(example097.this);
        TextView mTextView = new TextView(example097.this);        
        /* 创建LinearLayout对象 */
        LinearLayout lay = new LinearLayout(example097.this);
        
        /* 设置mTextView去抓取string值 */
        mTextView.setText(R.string.app_url);       
        /* 判断mTextView的内容为何，并与系统做连接 */
        Linkify.addLinks
        (
          mTextView,Linkify.WEB_URLS|
          Linkify.EMAIL_ADDRESSES|
          Linkify.PHONE_NUMBERS
        );        
        /*用Toast提示*/ 
        Toast toast = Toast.makeText
                      (
                        example097.this,
                        mTextView.getText(),
                        Toast.LENGTH_LONG
                      );       
        /* 自定义View对象 */
        View textView = toast.getView();        
        /* 以水平方式排列 */
        lay.setOrientation(LinearLayout.HORIZONTAL);       
        /* 在ImageView Widget里指定显示的图片 */
        mView01.setImageResource(R.drawable.icon);        
        /* 在Layout里添加刚创建的View */
        lay.addView(mView01);
        /* 在Toast里显示文字 */
        lay.addView(textView);
        
        /* 以Toast和setView方法传入Layout */
        toast.setView(lay);
        /* 显示Toast提示*/
        toast.show(); 
      }      
    }); 
  }
}
