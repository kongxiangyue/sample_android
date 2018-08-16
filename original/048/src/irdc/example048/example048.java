package irdc.example048;

import irdc.example048.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class example048 extends Activity
{
  /*���� TextView��EditText����*/
  private TextView mTextView01; 
  private EditText mEditText01;   
  
  /** Called when the activity is first created. */
    @Override
  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    /*ȡ��TextView��EditText*/   
    mTextView01 = (TextView)findViewById(R.id.myTextView);
    mEditText01 = (EditText)findViewById(R.id.myEditText);
    
    /*����EditText��OnKeyListener�¼�������*/
    mEditText01.setOnKeyListener(new EditText.OnKeyListener()
    {
      @Override
      public boolean onKey(View arg0, int arg1, KeyEvent arg2)
      {
        // TODO Auto-generated method stub
        /*����TextView��ʾEditText�����������*/
        mTextView01.setText(mEditText01.getText()); 
        return false;
      }
    });
}
}
