package irdc.example095;

import irdc.example095.R;
import android.app.Activity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class example095 extends Activity 
{
  private TextView mTextView01;
  private EditText mEditText01;
  
    /** Called when the activity is first created. */
    @Override
  public void onCreate(Bundle savedInstanceState) 
    {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    mTextView01 = (TextView)findViewById(R.id.myTextView1); 
    mEditText01 = (EditText)findViewById(R.id.myEditText1);
    
    mEditText01.setOnKeyListener(new EditText.OnKeyListener()
    {    
      @Override
      public boolean onKey(View arg0, int arg1, KeyEvent arg2)
      {
        // TODO Auto-generated method stub
        mTextView01.setText(mEditText01.getText());
        /*判断输入的是何种类型，并与系统连接*/
       Linkify.addLinks(mTextView01,Linkify.WEB_URLS|Linkify.
           EMAIL_ADDRESSES|Linkify.PHONE_NUMBERS);  
        return false;
      }
    });   
    }
}